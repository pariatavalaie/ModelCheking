// Generated from C:/Users/XMART/IdeaProjects/modelcheking/src/Hash.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HashParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HashVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HashParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(HashParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#basteDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasteDecl(HashParser.BasteDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#biarDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBiarDecl(HashParser.BiarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#topLevelDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelDecl(HashParser.TopLevelDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#klassDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKlassDecl(HashParser.KlassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#klassBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKlassBody(HashParser.KlassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(HashParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#callConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallConstructor(HashParser.CallConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(HashParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#fieldAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldAccess(HashParser.FieldAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(HashParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#constructorAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorAccess(HashParser.ConstructorAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#objectInstantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectInstantiation(HashParser.ObjectInstantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(HashParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(HashParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(HashParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#returnfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnfunction(HashParser.ReturnfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#voidfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidfunction(HashParser.VoidfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#callFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunction(HashParser.CallFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(HashParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(HashParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HashParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(HashParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(HashParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#loopStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStmt(HashParser.LoopStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(HashParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(HashParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(HashParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(HashParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#switchStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStmt(HashParser.SwitchStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#caseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStmt(HashParser.CaseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#defaultStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultStmt(HashParser.DefaultStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(HashParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(HashParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#logicalOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOr(HashParser.LogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#logicalAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAnd(HashParser.LogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(HashParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#relational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(HashParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#additive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(HashParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#multiplicative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative(HashParser.MultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(HashParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(HashParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix(HashParser.PostfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(HashParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(HashParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#inputStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputStmt(HashParser.InputStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#thisStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisStmt(HashParser.ThisStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#exceptionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptionType(HashParser.ExceptionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#exceptionHandeling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExceptionHandeling(HashParser.ExceptionHandelingContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#catchexception}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchexception(HashParser.CatchexceptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#finallyexception}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinallyexception(HashParser.FinallyexceptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HashParser#throwexception}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowexception(HashParser.ThrowexceptionContext ctx);
}