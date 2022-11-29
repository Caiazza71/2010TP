/*
Hash map
Array of word objects
Each object has key (obj.getKey), value (obj.getValue)
Take in string value → convert to key
Place new obj at key

Or bypass all by making key index 

*/



public class HashMap {

    public word[] arr;

    public void put(int key, String value) {
        word w = new word(key, value);

    }

}
