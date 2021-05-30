package checks;

import checks.variablenumcheck.NumberOfVariablesCheck;
import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class NumberOfVariablesTest {
    private ASTBundle astBundle = new ASTBundle();

    @Test
    public void testNonViolatingNumberOfVariables() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.VARIABLE_DEF);
        variable2.setType(TokenTypes.VARIABLE_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void testViolatingNumberOfVariables() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.VARIABLE_DEF);
        variable2.setType(TokenTypes.VARIABLE_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testNonViolatingNumberOfParameters() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.PARAMETER_DEF);
        variable2.setType(TokenTypes.PARAMETER_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void testViolatingNumberOfParameters() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.PARAMETER_DEF);
        variable2.setType(TokenTypes.PARAMETER_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testNonViolatingNumberOfMixedVarsParams() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.VARIABLE_DEF);
        variable2.setType(TokenTypes.PARAMETER_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void testViolatingNumberOfMixedVarsParams() {
        DetailAstImpl variable1 = new DetailAstImpl();
        DetailAstImpl variable2 = new DetailAstImpl();
        DetailAstImpl ident1 = new DetailAstImpl();
        DetailAstImpl ident2 = new DetailAstImpl();
        variable1.setType(TokenTypes.VARIABLE_DEF);
        variable2.setType(TokenTypes.PARAMETER_DEF);
        ident1.setType(TokenTypes.IDENT);
        ident2.setType(TokenTypes.IDENT);
        ident1.setText("a");
        ident2.setText("b");
        variable1.addChild(ident1);
        variable2.addChild(ident2);

        NumberOfVariablesCheck check = new NumberOfVariablesCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(ident1);
        check.visitToken(ident2);
        check.leaveToken(ident1);
        check.leaveToken(ident2);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }
}
