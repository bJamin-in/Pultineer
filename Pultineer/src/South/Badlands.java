package South;
import playerInfo.*;
import Funcs.*;
import java.util.*;

@SuppressWarnings("unused")
public class Badlands {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Badlands() {
        this.message = "\nYou go to walk through the badlands, but the heat is unbearable. You return to the village and rest.";
    }

    public void changeMessage(Player user){
        if(user.isHasHeatCloak() == true){
            this.setMessage("\nWalking over the sandy dunes of the badlands, you feel as cool as if you were traveling through the Dark Forest. \nLooking around, you see the familliar figure of Sir Branric Hollow. Sir Branric sees you approaching.");
        }
    }
}
