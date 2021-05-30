package checks;

import checks.nestedlogiccheck.NestedLogicCheck;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class NestedLogicTest {

    private ASTBundle astBundle = new ASTBundle();

    @Test
    public void testOneCycleViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneCycleDoesNotViolateLimitTwo() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void variableDefDoesNotCount() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.variableAST);
        check.visitToken(astBundle.variableAST);
        check.leaveToken(astBundle.variableAST);
        check.leaveToken(astBundle.variableAST);

        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void outsideMethodNotCount() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.leaveToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void testOneWhileViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.whileAST);
        check.visitToken(astBundle.whileAST);
        check.leaveToken(astBundle.whileAST);
        check.leaveToken(astBundle.whileAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneIfViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.ifAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneDoViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.doAST);
        check.visitToken(astBundle.doAST);
        check.leaveToken(astBundle.doAST);
        check.leaveToken(astBundle.doAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneSwitchViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.switchAST);
        check.visitToken(astBundle.switchAST);
        check.leaveToken(astBundle.switchAST);
        check.leaveToken(astBundle.switchAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneCatchViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.catchAST);
        check.visitToken(astBundle.catchAST);
        check.leaveToken(astBundle.catchAST);
        check.leaveToken(astBundle.catchAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testOneQuestionViolatesLimitOne() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.questionAST);
        check.visitToken(astBundle.questionAST);
        check.leaveToken(astBundle.questionAST);
        check.leaveToken(astBundle.questionAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }


    @Test
    public void testConstructorChecked() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testInitializationChecked() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.initAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.initAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testStaticInitializationChecked() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.staticInitAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.staticInitAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void testNestedMethods() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(1);

        check.visitToken(astBundle.constructorAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.forAST);
        check.visitToken(astBundle.methodAST);
        check.leaveToken(astBundle.methodAST);
        assertFalse(check.isViolationDetected());
        check.leaveToken(astBundle.constructorAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void multipleSimpleNestingShouldNotViolateTwo() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.visitToken(astBundle.switchAST);
        check.leaveToken(astBundle.switchAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertFalse(check.isViolationDetected());
    }

    @Test
    public void multipleNestedSomeViolate() {

        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        check.visitToken(astBundle.forAST);
        check.visitToken(astBundle.ifAST);
        check.visitToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.leaveToken(astBundle.ifAST);
        check.visitToken(astBundle.switchAST);
        check.leaveToken(astBundle.switchAST);
        check.leaveToken(astBundle.forAST);
        check.leaveToken(astBundle.methodAST);

        assertTrue(check.isViolationDetected());
    }

    @Test
    public void invalidTreeWalkThrows() {
        NestedLogicCheck check = new NestedLogicCheck();

        check.setLimit(2);

        check.visitToken(astBundle.methodAST);
        assertThrows(IllegalStateException.class, () -> check.leaveToken(astBundle.forAST));

    }

}
