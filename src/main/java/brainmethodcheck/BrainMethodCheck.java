package brainmethodcheck;


import checks.CheckType;
import checks.complexitycheck.CycloComplexityCheck;
import checks.complexitycheck.SetSwitchAsSingleLogicBlockVisitor;
import checks.methodlencheck.MethodLengthCheck;
import checks.methodlencheck.SetCountEmptyLinesVisitor;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;
import checks.contracts.SimpleCheckInterface;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.HashMap;
import java.util.Map;


/**
 * A complex checker that uses "puppet" checkers to check for brain methods.
 */
public class BrainMethodCheck extends AbstractCheck {

    public static final String MSG_KEY = "Brain Method";

    private final Map<CheckType, SimpleCheckInterface> checkers = new HashMap<>();

    public BrainMethodCheck() {

        /* Just add all needed puppet checks */
        checkers.putIfAbsent(CheckType.CYCLOMATIC_COMPLEXITY, new CycloComplexityCheck());
        checkers.putIfAbsent(CheckType.NESTED_LOGIC, new NestedLogicCheck());
        checkers.putIfAbsent(CheckType.METHOD_LENGTH, new MethodLengthCheck(getFileContents()));
        checkers.putIfAbsent(CheckType.VARIABLE_QUANTITY, new NumberOfVariablesCheck());
    }

    /**
     * Set limits for all puppet checks.
     */
    public void setMaxComplexity(int max) {
        checkers.get(CheckType.CYCLOMATIC_COMPLEXITY).setMax(max);
    }

    public void setMaxNesting(int max) {
        checkers.get(CheckType.NESTED_LOGIC).setMax(max);
    }

    public void setMaxLines(int max) {
        checkers.get(CheckType.METHOD_LENGTH).setMax(max);
    }

    public void setMaxVariables(int max) {
        checkers.get(CheckType.VARIABLE_QUANTITY).setMax(max);
    }


    /**
     * Set this option for the cyclomatic complexity check.
     * @param switchBlockAsSingleDecisionPoint value
     */
    public void setSwitchBlockAsSingleDecisionPoint(boolean switchBlockAsSingleDecisionPoint) {

        SetSwitchAsSingleLogicBlockVisitor visitor = new SetSwitchAsSingleLogicBlockVisitor();
        visitor.setOption(switchBlockAsSingleDecisionPoint);
        checkers.get(CheckType.CYCLOMATIC_COMPLEXITY).acceptVisitor(visitor);
    }

    /**
     * Set this specific option to method length check.
     * @param countEmpty value
     */
    public void setCountEmpty(boolean countEmpty) {

        SetCountEmptyLinesVisitor visitor = new SetCountEmptyLinesVisitor();
        visitor.setOption(countEmpty);
        checkers.get(CheckType.METHOD_LENGTH).acceptVisitor(visitor);
    }


    @Override
    public int[] getDefaultTokens() {
        return new int[] {
                TokenTypes.CTOR_DEF,
                TokenTypes.METHOD_DEF,
                TokenTypes.INSTANCE_INIT,
                TokenTypes.STATIC_INIT,
                TokenTypes.LITERAL_WHILE,
                TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_FOR,
                TokenTypes.LITERAL_IF,
                TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE,
                TokenTypes.LITERAL_CATCH,
                TokenTypes.QUESTION,
                TokenTypes.LAND,
                TokenTypes.LOR,
                TokenTypes.IDENT
//                TokenTypes.COMPACT_CTOR_DEF,
        };
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {
                TokenTypes.CTOR_DEF,
                TokenTypes.METHOD_DEF,
                TokenTypes.INSTANCE_INIT,
                TokenTypes.STATIC_INIT,
                TokenTypes.LITERAL_WHILE,
                TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_FOR,
                TokenTypes.LITERAL_IF,
                TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE,
                TokenTypes.LITERAL_CATCH,
                TokenTypes.QUESTION,
                TokenTypes.LAND,
                TokenTypes.LOR,
                TokenTypes.IDENT
//                TokenTypes.COMPACT_CTOR_DEF,
        };
    }

    @Override
    public final int[] getRequiredTokens() {
        return new int[] {
                TokenTypes.CTOR_DEF,
                TokenTypes.METHOD_DEF,
                TokenTypes.INSTANCE_INIT,
                TokenTypes.STATIC_INIT,
//                TokenTypes.COMPACT_CTOR_DEF,
        };
    }

    /**
     * Call all puppet checkers to do their visit job.
     * @param ast token
     */
    @Override
    public void visitToken(DetailAST ast) {
        checkers.values().forEach(checker -> checker.visitToken(ast));
    }

    /**
     * Call all puppet checkers to do their leaving job.
     * If we left a method, check whether all puppet checkers report violation.
     * @param ast token
     */
    @Override
    public void leaveToken(DetailAST ast) {
        checkers.values().forEach(checker -> checker.leaveToken(ast));

        if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {

            evaluateSubChecks(ast);
        }
    }

    private void evaluateSubChecks(DetailAST ast) {
        /* call reporting and fold results with LAND*/
        if (checkers.values().stream().map(SimpleCheckInterface::isViolationDetected)
                .reduce(true, (accumulator, element) -> accumulator && element)) {

            log(ast, MSG_KEY);
        }
    }

}
