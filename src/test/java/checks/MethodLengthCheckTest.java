package checks;

import checks.MethodLengthCheck;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Andrej Tomci
 */
public class MethodLengthCheckTest {
    @Test
    public void testVisitToken() {
        DetailAST mockedAst = new DetailAST();
        FileContents mockedFileContents = new FileContents("test", "test");

        MethodLengthCheck lengthCheck = new MethodLengthCheck();
        when(lengthCheck.getLengthOfBlock(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(1);

        assertTrue(lengthCheck.visitToken(mockedAst, mockedFileContents));


    }


}
