package checks;

import checks.complexitycheck.CycloComplexityCheck;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CycloComplexityTest {

    private final ASTBundle astBundle = new ASTBundle();

    @Test
    public void testOneCycleViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneCycleDoesNotViolateLimitTwo() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void variableDefDoesNotCount() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.variableAST);
        check.leaveToken(astBundle.variableAST);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void outsideMethodNotCount() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.leaveToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void testOneWhileViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.whileAST);
        check.leaveToken(astBundle.whileAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneIfViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneDoViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.doAST);
        check.leaveToken(astBundle.doAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneSwitchViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);
        check.setSwitchBlockAsSingleDecisionPoint(true);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.switchAST);
        check.leaveToken(astBundle.switchAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneCaseViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.caseAST);
        check.leaveToken(astBundle.caseAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneCatchViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.catchAST);
        check.leaveToken(astBundle.catchAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneQuestionViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.questionAST);
        check.leaveToken(astBundle.questionAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneLANDViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.landAST);
        check.leaveToken(astBundle.landAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneLORViolatesLimitOne() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.lorAST);
        check.leaveToken(astBundle.lorAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testConstructorChecked() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testInitializationChecked() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.initAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.initAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testStaticInitializationChecked() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.staticInitAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.staticInitAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testNestedMethods() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(1);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.visitToken(astBundle.methodAST);
        check.leaveToken(astBundle.methodAST);
        assertFalse(check.isViolationDetected());
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testComplexScenarioViolationEqual() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(4);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.forAST);
        check.visitToken(astBundle.doAST);
        check.leaveToken(astBundle.doAST);
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testComplexScenarioViolationMore() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(4);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.forAST);
        check.visitToken(astBundle.doAST);
        check.leaveToken(astBundle.doAST);
        check.visitToken(astBundle.whileAST);
        check.leaveToken(astBundle.whileAST);
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testComplexScenarioNoViolation() {

        CycloComplexityCheck check = new CycloComplexityCheck();

        check.setLimit(4);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.forAST);
        check.visitToken(astBundle.whileAST);
        check.leaveToken(astBundle.whileAST);
        check.leaveToken(astBundle.constructorAST);

        assertFalse(check.isViolationDetected());
    }
}
