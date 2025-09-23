
//Imports
import java.io.File;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
 * This game was last updated in August 3rd, 2025
 * 
 * NOTE:
 * All Pseudocode is written in different colored comments that follows the indications of VSC extension:   
 * Colorful Comments developed by Parth Rastogi
 */

public class App {
    // #region Funcs and Methods

    // checkGameState
    public static void checkGameState(Scanner keys, boolean gameState) {
        // Functionality: Checks the gameState boolean variable and ends the program if
        // it is false
        if (gameState == false) {
            keys.close(); // replace 'keys' with your actual Scanner object
            System.exit(0); // end the program
        }
    }// End of checkGameState

    // Move player in one of four different directions
    public static int get4Direction(String playerInput, String keyword1, String keyword2, String keyword3,
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
        return -1;
    }// End of get4Direction

    // Move player in one of two different directions
    public static void get2Direction(String playerInput, Player user, boolean VorH, String keyword1, String keyword2) {
        // Functionality: Gets input from the player and moves them in accordance with
        // their choice
        // Y-axis movement
        if (VorH) {
            // forward
            if (playerInput.toLowerCase().contains(keyword1)) {
                Functions.movePlayer(0, 1, user);
            }
            // back
            else if (playerInput.toLowerCase().contains(keyword2)) {
                Functions.movePlayer(0, -1, user);
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
                Functions.movePlayer(1, 0, user);
            }
            // forward
            else if (playerInput.toLowerCase().contains(keyword2)) {
                Functions.movePlayer(-1, 0, user);
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
    public static void returnQuestItems(Player user) {
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

        String[] yesAnswers = { "yes", "okay", "alright", "accept" }, contAnswers = { "continue", "forward" },
                noAnswers = { "no", "nevermind", "deny" }, backAnswers = { "back", "return", "reverse", "leave" };

        // Battle and game state variables
        boolean gameState = true, battleState = false, wolfDead = false, goblinDead = false, hordeGoblinsKilled = false,
                firstRun = true, wolfOrGoblinQuest = false, comingFromTown = false, savedSoldier = true;

        int storeCost = 0, questEnemiesKilled = 0, shopReset = 0;

        // Class Variables

        Scanner keys = new Scanner(System.in);

        Random rnd = new Random();

        // Location Variables
        ForestCottage forestCottage = new ForestCottage();

        ShoppingDistrict shoppingDistrict = new ShoppingDistrict();

        // Inputs

        // User Startup

        // Method Testing

        // Introduction
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
        Functions.delay(1000);
        Functions.createSound(
                "Welcome to Pultineer! The text based adventure game where your goal is to become the Holy Knight Champion of ~\nthe city. As you travel through the lands of Krynn, you will face monsters and meet new people. Each choice~\nyou make will affect your journey. Find your way through Krynn and become the Holy Knight Champion of~\nPultineer!",
                1);
        Functions.createSound("\nGood luck, and may the gods smile upon you..", 1);

        System.out.println();

        // Player setup
        // Get name
        Functions.createSound("\nEnter your name: ", 1);
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

                if (firstRun == false) {
                    if (user.isHasHeatCloak() == false && user.getRank().toLowerCase().contains("squire")) {
                        Functions.delayText("As you take the usual path back to Krynn, you see a knight in the center of the village. as you approach, he ~\nbegins to speak to you.\n", 25);

                        Functions.delayText(
                                "\nKnight: \"Ah! So you must be my new squire, glad to have you! The name is Sir Branric Hollow.\"\n",
                                25);

                        Functions.delayText(
                                "\nSir Branric hands you a silvery colored cloak that the other knights and soldiers appear to be wearing. He ~\nsays to you: \n",
                                25);

                        Functions.delayText(
                                "\nSir Branric: \"Here, you'll need this Heat Cloak to be able to travel through the desert. When you're ready, ~\nmeet us there and we'll fight this demon beast!\"\n",
                                25);
                        user.setHasHeatCloak(true);
                    }
                    System.out.println("\nYou are in the village of Krynn. You are able to go in these directions:" + 
                    "\nTravel North, to the nearby town of Pultineer, a bustling town where trade is heavy." + "\n\nTravel East, to the Dark Forest, where monsters roam and mysterious people wander." + "\n\nTravel South, to the Badlands, with it's intense heat, it is not for the feint of heart." + "\n\nOr, travel West, to the Heralds Hills, where those who have been deemed worthy by the Church of Pultineer can \nprove themvselves in this challenge.\n");

                    playerInput = keys.nextLine();
                }
                while (firstRun) {
                    System.out.println("\nYou are in the village of Krynn. You are able to go in these directions:\nTravel North, to the nearby town of Pultineer, a bustling town where trade is heavy."
                                    +
                                    "\n\nTravel East, to the Dark Forest, where monsters roam and mysterious people wander."
                                    +
                                    "\n\nTravel South, to the Badlands, with it's intense heat, it is not for the feint of heart."
                                    +
                                    "\n\nOr, travel West, to the Heralds Hills, where those who have been deemed worthy by the Church of Pultineer \ncan prove themvselves in this challenge.\n");

                    playerInput = keys.nextLine();
                    firstRun = false;
                }

                // #region testing
                // & Testing
                // ? Rank up Testing
                if (playerInput.toLowerCase().equals("xoc")) {
                    int rankNum = 3;
                    if (rankNum >= 2) {
                        user.setBoardUnlocked(true);
                    }
                    Functions.rankUp(rankNum, user);
                    System.out.println("Cheat activate. You are now a " + user.getRank());
                }
                // ? Shop Testing
                else if (playerInput.toLowerCase().equals("midas")) {
                    user.setGold(user.getGold() + 100);
                    System.out.println("Cheat activate. You now have " + user.getGold() + " gold");
                }
                // ? Attack Buff
                else if (playerInput.toLowerCase().equals("barbarian")) {
                    user.setAttack(100);
                    System.out.println("Cheat activate. You now have " + user.getAttack() + " strength");
                }
                // ? Speed Buff
                else if (playerInput.toLowerCase().equals("hercules")) {
                    user.setAgility(1000);
                    System.out.println("Cheat activate. You now have " + user.getAgility() + " agility");
                }
                // ? Defense Buff
                else if(playerInput.toLowerCase().equals("tank")) {
                    user.setDefense(100);
                    System.out.println("Cheat active. You now have " + user.getDefense() + " defense");
                }
                // ? Quest accepting
                else if (playerInput.toLowerCase().equals("quest")) {
                    user.setQuestAccepted(true);
                    user.setHasQuestItem(true);
                    System.out.println("Cheat activate. You have now accepted a quest.");
                }
                // ? Placement testing
                else if (playerInput.toLowerCase().equals("church")) {
                    user.setPlayerY(3);
                } else if (playerInput.toLowerCase().equals("cottage")) {
                    user.setPlayerX(2);
                    user.setPlayerY(0);
                }
                // ? LVL 8
                else if (playerInput.toLowerCase().equals("wiseman")) {
                    user.setXp(311);
                    Battle.levelUp(user, playerInput, keys);
                }
                else if(playerInput.toLowerCase().equals("ultimate")){
                    user.setDefense(100);
                    user.setAgility(100);
                    user.setAttack(100);
                    int rankNum = 3;
                    if (rankNum >= 2) {
                        user.setBoardUnlocked(true);
                    }
                    Functions.rankUp(rankNum, user);
                    System.out.println("Cheat activate. You are now a " + user.getRank());
                    
                    System.out.println("Cheat Active. You are now an ultimate being.");
                }

                // #endregion

                switch (get4Direction(playerInput, "north", "south", "east", "west")) {
                    // North
                    case 0:
                        Functions.movePlayer(0, 1, user);
                        break;
                    // South
                    case 1:
                        Functions.movePlayer(0, -1, user);
                        break;
                    // East
                    case 2:
                        Functions.movePlayer(1, 0, user);
                        break;
                    // West
                    case 3:
                        Functions.movePlayer(-1, 0, user);
                        break;
                    // Invalid
                    default:
                        System.out.println("\nInvalid direction. Please try again.");
                        Functions.delay(1500);
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

                try {
                    String battleSound = "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Pultineer\\src\\Audio\\battleMusic.wav";

                    AudioInputStream battleMusic = AudioSystem.getAudioInputStream(new File(battleSound).getAbsoluteFile());

                    Clip music = AudioSystem.getClip();

                    music.open(battleMusic);
                    music.loop(Clip.LOOP_CONTINUOUSLY);
                    if (goblinDead == false) {

                    Enemy goblin = new Enemy(5, 2, 0, 2, 5, 2, "Hungry Goblin");

                    System.out.println(darkForest.getMessage());

                    Functions.delay(2000);
                    // Battle
                    battleState = true;
                    
                    music.start();
                    gameState = Battle.battle(playerInput, battleState, gameState, goblin, user, keys, rnd, 10, 3);
                    music.stop();
                    
                    if (gameState == false) {
                        break;
                    }
                    if (goblin.getHealth() <= 0) {
                        music.stop();
                        goblinDead = true;
                        if (user.getSideQuestAccepted()) {
                            if (wolfOrGoblinQuest) {
                                questEnemiesKilled++;
                                System.out.println("\nYou have killed " + questEnemiesKilled + " goblins. "
                                        + (10 - questEnemiesKilled) + " left to kill.");
                                if (questEnemiesKilled == 10) {
                                    System.out.println(
                                            "\nYou have completed the quest from the Church. Return to the board in the Church district of town to claim your\nreward. ");
                                    user.setSideQuestItem(true);
                                }
                            }
                        }
                    } else {
                        music.stop();
                        System.out.println("\nYou ran away from the goblin. You return to Krymn to rest.");
                        Functions.movePlayer(-1, 0, user);
                        break;
                    }
                    
                }
                music.close();
                } catch (Exception e) {
                }

                // Create enemy and start battle
                
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

                System.out.println(forestCottage.getMessage());
                playerInput = keys.nextLine();

                switch (get4Direction(playerInput, user, "sneak", "knock", backAnswers, contAnswers)) {

                    // Sneak around
                    case 1:
                        outer: while (true) {
                            // Sneak around dialog
                            forestCottage.sneakAround(user);
                            playerInput = keys.nextLine();

                            // Back
                            for (int x = 0; x < backAnswers.length; x++) {
                                if (playerInput.toLowerCase().contains(backAnswers[x])) {
                                    break outer;
                                }
                            }
                            // Continue to Dire Wolf
                            if (playerInput.toLowerCase().contains("path")) {
                                user.setHasSnuckAround(true);
                                Functions.movePlayer(1, 0, user);
                                break;
                            } else {
                                System.out.println("\nIncorrect Choice. Please choose again.(Path/Back)");
                                Functions.delay(1500);
                            }

                        }
                        break;
                    // Knock at the door
                    case 2:
                    if(shopReset == 1){
                        forestCottage.setShop(forestCottage.getOriginalShop());
                        shopReset = 0;
                    }
                        forestCottage.knockAtDoor(user);
                        forestCottage.wizardConversation(user);
                        break;
                    // Turn back around
                    case 3:
                        System.out.println(forestCottage.turnBack());
                        Functions.movePlayer(-1, 0, user);
                        break;
                    // Continue through the forest
                    case 4:
                        System.out.println("\nYou continue through the forest.");
                        Functions.movePlayer(0, 1, user);
                        user.setComingFromTown(false);
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
                String[] messages = {
                        "\nWhile wandering through the forest, you find a brick wall interrupting your path. Near the bottom of the wall, you see a small hole that seems like you might be able to squeeze through it.\n",
                        "\nAs you finish getting through the hole, you are met with the familliar sight of the \nforest." };
                HoleWall holeInTheWall = new HoleWall();
                holeInTheWall.setMessages(messages[0], 0);
                holeInTheWall.setMessages(messages[1], 1);

                // Coming from the forest
                if (user.getComingFromTown() == false) {
                    System.out.println(holeInTheWall.getMessage(0));
                    playerInput = keys.nextLine();
                    // Squeeze through hole
                    if (playerInput.toLowerCase().contains("squeeze")) {
                        System.out.println(
                                "\nYou squeeze through the hole and find yourself near the Shopping district of the town.");
                        user.setGoneThroughHole(true);
                        Functions.movePlayer(-2, 1, user);

                    }
                    // Turn Back
                    else if (Functions.checkArray(backAnswers, playerInput)) {
                        System.out.println("\nYou turn back and head to the smoke stack in the distance.");
                        Functions.movePlayer(0, -1, user);
                    }
                    // Invalid input
                    else if (!(Functions.checkArray(backAnswers, playerInput))
                            && !(playerInput.toLowerCase().contains("squeeze"))) {
                        System.out.println("\nInvalid input. Please try again.(Squeeze/Back)");
                    }

                }
                // Coming from town
                else if (user.getComingFromTown()) {
                    System.out.println(holeInTheWall.getMessage(1));
                    playerInput = keys.nextLine();

                    // Continue
                    if (playerInput.toLowerCase().contains("continue")) {
                        System.out.println("\nYou head toards the smoke stack in the distance.");
                        Functions.movePlayer(0, -1, user);
                    }

                    // Back
                    else if (playerInput.toLowerCase().contains("squeeze")
                            || playerInput.toLowerCase().contains("back")) {
                        System.out.println(
                                "\nYou squeeze back through the hole and find yourself near the Shopping district of the town.");
                        user.setGoneThroughHole(true);
                        Functions.movePlayer(-2, 1, user);

                    }

                    // Invalid input
                    else {
                        System.out.println("\nInvalid input. Please try again.(Squeeze/continue)");
                    }
                }

            } // End of HoleInTheWall

            checkGameState(keys, gameState);

            // Darker Forest
            // ! While(player is at Darker Forest)
            while (user.getPlayerX() == 3 && user.getPlayerY() == 0) {
                DarkerForest darkerForest = new DarkerForest();

                // Goblin Horde battle
                // ! If user is a disciple and has accepted the quest
                if ((user.getRank().toLowerCase().contains("disciple")) && (user.getQuestAccepted() == true)) {
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

                    // Battle
                    battleState = true;
                    gameState = Battle.multiBattle(playerInput, battleState, gameState, enemies, user, keys, rnd, 5);
                    if (gameState == false) {
                        break;
                    } else if (user.getHasQuestItem()) {
                        hordeGoblinsKilled = true;

                        System.out.println(
                                "You turn back towards the cottage, having 5 goblin ears in hand as proof of completion of the quest.");
                                Functions.movePlayer(-1, 0, user);
                    } else {
                        System.out.println(
                                "To save yourself, you ran away from the goblins. Luckily, they don't give chase and you make it back to Gherald's cottage");
                    }
                } // End of Horde Battle
                  // Wolf Battle
                else if (wolfDead == false) {

                    System.out.println(darkerForest.getMessage());

                    // Create enemy and start battle
                    Enemy direWolf = new Enemy(10, 3, 0, 5, 15, 5, "Dire Wolf");

                    // Battle
                    battleState = true;
                    gameState = Battle.battle(playerInput, battleState, gameState, direWolf, user, keys, rnd, 2, 1);
                    if (gameState == false) {
                        break;
                    }

                    if (direWolf.getHealth() <= 0) {
                        System.out.println(
                                "\nYou defeated the Dire Wolf! There doesn't seem like any other path forward, so you \nare forced to turn back.\n");
                        wolfDead = true;
                        if (user.getSideQuestAccepted()) {
                            if (wolfOrGoblinQuest == false) {
                                questEnemiesKilled++;
                                System.out.println(
                                        "Before you leave, you cut the tail off the dire wolf as proof that you have killed it.");
                                if (questEnemiesKilled >= 5) {
                                    System.out.println(
                                            "\nYou have completed the quest from the Church. Return to the board in the Church district of town with the five \nwolf tails to claim your reward. ");
                                    user.setSideQuestItem(true);
                                }
                            }
                        }
                    } else {
                        System.out.println("Despite the wolf's speed, you manage to get away from it.");
                    }
                    Functions.movePlayer(-1, 0, user);
                    break;
                } // End of if(wolfDead == false)
                else {
                    System.out.println(
                            "\nYou have already defeated the Dire Wolf. There doesn't seem like any other path forward, so you \nare forced to turn back.\n");
                    Functions.movePlayer(-1, 0, user);
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
                badlands.changeMessage(user);
                System.out.println(badlands.getMessage());

                //Move the player back to town if they don't have the heat cloak
                if(user.isHasHeatCloak() == false){
                Functions.movePlayer(0, 1, user);
                break;
                }

                System.out.println("\nSir Branric: \"Ah! Good to have you join us! From here on out, it's just us. We have with us a total of seven \nsoldiers, not quite up to my skill, but they're good people. Follow us through the Badlands and we'll make it \nto this demon beast as fast as lightning!\"\nBranric finishes speaking with a hearty chuckle of laughter and walks towards the rest of the soldiers.");

                System.out.println("\nYou're not quite sure what this \'Demon Beast\' is that Sir Branric is refering to, but if you adventure out \ninto the Badlands, you'll probably find out what he means. \nWill you follow Sir Branric, Or will you go back to the village?(Warning: You will not be able to return!)\n");

                playerInput = keys.nextLine();

                while(true){
                if(playerInput.toLowerCase().contains("follow")){
                    System.out.println("\nYou follow Sir Branric and the soldiers deeper into the Badlands.");
                    Functions.movePlayer(0, -1, user);
                    break;
                }
                else if(playerInput.toLowerCase().contains("back")){
                    System.out.println("You follow the road back to the village. Returning to the familiar houses and farms you rest for a bit.");
                    Functions.movePlayer(0, 1, user);
                    break;
                }
                else{
                    System.out.println("\nInavlid input. Please try again.(Follow/Back)");
                    System.out.println("\nYou're not quite sure what this \'Demon Beast\' is that Sir Branric is refering to, but if you adventure out \ninto the Badlands, you'll probably find out what he means. \nWill you follow Sir Branric, Or will you go back to the village?(Warning: You will not be able to return!)\n");
                    playerInput = keys.nextLine();
                }
            }

                break;
            } // End of while(player is at Badlands)
            
            checkGameState(keys, gameState);

            // SandPit
            while(user.getPlayerX() == 0 && user.getPlayerY() == -2){
                SandPit sandPit = new SandPit();
                System.out.println(sandPit.getMessage());
                System.out.println("\nSoldier: \"Help! Help me! Sir Branric!\"\nYou turn to see a soldier waist deep in quicksand. Knowing that you could possibly get there before Sir \nBranric, do you try to save the soldier, knowing you could possibly get stuck yourself?\n");

                playerInput = keys.nextLine();

                while(true){
                    //Saves the soldier
                    if(playerInput.toLowerCase().contains("yes")){
                        int agi = user.getAgility();

                        if(agi < 5){
                            System.out.println("\nAs you run the the solider, you pull him out before he is devoured by the quicksand. However, as the soldier\nis freed, you fall in yourself! With Sir Branric already on his way to you, you manage to get out faster than\nthe soldier did.(-10 health)");
                            user.setHealth(user.getHealth() - 10);
                        }

                        if(agi >= 5){
                            System.out.println("\nYou rush towards the soldier and quickly pull him out of the quicksand.");
                        }

                        savedSoldier = true;
                        break;
                    }
                    //Doesnt save the soldier
                    else if(playerInput.toLowerCase().contains("no")){

                        System.out.println("\nJust like all the other soldiers, you standby and watch as Sir Branric sprints over towards the soldier and\npull him out of the quicksand.");

                        savedSoldier = false;
                        break;

                    }
                    //Invalid Input
                    else{
                        System.out.println("\nInvalid input. Please try again.(Yes/no)");
                        System.out.println("Do you run to save the soldier from the quicksand, risking being caught in it yourself?\n");
                        playerInput = keys.nextLine();
                    }
                    
                }//End of while(true)

                System.out.println("After the soldier is rescued from the quicksand, the whole group takes a moment to rest. Afterwards everyone continues onward.");

                Functions.movePlayer(0, -1, user);
                break;

            }//End of SandPit

            //DistractionFight
            while(user.getPlayerX() == 0 && user.getPlayerY() == -3){
                Distraction dFight = new Distraction();
                System.out.println(dFight.getMessage());

                System.out.println("\nSir Branric: \"We're too close to the Demon Beast! Three of you, split off and deal with those gecklins!\n");

                //Team
                Npc thanlin = new Npc(rnd.nextInt(5) + 25, rnd.nextInt(10) + 5, 8, 4, "Thanlin");
                Npc ordeka = new Npc(rnd.nextInt(10) + 20, rnd.nextInt(5) + 10, 8, 4, "Ordeka");

                Npc[] team = {thanlin, ordeka};

                Enemy gecklin1 = new Enemy(45, 20, 12, 7, 25, 10, "Gecklin");
                Enemy gecklin2 = new Enemy(45, 20, 12, 7, 25, 10, "Gecklin");
                Enemy gecklin3 = new Enemy(45, 20, 12, 7, 25, 10, "Gecklin");

                Enemy[] enemies = {gecklin1, gecklin2, gecklin3};

                battleState = true;
                gameState = Battle.teamBattle(playerInput, battleState, gameState, enemies, user, team, keys, rnd, 3, 2, "soldier");

                if(user.getHealth() <= 0){
                    System.out.println("Game Over.");
                    System.exit(0);
                }
                break;

            }//End of distractionFight
            // #endregion South

            checkGameState(keys, gameState);

            // #region North
            // Town
            // ! While(player is at Town)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 1) {
                // Create Town object
                Town town = new Town();
                System.out.println(town.getMessage());
                Functions.delay(2000);

                town.guardConversation(playerInput, keys, user);
                Functions.delay(2500);

                // Kicks out of while loop if player moves
                if (!(user.getPlayerX() == 0) || !(user.getPlayerY() == 1)) {
                    break;
                }

            } // End of while(player is at Town)

            checkGameState(keys, gameState);

            // Shopping District
            // ! While(player is at Shopping District)
            while (user.getPlayerX() == 0 && user.getPlayerY() == 2) {

                shoppingDistrict.merchantConversation(playerInput, keys, user, inventory);

                // Kicks player back to village
                if (user.getPlayerY() != 2 || user.getPlayerX() != 0) {
                    break;
                }

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
                            // ^ Wanderer Rankup -> Follower
                            if (user.getRank().toLowerCase().contains("wanderer")) {
                                System.out.println(
                                        "\nYou kneel at the alter and pray for a few minutes before standing up to leave. As you turn around you see the Priest \nstanding before you. He says to you\n\nPriest: \"I see that you have substantial talent. Follow me inside.\"\n");

                                Functions.delay(5000);

                                System.out.println(
                                        "You follow the Priest into the church as he begins to speak to you\n");

                                Functions.delay(2500);

                                while ((!Functions.checkArray(yesAnswers, playerInput))
                                        && (!Functions.checkArray(noAnswers, playerInput))) {
                                    System.out.println(
                                            "Priest: \"You show enormous potential, not only in your combative abilities, but in your faith as well. I would like \nto invite you to become a follower of Eryndros.\"\n");

                                    playerInput = keys.nextLine();

                                    switch (Functions.yesOrNo(playerInput)) {
                                        // Yes
                                        case 0:
                                            System.out.println(
                                                    "\nPriest: \"Excellent. You have made the right choice.\"\n");
                                            Functions.rankUp(1, user);
                                            break;
                                        // No
                                        case 1:
                                            System.out.println(
                                                    "\nPriest: \"I understand. You have your own path to follow.\"");
                                            break;
                                        // Invalid
                                        default:
                                            System.out.println(
                                                    "\nPriest: \"I'm sorry, I didn't quite catch that.\"(okay/no)\n");
                                            break;
                                    }// End of switch Functions.yesOrNo
                                } // End of while(playerInput != yes or no)
                            } // End of Wanderer rankup
                              // If player isnt wanderer
                            else if ((!user.getRank().equals("wanderer"))) {
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
                            // ^Board Unlock
                            // ! If player is the disciple rank and hasnt accepted the quest
                            else if ((user.getRank().toLowerCase().contains("disciple"))
                                    && (user.getQuestAccepted() == false)) {
                                System.out.println("\nThe Priest speaks to you:\n\nPriest: \"Ah! " + user.getName()
                                        + ", I'm glad to see that you're back. There are many things that I need done, and I recently had this \nboard installed to post church events. Instead, I think I will be using it as a local job board for the things \nthat I need done. When you want, feel free to check it out and help us here at the church!\"");
                                user.setBoardUnlocked(true);
                                Functions.delay(3000);
                            }
                            // ^ Follower Quest
                            // ! If the player is the follower rank and hasnt accepted the quest yet
                            else if (user.getRank().toLowerCase().contains("follower")
                                    && user.getQuestAccepted() == false) {
                                // ! While playerinput != yes or no
                                while ((!Functions.checkArray(yesAnswers, playerInput))
                                        && (!Functions.checkArray(noAnswers, playerInput))) {
                                    System.out.println("\nPriest: \"Ah! " + user.getName()
                                            + ", just who I was looking for. I need you to visit the potion maker in the Dark Forest. Let him know that I \nsent you and he will give you a potion that I need for an upcoming ceremony.\"\n");
                                    playerInput = keys.nextLine();

                                    switch (Functions.yesOrNo(playerInput)) {
                                        // Yes
                                        case 0:
                                            System.out.println(
                                                    "\nPriest: \"Great! I'm glad to hear it. When you have the potion, just bring it back to me\"\n\nThe Priest then turns around and leaves.");
                                            user.setQuestAccepted(true);
                                            break;

                                        // No
                                        case 1:
                                            System.out.println(
                                                    "\nPriest: \"Ah, I see. Well then if you change your mind come and let me know.\"");
                                            break;

                                        // Invalid
                                        default:
                                            System.out.println("\nPriest: \"I'm sorry, what was that?\"(okay/no)");
                                    }// End of switch(Functions.yesOrNo)
                                } // End of while playerinput != yes or no
                            } // ! End of if (userRank is follower and user hasnt accepted quest)
                              // ^ Follower Rankup -> Disciple
                              // ! If the player is the follower rank and has the quest item
                            else if (user.getRank().toLowerCase().contains("follower")
                                    && user.getQuestAccepted() == true && user.getHasQuestItem() == true) {
                                System.out.println(
                                        "\nPriest: \"Ah! You have my potion. Much thanks for you.\"\nThe Priest takes the potion from you as he hands you three gold coins\n");
                                user.setHasQuestItem(false);
                                user.setGold(user.getGold() + 3);
                                Functions.delay(1500);
                                returnQuestItems(user);
                                Functions.rankUp(2, user);
                                // Code to reset the Gherald's shop
                                // shopReset = 1;
                            }
                            break;
                        // Leave
                        case 2:
                            System.out.println("\nYou turn back around and head to the shopping district.");
                            Functions.movePlayer(0, -1, user);
                            break;
                        // Invalid
                        case 3:
                            System.out.println("\nInvalid direction, please try again.(Pray/Talk/Leave)");
                    }// End of get3Direction switch
                } // End of if(!user.getBoardUnlocked)

                // ! If player unlocked job board
                else if (user.getBoardUnlocked()) {
                    // ! If player is a disciple
                    if (user.getRank().equals("Disciple")) {
                        switch (Functions.get4Direction(playerInput, "pray", "talk", "board", backAnswers)) {
                            // Pray
                            case 0:
                                System.out.println(
                                        "\nThe Priest walks up to you and says:\n\nPriest: \"Oh! I'm glad to see you're back! I Hope you have been well my child. Unfortunately, I cannot talk \nfor long, I hope you have been well!\"\nHe then walks away.");
                                Functions.delay(3000);
                                System.out.println(
                                        "\nYou step up to the alter and kneel. You silently murmur a quick prayer as you feel your devotion to Eryndos grow.");
                                break;

                            // Talk
                            case 1:
                                //^ Disciple -> Squire rank up and quest reward
                                if (user.getRank().toLowerCase().contains("disciple") && (user.getHasQuestItem())) {
                                    churchDistrict.squireRankup(user);
                                    shopReset = 1;
                                } // End of if(userRank equals disciple && user has quest item)
                                else {
                                    System.out
                                            .println("\nCurrently, the Priest is too busy with church matters to talk");
                                }
                                break;

                            // Board
                            case 2:
                                String rank = user.getRank().toLowerCase();
                                // ^ Disciple Quest
                                if ((rank.contains("disciple") && (user.getQuestAccepted() == false))) {
                                    boolean leaveBoard = false;
                                    // ! While playerInput isnt yes and isnt no
                                    while (leaveBoard == false) {
                                        System.out.println(
                                                "\nYou approach the new job board to see some of the tasks. One in particular catches your eye. A horde of \ngoblins has been reported in the Dark Forest. When you read it, you hope Gherald is okay.\nAccept the quest?(yes/no)");
                                        playerInput = keys.nextLine();

                                        switch (Functions.yesOrNo(playerInput)) {
                                            // ! Yes
                                            case 0:
                                                System.out.println(
                                                        "\nYou accept the quest as you tear the posting off the board.");
                                                user.setQuestAccepted(true);
                                                leaveBoard = true;
                                                break;

                                            // ! No
                                            case 1:
                                                System.out.println(
                                                        "\nYou stare at the paper for a few extra seconds before walking away from the board.");
                                                leaveBoard = true;
                                                break;
                                            // ! Invalid
                                            default:
                                                System.out.println("\nInvalid input. Please try again.(yes/no)");
                                                break;
                                        }
                                    } // End of while(playerInput isnt yes or no)
                                }
                                // If a quest is already accepted
                                else {
                                    System.out.println("\nYou already accepted the quest about");
                                    if (rank.contains("disciple")) {
                                        System.out.print(" the Goblin Horde in the Dark Forest.\n");
                                    } // End of if(rank == disciple)
                                }
                                break;

                            // Leave
                            case 3:
                                System.out.println("\nYou turn back around and head to the shopping district.");
                                Functions.movePlayer(0, -1, user);
                                break;
                            default:
                                System.out.println("\nInvalid Direction, please try again.(Pray/Talk/Board/Leave)");
                                break;
                        }// End of switch(get4Directions)
                    }
                    // ! If player isn't Disciple
                    else if (!user.getRank().equals("Disciple")) {
                        switch (Functions.get5directions(playerInput, "pray", "talk", "board", "shop", "leave")) {
                            // Invalid
                            case 0:
                                System.out.println("Invalid answer, try again. (pray/talk/board/church/leave)");
                                Functions.delay(1000);
                                break;
                            // Pray
                            case 1:
                                System.out.println(
                                        "\nThe Priest walks up to you and says:\n\nPriest: \"Oh! I'm glad to see you're back! I Hope you have been well my child. Unfortunately, I cannot talk \nfor long, I hope you have been well!\"\nHe then walks away.");
                                Functions.delay(3000);
                                System.out.println(
                                        "\nYou step up to the alter and kneel. You silently murmur a quick prayer as you feel your devotion to Eryndos\ngrow.");
                                break;
                            // Talk
                            case 2:
                                System.out.println(
                                        "\nYou find a chance to talk with the Priest as he just finished a sermon. You both spend a few minutes catching\nup.");
                                Functions.delay(1750);
                                break;
                            // Board
                            case 3:
                                if (user.getSideQuestAccepted() == false) {
                                    System.out.println(
                                            "\nYou look at the board and see nothing that stands out to you. Just a few common goblin and wolf hunting\nquests.");
                                    Functions.delay(1000);
                                    System.out.println(
                                            "\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\     \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"
                                                    +
                                                    "\n\\\\     |_|      _  _|_     \\\\     \\\\     |_|      _  _|_     \\\\"
                                                    +
                                                    "\n\\\\     | | |_| | |  |      \\\\     \\\\     | | |_| | |  |      \\\\"
                                                    +
                                                    "\n\\\\                         \\\\     \\\\                         \\\\"
                                                    +
                                                    "\n\\\\          QUEST:         \\\\     \\\\          QUEST:         \\\\"
                                                    +
                                                    "\n\\\\      KILL 10 GOBLINS    \\\\     \\\\    KILL 5 DIRE WOLVES   \\\\"
                                                    +
                                                    "\n\\\\         REWARD:         \\\\     \\\\         REWARD:         \\\\"
                                                    +
                                                    "\n\\\\         25 GOLD         \\\\     \\\\         35 GOLD         \\\\"
                                                    +
                                                    "\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\     \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

                                    System.out.println("Do you want to take one of the quests?(yes/no)");
                                    playerInput = keys.nextLine();

                                    switch (Functions.yesOrNo(playerInput)) {
                                        // Yes
                                        case 0:
                                            while (true) {
                                                System.out.println(
                                                        "\nWhich would you like to select? Wolf or goblin hunting");
                                                playerInput = keys.nextLine();
                                                // Wolf
                                                if (playerInput.toLowerCase().contains("wolf")) {
                                                    System.out.println("\nYou have accepted the wolf hunting quest");
                                                    user.setSideQuestAccepted(true);
                                                    break;
                                                }
                                                // Goblin
                                                else if (playerInput.toLowerCase().contains("goblin")) {
                                                    System.out.println("You have taken the goblin hunting quest");
                                                    user.setSideQuestAccepted(true);
                                                    wolfOrGoblinQuest = true;
                                                    break;
                                                }
                                                // Invalid
                                                else {
                                                    System.out
                                                            .println("\nInvalid Input, please try again.(wolf/goblin)");
                                                }
                                            }
                                            break;
                                        // No
                                        case 1:
                                            System.out.println(
                                                    "\nYou decide not to take one of the postings and step away from the board.");
                                            break;
                                    }// End of switch(Func.yesOrNo())
                                } else if (user.getSideQuestItem()) {
                                    // Goblin reward
                                    if (wolfOrGoblinQuest) {
                                        System.out
                                                .println(
                                                        "\nYou turn in your quest and you get your reward of 25 gold.");
                                        user.setGold(user.getGold() + 25);
                                    }
                                    // Wolf reward
                                    else {
                                        System.out
                                                .println(
                                                        "\nYou turn in your quest and you get your reward of 35 gold.");
                                        user.setGold(user.getGold() + 35);
                                    }
                                    // Reset quests
                                    user.setSideQuestAccepted(false);
                                    user.setSideQuestItem(false);
                                } else {
                                    System.out.println("\nYou have already chosen a side quest.");
                                    Functions.delay(1000);
                                }
                                break;
                            // Church shop
                            case 4:
                                System.out.println("You walk into the church to find the shop.");
                                Functions.movePlayer(1, 0, user);
                                break;
                            // Leave
                            case 5:
                                System.out.println("\nYou turn back around and head to the shopping district.");
                                Functions.movePlayer(0, -1, user);
                                break;
                            default:
                                break;
                        }// End of switch
                    }
                }
            } // End of Church District

            // Church Shop
            // ! While user.X is 1 and user.Y is 3
            while (user.getPlayerX() == 1 && user.getPlayerY() == 3) {
                ChurchShop cs = new ChurchShop();

                // Introductory Message
                System.out.println("\n" + cs.getMessage());
                System.out.println("");
                Functions.delay(2500);
                exit: while (true) {
                    System.out.println("Armorer: \"Ah what can I do ye for?\"\n");
                    playerInput = keys.nextLine();

                    switch (Functions.get3Direction(playerInput, "shop", "talk", backAnswers)) {
                        // Shop Sequence
                        case 1:
                            System.out.println(
                                    "\nArmorer: \"Ah! So yer interested in the Church's undesireables! Well, lemme show ya our stock.");
                            do {
                                cs.printGoods(user);
                                System.out.println("");
                                playerInput = keys.nextLine();

                                // Steel Sword
                                if (playerInput.toLowerCase()
                                        .contains(cs.getShopValue(0, 0).toLowerCase().split(" ")[0])) {
                                    int storeTotal = Integer.parseInt(cs.getShopValue(0, 1));
                                    if (user.getGold() >= storeTotal) {
                                        user.setGold(user.getGold() - storeTotal);
                                        user.swapWeapons(inventory, user, cs.getShopValue(0, 0),
                                                Integer.parseInt(cs.getShopValue(0, 2)));
                                    } else {
                                        System.out.println(
                                                "\nArmorer: \"Sorry, yeh don't seem to have enough for it...\"");
                                    }

                                    Functions.delay(1500);
                                } // End of Steel Sword

                                // Iron Chestpiece
                                else if (playerInput.toLowerCase()
                                        .contains(cs.getShopValue(1, 0).toLowerCase().split(" ")[0])) {
                                    int storeTotal = Integer.parseInt(cs.getShopValue(1, 1));
                                    if (user.getGold() >= storeTotal) {
                                        user.setGold(user.getGold() - storeTotal);
                                        user.swapArmor(inventory, user, cs.getShopValue(1, 0),
                                                Integer.parseInt(cs.getShopValue(1, 2)));
                                    }
                                    // ! Unable to afford
                                    else {
                                        System.out.println(
                                                "\nArmorer: \"Sorry, yeh don't seem to have enough for it...\"");
                                    }
                                    Functions.delay(1500);
                                } // End of Iron Chestpiece

                                // Banded Shield
                                else if (playerInput.toLowerCase()
                                        .contains(cs.getShopValue(2, 0).toLowerCase().split(" ")[0])) {
                                    int storeTotal = Integer.parseInt(cs.getShopValue(2, 1));
                                    if (user.getGold() >= storeTotal) {
                                        user.setGold(user.getGold() - storeTotal);
                                        user.equipShield(inventory, user, cs.getShopValue(2, 0),
                                                Integer.parseInt(cs.getShopValue(2, 2)));
                                    }
                                    // ! Unable to purchase
                                    else {
                                        System.out.println("Armorer: \"Sorry lad, you don't have enough fer it.\"");
                                    }
                                    Functions.delay(1500);
                                } // End of Banded Shield
                                  // Exit
                                else if (playerInput.toLowerCase().contains("exit")) {
                                    break;
                                }
                                // Invalid input
                                else {
                                    System.out.println("\nArmorer: \"Sorry, what was that?\"\n");
                                }

                                System.out.println("\nArmorer: \"Would ye like to buy somethin else?\"\n");
                            } while (true);
                            break;

                        // Talk
                        case 2:
                            System.out.println("Armorer: \"Sorry lad, too busy to talk right now.\n");
                            break;

                        // Back
                        case 3:
                            System.out.println("Armorer: \"Thanks fer comin in! See yeh again!\n");
                            Functions.movePlayer(-1, 0, user);
                            break exit;

                        // Invalid Input
                        case 4:
                            System.out.println("Armorer: \"Hwat was that ye said?\"(Talk/Shop/Back)\n");
                    }// End of switch(playerInput)
                }
            } // End of Church Shop

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
                    Functions.movePlayer(-1, 0, user);
                    break;
                } else if (user.getRank().equals("Holy Knight") || user.getRank().equals("Holy Knight Champion")) {
                    System.out.println(heraldsHills.getMessage(2));
                    Functions.movePlayer(-1, 0, user);
                    break;
                } else {
                    System.out.println(heraldsHills.getMessage(0));
                    Functions.delay(2000);
                    Functions.movePlayer(1, 0, user);
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