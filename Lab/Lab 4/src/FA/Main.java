package FA;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FiniteAutomataRead finiteAutomataRead = new FiniteAutomataRead();
        finiteAutomataRead.readFile("C:\\Users\\Suciu Daniel\\Desktop\\LFTC\\Lab\\Lab 4\\src\\FA\\FA.in");

        Scanner scanner = new Scanner(System.in);
        printMenu();
        while(true){

            String command = scanner.nextLine();
            switch (command) {
                case "e":
                    return;
                case "1":
                    finiteAutomataRead.getStates().forEach(s -> System.out.print(s.getId() + " "));
                    System.out.println();
                    break;
                case "2":
                    finiteAutomataRead.getAlphabet().forEach(a -> System.out.print(a + " "));
                    System.out.println();
                    break;
                case "3":
                    System.out.println(finiteAutomataRead.getInitialState());
                    break;
                case "4":
                    finiteAutomataRead.getFinalStates().forEach(s -> System.out.print(s.getId() + " "));
                    System.out.println();
                    break;
                case "5":
                    finiteAutomataRead.getStates().forEach(state -> {
                        state.getTransitions().forEach(transition -> System.out.println(state.getId() + "->" + transition.getNext().getId() + ":" + transition.getRule()));
                    });
                    break;
                case "6":
                    State current = finiteAutomataRead.getInitialState();
                    while(!current.isFinal()){
                        boolean ok = false;
                        for(String input: finiteAutomataRead.getAlphabet()){
                            try{
                                if(!current.equals(current.transit(input)))
                                {
                                    current = current.transit(input);
                                    ok = true;
                                }
                            }
                            catch (RuntimeException ignored){
                            }
                        }
                        if(!ok) break;
                    }
                    if(current.isFinal())
                        System.out.println("DFA is verified and finite!");
                    else
                        System.out.println("DFA is not finite");
                    break;
                default:
                    System.out.println("Nonexistent command!");
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("MENU");
        System.out.println("1 - print all states");
        System.out.println("2 - print alphabet");
        System.out.println("3 - print initial state");
        System.out.println("4 - print final states");
        System.out.println("5 - print transitions");
        System.out.println("6 - verify DFA");
        System.out.println("e - exit");
    }
}
