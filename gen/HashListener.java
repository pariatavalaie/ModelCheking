// Generated from C:/Users/XMART/IdeaProjects/modelcheking/src/Hash.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HashParser}.
 */
public interface HashListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HashParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(HashParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(HashParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#basteDecl}.
	 * @param ctx the parse tree
	 */
	void enterBasteDecl(HashParser.BasteDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#basteDecl}.
	 * @param ctx the parse tree
	 */
	void exitBasteDecl(HashParser.BasteDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#biarDecl}.
	 * @param ctx the parse tree
	 */
	void enterBiarDecl(HashParser.BiarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#biarDecl}.
	 * @param ctx the parse tree
	 */
	void exitBiarDecl(HashParser.BiarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelDecl(HashParser.TopLevelDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#topLevelDecl}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelDecl(HashParser.TopLevelDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#klassDecl}.
	 * @param ctx the parse tree
	 */
	void enterKlassDecl(HashParser.KlassDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#klassDecl}.
	 * @param ctx the parse tree
	 */
	void exitKlassDecl(HashParser.KlassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#klassBody}.
	 * @param ctx the parse tree
	 */
	void enterKlassBody(HashParser.KlassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#klassBody}.
	 * @param ctx the parse tree
	 */
	void exitKlassBody(HashParser.KlassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(HashParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(HashParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#callConstructor}.
	 * @param ctx the parse tree
	 */
	void enterCallConstructor(HashParser.CallConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#callConstructor}.
	 * @param ctx the parse tree
	 */
	void exitCallConstructor(HashParser.CallConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(HashParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(HashParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void enterFieldAccess(HashParser.FieldAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void exitFieldAccess(HashParser.FieldAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(HashParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(HashParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#constructorAccess}.
	 * @param ctx the parse tree
	 */
	void enterConstructorAccess(HashParser.ConstructorAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#constructorAccess}.
	 * @param ctx the parse tree
	 */
	void exitConstructorAccess(HashParser.ConstructorAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#objectInstantiation}.
	 * @param ctx the parse tree
	 */
	void enterObjectInstantiation(HashParser.ObjectInstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#objectInstantiation}.
	 * @param ctx the parse tree
	 */
	void exitObjectInstantiation(HashParser.ObjectInstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(HashParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(HashParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(HashParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(HashParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(HashParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(HashParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#returnfunction}.
	 * @param ctx the parse tree
	 */
	void enterReturnfunction(HashParser.ReturnfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#returnfunction}.
	 * @param ctx the parse tree
	 */
	void exitReturnfunction(HashParser.ReturnfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#voidfunction}.
	 * @param ctx the parse tree
	 */
	void enterVoidfunction(HashParser.VoidfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#voidfunction}.
	 * @param ctx the parse tree
	 */
	void exitVoidfunction(HashParser.VoidfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#callFunction}.
	 * @param ctx the parse tree
	 */
	void enterCallFunction(HashParser.CallFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#callFunction}.
	 * @param ctx the parse tree
	 */
	void exitCallFunction(HashParser.CallFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(HashParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(HashParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(HashParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(HashParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HashParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HashParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(HashParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(HashParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(HashParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(HashParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void enterLoopStmt(HashParser.LoopStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void exitLoopStmt(HashParser.LoopStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(HashParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(HashParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(HashParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(HashParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(HashParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(HashParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(HashParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(HashParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(HashParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#switchStmt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(HashParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void enterCaseStmt(HashParser.CaseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#caseStmt}.
	 * @param ctx the parse tree
	 */
	void exitCaseStmt(HashParser.CaseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#defaultStmt}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStmt(HashParser.DefaultStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#defaultStmt}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStmt(HashParser.DefaultStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(HashParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(HashParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(HashParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(HashParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(HashParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#logicalOr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(HashParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(HashParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#logicalAnd}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(HashParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(HashParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(HashParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#relational}.
	 * @param ctx the parse tree
	 */
	void enterRelational(HashParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#relational}.
	 * @param ctx the parse tree
	 */
	void exitRelational(HashParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#additive}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(HashParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#additive}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(HashParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative(HashParser.MultiplicativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#multiplicative}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative(HashParser.MultiplicativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(HashParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(HashParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(HashParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(HashParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#postfix}.
	 * @param ctx the parse tree
	 */
	void enterPostfix(HashParser.PostfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#postfix}.
	 * @param ctx the parse tree
	 */
	void exitPostfix(HashParser.PostfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(HashParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(HashParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(HashParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(HashParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void enterInputStmt(HashParser.InputStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#inputStmt}.
	 * @param ctx the parse tree
	 */
	void exitInputStmt(HashParser.InputStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#thisStmt}.
	 * @param ctx the parse tree
	 */
	void enterThisStmt(HashParser.ThisStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#thisStmt}.
	 * @param ctx the parse tree
	 */
	void exitThisStmt(HashParser.ThisStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#exceptionType}.
	 * @param ctx the parse tree
	 */
	void enterExceptionType(HashParser.ExceptionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#exceptionType}.
	 * @param ctx the parse tree
	 */
	void exitExceptionType(HashParser.ExceptionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#exceptionHandeling}.
	 * @param ctx the parse tree
	 */
	void enterExceptionHandeling(HashParser.ExceptionHandelingContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#exceptionHandeling}.
	 * @param ctx the parse tree
	 */
	void exitExceptionHandeling(HashParser.ExceptionHandelingContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#catchexception}.
	 * @param ctx the parse tree
	 */
	void enterCatchexception(HashParser.CatchexceptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#catchexception}.
	 * @param ctx the parse tree
	 */
	void exitCatchexception(HashParser.CatchexceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#finallyexception}.
	 * @param ctx the parse tree
	 */
	void enterFinallyexception(HashParser.FinallyexceptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#finallyexception}.
	 * @param ctx the parse tree
	 */
	void exitFinallyexception(HashParser.FinallyexceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HashParser#throwexception}.
	 * @param ctx the parse tree
	 */
	void enterThrowexception(HashParser.ThrowexceptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HashParser#throwexception}.
	 * @param ctx the parse tree
	 */
	void exitThrowexception(HashParser.ThrowexceptionContext ctx);
}