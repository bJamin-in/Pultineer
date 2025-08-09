package North;

import java.util.*;

import playerInfo.*;
import Funcs.*;

public class Town {
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

    // Constructor
    public Town() {
        this.message = "\nYou tread the path to the town. When you reach the entrance, you meet the guards at the entrance.";
    }

    public void guardConversation(String playerInput, Scanner keys, Player user) {

        if (user.getGuardFavor() > 0) {
            System.out.println("\nGuard: \"Ahh! If it isn't " + user.getName()
                    + ". Since I know who you are, you're welcome to Pultineer!\" He spits at the \nground\n");
            user.setPlayerY(user.getPlayerY() + 1);
        }

        else {
            System.out.println(
                    "\nGuard: \"Oi! You there! Why are you seeking entrance into Pultineer? Are you a tradesman? Or an adventurer?\" He spits at the ground after he is done speaking\n");
            playerInput = keys.nextLine();

            switch (playerInput.toLowerCase()) {
                case "tradesman":
                    System.out.println(
                            "\nGuard: \"Pah, as if we need more tradesman! Well, you don't seem to have any illegal items, so I'll grant you \nentrance\" He again spits at the ground\n");
                    user.setPlayerY(user.getPlayerY() + 1);
                    user.setGuardFavor(1);
                    break;

                case "adventurer":
                    System.out.println(
                            "\nGuard: \"Ahh, I used to be an adventurer once, but now I'm just a guard! Get in there before I change my mind.\" He spits \nat the ground");
                    user.setPlayerY(user.getPlayerY() + 1);
                    user.setGuardFavor(1);
                    break;

                default:
                    System.out.println(
                            "\nGuard: \"I don't know what you're talking about! Get out of here!\" He spits towards you and kicks you back to town\n");
                    user.setPlayerY(user.getPlayerY() - 1);
                    break;
            }// End of switch
        } // End of else statement
    }// End of guardConversation

}
