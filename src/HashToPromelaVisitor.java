
import java.util.Stack;

public class HashToPromelaVisitor extends HashBaseVisitor<String> {

    private int ErrorCounter = 0;
    private String currentError = null;
    private Stack<LoopLabel> loopStack = new Stack<>();
    private int loopCounter = 0;
    private int tempCounter = 0;
    private StringBuilder declarations = new StringBuilder();
    private StringBuilder mainProcess = new StringBuilder();
    StringBuilder currentStmtPrefix = new StringBuilder();
    private boolean powerUsed = false;

    @Override
    public String visitProgram(HashParser.ProgramContext ctx) {

        StringBuilder programBody = new StringBuilder();

        for (var d : ctx.topLevelDecl()) {
            String translated = visit(d);

            if (translated != null) {
                programBody.append(translated);
            }
        }

        StringBuilder result = new StringBuilder();

        if (powerUsed) {
            result.append(
                    "inline power(base, exp, result) {\n" +
                            "    int q;\n" +
                            "    result = 1;\n" +
                            "    q = 0;\n" +
                            "\n" +
                            "    do\n" +
                            "    :: (q < exp) ->\n" +
                            "        result = result * base;\n" +
                            "        q++\n" +
                            "    :: else -> break\n" +
                            "    od\n" +
                            "}\n\n"
            );
        }

        result.append("bool divByZero = false;\n");
        result.append("bool endReached = false;\n\n");
        result.append(programBody);
        result.append(
                "init {\n" +
                        "    run main();\n" +
                        "}\n"
        );

        return result.toString();
    }




    @Override
    public String visitTopLevelDecl(HashParser.TopLevelDeclContext ctx) {

        if (ctx.varDecl() != null) {
            return visit(ctx.varDecl()) + ";\n";
        }

        if (ctx.functionDecl() != null) {
            return visit(ctx.functionDecl());
        }

        if (ctx.klassDecl() != null) {
            return visit(ctx.klassDecl());
        }



        return "";
    }
    @Override
    public String visitFunctionDecl(HashParser.FunctionDeclContext ctx) {
        if(ctx.voidfunction() != null) {
            visit(ctx.voidfunction());
        }

        StringBuilder result = new StringBuilder();
        result.append("proctype main() {\n");

        result.append(declarations);
        result.append(mainProcess);
        result.append("endReached=true; \n");

        result.append("}\n\n");
        return result.toString();
    }


    @Override
    public String visitVoidfunction(HashParser. VoidfunctionContext ctx){

        for (int i=0;i<ctx.stmt().size();i++){
            mainProcess.append(visit(ctx.stmt(i)));
        }
        return "";
    }


    @Override
    public String visitVarDecl(HashParser.VarDeclContext ctx) {
        String type = visit(ctx.type());
        String name = ctx.ID().getText();
        String init = "";
        if (ctx.exp() != null) {
            String expValue = visit(ctx.exp());
            init = " = " + (expValue != null ? expValue : "0");
        }
        return type + " " + name + init;
    }

    @Override
    public String visitType(HashParser.TypeContext ctx) {
        if (ctx.Adad() != null) return "int";
        if (ctx.Boole() != null) return "bool";
        return "int";
    }


    @Override
    public String visitPrimary(HashParser.PrimaryContext ctx) {
        if (ctx.Adad_Literal() != null) return ctx.Adad_Literal().getText();
        if (ctx.Boole_Literal() != null) {
            String value = ctx.Boole_Literal().getText();
            if (value.equals("dorost")) {
                return "true";
            } else if (value.equals("ghalat")) {
                return "false";
            }


        }
        if (ctx.exp() != null) {
            return "(" + visit(ctx.exp()) + ")";
        }
        if (ctx.ID() != null) return ctx.ID().getText();
        return "";
    }
    @Override
    public String visitStmt(HashParser.StmtContext ctx) {
        currentStmtPrefix.setLength(0);

        String stmtBody = "";

        if (ctx.ifStmt() != null) {
            stmtBody = visit(ctx.ifStmt());
        } else if (ctx.loopStmt() != null) {
            stmtBody = visit(ctx.loopStmt());
        }  else if (ctx.breakStmt() != null) {
            stmtBody = visit((ctx.breakStmt()));
        } else if (ctx.continueStmt() != null) {
            stmtBody =visit(ctx.continueStmt());
        } else if (ctx.exceptionHandeling() != null) {
            stmtBody = visit(ctx.exceptionHandeling());
        } else if (ctx.throwexception() != null) {
            stmtBody = visit(ctx.throwexception()) ;
        } else if (ctx.varDecl() != null) {
            stmtBody = visit(ctx.varDecl()) + ";";
        } else if (ctx.exp() != null) {
            stmtBody = visit(ctx.exp()) + ";";
        }
        String finalResult = currentStmtPrefix.toString() + stmtBody + "\n";

        currentStmtPrefix.setLength(0);

        return finalResult;
    }


    @Override
    public String visitLogicalAnd(HashParser.LogicalAndContext ctx) {

        StringBuilder sb = new StringBuilder();

        sb.append(visit(ctx.equality(0)));

        for (int i = 1; i < ctx.equality().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ");
            sb.append(visit(ctx.equality(i)));
        }

        return sb.toString();
    }
    @Override
    public String visitLogicalOr(HashParser.LogicalOrContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.logicalAnd(0)));
        for (int i = 1; i < ctx.logicalAnd().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ");
            sb.append(visit(ctx.logicalAnd(i)));
        }

        return sb.toString();
    }
    @Override
    public String visitAdditive(HashParser.AdditiveContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.multiplicative(0)));
        for (int i = 1; i < ctx.multiplicative().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ");
            sb.append(visit(ctx.multiplicative(i)));
        }
        return sb.toString(); }
    @Override
    public String visitMultiplicative(HashParser.MultiplicativeContext ctx) {
        String current = visit(ctx.power(0));

        for (int i = 1; i < ctx.power().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String right = visit(ctx.power(i));

            if (op.equals("/")) {
                String tmp = "tmp_div_" + (tempCounter++);
                declarations.append("int ").append(tmp).append(";\n");

                currentStmtPrefix.append("if\n");
                currentStmtPrefix.append(":: (").append(right).append(" == 0) ->\n");
                currentStmtPrefix.append("    divByZero = true;\n");
                currentStmtPrefix.append(":: else ->\n");
                currentStmtPrefix.append("    ").append(tmp).append(" = ")
                        .append(current).append(" / ").append(right).append(";\n");
                currentStmtPrefix.append("fi;\n");

                current = tmp;
            } else if (op.equals("%")) {
                String tmp = "tmp_mod_" + (tempCounter++);
                declarations.append("int ").append(tmp).append(";\n");

                currentStmtPrefix.append("if\n");
                currentStmtPrefix.append(":: (").append(right).append(" == 0) ->\n");
                currentStmtPrefix.append("    divByZero = true;\n");
                currentStmtPrefix.append(":: else ->\n");
                currentStmtPrefix.append("    ").append(tmp).append(" = ")
                        .append(current).append(" % ").append(right).append(";\n");
                currentStmtPrefix.append("fi;\n");

                current = tmp;
            } else {
                current = current + " " + op + " " + right;
            }
        }

        return current;
    }

    @Override
    public String visitPower(HashParser.PowerContext ctx) {


        if (ctx.power() == null) {
            return visit(ctx.unary());
        }

        String left = visit(ctx.unary());
        String right = visit(ctx.power());

        String tmp = "tmp" + (tempCounter++);
        powerUsed=true;
        declarations.append("int ").append(tmp).append(";\n");

        mainProcess.append(
                "power(" +
                        left + ", " +
                        right + ", " +
                        tmp +
                        ");\n"
        );


        return tmp;
    }




    @Override
    public String visitAssign(HashParser.AssignContext ctx) {
        if (ctx.assign() == null) {
            return visit(ctx.logicalOr());
        }
        String left = visit(ctx.logicalOr());
        String right = visit(ctx.assign());
        String op = ctx.getChild(1).getText();
        switch (op) {
            case "=":
                return left + " = " + right;
            case "+=":
                return left + " = " + left + " + " + right;
            case "-=":
                return left + " = " + left + " - " + right;
            case "*=":
                return left + " = " + left + " * " + right;
            case "/=":
                return "if\n" +
                        ":: (" + right + " == 0) ->\n" +
                        "    divByZero = true;\n" +
                        ":: else ->\n" +
                        "    " + left + " = " + left + " / " + right + ";\n" +
                        "fi";
            default:
                return left + " = " + right;
        }

    }
    @Override
    public String visitEquality(HashParser.EqualityContext ctx) {
        if (ctx.relational().size() == 1) {
            return visit(ctx.relational(0));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.relational(0)));
        for (int i = 0; i < ctx.relational().size() - 1; i++) {
            String op = ctx.getChild(2 * i + 1).getText();
            if (op.equals("!=")) {
                sb.append(" != ");
            } else {
                sb.append(" == ");
            }
            sb.append(visit(ctx.relational(i + 1)));
        }
        return sb.toString();
    }
    @Override
    public String visitRelational(HashParser.RelationalContext ctx) {
        if (ctx.additive().size() == 1) {
            return visit(ctx.additive(0));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.additive(0)));
        for (int i = 0; i < ctx.additive().size() - 1; i++) {
            String op = ctx.getChild(2 * i + 1).getText();
            sb.append(" ").append(op).append(" ");
            sb.append(visit(ctx.additive(i + 1)));
        }
        return sb.toString();
    }
    @Override
    public String visitUnary(HashParser.UnaryContext ctx) {
        if (ctx.postfix() != null) {
            return visit(ctx.postfix());
        }
        String op = ctx.getChild(0).getText();
        String operand = visit(ctx.unary());
        switch (op) {
            case "!":
                return "!" + operand;
            case "++":
                return "++" + operand;
            case "--":
                return "--" + operand;
            default:
                return operand;
        }
    }
    @Override
    public String visitPostfix(HashParser.PostfixContext ctx) {
        String primaryVal = visit(ctx.primary());
        if (ctx.getChildCount() > 1) {
            String op = ctx.getChild(1).getText();
            if (op.equals("++")) {
                return primaryVal + "++";
            } else if (op.equals("--")) {
                return primaryVal + "--";
            }
        }
        return primaryVal;
    }
    @Override
    public String visitExp(HashParser.ExpContext ctx) {
        return visit(ctx.assign());
    }
    @Override
    public String visitExceptionHandeling(HashParser.ExceptionHandelingContext ctx) {
        currentError = "err_" + (++ErrorCounter);
        StringBuilder code = new StringBuilder();
        code.append("bool ").append(currentError).append(" = false;\n");
        for (HashParser.StmtContext stmtCtx : ctx.stmt()) {
            code.append(visit(stmtCtx));
        }
        if (!ctx.catchexception().isEmpty()) {
            HashParser.CatchexceptionContext catchCtx = ctx.catchexception(0);
            code.append("if\n");
            code.append(":: ").append(currentError).append(" -> {\n");
            for (HashParser.StmtContext stmtCtx : catchCtx.stmt()) {
                code.append(visit(stmtCtx));
            }
            code.append("}\n");
            code.append(":: else -> skip\n");
            code.append("fi;\n");
        }
        code.append(currentError).append(" = false;\n");
        return code.toString();
    }

    @Override
    public String visitThrowexception(HashParser.ThrowexceptionContext ctx){
        if (currentError == null) {
            return "";
        }
        return currentError + " = true;\n";
    }
    @Override
    public String visitBreakStmt(HashParser.BreakStmtContext ctx) {

        if (loopStack.isEmpty()) {
            throw new RuntimeException("break outside loop");
        }


        return  "break;\n";
    }

    @Override
    public String visitContinueStmt(HashParser.ContinueStmtContext ctx) {

        if (loopStack.isEmpty()) {
            throw new RuntimeException("continue outside loop");
        }

        return "goto " + loopStack.peek().start + ";\n";
    }

    @Override
    public String visitLoopStmt(HashParser.LoopStmtContext ctx) {

        if (ctx.whileStmt() != null) {
            return visit(ctx.whileStmt());
        }

        if (ctx.forStmt() != null) {
            return visit(ctx.forStmt());
        }

        return "";
    }
    @Override
    public String visitWhileStmt(HashParser.WhileStmtContext ctx) {
        String cond = visit(ctx.exp());
        int id = loopCounter++;
        String startLabel = "L" + id + "_start";
        loopStack.push(new LoopLabel(startLabel));
        StringBuilder body = new StringBuilder();
        for (var s : ctx.stmt()) {
            body.append(visit(s)).append("\n");
        }

        loopStack.pop();
        String inLoopLabel = "inLoop_" + id;
        String exitLoopLabel = "exitLoop_" + id;
        return startLabel + ":\n" +
                "do\n" +
                ":: (" + cond + ") -> \n" +
                inLoopLabel + " : skip " + ";\n" +
                body +
                "\n" +
                ":: else -> break  \n" +
                "od\n"  +
                exitLoopLabel + ": skip;\n";


    }
    @Override
    public String visitForStmt(HashParser.ForStmtContext ctx) {

        String init = "";
        String cond = "";
        String update = "";
        int id = loopCounter++;
        int expIndex = 0;
        String updateLabel = "L" + id + "_update";
        loopStack.push(new LoopLabel(updateLabel));
        if (ctx.varDecl() != null) {
            init = visit(ctx.varDecl());
        } else if (ctx.exp().size() > expIndex) {
            init = visit(ctx.exp(expIndex));
            expIndex++;
        }

        if (ctx.exp().size() > expIndex) {
            cond = visit(ctx.exp(expIndex));
            expIndex++;
        }

        if (cond.isEmpty()) {
            cond = "true";
        }

        StringBuilder body = new StringBuilder();

        for (var stmt : ctx.stmt()) {
            body.append(visit(stmt));
        }

        if (ctx.exp().size() > expIndex) {
            update = visit(ctx.exp(expIndex));
        }

        loopStack.pop();

        String inLoopLabel = "inLoop_" + id;
        String exitLoopLabel = "exitLoop_" + id;

        StringBuilder result = new StringBuilder();

        if (!init.isEmpty()) {
            result.append(init).append(";\n");
        }

        result.append("do\n");

        result.append(":: (").append(cond).append(") ->\n");
        result.append(inLoopLabel).append(": skip;\n");

        result.append(body);

        result.append(updateLabel).append(": skip;\n");

        if (!update.isEmpty()) {
            result.append("    ").append(update).append(";\n");
        }
        result.append(":: else -> break\n");
        result.append("od\n");

        result.append(exitLoopLabel).append(": skip;\n");

        return result.toString();
    }

    @Override
    public String visitIfStmt(HashParser.IfStmtContext ctx) {
        return buildIf(ctx, 0, 0);
    }

    private String buildIf(
            HashParser.IfStmtContext ctx,
            int expIndex,
            int stmtIndex
    ) {
        StringBuilder result = new StringBuilder();

        result.append("if\n");

        String cond = visit(ctx.exp(expIndex));

        result.append(":: (")
                .append(cond)
                .append(") ->\n");

        int nextStmt = stmtIndex;

        int branchEnd = Integer.MAX_VALUE;

        if (expIndex < ctx.Vagarna().size()) {
            branchEnd = ctx.Vagarna(expIndex)
                    .getSymbol()
                    .getStartIndex();
        }

        boolean hasThenStatement = false;

        while (nextStmt < ctx.stmt().size()) {

            int stmtStart =
                    ctx.stmt(nextStmt).start.getStartIndex();

            if (stmtStart >= branchEnd) {
                break;
            }

            result.append(visit(ctx.stmt(nextStmt)));
            nextStmt++;
            hasThenStatement = true;
        }


        if (!hasThenStatement) {
            result.append("skip;\n");
        }

        boolean hasNextCondition =
                expIndex + 1 < ctx.exp().size();


        boolean hasFinalElse =
                ctx.Vagarna().size() >= ctx.exp().size();

        if (hasNextCondition) {


            result.append(":: else ->\n");

            result.append(
                    buildIf(ctx, expIndex + 1, nextStmt)
            );

        } else if (hasFinalElse) {


            result.append(":: else ->\n");

            boolean hasElseStatement = false;

            while (nextStmt < ctx.stmt().size()) {
                result.append(visit(ctx.stmt(nextStmt)));
                nextStmt++;
                hasElseStatement = true;
            }

            if (!hasElseStatement) {
                result.append("skip;\n");
            }

        } else {

            result.append(":: else -> skip;\n");
        }

        result.append("fi;\n");

        return result.toString();
    }





    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}
class LoopLabel {
    String start;

    LoopLabel(String start) {
        this.start = start;
    }
}

