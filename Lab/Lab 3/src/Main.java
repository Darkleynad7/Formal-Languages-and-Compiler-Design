import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> operators = Arrays.asList("=", "==", "!=", "<", "<=", ">=", ">", "+", "-", "*", "/", "%", "^");
        List<String> separators = Arrays.asList("{", "}", "(", ")", " ", "<", ">", ",", "\"", "'", "[", "]");
        List<String> reservedWords = Arrays.asList("assign", "int", "float", "bool", "char", "string", "Array", "if", "elif", "else", "do", "iterate", "in", "and", "or", "consoleRead", "consoleWrite");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(operators, separators, reservedWords);
        lexicalAnalyzer.run("C:\\Users\\Suciu Daniel\\Desktop\\LFTC\\Lab\\Lab 3\\src\\p3.txt");
    }
}
