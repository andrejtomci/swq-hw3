package checks.contracts;

import checks.complexitycheck.CycloComplexityCheck;
import checks.methodlencheck.MethodLengthCheck;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;


/**
 * A visitor class for setting some options for specific checkers, that others do not have.
 * To not use too broad interfaces or not rely on calling "instanceof", the visitor patterns seem a better option.
 */
public interface CheckSetOptionVisitor {

    /**
     * Traditional visit methods.
     */
    void visit(CycloComplexityCheck check);

    void visit(NestedLogicCheck check);

    void visit(MethodLengthCheck check);

    void visit(NumberOfVariablesCheck check);

    /**
     * Sets the desired option that will be set.
     * @param value the boolean value
     */
    void setOption(boolean value);


}
