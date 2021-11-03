public class Pair <K, V>{
    private final K key;
    private final V Value;

    public Pair(K key, V value) {
        this.key = key;
        Value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return Value;
    }
}
