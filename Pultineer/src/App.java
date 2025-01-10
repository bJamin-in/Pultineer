
//Imports
import java.util.*;

//Different locations of the game
import East.*;
import North.*;
import South.*;
import West.*;

//All player information
import playerInfo.*;

//Functions
import Funcs.*;

/*
 * This is a text based Java game created and developed by Benjamin James
 * This game was started in December of 2023, with the goal
 * of creating a fully working, challenging and fun, text based adventure game.
 * This game was last updated in January 7th, 2025
 * 
 * NOTE:
 * All Pseudocode is written in red comments that follows the indications of VSC extension:   
 * Colorful Comments developed by Parth Rastogi
 */

public class App {
    // #region Funcs and Methods

    // Move player
    public static void movePlayer(int dx, int dy, Player user) {
        user.setPlayerX(user.getPlayerX() + dx);
        user.setPlayerY(user.getPlayerY() + dy);
    }// End of movePlayer

    // checkGameState
    public static void checkGameState(Scanner keys, boolean gameState) {
        // Functionality: Checks the gameState boolean variable and ends the program if
        // it is false
        if (gameState == false) {
            keys.close(); // replace 'keys' with your actual Scanner object
            System.exit(0); // end the program
        }
    }// End of checkGameState

    // Rank Up
    public static void rankUp(int rankNum, Player user) {
        // Functionality: Sets the player's rank and gives a description of the new rank
        String[] ranks = { "Wanderer", "Follower", "Disciple", "Squire", "Knight", "Paladin", "General", "Holy Knight",
                "Holy Knight Champion" };

        user.setRank(ranks[rankNum]);
        user.rankDescription(user);
    }

    // Move player in one of four different directions
    public static int get4Direction(String playerInput, Player user, String keyword1, String keyword2, String keyword3,
            String keyword4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // North
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 0;
        }
        // South
        else if (playerInput.toLowerCase().contains(keyword2)) {
            return 1;
        }
        // East
        else if (playerInput.toLowerCase().contains(keyword3)) {
            return 2;
        }
        // West
        else if (playerInput.toLowerCase().contains(keyword4)) {
            return 3;
        }
        // Invalid input
        else {
            System.out.println("\nInvalid direction. Please try again.");
            Functions.delay(1500);
        }
        return 0;
    }// End of get4Direction

    // Move player in one of two different directions
    public static void get2Direction(String playerInput, Player user, boolean VorH, String keyword1, String keyword2) {
        // Functionality: Gets input from the player and moves them in accordance with
        // their choice
        // Y-axis movement
        if (VorH) {
            // forward
            if (playerInput.toLowerCase().contains(keyword1)) {
                movePlayer(0, 1, user);
            }
            // back
            else if (playerInput.toLowerCase().contains(keyword2)) {
                movePlayer(0, -1, user);
            }
            // Invalid input
            else {
                System.out.println("\nInvalid direction. Please try again.(" + keyword1 + "/" + keyword2 + ")");
            }
        }
        // X-axis movement
        else if (VorH == false) {
            // forward
            if (playerInput.toLowerCase().contains(keyword1)) {
                movePlayer(1, 0, user);
            }
            // forward
            else if (playerInput.toLowerCase().contains(keyword2)) {
                movePlayer(-1, 0, user);
            }
            // Invalid input
            else {
                System.out.println("\nInvalid direction. Please try again.(" + keyword1 + "/" + keyword2 + ")");
                Functions.delay(1500);
            }
        }
        return;
    }// End of get2Direction

    // Move player in one of three different directions
    public static int get3Direction(String playerInput, String keyword1, String keyword2, String keyword3) {
        // Functionality: Recieves input and sends the player in one of three directions
        // keyword1
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 0;
        }
        // forward
        else if (playerInput.toLowerCase().contains(keyword2)) {
            return 1;
        }
        // Invalid input
        else if (playerInput.toLowerCase().contains(keyword3)) {
            return 2;
        } else {
            return 3;
        }
    }// End of get3Direction

    // Turn in Quest items
    public static void returnQuestItems(Player user){
        user.setHasQuestItem(false);
        user.setQuestAccepted(false);
    }
    // #region ArrayMethods
    // ^ getDirections: ARRAY METHODS
    // Move player in one of four different directions: 1 Array
    public static int get4Direction(String playerInput, Player user, String keyword1, String keyword2, String keyword3,
            String[] keywords) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 1;
        }
        // 2
        else if (playerInput.toLowerCase().contains(keyword2)) {
            return 2;
        }
        // 3
        else if (playerInput.toLowerCase().contains(keyword3)) {
            return 3;
        }

        for (int x = 0; x < keywords.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(keywords[x])) {
                return 4;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // Move player in one of four different directions: 2 Arrays
    public static int get4Direction(String playerInput, Player user, String keyword1, String keyword2,
            String[] keywords3,
            String[] keywords4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 1;
        }
        // 2
        else if (playerInput.toLowerCase().contains(keyword2)) {
            return 2;
        }
        // 3
        for (int x = 0; x < keywords3.length; x++) {
            if (playerInput.toLowerCase().contains(keywords3[x])) {
                return 3;
            }
        }

        for (int x = 0; x < keywords4.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(keywords4[x])) {
                return 4;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // Move player in one of three different directions: 1 Array
    public static int get3Direction(String playerInput, String keyword1, String keyword2, String[] keywords) {
        // Functionality: Recieves input and sends the player in one of three directions
        // 1
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 1;
        }
        // 2
        else if (playerInput.toLowerCase().contains(keyword2)) {
            return 2;
        }
        // 3
        for (int x = 0; x < keywords.length; x++) {
            if (playerInput.toLowerCase().contains(keywords[x])) {
                return 3;
            }
        }
        // Invalid Input
        return 4;
    }// End of get3Direction

    // #endregion ArrayMethods
    // #endregion Funcs and Methods

    // #region Main
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {

        // #region Starting Info
        // Variables

        String playerInput;

        String[][] townShopGoods = { { "Wooden Sword", "10", "5" }, { "Leather Armor", "15", "5" },
                { "Health Potion", "10", "5" } };
        String[] contAnswers = { "continue", "forward" }, yesAnswers = { "yes", "okay", "alright", "accept" },
                noAnswers = { "no", "nevermind", "deny" }, backAnswers = { "back", "return", "reverse" };

        // Battle and game state variables
        boolean gameState = true, battleState = false, wolfDead = false, goblinDead = false;

        int storeCost = 0;

        // Class Variables

        Scanner keys = new Scanner(System.in);

        Random rnd = new Random();

        // Inputs

        // User Startup

        // Introduction
        System.out.println(
                "Welcome to Pultineer! The text based adventure game where you have the goal to become the Holy Knight Champion \nof Pultineer. You will travel through the lands of Krynn, facing monsters, and meeting new people. You will \nhave to make choices that will affect your journey. Find your way through and become the Holy Knight Champion! \n\nGood luck, and may the gods smile upon you.\n");

        // Player setup
        // Get name
        System.out.print("Enter your name: ");
        playerInput = keys.nextLine();

        Player user = new Player(playerInput, "Wanderer");
        PlayerInventory inventory = new PlayerInventory();

        // #endregion Starting Info
        // Game start
        while (gameState) {

            // #region StartGame
            // Krymn
            // ! While(player is at Krymn)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 0) {
                user.setHealth(user.getTotalHealth());
                goblinDead = false;
                wolfDead = false;
                System.out.println(
                        "\nYou are in the village of Krynn. You are able to go in these directions:\n Travel North, to the nearby town of Pultineer, a bustling town where trade is heavy.\n\n Travel East, to the Dark Forest, where monsters roam and mysterious people wander.\n\n Travel South, to the Badlands, with it's intense heat, it is not for the feint of heart.\n\n Or, travel West, to the Heralds Hills, where those who have been deemed worthy by the Church of Pultineer can prove themvselves in this challenge. \n");

                playerInput = keys.nextLine();
                // #region testing
                // & Testing
                // ? Rank up Testing
                if (playerInput.toLowerCase().equals("xoc")) {
                    rankUp(2, user);
                    System.out.println("Cheat activate. You are now a " + user.getRank());
                }
                // ? Shop Testing
                if (playerInput.toLowerCase().equals("midas")) {
                    user.setGold(100);
                    System.out.println("Cheat activate. You now have " + user.getGold() + " gold");
                }
                // ? Attack Buff
                if (playerInput.toLowerCase().equals("barbarian")) {
                    user.setAttack(100);
                    System.out.println("Cheat activate. You now have " + user.getAttack() + " strength");
                }
                //? Quest accepting
                if (playerInput.toLowerCase().equals("quest")){
                    user.setQuestAccepted(true);
                    System.out.println("Cheat activate. You have now accepted a quest.");
                }
                // ? Placement testing
                if (playerInput.toLowerCase().equals("church")) {
                    user.setPlayerY(3);
                }
                if (playerInput.toLowerCase().equals("cottage")) {
                    user.setPlayerX(2);
                    user.setPlayerY(0);
                }
                
                // #endregion

                switch (get4Direction(playerInput, user, "north", "south", "east", "west")) {
                    // North
                    case 0:
                        movePlayer(0, 1, user);
                        break;
                    // South
                    case 1:
                        movePlayer(0, -1, user);
                        break;
                    // East
                    case 2:
                        movePlayer(1, 0, user);
                        break;
                    // West
                    case 3:
                        movePlayer(-1, 0, user);
                        break;
                    // Invalid
                    default:
                        System.out.println("\nInvalid direction. Please try again.");
                        break;
                }

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 0) || !(user.getPlayerY() == 0)) {
                    break;
                }
            } // End of while(at Starting Locaation)
              // #endregion StartGame

            checkGameState(keys, gameState);

            // #region East
            // Dark Forest
            // ! While (player is at Dark Forest)
            while (user.getPlayerX() == 1 && user.getPlayerY() == 0) {

                DarkForest darkForest = new DarkForest();

                // Create enemy and start battle
                if (goblinDead == false) {

                    Enemy goblin = new Enemy(5, 2, 0, 2, 5, 2, "Hungry Goblin");

                    System.out.println(darkForest.getMessage());

                    Functions.delay(2000);
                    // Battle
                    battleState = true;
                    gameState = Battle.battle(playerInput, battleState, gameState, goblin, user, keys, rnd, 10, 3);

                    if (gameState == false) {
                        break;
                    }
                    if (goblin.getHealth() <= 0) {
                        goblinDead = true;
                    } else {
                        System.out.println("\nYou ran away from the goblin. You return to Krymn to rest.");
                        movePlayer(-1, 0, user);
                        break;
                    }
                }

                System.out.println(
                        "\nYou defeated the goblin! You can continue going forward through the forest, or go back to the village and \nrest. But the monsters might come back.\n");
                playerInput = keys.nextLine();
                get2Direction(playerInput, user, false, "continue", "back");

                if (user.getPlayerX() == 0) {
                    System.out.println("\nYou return to the village and rest.");
                } else if (user.getPlayerX() == 2) {
                    System.out.println("\nYou continue through the forest.");
                }

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 1)) {
                    break;
                }

            } // End of while(at Dark Forest)

            checkGameState(keys, gameState);

            // Forest Cottage
            // ! While(player is at Forest Cottage)
            while (user.getPlayerX() == 2 && user.getPlayerY() == 0) {
                ForestCottage forestCottage = new ForestCottage();
                System.out.println(forestCottage.getMessage());
                playerInput = keys.nextLine();
                switch (get4Direction(playerInput, user, "sneak", "knock", backAnswers, contAnswers)) {

                    // Sneak around
                    case 1:
                        while (true) {
                            // Sneak around dialog
                            forestCottage.sneakAround(user);
                            playerInput = keys.nextLine();

                            // Back
                            for (int x = 0; x < backAnswers.length; x++) {
                                if (playerInput.toLowerCase().contains(backAnswers[x])) {
                                    break;
                                }
                            }
                            // Continue to Dire Wolf
                            if (playerInput.toLowerCase().contains("path")) {
                                movePlayer(1, 0, user);
                                break;
                            } else {
                                System.out.println("\nIncorrect Choice. Please choose again.(Path/Back)");
                            }

                        }
                        break;
                    // Knock at the door
                    case 2:
                        forestCottage.knockAtDoor(user);
                        forestCottage.wizardConversation(user);
                        break;
                    // Turn back around
                    case 3:
                        System.out.println(forestCottage.turnBack());
                        movePlayer(-1, 0, user);
                        break;
                    // Continue through the forest
                    case 4:
                        System.out.println("\nYou continue through the forest.");
                        movePlayer(0, 1, user);
                        break;
                    default:
                        System.out.println("\nIncorrect Choice. Please choose again.(Sneak, Knock, or Back)");
                }

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 2)) {
                    break;
                }
            } // End of while(at Forest Cottage)

            checkGameState(keys, gameState);

            // HoleInTheWall
            // ! While(player is at HoleInTheWall)
            while (user.getPlayerX() == 2 && user.getPlayerY() == 1) {
                HoleWall holeInTheWall = new HoleWall();

                System.out.println(holeInTheWall.getMessage());
                playerInput = keys.nextLine();

                // Squeeze through hole
                if (playerInput.toLowerCase().contains("squeeze")) {
                    System.out.println(
                            "\nYou squeeze through the hole and find yourself near the Shopping district of the town.");
                    user.setGoneThroughHole(true);
                    movePlayer(-2, 1, user);

                }
                // Turn Back
                for (int x = 0; x < backAnswers.length; x++) {
                    if (playerInput.toLowerCase().contains(backAnswers[x])) {
                        System.out.println("\nYou turn back and head to a smoke stack in the distance.");
                        user.setPlayerY(0);
                    }
                    if (!(playerInput.toLowerCase().contains(backAnswers[0]))
                            && !(playerInput.toLowerCase().contains(backAnswers[1]))
                            && !(playerInput.toLowerCase().contains(backAnswers[2]))
                            && !(playerInput.toLowerCase().contains("squeeze"))) {
                        // Invalid
                        System.out.println("\nInvalid input. Please try again.(Hole/Back)");
                    }
                } // End of Turn Back

            } // End of HoleInTheWall

            checkGameState(keys, gameState);

            // Darker Forest
            // ! While(player is at Darker Forest)
            while (user.getPlayerX() == 3 && user.getPlayerY() == 0) {
                DarkerForest darkerForest = new DarkerForest();

                //Wolf Battle
                // ! If user is not a disciple and hasnt accepted a quest
                if (!(user.getRank().toLowerCase().contains("disciple")) && (user.getQuestAccepted() == false)) {
                    if (wolfDead == false) {

                        System.out.println(darkerForest.getMessage());

                        // Create enemy and start battle
                        Enemy direWolf = new Enemy(10, 3, 0, 5, 15, 5, "Dire Wolf");

                        // Battle
                        battleState = true;
                        gameState = Battle.battle(playerInput, battleState, gameState, direWolf, user, keys, rnd, 2, 1);
                        if (gameState == false) {
                            break;
                        }

                        System.out.println(
                                "\nYou defeated the Dire Wolf! There doesn't seem like any other path forward, so you \nare forced to turn back.\n");
                        wolfDead = true;
                        movePlayer(-1, 0, user);
                        break;
                    } // End of if(wolfDead == false)
                }
                //Goblin Horde battle
                // ! If user is a disciple and has accepted the quest
                else if ((user.getRank().toLowerCase().contains("disciple")) && (user.getQuestAccepted() == true)) {
                    System.out.println(
                            "\nYou walk about the forest and hear a goblin rustling in a bush. When you approach the goblin to attack, four more jump \nout at you!");
                    // #region Enemy Horde
                    Enemy goblin1 = new Enemy(25, 10, 5, 4, 6, 3, "Goblin");
                    Enemy goblin2 = new Enemy(25, 10, 5, 4, 6, 3, "Goblin");
                    Enemy goblin3 = new Enemy(25, 10, 5, 4, 6, 3, "Goblin");
                    Enemy goblin4 = new Enemy(25, 10, 5, 4, 6, 3, "Goblin");
                    Enemy goblin5 = new Enemy(25, 10, 5, 4, 6, 3, "Goblin");
                    Enemy[] enemies = new Enemy[5];
                    enemies[0] = goblin1;
                    enemies[1] = goblin2;
                    enemies[2] = goblin3;
                    enemies[3] = goblin4;
                    enemies[4] = goblin5;
                    // #endregion Enemy Horde

                    //Battle
                    battleState = true;
                    gameState = Battle.multiBattle(playerInput, battleState, gameState, enemies, user, keys, rnd, 5);
                    if (gameState == false) {
                        break;
                    }
                } else {
                    System.out.println(
                            "\nYou have already defeated the Dire Wolf. There doesn't seem like any other path forward, so you \nare forced to turn back.\n");
                    movePlayer(-1, 0, user);
                    break;
                }

            } // End of while(player at Darker Forest)
              // #endregion East

            checkGameState(keys, gameState);

            // #region South
            // Badlands
            // ! While(player is at Badlands)
            while (user.getPlayerX() == 0 && user.getPlayerY() == -1) {
                Badlands badlands = new Badlands();
                System.out.println(badlands.getMessage());
                movePlayer(0, 1, user);
                break;
            } // End of while(player is at Badlands)
              // #endregion South

            checkGameState(keys, gameState);

            // #region North
            // Town
            // ! While(player is at Town)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 1) {
                // Create Town object
                Town town = new Town();
                System.out.println(town.getMessage());
                town.guardConversation(playerInput, keys, user);

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 0) || !(user.getPlayerY() == 1)) {
                    break;
                }

            } // End of while(player is at Town)

            checkGameState(keys, gameState);

            // Shopping District
            // ! While(player is at Shopping District)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 2) {
                ShoppingDistrict shoppingDistrict = new ShoppingDistrict();

                shoppingDistrict.merchantConversation(playerInput, keys, user);

                // Kicks player back to village
                if (user.getPlayerY() != 2 || user.getPlayerX() != 0) {
                    break;
                }

                playerInput = keys.nextLine();

                boolean keepShopping = true;

                // Buying items from the merchant
                do {

                    // Wooden Sword
                    if (playerInput.toLowerCase()
                            .contains(townShopGoods[0][0].toLowerCase())) {
                        storeCost = Integer.parseInt(townShopGoods[0][1]);
                        if (user.getGold() >= storeCost) {
                            user.setGold(user.getGold() - storeCost);

                            // Equips armor and adds the defense buff to player
                            inventory.setEquipedWeapon("Wooden Sword");
                            inventory.setWeaponValue(5);
                            user.equipWeapon(inventory, user);
                            System.out.println(
                                    "\n\nYou equipd the Wooden Sword. Swinging it around, you feel you can take on stronger monsters.(Attack increased by "
                                            + inventory.getWeaponValue() + ")");

                        } else {
                            System.out.println("\nYou do not have enough gold to purchase this item.");
                        }
                    } // End of Wooden Sword

                    // Leather armor
                    else if (playerInput.toLowerCase()
                            .equals(townShopGoods[1][0].toLowerCase())) {
                        storeCost = Integer.parseInt(townShopGoods[1][1]);
                        if (user.getGold() >= storeCost) {
                            user.setGold(user.getGold() - storeCost);

                            // Equips armor and adds the defense buff to player
                            inventory.setEquipedArmor(townShopGoods[1][0]);
                            inventory.setArmorValue(Integer.parseInt(townShopGoods[1][2]));
                            user.equipArmor(inventory, user);
                            System.out.println(
                                    "\nYou equipd the leather armor. You feel better protected.(Defense increased by "
                                            + inventory.getArmorValue() + ")");

                        } else {
                            System.out.println("You do not have enough gold to purchase this item.");
                        }
                    } // End of Leather Armor
                      // Health Potion
                    else if (playerInput.toLowerCase()
                            .equals(townShopGoods[2][0].toLowerCase())) {
                        storeCost = Integer.parseInt(townShopGoods[2][1]);
                        if (user.getGold() >= storeCost) {
                            user.setGold(user.getGold() - storeCost);

                            // Drink potion and add buff to player
                            user.setTotalHealth(user.getTotalHealth() + Integer.parseInt(townShopGoods[2][2]));
                            System.out.println(
                                    "\nYou drink the potion and feel a surge of energy. Your health has increased by "
                                            + townShopGoods[2][2] + " points.");

                        } else {
                            System.out.println("You do not have enough gold to purchase this item.");
                        }
                    } // End of Health Potion

                    // Leave
                    else if (playerInput.toLowerCase().contains("exit")) {
                        keepShopping = false;
                        break;
                    }
                    // Invalid Selection
                    else {
                        System.out.println("Item not found.");
                    }
                    if (keepShopping) {
                        System.out.println("\n\nMerchant: Would you like to buy something else?(yes/no)");
                        playerInput = keys.nextLine();
                        if (playerInput.toLowerCase().contains("no")) {
                            keepShopping = false;
                            break;
                        }
                    }

                    shoppingDistrict.printGoods();
                    System.out.println("(EXIT to leave)");

                    System.out.println("\n You have " + user.getGold() + " gold.");
                    playerInput = keys.nextLine();
                } while (keepShopping);// End of do-while(keepShopping)
                // End of Merchant sequence

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 0) || !(user.getPlayerY() == 2)) {
                    break;
                }
            } // End of while(user.getPlayerX() == 0 && user.getPlayerY() == 2)

            checkGameState(keys, gameState);

            // Church District
            // ! While(player is at Church District)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 3) {
                ChurchDistrict churchDistrict = new ChurchDistrict();
                churchDistrict.changeMessage(user);
                System.out.println("\n" + churchDistrict.getMessage());

                playerInput = keys.nextLine();

                // ! If player hasnt unlocked job board
                if (!user.getBoardUnlocked()) {
                    switch (get3Direction(playerInput, "pray", "talk", "leave")) {
                        // Pray
                        case 0:
                            //^ Wanderer Rankup
                            if (user.getRank().toLowerCase().contains("wanderer")) {
                                System.out.println(
                                        "\nYou kneel at the alter and pray for a few minutes before standing up to leave. As you turn around you see the priest \nstanding before you. He says to you\n\nPriest: \"I see that you have substantial talent. Follow me inside.\"\n");

                                try {
                                    Thread.sleep(5000);
                                } catch (Exception e) {
                                    System.out.println("Error: " + e);
                                }

                                System.out.println(
                                        "You follow the priest into the church as he begins to speak to you\n");

                                try {
                                    Thread.sleep(2500);
                                } catch (Exception e) {
                                    System.out.println("Error: " + e);
                                }

                                while (!(playerInput.toLowerCase().contains("no"))
                                        && !(playerInput.toLowerCase().contains("yes"))) {
                                    System.out.println(
                                            "Priest: \"You show enormous potential, not only in your combative abilities, but in your faith as well. I would like \nto invite you to become a follower of Eryndros.\"\n");

                                    playerInput = keys.nextLine();

                                    if (playerInput.toLowerCase().contains("yes")) {
                                        System.out.println("Priest: \"Excellent. You have made the right choice.\"\n");
                                        rankUp(1, user);
                                    } else if (playerInput.toLowerCase().contains("no")) {
                                        System.out.println(
                                                "Priest: \"I understand. You have your own path to follow.\"\n");
                                    } else {
                                        System.out.println("\nPriest: \"I'm sorry, I didn't catch that.\"(yes/no)\n");
                                    }
                                } // End of while(playerInput != yes or no)
                            } else if (user.getRank() != "wanderer") {
                                System.out.println(
                                        "\nThe Priest walks up to you and says:\n\nPriest: \"Oh! I'm glad to see you're back! I Hope you have been well my child.\"\n He then walks away.");
                                Functions.delay(1500);
                                System.out.println(
                                        "You step up to the alter and kneel. You silently murmur a quick prayer as you feel your devotion to Eryndos grow.");
                            }
                            break;
                        // Talk
                        case 1:
                            // ! If player is the wanderer rank
                            if ((user.getRank().toLowerCase().contains("wanderer"))
                                    || user.getQuestAccepted() == true && user.getHasQuestItem() == false) {
                                System.out.println("\nThe Priest seems busy now. You can't talk to him.");
                            }
                            //^Board Unlock
                            // ! If player is the disciple rank and hasnt accepted the quest
                            else if ((user.getRank().toLowerCase().contains("disciple"))
                                    && (user.getQuestAccepted() == false)) {
                                System.out.println("\nThe priest speaks to you:\n\nPriest: \"Ah! " + user.getName()
                                        + ", I'm glad to see that you're back. There are many things that I need done, and I recently had this \nboard installed to post church events. Instead, I think I will be using it as a local job board for the things \nthat I need done. When you want, feel free to check it out and help us here at the church!\"");
                                        user.setBoardUnlocked(true);
                                        Functions.delay(3000);
                            }
                            //^ Follower Quest
                            // ! If the player is the follower rank and hasnt accepted the quest yet
                            else if (user.getRank().toLowerCase().contains("follower")
                                    && user.getQuestAccepted() == false) {
                                // ! While playerinput != yes or no
                                while (!(playerInput.toLowerCase().contains("yes"))
                                        && !(playerInput.toLowerCase().contains("no"))) {
                                    System.out.println("\nPriest: \"Ah! " + user.getName()
                                            + ", just who I was looking for. I need you to visit the potion maker in the Dark Forest. Let him know that I \nsent you and he will give you a potion that I need for an upcoming ceremony.\"\n");
                                    playerInput = keys.nextLine();

                                    // Yes
                                    if (playerInput.toLowerCase().contains("yes")) {
                                        System.out.println(
                                                "\nPriest: \"Great! I'm glad to hear it. When you have the potion, just bring it back to me\"");
                                        user.setQuestAccepted(true);
                                    }
                                    // No
                                    else if (playerInput.toLowerCase().contains("no")) {
                                        System.out.println(
                                                "\nPriest: \"Ah, I see. Well then if you change your mind come and let me know.\"");
                                    }
                                    // Other input
                                    else {
                                        System.out.println("\nPriest: \"I'm sorry what was that?\"(yes/no)");
                                    }
                                } // End of while playerinput != yes or no
                            } // ! End of if (userRank is follower and user hasnt accepted quest)
                            //^ Follower Rankup
                            // ! If the player is the follower rank and has the quest item
                            else if (user.getRank().toLowerCase().contains("follower")
                                    && user.getQuestAccepted() == true && user.getHasQuestItem() == true) {
                                System.out.println(
                                        "\nPriest: \"Ah! You have my potion. Much thanks for you.\nThe Priest takes the potion from you\"\n");
                                        Functions.delay(1500);
                                returnQuestItems(user);
                                rankUp(2, user);
                            }
                            break;
                        // Leave
                        case 2:
                            System.out.println("\nYou turn back around and head to the shopping district.");
                            movePlayer(0, -1, user);
                            break;
                        // Invalid
                        case 3:
                            System.out.println("\nInvalid direction, please try again.(Pray/Talk/Leave)");
                    }// End of get3Direction switch
                } // End of if(!user.getBoardUnlocked)

                // ! If player unlocked job board
                else if (user.getBoardUnlocked()) {
                    switch (get4Direction(playerInput, user, "pray", "talk", "board", "leave")) {
                        //Pray
                        case 0:
                            System.out.println(
                                    "\nThe Priest walks up to you and says:\n\nPriest: \"Oh! I'm glad to see you're back! I Hope you have been well my child. Unfortunately, I cannot talk \nfor long, I hope you have been well!\"\n He then walks away.");
                            Functions.delay(1500);
                            System.out.println(
                                    "You step up to the alter and kneel. You silently murmur a quick prayer as you feel your devotion to Eryndos grow.");
                            break;

                        //Talk
                        case 1:
                            System.out.println("\nCurrently, the priest is too busy with church matters to talk");
                            break;
                        
                        //Board
                        case 2:
                            String rank = user.getRank().toLowerCase();
                            //^ Disciple Quest
                            if(rank.contains("disciple")){
                                //! While playerInput isnt yes and isnt no
                                while((!playerInput.toLowerCase().contains("yes")) && (!playerInput.toLowerCase().contains("no"))){
                                System.out.println("\nYou approach the new job board to see some of the tasks. One in particular catches your eye. A horde of \ngoblins has been reported in the Dark Forest. When you read it, you hope Gherald is okay.\nAccept the quest?(yes/no)");
                                playerInput = keys.nextLine();
                                if(playerInput.toLowerCase().contains("yes")){
                                    System.out.println("\nYou accept the quest as you tear the posting off the board.");
                                    user.setQuestAccepted(true);
                                }
                                else if(playerInput.toLowerCase().contains("no")){
                                    System.out.println("\nYou stare at the paper for a few extra seconds before walking away from the board.");
                                }
                                }//End of while(playerInput isnt yes or no)
                            }
                            break;
                        
                        //Leave
                        case 3:
                            System.out.println("\nYou turn back around and head to the shopping district.");
                            movePlayer(0, -1, user);
                            break;
                        case 4:
                            System.out.println("\nInvalid Direction, please try again.(Pray/Talk/Board/Leave)");
                            break;
                    }//End of switch(get4Directions) 
                }
            }
            // #endregion North

            checkGameState(keys, gameState);

            // #region West
            // Heralds Hills
            // ! While(player is at Heralds Hills)
            while (user.getPlayerX() == -1 && user.getPlayerY() == 0) {

                String[] messages = {
                        "\nYou walk up the hills to find a guard. He stops you and says: \"This is the proving grounds of The Heralds. The high order of the Church of Pultineer. You are not strong enough to take on the trials. Come back later once \nyou are stronger.\" You are forced to leave and go back to the village.",
                        "\nYou walk up the hills to find a guard. He stops you and says: \"You have proven yourself worthy to take on the trials of The Heralds. You may enter.\" You walk past the guard and enter the proving grounds of The Heralds.",
                        "You walk up the hills to see the same guard that has always stood here. He greets you with more respect and professionalism than he did when you first took your trials." };

                HeraldsHills heraldsHills = new HeraldsHills(messages);

                // Cycle through greeting messages
                if (user.getRank().equals("General")) {
                    System.out.println(heraldsHills.getMessage(1));
                    movePlayer(-1, 0, user);
                    break;
                } else if (user.getRank().equals("Holy Knight") || user.getRank().equals("Holy Knight Champion")) {
                    System.out.println(heraldsHills.getMessage(2));
                    movePlayer(-1, 0, user);
                    break;
                } else {
                    System.out.println(heraldsHills.getMessage(0));
                    movePlayer(1, 0, user);
                    break;
                }
            } // End of while(user.getPlayerX() == -1 && user.getPlayerY() == 0)

            checkGameState(keys, gameState);

            // The Trials
            // ! While(player is at The Trials)
            while (user.getPlayerX() == -2 && user.getPlayerY() == 0) {
                TheTrials trials = new TheTrials();
                System.out.println(trials.getMessage());
                playerInput = keys.nextLine();
                get2Direction(playerInput, user, true, "follow", "continue");
            } // End of while(user.getPlayerX() == -2 && user.getPlayerY() == 0)
              // #endregion West

            checkGameState(keys, gameState);

        } // End of while(gameState)

        // Close any resources
        keys.close();
    }// End of Main
     // #endregion Main
}// End of Class