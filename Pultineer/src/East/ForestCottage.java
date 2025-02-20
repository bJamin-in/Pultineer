package East;

import java.util.*;
import playerInfo.*;
import Funcs.*;

public class ForestCottage {

    // Variables
    private String message;
    private Scanner keys = new Scanner(System.in);
    private String input = "";
    private String[][] originalShop = { { "Health Potion", "15", "10" }, { "Strength Potion", "20", "5" },
            { "Speed Potion", "10", "3" }, { "Hardiness Potion", "20", "3" } };
    private String[][] shop = { { "Health Potion", "15", "10" }, { "Strength Potion", "20", "5" },
            { "Speed Potion", "10", "3" }, { "Hardiness Potion", "20", "3" } };

    protected boolean keepShopping = true;

    // Gets
    public String getMessage() {
        return message;
    }

    public String getInput() {
        return input;
    }

    public String[][] getShop() {
        return shop;
    }

    public String[][] getOriginalShop() {
        return originalShop;
    }

    public String getShopItem(int x, int y) {
        return shop[x][y];
    }

    // Sets
    public void setMessage(String message) {
        this.message = message;
    }

    public void setShop(String[][] shop) {
        this.shop = shop;
    }

    private void printGoods() {
        // Print shop potions
        for (int i = 0; i < getShop().length; i++) {
            if (getShop()[i][0].equals("Health Potion")) {
                System.out.println(shop[i][0] + " - " + shop[i][1] + " gold - +" + shop[i][2] + " HP");
            } else if (getShop()[i][0].equals("Strength Potion")) {
                System.out.println(shop[i][0] + " - " + shop[i][1] + " gold - +" + shop[i][2] + " STR");
            } else if (getShop()[i][0].equals("Speed Potion")) {
                System.out.println(shop[i][0] + " - " + shop[i][1] + " gold - +" + shop[i][2] + " AGI");
            } else if (getShop()[i][0].equals("Hardiness Potion")) {
                System.out.println(shop[i][0] + " - " + shop[i][1] + " gold - +" + shop[i][2] + " DEF");
            }
        } // End of print shop
    }

    // Constructors
    public ForestCottage() {
        this.message = "\nAs you walk through the forest, you come upon a small cottage with smoke rising from the roof. Inside you \nhear someone talking. What do you do?(Sneak around, Knock at door, turn back, or continue through the forest)\n";
    }

    public void sneakAround(Player user) {
        // Function: Print out the following lines of code to depict the player sneaking
        // around the side of the cottage

        // Player has already snuck around previously
        if (user.getHasSnuckAround() == true) {
            System.out.println(
                    "\nYou sneak around the cottage just as you have before, except this time there is silence. You are once again met with the path that leads to the darker parts of the forest.");
        }
        // Player has not snuck around previously
        else if (user.getHasSnuckAround() == false && user.getHasMetGherald() == false) {

            System.out.println(
                    "\nYou sneak around the edge of the cottage. Reaching the back, you see an open window where an old man is talking to someone.\n\n ???: Hef, bring me the mugwort. I need to finish this potion. I cannot risk anyone getting deeper into the forest.\n\nYou hear a small squawk and a small creature flies to the old man with a small plant in its beak. You decide to turn around and go back to the front of the cottage, but you see a small beaten down path going deeper into the forest. Do you tread this path or go back to the front of the cottage?\n");
        }
        // Player has not snuck around previously(Changed from above to show Gheralds
        // name)
        else if (user.getHasSnuckAround() == false && user.getHasMetGherald() == true) {

            System.out.println(
                    "\nYou sneak around the edge of the cottage. Reaching the back, you see an open window where an old man is talking to someone.\n\n Gherald: \"Hef, bring me the mugwort. I need to finish this potion. I cannot risk anyone getting deeper into the forest.\"\n\nYou hear a small squawk and a small creature flies to the old man with a small plant in its beak. You decide to turn around and go back to the front of the cottage, but you see a small beaten down path going deeper into the forest. Do you tread this path or go back to the front of the cottage?\n");
        }
    }// End of sneakAround()

    public void knockAtDoor(Player user) {
        // Functionality: Print out the following lines of text to depict the player
        // knocking on the cottage door

        // If user has seen Gherald before
        if (user.getHasMetGherald() == true) {
            // Prints this set of text if the player has met Gherald before
            System.out.println(
                    "\nYou knock at the door. From inside, you hear Gherald speak out, \n\nGherald: \"One moment, please.\" \n\nA few seconds later, the door opens and, as Gherald sees you, he sparks up");

            delay(2000);
            System.out.println(
                    "\nGherald: \"Ah! Welcome back my friend, I'm glad to have you. What might I be able to assist you with?\n");
        }
        // If user hasnt met Gherald
        else if (user.getHasMetGherald() == false) {
            // Prints this set of text if the player has not met Gherald
            System.out.println(
                    "\nYou knock at the door of the cottage. Inside you hear a voice speak out,\n\n???: \"Yes, yes, I'll be right there.\" \n\nA moment later, the door swings open to reveal an old man with a long pointed white beard.");

            delay(4000);
            System.out.println(
                    "\nGherald: Ah! You must be the one that's making all the rustle and bustle eh? My name is Gherald. Tell me, what \nbrings you here? Might you need some help in your adventure? I can supply one with potions, for a price of \ncourse.\n");
            user.setHasMetGherald(true);
        }
    }// End of knockAtDoor

    public String turnBack() {
        // Functionality: To print out the following line of text to deict the player
        // turning around

        return "\nYou decide to turn back and retrace your steps through the forest.";
    }

    public void wizardConversation(Player user) {
        // Functionality: Runs the shop mechanic for Gherald
        String input = keys.nextLine();
        outer:
        // While player chooses shop
        while (!input.toLowerCase().contains("shop") || !input.toLowerCase().contains("back")
                || !input.toLowerCase().contains("continue")) {

            // Shop
            // ! if (input.toLower contains "shop", or "continue")
            if (input.toLowerCase().contains("shop") || input.toLowerCase().contains("continue")) {
                try {
                    if (getShopItem(0, 0).isEmpty()) {
                        System.out
                                .println("\nGherald: \"I am sorry, but you have bought all of my available potions!\n");
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\nGherald: \"I am sorry, but you have bought all of my available potions!\n");
                    break;
                }
                System.out.println(
                        "\nGherald: Ah, you're interested in my wares. I have a few potions that could help you on your journey. Here is \nwhat I have currently:\n");

                printGoods();

                System.out.println("You have " + user.getGold() + " gold.");
                System.out.println("\n(EXIT to leave)\n");

                input = keys.nextLine();

                do {

                    boolean repeat = true;
                    do {
                        // Leave
                        if (input.toLowerCase().contains("exit")) {
                            keepShopping = false;
                            break;
                        }

                        // Health Potion
                        try{
                            if(getShopItem(0, 0).isEmpty()){
                                
                            }
                            if (input.toLowerCase()
                                .contains(getShopItem(0, 0).toLowerCase())) {
                            int storeCost = Integer.parseInt(getShopItem(0, 1));
                            if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Drink potion and add buff to player
                                if (getShopItem(0, 0).toLowerCase().contains("health")) {
                                    user.setTotalHealth(
                                            user.getTotalHealth() + Integer.parseInt(getShopItem(0, 2)));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your health has increased by "
                                                    + shop[0][2] + " points.");
                                }
                                // Strength potion
                                else if (getShop()[0][0].toLowerCase().contains("strength")) {
                                    // Drink potion and add buff to player
                                    user.setAttack(user.getAttack() + Integer.parseInt(shop[0][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your strength has increased by "
                                                    + shop[0][2] + " points.");
                                }
                                // Speed
                                else if (getShop()[0][0].toLowerCase().contains("speed")) {
                                    // Drink potion and add buff to player
                                    user.setAgility(user.getAgility() + Integer.parseInt(shop[0][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your speed has increased by "
                                                    + shop[0][2] + " points.");
                                }
                                // Hardiness
                                else if (getShop()[0][0].toLowerCase().contains("Hardiness")) {
                                    // Drink potion and add buff to player
                                    user.setDefense(user.getDefense() + Integer.parseInt(shop[3][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your defense has increased by "
                                                    + shop[0][2] + " points.");
                                }

                                // Remove the potion
                                setShop(Functions.removeArrayElement(getShop(), 0));
                            } else {
                                System.out.println("\nYou do not have enough gold to purchase this item.");
                            }
                        } //^ End of Health Potion
                        } catch (ArrayIndexOutOfBoundsException e){}
                        
                        // Strength Potion
                        try{
                            if(getShopItem(1, 0).isEmpty()){
                                
                            }
                            if (input.toLowerCase()
                                .contains(getShop()[1][0].toLowerCase())) {
                            int storeCost = Integer.parseInt(getShop()[1][1]);
                            if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Drink potion and add buff to player
                                if (getShop()[1][0].toLowerCase().contains("strength")) {
                                    user.setAttack(user.getAttack() + Integer.parseInt(shop[1][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your strength has increased by "
                                                    + shop[1][2] + " points.");
                                }
                                // Speed
                                else if (getShop()[1][0].toLowerCase().contains("speed")) {
                                    // Drink potion and add buff to player
                                    user.setAgility(user.getAgility() + Integer.parseInt(shop[0][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your speed has increased by "
                                                    + shop[1][2] + " points.");
                                }
                                // Hardiness
                                else if (getShop()[1][0].toLowerCase().contains("Hardiness")) {
                                    // Drink potion and add buff to player
                                    user.setDefense(user.getDefense() + Integer.parseInt(shop[3][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your defense has increased by "
                                                    + shop[1][2] + " points.");
                                }
                                // Remove the potion
                                setShop(Functions.removeArrayElement(getShop(), 1));
                            } else {
                                System.out.println("\nYou do not have enough gold to purchase this item.");
                            }
                        }//^ End of strength potion

                        } catch (ArrayIndexOutOfBoundsException e){}
                        
                        // Speed Potion
                        try{
                            if(getShopItem(2, 0).isEmpty()){
                                
                            }
                            if (input.toLowerCase()
                                .contains(getShop()[2][0].toLowerCase())) {
                            int storeCost = Integer.parseInt(getShop()[2][1]);
                            if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Speed
                                if (getShop()[2][0].toLowerCase().contains("speed")) {
                                    // Drink potion and add buff to player
                                    user.setAgility(user.getAgility() + Integer.parseInt(shop[0][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your speed has increased by "
                                                    + shop[2][2] + " points.");
                                }
                                // Hardiness
                                else if (getShop()[2][0].toLowerCase().contains("Hardiness")) {
                                    // Drink potion and add buff to player
                                    user.setDefense(user.getDefense() + Integer.parseInt(shop[3][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your defense has increased by "
                                                    + shop[2][2] + " points.");
                                }

                                // Remove the potion
                                setShop(Functions.removeArrayElement(getShop(), 2));
                            } else {
                                System.out.println("\nYou do not have enough gold to purchase this item.");
                            }
                        }//^ End of speed potion

                        } catch (ArrayIndexOutOfBoundsException e){}
                        
                        // Hardiness Potion
                        try{
                        if(getShopItem(3, 0).isEmpty()){
                            
                        }
                        if (input.toLowerCase()
                                    .contains(getShop()[3][0].toLowerCase())) {

                                int storeCost = Integer.parseInt(getShop()[3][1]);
                                if (user.getGold() >= storeCost) {
                                    user.setGold(user.getGold() - storeCost);

                                    // Drink potion and add buff to player
                                    user.setDefense(user.getDefense() + Integer.parseInt(shop[3][2]));
                                    System.out.println(
                                            "\nYou drink the potion and feel a surge of energy. Your defense has increased by "
                                                    + shop[3][2] + " points.");

                                    // Remove the potion
                                    setShop(Functions.removeArrayElement(getShop(), 3));
                                } else {
                                    System.out.println("\nYou do not have enough gold to purchase this item.");
                                }
                            }//^ End of Hardiness potion

                        } catch (ArrayIndexOutOfBoundsException e) {}
                            
                        // Invalid Selection
                        if ((!input.toLowerCase().contains("health potion")) && (!input.toLowerCase().contains("strength potion")) && (!input.toLowerCase().contains("speed potion")) && (!input.toLowerCase().contains("hardiness potion"))) {
                            System.out.println("\nGherald: \"I'm sorry, I don't seem to have that item in stock.\"");
                        } // ^ End of invalid input
                        try {
                            if (getShop()[0][0].isEmpty()) {
                                System.out.println(
                                        "\nGherald: \"I am sorry, but you have bought all of my available potions!\n");
                                break outer;
                            } // ^ End of if(shop is empty)
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(
                                    "\nGherald: \"I am sorry, but you have bought all of my available potions!\n");
                            break outer;
                        } // ^ End of try/catch
                        System.out.println("\n\nGherald: Would you like to buy something else?\n");
                        input = keys.nextLine();

                        // Leave shop
                        if (input.toLowerCase().contains("no")) {
                            keepShopping = false;
                            repeat = false;
                            break;
                        } // ^ End of if(input contains "no")

                        // Continue shopping
                        else if (input.toLowerCase().contains("yes")) {
                            repeat = true;
                            System.out.println();
                            printGoods();
                            System.out.println("(EXIT to leave)");
                            System.out.println("\n You have " + user.getGold() + " gold.");
                            input = keys.nextLine();
                        } // ^ End of if(input contains "yes")

                        // Invalid Input
                        else {
                            System.out.println("\n\nGherald: What was that?(yes/no)");
                        } // ^ End of invalid input

                    } while (repeat); // End of do-while(repeat)
                } while (keepShopping);// End of do-while(keepShopping)

            } // ^End of if(input == shop or continue)
              // Exit
              // ! if (input.toLower contains "exit")
            else if (input.toLowerCase().contains("exit") || (input.toLowerCase().contains("no"))) {
                System.out.println("\nGherald: \"Well, come again later!\"");
                break;
            } // ^End of if(input contains "back")
              // Back
              // ! if (input.toLower contains "back")
            else if (input.toLowerCase().contains("back")) {
                System.out.println(
                        "\nGherald: Ah, I see. You're not interested in my wares. Well, if you ever need anything, you know where to find me.\n");
                break;
            } // ^End of if(input == back)

            // Talk
            // ! if (input.tolower contains "talk" and user Rank is follower and user
            // accepted quest)
            else if (input.toLowerCase().contains("talk") && user.getRank().toLowerCase().contains("follower")
                    && user.getQuestAccepted() == true && user.getHasQuestItem() == false) {
                System.out.println(
                        "\nGherald: \"Oh? You're picking up a potion for the Priest in town? Let me go get that potion for him.\"");
                delay(1500);
                System.out.println(
                        "\nHe walks towards his cabinets as he shoves bottles to the side, Gherald eventually brings out a teal \ncolored bottle and hands it to you.\n\nGherald: \"Make sure to tell the Priest to be careful with this, it's quite dangerous.\"\nGherald leads you to the door of his cottage as you exit, he closes the door behind you.");
                user.setHasQuestItem(true);
                break;
            } // ^ End of if(input contains talk, user rank is follower, and user has accepted
              // quest)
              // Invalid(Quest)
              // ! if (input.toLower does not contain "shop", "back", and "exit")
            else if (!input.toLowerCase().contains("shop") || !(input.toLowerCase().contains("back"))
                    || !(input.toLowerCase().contains("exit")) || !(input.toLowerCase().contains("no"))
                    || !(input.toLowerCase().contains("talk")) && (user.getQuestAccepted() == true)) {
                System.out.println(
                        "\nGherald: I'm sorry, I didn't quite catch that. Could you repeat yourself?(Shop/Back/Talk)\n");
                input = keys.nextLine();
            } // ^ End of Invalid selection(Quest differential)
              // Invalid
              // ! if (input.toLower does not contain "shop", "back", and "exit")
            else if (!input.toLowerCase().contains("shop") || !input.toLowerCase().contains("back")
                    || !input.toLowerCase().contains("exit") || !input.toLowerCase().contains("no")
                            && user.getQuestAccepted() == false) {
                System.out.println(
                        "\nGherald: I'm sorry, I didn't quite catch that. Could you repeat yourself?(Shop/Back)\n");
                input = keys.nextLine();
            } // ^ End of Invalid selection
        } // ^ End of shop sequence
    }// ^ End of wizardConversation

    // Delay
    public static void delay(int x) {
        // Functionality: Sleeps the thread to delay output
        try {
            Thread.sleep(x);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }// End of delay(int x)
}
