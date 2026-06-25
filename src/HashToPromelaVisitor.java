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
    public String visitExp(HashParser.ExpContext ctx) {
        return visitChildren(ctx);
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
