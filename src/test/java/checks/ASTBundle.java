package checks;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

class ASTBundle {


    public DetailAstImpl methodAST = new DetailAstImpl();
    public DetailAstImpl initAST = new DetailAstImpl();
    public DetailAstImpl constructorAST = new DetailAstImpl();
    public DetailAstImpl staticInitAST = new DetailAstImpl();
    public DetailAstImpl forAST = new DetailAstImpl();
    public DetailAstImpl whileAST = new DetailAstImpl();
    public DetailAstImpl ifAST = new DetailAstImpl();
    public DetailAstImpl switchAST = new DetailAstImpl();
    public DetailAstImpl caseAST = new DetailAstImpl();
    public DetailAstImpl catchAST = new DetailAstImpl();
    public DetailAstImpl landAST = new DetailAstImpl();
    public DetailAstImpl lorAST = new DetailAstImpl();
    public DetailAstImpl questionAST = new DetailAstImpl();
    public DetailAstImpl doAST = new DetailAstImpl();

    public DetailAstImpl variableAST = new DetailAstImpl();


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
