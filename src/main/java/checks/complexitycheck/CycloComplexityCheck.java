package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

public class CycloComplexityCheck  implements SimpleCheckInterface{

    /** The initial current value. */
    private static final BigInteger INITIAL_VALUE = BigInteger.ONE;

    /** Default allowed complexity. */
    private static final int DEFAULT_COMPLEXITY_VALUE = 10;

    /** Control whether to treat the whole switch block as a single decision point. */
    private boolean switchBlockAsSingleDecisionPoint;

    /** The current value. */
    private BigInteger currentValue = INITIAL_VALUE;

    /** Specify the maximum threshold allowed. */
    private int max = DEFAULT_COMPLEXITY_VALUE;

    /** Stack of values - all but the current value. */
    private final Deque<BigInteger> valueStack = new ArrayDeque<>();

    private boolean violationDetected = false;


    /**
     * Setter to control whether to treat the whole switch block as a single decision point.
     *
     * @param switchBlockAsSingleDecisionPoint whether to treat the whole switch
     *                                          block as a single decision point.
     */
    public void setSwitchBlockAsSingleDecisionPoint(boolean switchBlockAsSingleDecisionPoint) {
        this.switchBlockAsSingleDecisionPoint = switchBlockAsSingleDecisionPoint;
    }

    /* get result for last left method */
    @Override
    public boolean isViolationDetected() {
        return violationDetected;
    }

    /**
     * Setter to specify the maximum threshold allowed.
     *
     * @param max the maximum threshold
     */
    public final void setMax(int max) {
        this.max = max;
    }

    @Override
    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
           // case TokenTypes.COMPACT_CTOR_DEF:
                visitMethodDef();
                break;

            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
            case TokenTypes.LITERAL_SWITCH:
            case TokenTypes.LITERAL_CASE:
            case TokenTypes.LITERAL_CATCH:
            case TokenTypes.QUESTION:
            case TokenTypes.LAND:
            case TokenTypes.LOR:
                visitTokenHook(ast);
                break;

            default:
                break;
        }
    }

    @Override
    public void leaveToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
            //case TokenTypes.COMPACT_CTOR_DEF:
                leaveMethodDef();
                break;
            default:
                break;
        }
    }

    /**
     * Hook called when visiting a token. Will not be called the method
     * definition tokens.
     *
     * @param ast the token being visited
     */
    private void visitTokenHook(DetailAST ast) {
        if (switchBlockAsSingleDecisionPoint) {
            if (ast.getType() != TokenTypes.LITERAL_CASE) {
                incrementCurrentValue(BigInteger.ONE);
            }
        }
        else if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
            incrementCurrentValue(BigInteger.ONE);
        }
    }

    /**
     * Process the end of a method definition.
     */
    private void leaveMethodDef() {
        final BigInteger bigIntegerMax = BigInteger.valueOf(max);
        if (currentValue.compareTo(bigIntegerMax) > 0) {
            violationDetected = true;
        }
        popValue();
    }

    /** Push the current value on the stack. */
    private void pushValue() {
        valueStack.push(currentValue);
        currentValue = INITIAL_VALUE;
    }

    /**
     * Pops a value off the stack and makes it the current value.
     */
    private void popValue() {
        currentValue = valueStack.pop();
    }

    /**
     * Increments the current value by a specified amount.
     *
     * @param amount the amount to increment by
     */
    private void incrementCurrentValue(BigInteger amount) {
        currentValue = currentValue.add(amount);
    }


    /** Process the start of the method definition. */
    private void visitMethodDef() {
        violationDetected = false;
        pushValue();
    }



}
