public class HashToPromelaVisitor extends HashBaseVisitor<String> {

    private int ErrorCounter = 0;
    private String currentError = null;

    @Override
    public String visitProgram(HashParser.ProgramContext ctx) {
        StringBuilder out = new StringBuilder();
        // اضافه کردن هدر برای اینکه خروجی خالی نباشد
        out.append("// --- Promela Output ---\n");

        if (ctx.children != null) {
            for (var child : ctx.children) {
                String result = visit(child);
                if (result != null) out.append(result);
            }
        }
        return out.toString();
    }

    // این خیلی مهم است: هدایت از topLevelDecl به سمت varDecl یا functionDecl
    @Override
    public String visitTopLevelDecl(HashParser.TopLevelDeclContext ctx) {
        if (ctx.varDecl() != null) return visit(ctx.varDecl()) + ";\n";
        if (ctx.functionDecl() != null) return visit(ctx.functionDecl());
        if (ctx.klassDecl() != null) return visit(ctx.klassDecl());
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

    // متدهای کمکی برای جلوگیری از نال در سطوح بالاتر
    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}
