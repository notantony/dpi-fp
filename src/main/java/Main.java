import antlr_generated.RegexLexer;
import antlr_generated.RegexParser;
import org.antlr.v4.runtime.*;
import parsing.ParsingError;
import parsing.RegexVisitorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
//        char x = (char) ((byte) 'a');// Byte.parseByte("52", 16);
//        byte x = (byte) ((char) 255);// Byte.parseByte("52", 16);
//        char x = (char) Integer.parseInt("3a", 16);// (Integer.valueOf("3a"));
//        char x = Character.codePointOf("\n");
//        System.out.println(x);
//        Character.

        BufferedReader rulesReader = Files.newBufferedReader(Paths.get("./input/filtered.txt"));
        rulesReader.lines().forEach(Main::processRule);
    }

    private static void processRule(String rule) {
        ANTLRErrorListener parsingErrorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
                throw new ParsingError("Parsing error: " + msg, e);
            }
        };
        RegexLexer lexer = new RegexLexer(CharStreams.fromString(rule));
        lexer.addErrorListener(parsingErrorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RegexParser parser = new RegexParser(tokenStream);
        parser.addErrorListener(parsingErrorListener);
        parser.addErrorListener(new BaseErrorListener());
        try {
            RegexParser.StartContext start = parser.start();
            RegexVisitorImpl visitor = new RegexVisitorImpl();
            visitor.visit(start);
        } catch (ParsingError e) {
            e.printStackTrace();
            System.out.println(rule);
        }
    }
}
