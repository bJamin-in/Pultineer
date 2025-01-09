package East;

public class HoleWall {

    //Variables
    private String message;


    //Getters
    public String getMessage() {
        return message;
    }

    //Setters
    public void setMessage(String message) {
        this.message = message;
    }

    //Constructors
    public HoleWall(String message){
        this.message = message;
    }

    public HoleWall(){
        message = "\nWhile wandering through the forest, you find a brick wall interrupting your path. Near the bottom of the wall, you see a small hole that seems like you might be able to squeeze through it.\n";
    }
    
}
