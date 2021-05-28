package checks.contracts;

import com.puppycrawl.tools.checkstyle.api.DetailAST;


/**
 * A simple contract for the checker classes that the complex checker will use.
 */
public interface SimpleCheckInterface {

    /**
     * A method to pass the token visit action.
     * It should take care of filtering out the token type
     * and react as wanted.
     * @param token  token from TreeWalker
     */
    void visitToken(DetailAST token);

    /**
     * Method to pass token leave even
     * @param token  token from TreeWalker
     */
    void leaveToken(DetailAST token);

    /**
     * Reports whether the latest left method
     * violated the check if no new method has been visited.
     * @return true if violation was detected
     */
    boolean isViolationDetected();

    /**
     * Set the limit for the specific checker
     * @param max the limit
     */
    void setMax(int max);

    void acceptVisitor(CheckSetOptionVisitor visitor);
}
