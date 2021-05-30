package checks.variablenumcheck;

import checks.contracts.CheckSetOptionVisitor;
import checks.contracts.CheckUpdateFileContentsVisitor;
import checks.contracts.SimpleCheckInterface;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Puppet checker class to search for methods with too many variables
 * Based on checkstyles cyclomatic complexity check.
 *
 * We use blacklist approach and filter out non attribute/variable/parameter IDENTs.
 * This covers most "standard" features of the language, however, there are still some constructs that break the check.
 * Other possible approach would be to whitelist the names, but that still isn't bulletproof.
 * Another possible way would be to only count VARIABLE_DEF and PARAMETER_DEF, but that doesn't handle attributes (as requested in specification).
 * We consider this to be a bit too much context dependent.
 */
public class NumberOfVariablesCheck  implements SimpleCheckInterface {

    private static final int DEFAULT_MAX_VARIABLES = 10;
    private int max = DEFAULT_MAX_VARIABLES;
    private boolean violationDetected;

    private Set<String> variables = new HashSet<>();
    private final Deque<Set<String>> valueStack = new ArrayDeque<>();

    @Override
    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
                visitMethodDef();
                break;
            case TokenTypes.IDENT:
                visitTokenHook(ast);
            default:
                break;
        }
    }

    @Override
    public void leaveToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
                leaveMethodDef();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isViolationDetected() {
        return violationDetected;
    }

    /**
     *
     * Process beginning of a method definition.
     */
    private void visitMethodDef() {
        violationDetected = false;
        valueStack.push(variables);
        variables = new HashSet<>();
    }

    /**
     * Hook called when visiting a token. Will not be called the method
     * definition tokens.
     *
     * @param ast the token being visited
     */
    private void visitTokenHook(DetailAST ast) {
        if (ast.getParent() == null) {
            return;
        }
        switch (ast.getParent().getType()) {
            case TokenTypes.METHOD_CALL:
            case TokenTypes.TYPE:
            case TokenTypes.TYPE_ARGUMENT:
            case TokenTypes.LITERAL_NEW:
            case TokenTypes.DOT:
                return;
            default:
                variables.add(ast.getText());
        }
    }

    /**
     * Process the end of a method definition.
     */
    private void leaveMethodDef() {
        violationDetected = variables.size() > max;
        variables = valueStack.pop();
    }

    @Override
    public void setLimit(int numberOfVariables) {
        max = numberOfVariables;
    }

    @Override
    public void acceptVisitor(CheckSetOptionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void acceptVisitor(CheckUpdateFileContentsVisitor visitor) {
        visitor.visit(this);
    }

}
