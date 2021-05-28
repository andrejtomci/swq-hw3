package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;



public class MethodLengthCheck implements SimpleCheckInterface{

    private static final int DEFAULT_MAX_LINES = 50;

    private boolean countEmpty = true;

    private int max = DEFAULT_MAX_LINES;

    private boolean violationDetected;

    private final FileContents fileContents;

    public MethodLengthCheck(FileContents fileContents) {
        this.fileContents = fileContents;
    }

    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
                violationDetected = false;
                final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
                if (openingBrace != null) {
                    final DetailAST closingBrace =
                            openingBrace.findFirstToken(TokenTypes.RCURLY);
                    final int length = getLengthOfBlock(openingBrace, closingBrace);
                    violationDetected = length > max;
                }
                break;
            default:
                break;
        }
    }

    public void leaveToken(DetailAST ast) {

    }


    /**
     * Returns length of code only without comments and blank lines.
     *
     * @param openingBrace block opening brace
     * @param closingBrace block closing brace
     * @return number of lines with code for current block
     */
    private int getLengthOfBlock(DetailAST openingBrace, DetailAST closingBrace) {
        int length = closingBrace.getLineNo() - openingBrace.getLineNo() + 1;

        if (!countEmpty) {
            final int lastLine = closingBrace.getLineNo();
            // lastLine - 1 is actual last line index. Last line is line with curly brace,
            // which is always not empty. So, we make it lastLine - 2 to cover last line that
            // actually may be empty.
            for (int i = openingBrace.getLineNo() - 1; i <= lastLine - 2; i++) {
                if (fileContents.lineIsBlank(i) || fileContents.lineIsComment(i)) {
                    length--;
                }
            }
        }
        return length;
    }

    /**
     * Setter to specify the maximum number of lines allowed.
     *
     * @param length the maximum length of a method.
     */
    public void setMax(int length) {
        max = length;
    }

    /**
     * Setter to control whether to count empty lines and single line comments
     * of the form {@code //}.
     *
     * @param countEmpty whether to count empty and single line comments
     *     of the form //.
     */
    public void setCountEmpty(boolean countEmpty) {
        this.countEmpty = countEmpty;
    }

    public boolean isViolationDetected() {
        return violationDetected;
    }

}