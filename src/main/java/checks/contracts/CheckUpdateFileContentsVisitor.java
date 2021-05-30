package checks.contracts;


import checks.complexitycheck.CycloComplexityCheck;
import checks.methodlencheck.MethodLengthCheck;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;
import com.puppycrawl.tools.checkstyle.api.FileContents;

/**
 * A visitor class for setting the file contents for checkers, as not all checkers need this but to
 * not use too broad interfaces or not rely on calling "instanceof", the visitor patterns seems a better option.
 */
public interface CheckUpdateFileContentsVisitor {


    /**
     * Traditional visit methods.
     */
    void visit(CycloComplexityCheck check);

    void visit(NestedLogicCheck check);

    void visit(MethodLengthCheck check);

    void visit(NumberOfVariablesCheck check);

    /**
     * Sets the fileContents that will be passed to checkers.
     * @param contents the boolean value
     */
    void setFileContents(FileContents contents);
}
