package checks.nestedlogiccheck;

import checks.contracts.CheckSetOptionVisitor;
import checks.contracts.SimpleCheckInterface;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Puppet checker class to search for methods with too much nesting
 * Based on checkstyles cyclomatic complexity check.
 */
public class NestedLogicCheck  implements SimpleCheckInterface {


    private static final class Pair<T, U> {

        private final T first;
        private final U second;

        private Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        private T getFirst() {
            return first;
        }

        private U getSecond() {
            return second;
        }

    }

    /** The initial current depth */
    private static final BigInteger INITIAL_VALUE = BigInteger.ZERO;

    /** Default allowed nesting depth */
    private static final int DEFAULT_NESTING_DEPTH = 3;

    /** The current depth */
    private BigInteger currentDepth = INITIAL_VALUE;

    /** The maximal achieved nesting depth in the method **/
    private BigInteger maxDepthAchieved = INITIAL_VALUE;

    /** Specify the maximum threshold allowed. */
    private int max = DEFAULT_NESTING_DEPTH;

    /** Stack of values - all but the current value. */
    private final Deque<Pair<BigInteger, BigInteger>> valueStack = new ArrayDeque<>();

    private boolean violationDetected = false;

    /**
     * Setter to specify the maximum threshold allowed.
     *
     * @param max the maximum threshold
     */
    @Override
    public final void setMax(int max) {
        this.max = max;
    }

    @Override
    public void acceptVisitor(CheckSetOptionVisitor visitor) {
        visitor.visit(this);
    }

    /* get result for last left method */
    @Override
    public boolean isViolationDetected() {
        return violationDetected;
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
            case TokenTypes.LITERAL_CATCH:
            case TokenTypes.QUESTION:
                visitTokenHook();
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
            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
            case TokenTypes.LITERAL_SWITCH:
            case TokenTypes.LITERAL_CATCH:
            case TokenTypes.QUESTION:
                leaveTokenHook();
                break;

            default:
                break;
        }
    }

    /**
     * Hook called when visiting a token. Will not be called the method
     * definition tokens.
     */
    private void visitTokenHook() {
        incrementCurrentValue(BigInteger.ONE);
        if (currentDepth.compareTo(maxDepthAchieved) > 0) {
            maxDepthAchieved = currentDepth;
        }
    }

    /**
     * leaves control logic token,
     * if we somehow leave more than visit, we throw an error
     */
    private void leaveTokenHook() {
        if (currentDepth.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalStateException("More closed control tokens than opened.");
        }
        decrementCurrentValue(BigInteger.ONE);
    }

    /**
     * Process the end of a method definition.
     */
    private void leaveMethodDef() {
        final BigInteger bigIntegerMax = BigInteger.valueOf(max);
        if (maxDepthAchieved.compareTo(bigIntegerMax) > 0) {
            violationDetected = true;
        }
        popValue();
    }

    /** Push the current value on the stack. */
    private void pushValue() {
        valueStack.push(new Pair<>(currentDepth, maxDepthAchieved));
        currentDepth = INITIAL_VALUE;
        maxDepthAchieved = INITIAL_VALUE;
    }

    /**
     * Pops a value off the stack and makes it the current value.
     */
    private void popValue() {
        Pair<BigInteger, BigInteger> value = valueStack.pop();
        currentDepth = value.getFirst();
        maxDepthAchieved = value.getSecond();
    }

    /**
     * Increments the current value by a specified amount.
     *
     * @param amount the amount to increment by
     */
    private void incrementCurrentValue(BigInteger amount) {
        currentDepth = currentDepth.add(amount);
    }

    private void decrementCurrentValue(BigInteger amount) {
        currentDepth = currentDepth.subtract(amount);
    }


    /** Process the start of the method definition. */
    private void visitMethodDef() {
        violationDetected = false;
        pushValue();
    }

}
