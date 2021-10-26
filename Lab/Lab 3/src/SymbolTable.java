import java.util.ArrayList;

public class SymbolTable{
    private ArrayList<HashNode<String, Integer>> nodeList;
    private Integer capacity;
    private Integer size;

    public SymbolTable() {
        nodeList = new ArrayList<>();
        capacity = 10;
        size = 0;

        for(int i = 0; i < capacity; i++)
            nodeList.add(null);
    }

    public Integer getCapacity(){
        return capacity;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    public Integer getSize(){
        return size;
    }

    public Integer getIndex(String key){
        Integer hashcode = hashCode(key);
        Integer index = hashcode % capacity;
        if(index < 0) index *= -1;
        return index;
    }

    public Integer searchValue(String key){
        Integer index = getIndex(key);
        Integer hashCode = hashCode(key);

        HashNode<String, Integer> headNode = nodeList.get(index);

        while(headNode != null){
            if(headNode.getKey().equals(key) && headNode.getHashCode().equals(hashCode))
                return headNode.getValue();
            headNode = headNode.getNext();
        }
        return null;
    }

    public Integer addKeyValue(String key, Integer value){
        Integer index = getIndex(key);
        Integer hashCode = hashCode(key);
        HashNode<String, Integer> headNode = nodeList.get(index);

        while(headNode != null){
            if(headNode.getKey().equals(key) && headNode.getHashCode().equals(hashCode)){
                return headNode.getValue();
            }
            headNode = headNode.getNext();
        }

        size++;
        headNode = nodeList.get(index);
        HashNode<String, Integer> newNode = new HashNode<>(key, index, hashCode);
        newNode.setNext(headNode);
        nodeList.set(index, newNode);

        if((1.0 * size) / capacity > 0.7){
            ArrayList<HashNode<String, Integer>> temp = nodeList;
            nodeList = new ArrayList<>();
            capacity *= 2;
            size = 0;
            for(int i = 0; i < capacity; i++)
                nodeList.add(null);

            for(HashNode<String, Integer> node: temp){
                while(node != null){
                    addKeyValue(node.getKey(), node.getValue());
                    node = node.getNext();
                }
            }
        }
        return newNode.getValue();
    }



    public Integer hashCode(String key) {
        int hashCode = 0;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            hashCode += (int) c;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        String string = "";
        for(HashNode<String, Integer> hashNode: nodeList){
            if(hashNode != null)
                string += hashNode.getValue() + " " + hashNode.getKey() + "\n";
        }
        return string;
    }
}
