import java.util.ArrayList;
import java.util.Objects;

public class HashTable<K, V> {
    private ArrayList<HashNode<K, V>> nodeList;
    private Integer capacity;
    private Integer size;

    public HashTable() {
        nodeList = new ArrayList<>();
        capacity = 10;
        size = 0;

        for(int i = 0; i < capacity; i++)
            nodeList.add(null);
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public Integer getSize(){
        return size;
    }

    protected Integer hashCode(K key){
        return Objects.hashCode(key);
    }

    protected Integer getIndex(K key){
        Integer hashcode = hashCode(key);
        Integer index = hashcode % size;
        if(index < 0) index *= -1;
        return index;
    }

    public V searchValue(K key){
        Integer index = getIndex(key);
        Integer hashCode = hashCode(key);

        HashNode<K, V> headNode = nodeList.get(index);

        while(headNode != null){
            if(headNode.getKey().equals(key) && headNode.getHashCode().equals(hashCode))
                return headNode.getValue();
            headNode = headNode.getNext();
        }
        return null;
    }

    public V addKeyValue(K key, V value){
        Integer index = getIndex(key);
        Integer hashCode = hashCode(key);
        HashNode<K, V> headNode = nodeList.get(index);

        while(headNode != null){
            if(headNode.getKey().equals(key) && headNode.getHashCode().equals(hashCode)){
                return headNode.getValue();
            }
            headNode = headNode.getNext();
        }

        size++;
        headNode = nodeList.get(index);
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        newNode.setNext(headNode);
        nodeList.set(index, newNode);

        if((1.0 * size) / capacity > 0.7){
            ArrayList<HashNode<K, V>> temp = nodeList;
            nodeList = new ArrayList<>();
            capacity *= 2;
            size = 0;
            for(int i = 0; i < capacity; i++)
                nodeList.add(null);

            for(HashNode<K, V> node: temp){
                while(node != null){
                    addKeyValue(node.getKey(), node.getValue());
                    node = node.getNext();
                }
            }
        }
        return newNode.getValue();
    }
}
