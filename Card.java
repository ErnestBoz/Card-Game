public class Card{
    private final int value;
    private static int idCounter = 0;
    private int id;
    
    // Function to reset counter used in test case only
    public static void resetCardCounter() {
        idCounter = 0;
    }
    
    // Constructor
    public Card(int value){
        this.value = value;
        this.id = idCounter++;
    }
    
    public int getValue(){
        return value;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
