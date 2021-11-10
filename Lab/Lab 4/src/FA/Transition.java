package FA;

public class Transition {
    private final String rule;
    private final State next;

    public Transition(String rule, State next) {
        this.rule = rule;
        this.next = next;
    }

    public State state(){
        return this.next;
    }

    public boolean isPossible(String input){
        return this.rule.equalsIgnoreCase(input);
    }

    public String getRule() {
        return rule;
    }

    public State getNext() {
        return next;
    }
}
