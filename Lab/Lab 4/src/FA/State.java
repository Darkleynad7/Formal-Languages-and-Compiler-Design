package FA;

import java.util.ArrayList;
import java.util.List;

public class State {
    private final String id;
    private final List<Transition> transitions;
    private boolean isFinal;

    public State(String id, boolean isFinal){
        this.id = id;
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public State transit(String input){
        return transitions
                .stream()
                .filter(t -> t.isPossible(input))
                .map(Transition::state)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Input not accepted: " + input));
    }

    public boolean isFinal(){
        return this.isFinal;
    }

    public void setFinal(boolean isFinal){
        this.isFinal = isFinal;
    }

    public State with(Transition transition){
        this.transitions.add(transition);
        return this;
    }

    @Override
    public String toString() {
        return this.id;
    }

    public String getId() {
        return id;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }
}
