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

    private void visitMethodDef() {
        violationDetected = false;
        valueStack.push(variables);
        variables = new HashSet<>();
    }

    private void visitTokenHook(DetailAST ast) {
        if (ast.getParent() == null) {
            return;
        }
        if (ast.getParent().getType() == TokenTypes.VARIABLE_DEF || ast.getParent().getType() == TokenTypes.PARAMETER_DEF) {
            variables.add(ast.getText());
        }
    }

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
