package paladinRankupQuest;
// import java.util.*;

public class CultTown {
//Variables

String message = "";

//Getters

public String getMessage(){
    return message;
}

//Setters

public void setMessage(String message){
    this.message = message;
}

//Constructors

public CultTown() {
message = "This is a small town controlled by a cult.";
}//End of blank constructor

public CultTown(String message) {
this.message = message;
}//End of preferred constructor

//toString
@Override
public String toString() {
return message;
}
}//End of Class