public class SymbolTable extends HashTable<String, Integer>{
    @Override
    protected Integer hashCode(String key) {
        int hashCode = 0;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            hashCode += (int) c;
        }
        return hashCode;
    }
}
