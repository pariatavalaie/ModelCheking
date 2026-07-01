import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // ۱. نام فایل ورودی (مثلاً input.hash)
        String inputFile = "C:\\Users\\XMART\\IdeaProjects\\modelcheking\\src\\input";
        String outputFile = "output.pml";


        try {
            // ۲. خواندن فایل و ایجاد جریان کاراکتر
            CharStream input = CharStreams.fromFileName(inputFile);

            // ۳. ایجاد لکسر (Lexer)
            HashLexer lexer = new HashLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // ۴. ایجاد پارسر (Parser)
            HashParser parser = new HashParser(tokens);

            // ۵. شروع پارس کردن از قاعده اصلی (program)
            ParseTree tree = parser.program();


            // ۶. اگر خطای سینتکسی وجود داشت، ادامه ندهیم
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("❌ خطای نحوی (Syntax Error) در کد ورودی پیدا شد.");
                return;
            }

            // ۷. فراخوانی Visitor برای ترجمه به Promela
            HashToPromelaVisitor translator = new HashToPromelaVisitor();
            String promelaCode = translator.visit(tree);

            // ۸. چاپ خروجی در کنسول (برای تست)
            System.out.println("✅ Translation Successful!");
            System.out.println("-------------------------");
            System.out.println(promelaCode);

            // ۹. ذخیره کد تولید شده در فایل .pml
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
