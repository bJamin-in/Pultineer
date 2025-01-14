package North;

import java.util.*;

import Funcs.Functions;
import playerInfo.*;

public class ShoppingDistrict {
    private String message;
    public String[][] shopGoods = { { "Wooden Sword", "10", "5" }, { "Leather Armor", "15", "5" },
            { "Health Potion", "10", "5" } };

    public void printGoods() {

        // Wooden Sword
        System.out.println(shopGoods[0][0] + " - " + shopGoods[0][1] + " gold - +" + shopGoods[0][2] + " ATK");
        // Leather Armor
        System.out.println(shopGoods[1][0] + " - " + shopGoods[1][1] + " gold - +" + shopGoods[1][2] + " DEF");
        // Health Potion
        System.out.println(shopGoods[2][0] + " - " + shopGoods[2][1] + " gold - +" + shopGoods[2][2] + " HP");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShoppingDistrict() {
        this.message = "\nYou enter the shopping district. You see a few shops, but one sticks out more than the rest. Do you walk up to the \nmerchant, continue through the town, or go back to the village?\n";
    }

    public void merchantConversation(String playerInput, Scanner keys, Player user) {
        boolean merchant = true;

        changeMessage(user);
        while (merchant) {
            System.out.println(this.getMessage());
            playerInput = keys.nextLine();

            while (true) {
                // Merchant
                if (playerInput.toLowerCase().contains("merchant")) {
                    System.out.println(
                            "\nYou walk up to the merchant. The merchant greets you with a smile. \nMerchant: \"Ahh helloo there! Care to buy some goods? The potion though, I only have the one! Limited stock, yes!\" You look at what his shop has to offer.\n");
                    printGoods();

                    System.out.println("\n You have " + user.getGold() + " gold.");
                    System.out.println("\n(EXIT to leave)\n");
                    merchant = false;
                    break;
                }

                // Continue
                else if (playerInput.toLowerCase().contains("continue")) {
                    System.out.println("\nYou continue traveling through the town.");
                    user.setPlayerY(user.getPlayerY() + 1);
                    merchant = false;
                    Functions.delay(1500);
                    break;
                }

                // Turn Back
                else if (playerInput.toLowerCase().contains("back")) {
                    System.out.println("You return to Krymn.");
                    user.setPlayerY(user.getPlayerY() - 2);
                    merchant = false;
                    break;
                }

                // Hole
                else if (playerInput.toLowerCase().contains("hole")) {
                    // If player hasnt gone through the hole from the forest
                    if (user.getGoneThroughHole() == false) {
                        System.out.println(
                                "\nYou navigate through the Shopping District and come upon a wall. Nothing about the wall is notable");
                                break;
                    } else {
                        System.out.println(
                                "\nYou come to the same wall that you once squeezed through. You managed to do it again and now you see that you are in the depths of the forest.");
                        user.setPlayerX(2);
                        user.setPlayerY(1);
                        merchant = false;
                        break;
                    }
                } // End of Hole

                // Invalid Selection
                else {
                    if (user.getGoneThroughHole() == true) {
                        System.out.println("\nImproper input. Please try again.(Merchant/Continue/Hole/Back)");
                    } else {
                        System.out.println("\nImproper Input. Please try again.(Merchant/Continue/Back)");
                    }
                    Functions.delay(1500);
                    System.out.println(this.getMessage());
                    playerInput = keys.nextLine();
                }

            } // End of while(true)
        } // End of while(merchant)
    }// End of MerchantConversation

    public void changeMessage(Player user) {
        if (user.getGoneThroughHole() == true) {
            this.setMessage(
                    "\nYou enter the shopping district. You see a few shops, but one sticks out more than the rest. Do you walk up to the \nmerchant, continue through the town, go back to the village, or through the hole in the wall?\n");
        }
    }

}// End of class
