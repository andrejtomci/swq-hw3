package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class NumberOfVariablesCheck {
    private static final int DEFAULT_MAX_VARIABLES = 10;
    private int max = DEFAULT_MAX_VARIABLES;
    private boolean violates;

    private HashSet<String> variables;
    private final Deque<HashSet<String>> valueStack = new ArrayDeque<>();

    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                visitMethodDef();
                break;
            case TokenTypes.IDENT:
                visitTokenHook(ast);
            default:
                break;
        }
    }

    public void leaveToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                leaveMethodDef();
                break;
            default:
                break;
        }
    }

    private void visitMethodDef() {
        violates = false;
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
        violates = variables.size() > max;
        variables = valueStack.pop();
    }

    public void setMax(int numberOfVariables) {
        max = numberOfVariables;
    }
}
