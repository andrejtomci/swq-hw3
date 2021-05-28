package checks.methodlencheck;

import checks.complexitycheck.CycloComplexityCheck;
import checks.contracts.CheckSetOptionVisitor;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;

public class SetCountEmptyLinesVisitor implements CheckSetOptionVisitor {

    private boolean value = false;

    @Override
    public void visit(CycloComplexityCheck check) {

    }

    @Override
    public void visit(NestedLogicCheck check) {

    }

    @Override
    public void visit(MethodLengthCheck check) {
        check.setCountEmpty(value);
    }

    @Override
    public void visit(NumberOfVariablesCheck check) {

    }

    @Override
    public void setOption(boolean value) {
        this.value = value;
    }
}
