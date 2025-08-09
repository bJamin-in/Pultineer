package North;

import java.util.*;

import Funcs.Functions;
import playerInfo.*;

public class ShoppingDistrict {
    private String message;
    public String[][] shopGoods = { { "Wooden Sword", "10", "5" }, { "Leather Armor", "15", "5" },
            { "Health Potion", "10", "5" } };

    public void printGoods(Player user) {
        try {
            if (shopGoods.length == 2) {
                // Wooden Sword
                System.out.println(shopGoods[0][0] + " - " + shopGoods[0][1] + " gold - +" + shopGoods[0][2] + " ATK");
                // Leather Armor
                System.out.println(shopGoods[1][0] + " - " + shopGoods[1][1] + " gold - +" + shopGoods[1][2] + " DEF");
                // Exit message
                System.out.println("\n You have " + user.getGold() + " gold.(EXIT to leave)");

            } else {
                // Wooden Sword
                System.out.println(shopGoods[0][0] + " - " + shopGoods[0][1] + " gold - +" + shopGoods[0][2] + " ATK");
                // Leather Armor
                System.out.println(shopGoods[1][0] + " - " + shopGoods[1][1] + " gold - +" + shopGoods[1][2] + " DEF");
                // Health Potion
                System.out.println(shopGoods[2][0] + " - " + shopGoods[2][1] + " gold - +" + shopGoods[2][2] + " HP");
                // Exit message
                System.out.println("\n You have " + user.getGold() + " gold.(EXIT to leave)");
            }

        } catch (Exception e) {
            // Wooden Sword
            System.out.println(shopGoods[0][0] + " - " + shopGoods[0][1] + " gold - +" + shopGoods[0][2] + " ATK");
            // Leather Armor
            System.out.println(shopGoods[1][0] + " - " + shopGoods[1][1] + " gold - +" + shopGoods[1][2] + " DEF");
            // Exit message
            System.out.println("\n You have " + user.getGold() + " gold.(EXIT to leave)");
        }
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

    public void merchantConversation(String playerInput, Scanner keys, Player user, PlayerInventory inv) {
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
                    printGoods(user);
                    merchant = false;

                    playerInput = keys.nextLine();

                    boolean keepShopping = true;
                    int storeCost = 0;

                    // Buying items from the merchant
                    do {

                        // Leave
                        if (playerInput.toLowerCase().contains("exit")) {
                            keepShopping = false;
                            break;
                        }

                        // Wooden Sword
                        else if (playerInput.toLowerCase()
                                .contains(shopGoods[0][0].toLowerCase())) {
                            storeCost = Integer.parseInt(shopGoods[0][1]);
                            // If user already has wooden sword
                            if (inv.getEquipedWeapon().toLowerCase().contains("wooden sword")) {
                                System.out.println("\nYou already have the Wooden Sword equipped.");
                                break;
                            } else if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Equips armor and adds the defense buff to player
                                // inv.setWeapon(Integer.parseInt(shopGoods[0][2]),
                                // shopGoods[0][0]);
                                user.swapWeapons(inv, user, shopGoods[0][0],
                                        Integer.parseInt(shopGoods[0][2]));

                            } else {
                                System.out.println("\nYou do not have enough gold to purchase this item.");
                            }
                        } // End of Wooden Sword

                        // Leather armor
                        else if (playerInput.toLowerCase()
                                .equals(shopGoods[1][0].toLowerCase())) {
                            storeCost = Integer.parseInt(shopGoods[1][1]);
                            // If user already has Leather Armor
                            if (inv.getEquipedArmor().toLowerCase().contains("leather armor")) {
                                System.out.println("\nYou already have the Leather Armor equipped");
                                break;
                            } else if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Equips armor and adds the defense buff to player
                                // inv.setEquipedArmor(shopGoods[1][0]);
                                // inv.setArmorValue(Integer.parseInt(shopGoods[1][2]));
                                user.swapArmor(inv, user, shopGoods[1][0], Integer.parseInt(shopGoods[1][2]));
                            } else {
                                System.out.println("You do not have enough gold to purchase this item.");
                            }
                        } // End of Leather Armor
                          // Health Potion
                        else if (playerInput.toLowerCase()
                                .equals(shopGoods[2][0].toLowerCase())) {
                            storeCost = Integer.parseInt(shopGoods[2][1]);
                            if (user.getGold() >= storeCost) {
                                user.setGold(user.getGold() - storeCost);

                                // Drink potion and add buff to player
                                user.setTotalHealth(user.getTotalHealth() + Integer.parseInt(shopGoods[2][2]));
                                System.out.println(
                                        "\nYou drink the potion and feel a surge of energy. Your health has increased by "
                                                + shopGoods[2][2] + " points.");

                                // Remove the potion from the shop
                                shopGoods = Functions.removeArrayElement(shopGoods, 2);
                            } else {
                                System.out.println("You do not have enough gold to purchase this item.");
                            }
                        } // End of Health Potion

                        // Invalid Selection
                        else {
                            System.out.println("Item not found.");
                        }

                        Functions.delay(3000);
                        System.out.println("\n\nMerchant: Would you like to buy something else?");
                        printGoods(user);

                        System.out.println("\n You have " + user.getGold() + " gold.");
                        playerInput = keys.nextLine();
                    } while (keepShopping);// End of do-while(keepShopping)
                    // End of Merchant sequence
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
                                user.setComingFromTown(true);
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
