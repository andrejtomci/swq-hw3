package checks.complexitycheck;

import checks.contracts.CheckSetOptionVisitor;
import checks.methodlencheck.MethodLengthCheck;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;


/**
 * Visitor that sets the switch as one logical block option for
 * CycloComplexityCheck
 */
public class SetSwitchAsSingleLogicBlockVisitor implements CheckSetOptionVisitor {

    private boolean value = false;

    @Override
    public void visit(CycloComplexityCheck check) {
        check.setSwitchBlockAsSingleDecisionPoint(value);
    }

    @Override
    public void visit(NestedLogicCheck check) {

    }

    @Override
    public void visit(MethodLengthCheck check) {

    }

    @Override
    public void visit(NumberOfVariablesCheck check) {

    }

    @Override
    public void setOption(boolean value) {
        this.value = value;
    }
}
