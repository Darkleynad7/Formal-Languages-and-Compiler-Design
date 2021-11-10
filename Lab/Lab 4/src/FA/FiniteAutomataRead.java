package FA;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FiniteAutomataRead {
    private final List<State> states;
    private final List<String> alphabet;
    private State initialState;
    private final List<State> finalStates;

    public FiniteAutomataRead() {
        states = new ArrayList<>();
        alphabet = new ArrayList<>();
        finalStates = new ArrayList<>();
    }

    public void readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
        String states = bufferedReader.readLine();
        String[] parts = states.split(" ");
        for(String state: parts){
            this.states.add(new State(state, false));
        }

        String alphabet = bufferedReader.readLine();
        parts = alphabet.split(" ");
        this.alphabet.addAll(Arrays.asList(parts));

        String initialState = bufferedReader.readLine();
        this.initialState = this.states.stream().filter(s -> s.getId().equals(initialState)).findFirst().orElseThrow(() -> new RuntimeException("The initial state is not contained in the list of states!\n"));

        String finalStates = bufferedReader.readLine();;
        parts = finalStates.split(" ");
        for(String state: parts){
            State finalState = this.states.stream().filter(s -> s.getId().equals(state)).findFirst().orElseThrow(() -> new RuntimeException("The final state is not contained in the list of states!\n"));
            finalState.setFinal(true);
            this.finalStates.add(finalState);
        }

        String transition;
        while((transition = bufferedReader.readLine()) != null){
            parts = transition.split("->");
            String[] finalParts = parts;
            State startState = this.states.stream().filter(s -> s.getId().equals(finalParts[0])).findFirst().orElseThrow();
            parts = parts[1].split(":");
            String[] finalParts1 = parts;
            State nextState = this.states.stream().filter(s -> s.getId().equals(finalParts1[0])).findFirst().orElseThrow();
            Transition transition1 = new Transition(parts[1], nextState);
            startState.with(transition1);
        }
    }

    public List<State> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }
}
