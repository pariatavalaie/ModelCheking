public class HashToPromelaVisitor extends HashBaseVisitor<String> {

    @Override
    public String visitProgram(HashParser.ProgramContext ctx) {
        StringBuilder out = new StringBuilder();
        if (ctx.children != null) {
            for (var child : ctx.children) {
                String result = visit(child);
                if (result != null) out.append(result);
            }
        }
        return out.toString();
    }

    @Override
    public String visitTopLevelDecl(HashParser.TopLevelDeclContext ctx) {
        if (ctx.varDecl() != null) return visit(ctx.varDecl()) ;
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
        return type + " " + name + init + ";\n";
    }

    @Override
    public String visitType(HashParser.TypeContext ctx) {
        if (ctx.Adad() != null) return "int";
        if (ctx.Boole() != null) return "bool";
        return "int";
    }
    @Override
    public String visitEquality(HashParser.EqualityContext ctx) {
        String result = visit(ctx.relational(0));

        for (int i = 1; i < ctx.relational().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            result += " " + op + " " + visit(ctx.relational(i));
        }

        return result;
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

        String baseExpr = visit(ctx.unary());

        int exponent = evalConstPower(ctx.power());

        if (exponent < 0) {
            throw new RuntimeException("Negative exponent is not supported in Promela");
        }

        if (exponent == 0) {
            return "1";
        }

        if (exponent == 1) {
            return baseExpr;
        }

        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < exponent; i++) {
            if (i > 0) {
                sb.append(" * ");
            }
            sb.append(baseExpr);
        }
        sb.append(")");

        return sb.toString();
    }

    private int evalConstPower(HashParser.PowerContext ctx) {
        int base = evalConstUnary(ctx.unary());

        if (ctx.power() == null) {
            return base;
        }

        int exponent = evalConstPower(ctx.power());

        if (exponent < 0) {
            throw new RuntimeException("Negative exponent is not supported in constant power expression");
        }

        return intPow(base, exponent);
    }

    private int evalConstUnary(HashParser.UnaryContext ctx) {
        String text = ctx.getText();

        if (text.matches("\\d+")) {
            return Integer.parseInt(text);
        }

        if (text.matches("-\\d+")) {
            return Integer.parseInt(text);
        }

        throw new RuntimeException("Power exponent must be a constant integer, but got: " + text);
    }

    private int intPow(int base, int exponent) {
        int result = 1;

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
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


    }
