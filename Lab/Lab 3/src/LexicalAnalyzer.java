import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
    private final SymbolTable ST;

    private final List<String> operators;
    private final List<String> separators;
    private final List<String> reservedWords;

    LexicalAnalyzer(List<String> operators, List<String> separators, List<String> reservedWords){
        this.operators = operators;
        this.separators = separators;
        this.reservedWords = reservedWords;
        ST = new SymbolTable();
    }

    private String prelucrateFile(String filePath) throws IOException {
        StringBuilder s = new StringBuilder();
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        List<Character> separators = Arrays.asList('{', '}', '(', ')', '<', '>', ',', ']', '[');
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            for(int i = 1; i < line.length(); i++){
                if(separators.contains(line.charAt(i))){
                    if(line.charAt(i - 1) != ' ') {
                        line = line.substring(0, i) + " " + line.substring(i);
                        i--;
                    }
                    if(i + 1 < line.length())
                        if(line.charAt(i + 1) != ' ')
                            line = line.substring(0, i+1) + " " + line.substring(i + 1);
                }
            }
            s.append(line).append("\n");
        }
        return String.valueOf(s);
    }

    public void run(String filePath) throws IOException {
        Scanner scanner = new Scanner(prelucrateFile(filePath));
        FileWriter PIFWriter = new FileWriter("PIF.out");
        FileWriter STWriter = new FileWriter("ST.out");
        Integer lineNumber = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            lineNumber++;
            String[] tokens = line.split("\s+");
            for(String currentToken: tokens) {
                currentToken = currentToken.strip();
                if(currentToken == "")
                    continue;
                if (operators.contains(currentToken) || separators.contains(currentToken) || reservedWords.contains(currentToken)) {
                    PIFWriter.append(currentToken).append(" -1").append("\n");
                } else if (currentToken.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                    Integer index = ST.addKeyValue(currentToken, null);
                    PIFWriter.append("id ").append(String.valueOf(index)).append("\n");
                } else if (currentToken.matches("[+-]?[0-9]+")                     //integer
                        || currentToken.matches("[0-9]+\\.[0-9]*")         //float
                        || currentToken.matches("\"[^\s\"]+\"")            //string
                        || currentToken.matches("'[^\\s']'")               //char
                        || currentToken.equals("true")
                        || currentToken.equals("false")) {

                    Integer index = ST.addKeyValue(currentToken, null);
                    PIFWriter.append("const ").append(String.valueOf(index)).append("\n");
                } else {
                    System.out.println("Token \"" + currentToken + "\" on line number " + lineNumber + " is a lexical error!\n");
                    return;
                }
            }

        }
        PIFWriter.close();
        STWriter.append(ST.toString());
        STWriter.close();
        System.out.println("No lexical errors!\n");
    }
}
