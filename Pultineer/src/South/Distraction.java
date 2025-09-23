package South;

import java.util.*;
import Funcs.*;
import playerInfo.*;

@SuppressWarnings("unused")
public class Distraction {
    // Variables

    private String message;

    // Getters

    public String getMessage() {
        return message;
    }

    // Setters

    public void setMessage(String message) {
        this.message = message;
    }

    // Constructors

    public Distraction(){
        message = "As your group is following Sir Branric's lead, a group of 3 lizard-looking figures jump out with curvy daggars.";
    }

    public Distraction(String message){
        this.message = message;
    }


}// End of Class