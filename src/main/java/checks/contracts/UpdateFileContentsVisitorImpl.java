package checks.contracts;

import checks.complexitycheck.CycloComplexityCheck;
import checks.methodlencheck.MethodLengthCheck;
import checks.nestedlogiccheck.NestedLogicCheck;
import checks.variablenumcheck.NumberOfVariablesCheck;
import com.puppycrawl.tools.checkstyle.api.FileContents;

public class UpdateFileContentsVisitorImpl implements CheckUpdateFileContentsVisitor {

    private FileContents contents;


    @Override
    public void visit(CycloComplexityCheck check) {

    }

    @Override
    public void visit(NestedLogicCheck check) {

    }

    @Override
    public void visit(MethodLengthCheck check) {
        check.updateFileContents(contents);
    }

    @Override
    public void visit(NumberOfVariablesCheck check) {

    }

    @Override
    public void setFileContents(FileContents contents) {
        this.contents = contents;
    }
}
