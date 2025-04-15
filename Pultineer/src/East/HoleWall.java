package East;

public class HoleWall {

    //Variables
    private String[] messages = {"", ""};


    //Getters
    public String getMessage(int x) {
        return messages[x];
    }

    //Setters
    public void setMessages(String message, int x) {
        messages[x] = message;
    }

    //Constructors
    public HoleWall(){
    }
}
