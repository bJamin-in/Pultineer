package North;

import playerInfo.*;
import Funcs.*;

public class ChurchDistrict {

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
    public ChurchDistrict(String message) {
        this.message = message;
    }

    public ChurchDistrict() {
        message = "You have entered the Church District. You see a few people praying and a priest standing at the altar. \nDo you want to pray, talk to the priest, or leave?\n";
    }

    public void changeMessage(Player user) {
        if (user.getBoardUnlocked()) {
            this.setMessage(
                    "You have entered the Church District. You see a few people praying and a priest standing at the altar. \nDo you want to pray, talk to the priest, look at the job board, or leave?\n");
        }
        if (user.getRank().contains("Squire") || user.getRank().contains("Paladin") || user.getRank().contains("General") || user.getRank().contains("Holy Knigh") || user.getRank().contains("Holy Knight Champion")){
            this.setMessage(
                    "You have entered the Church District. You see a few people praying and a priest standing at the altar. \nDo you want to pray, talk to the priest, look at the job board, go to the church's shop, or leave?\n");
        }
    }

    public void squireRankup(Player user) {
        System.out.println(
                "\nYou go to talk to the Priest to tell him of your recent conquest. The Priest notices you walking towards him.\n\nPriest: \"Ah! "
                        + user.getName()
                        + ", I'm glad you're alive. I can see you took care of the Goblins in the Dark Forest, I am thankful.\nThe Priest gives a slight bow to you as he finishes speaking\n\nPriest: \"Let me go fetch your reward.\"");
        Functions.delay(10000);
        System.out.println(
                "\nThe Priest walks into the church and comes out a minute later carrying a small pouch and a scroll of paper. He hands you the pouch as he speaks.\n\nPriest: \"For your great efforts of slaying the nearby Goblin Horde, you get 10 gold! And one more thing...\"");
        user.setGold(user.getGold() + 10);
        Functions.delay(10000);
        System.out.println(
                "\nThe Priest hands you the small paper scroll as he announces to not only you, but the nearby crowd as well:\n\nPriest: \"Due to this man's efforts, the Church of Eryndros is now commiting him to be a Squire! This man will\nrise through the ranks of the Church with the goal of becoming, our next Holy Knight Champion!");
        Functions.rankUp(3, user);
        Functions.delay(13000);

        // Squire Quest
        System.out.println(
                "\nPriest: \"Now there is one more subject that is now pertinent for you to know, if you'll follow me inside.\"\n\nYou follow the Priest inside the church, and after a bit of walking you reach the Priest's office. The Priest\nwalks into the room and invites you in.");
        Functions.delay(65000);
        System.out.println(
                "\nYou walk into the room to see a well decorated office space, with a large mahogany desk cluttered with papers\nand a few pictures of who you assume is the Priest's family. You look up to see a wall lined with many\ndifferent books, with none that stand out in particular. And finally, and two simple chairs facing the desk.");
        Functions.delay(7500);
        System.out.println(
                "\nThe Priest walks around and sits at his desk chair and invites you to sit in one of the chairs facing him. As you do, the Priest begins to explain.\n\nPriest: \"Because of your recent promotion, you will be tending to one of our knights into battle. Polishing\nhis armor, sharpening his sword, whatever he requires you to do, you must do. When you are ready, go South, to\nthe Badlands. There will be your first assignment.\"");
        Functions.delay(10000);
        System.out.println(
                "Priest: \"Since you will be following a knight around, you will find yourself on the field of combat much more \noften. To assist in your survival chances, the Church is willing to help offer you better gear. We have some \ngear you might be interested in that we picked up from conquests and allying with other national powers. Feel \nfree to walk in and inquire more at the desk.\n");
    }
}// End of Class