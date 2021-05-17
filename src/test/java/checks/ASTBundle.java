package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class ASTBundle {


    public DetailAST methodAST = new DetailAST();
    public DetailAST initAST = new DetailAST();
    public DetailAST constructorAST = new DetailAST();
    public DetailAST staticInitAST = new DetailAST();
    public DetailAST forAST = new DetailAST();
    public DetailAST whileAST = new DetailAST();
    public DetailAST ifAST = new DetailAST();
    public DetailAST switchAST = new DetailAST();
    public DetailAST caseAST = new DetailAST();
    public DetailAST catchAST = new DetailAST();
    public DetailAST landAST = new DetailAST();
    public DetailAST lorAST = new DetailAST();
    public DetailAST questionAST = new DetailAST();
    public DetailAST doAST = new DetailAST();

    public DetailAST variableAST = new DetailAST();


    ASTBundle() {

        methodAST.setType(TokenTypes.METHOD_DEF);
        initAST.setType(TokenTypes.INSTANCE_INIT);
        constructorAST.setType(TokenTypes.CTOR_DEF);
        staticInitAST.setType(TokenTypes.STATIC_INIT);
        forAST.setType(TokenTypes.LITERAL_FOR);
        whileAST.setType(TokenTypes.LITERAL_WHILE);
        ifAST.setType(TokenTypes.LITERAL_IF);
        switchAST.setType(TokenTypes.LITERAL_SWITCH);
        caseAST.setType(TokenTypes.LITERAL_CASE);
        catchAST.setType(TokenTypes.LITERAL_CATCH);
        landAST.setType(TokenTypes.LAND);
        lorAST.setType(TokenTypes.LOR);
        questionAST.setType(TokenTypes.QUESTION);
        doAST.setType(TokenTypes.LITERAL_DO);

        variableAST.setType(TokenTypes.VARIABLE_DEF);
    }
}
