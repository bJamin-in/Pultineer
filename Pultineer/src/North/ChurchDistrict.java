package North;
import playerInfo.*;
import Funcs.*;

public class ChurchDistrict {

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
    public ChurchDistrict(String message){
        this.message = message;
    }

    public ChurchDistrict(){
        message = "You have entered the Church District. You see a few people praying and a priest standing at the altar. \nDo you want to pray, talk to the priest, or leave?\n";
    }
    
    public void changeMessage(Player user){
        if(user.getBoardUnlocked()){
            this.setMessage("You have entered the Church District. You see a few people praying and a priest standing at the altar. \nDo you want to pray, talk to the priest, look at the job board, or leave?\n");
        }
    }
}//End of Class