package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Andrej Tomci
 */
public class MethodLengthCheckTest {
    @Test
    public void testVisitToken() {
        DetailAST mockedAst = new DetailAST();
        FileContents mockedFileContents = new FileContents("test", "test");

        //MethodLengthCheck lengthCheck = new MethodLengthCheck();
        //when(lengthCheck.getLengthOfBlock(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        //assertTrue(lengthCheck.visitToken(mockedAst, mockedFileContents));


    }


}
