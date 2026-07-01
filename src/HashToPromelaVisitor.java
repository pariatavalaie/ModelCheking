import java.util.HashMap;

import java.util.Stack;

public class HashToPromelaVisitor extends HashBaseVisitor<String> {

    private int ErrorCounter = 0;
    private String currentError = null;
    private Stack<LoopLabel> loopStack = new Stack<>();
    private int loopCounter = 0;
    boolean powerUsed = false;
    StringBuilder mainProcess = new StringBuilder();



    @Override
    public String visitProgram(HashParser.ProgramContext ctx) {

        StringBuilder result = new StringBuilder();

        for (var d : ctx.topLevelDecl()) {
            result.append(visit(d));
        }

        result.append("\nproctype main() {\n");
        result.append(mainProcess);
        result.append("}\n");
        if (powerUsed) {
            result.append(
                 "inline power(base, exp) {\n" +
                         "    int result = 1;\n" +
                         "    int i = 0;\n" +
                         "\n" +
                         "    do\n" +
                         "    :: (i < exp) ->\n" +
                         "        result = result * base;\n" +
                         "        i++\n" +
                         "    :: else ->\n" +
                         "        break\n" +
                         "    od\n" +
                         "}"
            );
        }



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

        if (ctx.stmt() != null) {
            mainProcess.append(visit(ctx.stmt())).append("\n");
            return "";
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
        if (ctx.ifStmt() != null) return visit(ctx.ifStmt());
        if (ctx.loopStmt() != null) return visit(ctx.loopStmt());
        if (ctx.printStmt() != null) return visit(ctx.printStmt());
        if (ctx.inputStmt() != null) return visit(ctx.inputStmt());
        if (ctx.switchStmt() != null) return visit(ctx.switchStmt());
        if (ctx.breakStmt() != null) return visit(ctx.breakStmt());
        if (ctx.continueStmt() != null) return visit(ctx.continueStmt());
        if (ctx.exceptionHandeling() != null) return visit(ctx.exceptionHandeling());
        if (ctx.throwexception() != null) return visit(ctx.throwexception());
        if (ctx.varDecl() != null) return visit(ctx.varDecl()) + ";\n";
        if (ctx.exp() != null) return visit(ctx.exp()) + ";\n";
        return "";
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
    public String visitMultiplicative(HashParser.MultiplicativeContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.power(0)));
        for (int i = 1; i < ctx.power().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ");
            sb.append(visit(ctx.power(i)));
        }


        return sb.toString();}
    @Override
    public String visitPower(HashParser.PowerContext ctx) {

        if (ctx.power() == null) {
            return visit(ctx.unary());
        }

        powerUsed = true;

        String base = visit(ctx.unary());
        String exp  = visit(ctx.power());

        return "power(" + base + ", " + exp + ")";
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
                return left + " = " + left + " / " + right;
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
        return startLabel + ":\n" +
                "do\n" +
                ":: (" + cond + ") -> \n" +
                body +
                "\n" +
                ":: else -> break  \n" +
                "od\n"  ;

    }
    @Override
    public String visitForStmt(HashParser.ForStmtContext ctx) {

        String init = "";
        int id = loopCounter++;
        int expIndex = 0;
        String startLabel = "L" + id + "_start";
        loopStack.push(new LoopLabel(startLabel));
        if (ctx.varDecl() != null) {
            init = visit(ctx.varDecl());
        } else if (ctx.exp(0) != null) {
            init = visit(ctx.exp(0)) + ";\n";
            expIndex++;
        }
        String cond="" ;
        if (ctx.exp(expIndex)!=null) {
            cond = visit(ctx.exp(expIndex));
            expIndex++;
        }
        String update="";

        StringBuilder body = new StringBuilder();
        for (var s : ctx.stmt()) {
            body.append(visit(s)).append("\n");
        }
        if (ctx.exp(expIndex)!=null) {
           update= visit(ctx.exp(expIndex));
        }
        loopStack.pop();
        return init +startLabel+":\n" +
                "do\n" +
                ":: (" + cond + ") -> \n" +
                body +
                update + ";\n" +
                "\n" +
                ":: else -> break\n" +
                "od\n";
    }
    @Override
    public String visitIfStmt(HashParser.IfStmtContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("if\n");
        int stmtCounter = 0;
        int expSize = ctx.exp().size();
        for (int i = 0; i < expSize; i++) {
            String condition = visit(ctx.exp(i));
            sb.append("  :: (").append(condition).append(") -> ");
            sb.append(getStatementsInBlock(ctx, i, stmtCounter));
            stmtCounter = updateStmtCounter(ctx, i, stmtCounter);
            sb.append("\n");
        }
        int totalVagarna = ctx.Vagarna().size();
        int elseIfCount = expSize - 1;

        if (totalVagarna > elseIfCount) {
            sb.append("  :: else -> ");
            for (int k = stmtCounter; k < ctx.stmt().size(); k++) {
                sb.append("\n      ").append(visit(ctx.stmt(k)));
            }
            sb.append("\n");
        }

        sb.append("fi");
        return sb.toString();
    }

    private String getStatementsInBlock(HashParser.IfStmtContext ctx, int expIndex, int startStmt) {
        StringBuilder block = new StringBuilder();
        int nextExpStart = (expIndex + 1 < ctx.exp().size()) ? ctx.exp(expIndex + 1).start.getStartIndex()
                : Integer.MAX_VALUE;
        for (int i = startStmt; i < ctx.stmt().size(); i++) {
            if (ctx.stmt(i).start.getStartIndex() < nextExpStart) {
                if (ctx.Vagarna().size() > (ctx.exp().size()-1)) {
                    int elseStart = ctx.Vagarna(ctx.Vagarna().size()-1).getSymbol().getStartIndex();
                    if (ctx.stmt(i).start.getStartIndex() > elseStart) break;
                }
                block.append("\n      ").append(visit(ctx.stmt(i)));
            } else {
                break;
            }
        }
        return block.toString();
    }

    private int updateStmtCounter(HashParser.IfStmtContext ctx, int expIndex, int currentCounter) {
        int nextExpStart = (expIndex + 1 < ctx.exp().size())
                ? ctx.exp(expIndex + 1).start.getStartIndex()
                : Integer.MAX_VALUE;

        int count = currentCounter;
        while (count < ctx.stmt().size() && ctx.stmt(count).start.getStartIndex() < nextExpStart) {
            if (ctx.Vagarna().size() > (ctx.exp().size()-1)) {
                int elseStart = ctx.Vagarna(ctx.Vagarna().size()-1).getSymbol().getStartIndex();
                if (ctx.stmt(count).start.getStartIndex() > elseStart) break;
            }
            count++;
        }
        return count;
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

