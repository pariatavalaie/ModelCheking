import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String inputFile = "C:\\Users\\XMART\\IdeaProjects\\modelcheking\\src\\input";
        String test="C:\\Users\\XMART\\IdeaProjects\\modelcheking\\src\\test\\test6.txt";
        String outputFile = "output.pml";

        try {

            CharStream input = CharStreams.fromFileName(test);

            HashLexer lexer = new HashLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            HashParser parser = new HashParser(tokens);

            ParseTree tree = parser.program();

            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("❌ خطای نحوی (Syntax Error) در کد ورودی پیدا شد.");
                return;
            }
            HashToPromelaVisitor translator = new HashToPromelaVisitor();
            String promelaCode = translator.visit(tree);
            System.out.println("✅ Translation Successful!");
            System.out.println("-------------------------");
            System.out.println(promelaCode);
            saveToFile(outputFile, promelaCode);
            System.out.println("-------------------------");
            System.out.println("💾 Output saved to: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
        }
    }

    private static void saveToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Could not save to file: " + e.getMessage());
        }
    }
}
