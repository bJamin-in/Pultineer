package South;

import java.util.*;
import playerInfo.*;
import Funcs.Functions;

@SuppressWarnings("unused")
public class SandPit {
    // Variables

    String message = "";


    // Getters

    public String getMessage() {
        return message;
    }

    // Setters

    public void setMessage(String message) {
        this.message = message;
    }

    // Constructors

    public SandPit(String message){
        this.message = message;
    }
    public SandPit(){
        message = "As the group is marching through the dunes, you hear a shouting from a soldier.";
    }

    // toString
    @Override
    public String toString(){
        return "SandPit: " + message;
    }

}// End of Class