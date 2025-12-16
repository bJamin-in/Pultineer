import java.util.*;

import playerInfo.*;
import Funcs.*;

public class Battle extends Player {

    // Level Up
    public static void levelUp(Player user, String playerInput, Scanner keys) {
        while (user.getXp() >= user.getXpLimit()) {

            System.out.println("\nYou leveled up! You are now level " + (user.getLevel() + 1)
                    + " and may upgrade one stat: attack, health, or agility.");
            playerInput = keys.nextLine();

            switch (playerInput.toLowerCase()) {
                // Attack
                case "attack":
                    user.setAttack(user.getAttack() + 1);
                    user.setXp(user.getXp() - user.getXpLimit());
                    user.setXpLimit(user.getXpLimit() + (int) (user.getXpLimit() * .5));
                    user.setLevel(user.getLevel() + 1);
                    break;

                // Health
                case "health":
                    user.setTotalHealth(user.getTotalHealth() + 2);
                    user.setXp(user.getXp() - user.getXpLimit());
                    user.setXpLimit(user.getXpLimit() + (int) (user.getXpLimit() * .5));
                    user.setLevel(user.getLevel() + 1);
                    break;

                // Agility
                case "agility":
                    user.setAgility(user.getAgility() + 1);
                    user.setXp(user.getXp() - user.getXpLimit());
                    user.setXpLimit(user.getXpLimit() + (int) (user.getXpLimit() * .5));
                    user.setLevel(user.getLevel() + 1);
                    break;

                // Invalid input
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }// End of switch(playerInput.toLowerCase())
            user.setHealth(user.getTotalHealth());
        } // End of while(user.getXp() >= user.getXpLimit())
    }// End of levelUp

    // Get Actions
    public static int getActions(String playerInput) {
        // Attack
        if (playerInput.toLowerCase().contains("attack")) {
            return 1;
        }
        // Dodge
        else if (playerInput.toLowerCase().contains("dodge")) {
            return 2;
        }
        // Run
        else if (playerInput.toLowerCase().contains("run")) {
            return 3;
        }
        // Invalid input
        else {
            System.out.println("\nInvalid action. Please try again.");
            return 4;
        }
    }// End of getActions

    public static boolean battle(String playerInput, boolean battleState, boolean gameState, Enemy enemy, Player user,
            Scanner keys, Random rnd, int hitChance, int hitChanceLower) {
        boolean playerTurn = true, successfulDodge = false;

        // Determines who goes first
        // Player goes first
        if (enemy.getAgility() <= user.getAgility()) {
            playerTurn = true;
        }
        // Enemy goes first
        else if (enemy.getAgility() > user.getAgility()) {
            playerTurn = false;
            System.out.println("\nThe enemy is faster than you! They get the drop on you!");
        }

        while (battleState) {

            // Player Turn
            while (playerTurn) {
                // Delay
                Functions.delay(1500);

                // Prints enemy info
                System.out.println("The " + enemy.getName() + " stands before you!\n" + enemy.getName() + "\nHP:"
                        + enemy.getHealth() + "\nATK:" + enemy.getAttack() + " AGI:" + enemy.getAgility());

                // Delay
                Functions.delay(1500);

                // Prints player info
                System.out.println("\n" + user.getName() + " the " + user.getRank() + "\nLVL: " + user.getLevel()
                        + " XP: " + user.getXp() + "\nHP: " + user.getHealth() + " ATK: " + user.getAttack() + "\nDEF: "
                        + user.getDefense() + " AGI: " + user.getAgility()
                        + "\n\n What do you do? (Attack, Dodge, Run)");
                playerInput = keys.nextLine();

                // Attack
                if (getActions(playerInput) == 1) {
                    System.out.println("\nYou attack the " + enemy.getName() + "!");
                    enemy.setHealth(enemy.getHealth() - (user.getAttack() - enemy.getDefense()));
                    playerTurn = false;
                    break;
                }
                // Dodge
                else if (getActions(playerInput) == 2) {
                    if (rnd.nextInt(enemy.getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nThe enemy is faster than that! You still get hit!");
                        successfulDodge = false;
                    } else {
                        System.out.println("\nYou move to dodge the attack!");
                        successfulDodge = true;
                    }
                    playerTurn = false;
                    break;
                }
                // Run
                else if (getActions(playerInput) == 3) {
                    System.out.println("\nYou try to run...\n");
                    try {
                        System.out.print(".");
                        Thread.sleep(1500);
                        System.out.print("\b..");
                        Thread.sleep(1500);
                        System.out.print("\b\b...\n");
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    if (rnd.nextInt(enemy.getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nYou're unable to run away!");
                        playerTurn = false;
                    } else {
                        battleState = false;
                        break;
                    }
                }
                // Invalid input
                else {
                    System.out.println("\nInvalid action. Please try again.");
                }

                break;
            } // End of while(playerTurn)

            // Ends the battle if player runs
            if (battleState == false) {
                break;
            }

            // Ends the battle if enemy dies
            if (enemy.getHealth() <= 0) {
                System.out.println("\nYou defeated the " + enemy.getName() + "! You got " + enemy.getXpValue()
                        + " XP and " + enemy.getGoldValue() + " gold.");
                user.setXp(user.getXp() + enemy.getXpValue());
                user.setGold(user.getGold() + enemy.getGoldValue());
                battleState = false;
                break;
            }
            // Delay
            Functions.delay(2000);

            // Enemy Turn
            while (playerTurn == false) {

                // User failed dodge, enemy auto attacks
                if (playerInput.toLowerCase().contains("dodge")) {
                    if (!successfulDodge) {
                        System.out.println("\nThe " + enemy.getName() + " attacks you! You can't defend yourself!\n");
                        user.setHealth(user.getHealth() - enemy.getAttack());
                        playerTurn = true;
                        break;
                    }

                    if (successfulDodge) {
                        System.out.println("\nYou dodge the " + enemy.getName() + "'s attack!");
                        successfulDodge = false;
                        playerTurn = true;
                        break;
                    }
                }

                // Enemy Attacks
                if (rnd.nextInt(hitChance) + 1 <= hitChanceLower) {

                    System.out.println("\nThe " + enemy.getName() + " attacks you!\n");
                    user.setHealth(user.getHealth() - (enemy.getAttack() - user.getDefense()));

                } else {
                    System.out.println("\nThe " + enemy.getName() + " misses it's attack!\n");
                }

                playerTurn = true;
                break;
            } // End of while(playerTurn == false)

            // Delay
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            // Player dies
            if (user.getHealth() <= 0) {
                System.out.println("\nYou have been defeated! Game Over.");
                battleState = false;
                gameState = false;
                break;
            }

        } // End of while(battleState)
        if (gameState == true) {
            levelUp(user, playerInput, keys);
        }
        return gameState;
    }// End of battle

    public static boolean multiBattle(String playerInput, boolean battleState, boolean gameState, Enemy[] enemies,
            Player user, Scanner keys, Random rnd, int numberOfEnemies) {
        boolean playerTurn = true, successfulDodge = false;
        int deadEnemies = 0;
        while (battleState) {
            // Player Turn
            while (playerTurn) {
                // Delay
                Functions.delay(1500);

                System.out.println("The " + enemies[0].getName() + "s stand before you!");
                for (int x = 0; x < enemies.length; x++) {

                    // Prints enemy info
                    if (enemies[x].getHealth() > 0) {
                        System.out.println(enemies[x].getName() + " " + (x + 1) + "\nHP:"
                                + enemies[x].getHealth() + "\nATK:" + enemies[x].getAttack() + " AGI:"
                                + enemies[x].getAgility());
                        // Delay
                        Functions.delay(2000);
                    }

                }
                // Prints player info
                System.out.println("\n" + user.getName() + " the " + user.getRank() + "\nLVL: " + user.getLevel()
                        + " XP: " + user.getXp() + "\nHP: " + user.getHealth() + " ATK: " + user.getAttack() + "\nDEF: "
                        + user.getDefense() + " AGI: " + user.getAgility()
                        + "\n\n What do you do? (Attack, Dodge, Run)");
                playerInput = keys.nextLine();

                // Attack
                if (getActions(playerInput) == 1) {
                    do {
                        try {
                            System.out.println("\nWhich enemy would you like to attack?(Input number)");
                            playerInput = keys.nextLine();
                            int attackedEnemy = Integer.parseInt(playerInput) - 1;

                            if (attackedEnemy >= enemies.length) {
                                System.out.println("\nChoose a valid enemy.");
                                break;
                            }
                            // ! If user attack is lower than enemy defense
                            if (user.getAttack() < enemies[attackedEnemy].getDefense()) {
                                System.out.println("You try to swipe at the " + enemies[attackedEnemy].getName()
                                        + ", but their armor is too high!");
                            } else {
                                System.out.println("\nYou attack the " + enemies[attackedEnemy].getName() + "!");
                                enemies[attackedEnemy].setHealth(enemies[attackedEnemy].getHealth()
                                        - (user.getAttack() - enemies[attackedEnemy].getDefense()));

                                // Ends the battle if all enemies dies
                                for (int x = 0; x < enemies.length; x++) {
                                    if (enemies[attackedEnemy].getHealth() <= 0) {
                                        System.out.println(
                                                "\nYou defeated the " + enemies[attackedEnemy].getName() + "! You got "
                                                        + enemies[x].getXpValue()
                                                        + " XP and " + enemies[attackedEnemy].getGoldValue()
                                                        + " gold.");
                                        user.setXp(user.getXp() + enemies[attackedEnemy].getXpValue());
                                        user.setGold(user.getGold() + enemies[attackedEnemy].getGoldValue());
                                        deadEnemies++;
                                        if (deadEnemies == enemies.length) {
                                            System.out.println("\nYou defeated all the " + enemies[0].getName()
                                                    + "s! You revel in your victory for a moment before moving onward.");
                                            battleState = false;
                                            user.setHasQuestItem(true);
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            playerTurn = false;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please choose a valid number.(Ex: 1, 2, 3)");
                        }
                    } while (true);
                }

                // Dodge
                else if (getActions(playerInput) == 2) {
                    System.out.println("\nThere are too many enemies, you can't dodge them!");
                    successfulDodge = false;

                    break;
                }
                // Run
                else if (getActions(playerInput) == 3) {
                    System.out.println("\nYou try to run...\n");
                    try {
                        System.out.print(".");
                        Thread.sleep(1500);
                        System.out.print("\b..");
                        Thread.sleep(1500);
                        System.out.print("\b\b...\n");
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    if (rnd.nextInt(enemies[0].getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nYou're unable to run away!");
                        playerTurn = false;
                    } else {
                        System.out.println("\nYou successfully run away from the horde!");
                        Functions.movePlayer(-1, 0, user);
                        battleState = false;
                        break;
                    }
                }
                // Invalid input
                else {
                    System.out.println("\nInvalid action. Please try again.");
                }

                playerTurn = false;
                break;
            } // End of while(playerTurn)

            // Ends the battle if player runs
            if (battleState == false) {
                break;
            }

            // Delay
            Functions.delay(2000);

            // Enemy Turn
            while (playerTurn == false) {
                for (int x = 0; x < numberOfEnemies; x++) {

                    if (enemies[x].getHealth() > 0) {
                        // User failed dodge, enemy auto attacks
                        if (playerInput.toLowerCase().contains("dodge")) {
                            if (!successfulDodge) {
                                System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1)
                                        + " attacks you! You can't defend yourself!\n");
                                user.setHealth(user.getHealth() - enemies[x].getAttack());
                                playerTurn = true;
                                break;
                            }

                            if (successfulDodge) {
                                System.out.println(
                                        "\nYou dodge the " + enemies[x].getName() + " " + (x + 1) + "'s attack!");
                                successfulDodge = false;
                                playerTurn = true;
                                break;
                            }
                        }

                        // Enemy Attacks
                        if (rnd.nextInt(2) + 1 == 1) {

                            if (enemies[x].getAttack() < user.getDefense()) {
                                System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1)
                                        + "'s attack bounces off your armor!");
                            }
                            System.out.println("\n" + enemies[x].getName() + " " + (x + 1) + " attacks you!\n");
                            user.setHealth(user.getHealth() - (enemies[x].getAttack() - user.getDefense()));

                        } else {
                            System.out.println("\n" + enemies[x].getName() + " " + (x + 1) + " misses it's attack!\n");
                        }

                        Functions.delay(1500);
                    }
                } // End of while(playerTurn == false)
                playerTurn = true;
                break;
            } // End of enemy turn

            // Player dies
            if (user.getHealth() <= 0) {
                System.out.println("\nYou have been defeated! Game Over.");
                battleState = false;
                return false;
            }
        }
        return true;
    }

    public static boolean teamBattle(String playerInput, boolean battleState, boolean gameState, Enemy[] enemies,
            Player user, Npc[] team, Scanner keys, Random rnd, int numberOfEnemies, int numberOfTeammates,
            String teamType) {
        boolean playerTurn = true, successfulDodge = false;
        int deadEnemies = 0, thanlinBaseAtk = team[0].getAttack(), ordekaBaseAtk = team[1].getAttack();
        if (enemies[0].getAgility() > user.getAgility()) {
            playerTurn = false;
        }
        while (battleState) {
            // Player Turn
            while (playerTurn) {
                // Delay
                Functions.delay(1500);

                System.out.println("The " + enemies[0].getName() + " stand before you!");
                for (int x = 0; x < enemies.length; x++) {

                    // Prints enemy info
                    if (enemies[x].getHealth() > 0) {
                        System.out.println(enemies[x].getName() + " " + (x + 1) + "\nHP:"
                                + enemies[x].getHealth() + "\nATK:" + enemies[x].getAttack() + " AGI:"
                                + enemies[x].getAgility());
                        // Delay
                        Functions.delay(2000);
                    }

                }
                // Teammates
                System.out.println("\nThe " + teamType + " stand at your side.");

                for (int x = 0; x < team.length; x++) {
                    if (team[x].getHealth() > 0) {
                        if (x > 0) {
                            System.out.println();
                        }
                        System.out.print(team[x].toString());

                        Functions.delay(2000);
                    }
                }

                // Prints player info
                System.out.println("\n\n" + user.getName() + " the " + user.getRank() + "\nLVL: " + user.getLevel()
                        + " XP: " + user.getXp() + "\nHP: " + user.getHealth() + " ATK: " + user.getAttack() + "\nDEF: "
                        + user.getDefense() + " AGI: " + user.getAgility()
                        + "\n\n What do you do? (Attack, Dodge, Run)");
                playerInput = keys.nextLine();

                // Attack
                if (getActions(playerInput) == 1) {
                    do {
                        try {
                            System.out.println("\nWhich enemy would you like to attack?(Input number)");
                            int attackedEnemy = Integer.parseInt(keys.nextLine()) - 1;

                            if (attackedEnemy >= enemies.length) {
                                System.out.println("\nChoose a valid enemy.");
                                break;
                            }
                            // ! If user attack is lower than enemy defense
                            if (user.getAttack() < enemies[attackedEnemy].getDefense()) {
                                System.out.println("You try to swipe at the " + enemies[attackedEnemy].getName()
                                        + ", but their armor is too high!");

                                // Team turn
                                for (int x = 0; x < team.length; x++) {
                                    if (team[x].getHealth() > 0) {

                                        if (team[x].getAttack() > 20) {

                                            int target = rnd.nextInt(numberOfEnemies);
                                            System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                                    + enemies[target].getName() + " " + (target + 1));
                                            if (enemies[target].getHealth()
                                                    - (team[x].getAttack() - enemies[target].getDefense()) <= 0) {
                                                System.out.println(team[x].getName() + "'s attack was ineffective!");
                                                break;
                                            } else {
                                                enemies[target].setHealth(enemies[target].getHealth()
                                                        - (team[x].getAttack() - enemies[target].getDefense()));
                                                if (x == 0) {
                                                    team[x].setAttack(thanlinBaseAtk);
                                                } else if (x == 1) {
                                                    team[x].setAttack(ordekaBaseAtk);
                                                }
                                                break;
                                            }
                                        }
                                        int action = rnd.nextInt(3) + 1;

                                        // Teammate attacks
                                        if (action == 1) {
                                            int target = rnd.nextInt(numberOfEnemies);
                                            System.out.println(team[x].getName() + " attacks "
                                                    + enemies[target].getName() + " " + (target + 1));
                                            if (enemies[target].getHealth()
                                                    - (team[x].getAttack() - enemies[target].getDefense()) <= 0) {
                                                System.out.println(team[x].getName() + "'s attack was ineffective!");
                                                break;
                                            } else {
                                                enemies[target].setHealth(enemies[target].getHealth()
                                                        - (team[x].getAttack() - enemies[target].getDefense()));
                                            }
                                        } // End of team attacks
                                          // Teammate prepares to defend
                                        else if (action == 2) {
                                            System.out.println(team[x].getName() + " readies for an attack!");
                                            team[x].setDefense(team[x].getDefense() * 2);
                                        } // End of team defends
                                        else if (action == 3) {
                                            System.out.println(team[x].getName() + " prepares a heavy attack...");
                                            team[x].setAttack(team[x].getAttack() * 2);
                                        }
                                    }
                                } // End of for(teamLength)
                            } else {
                                System.out.println("\nYou attack the " + enemies[attackedEnemy].getName() + "!");
                                enemies[attackedEnemy].setHealth(enemies[attackedEnemy].getHealth()
                                        - (user.getAttack() - enemies[attackedEnemy].getDefense()));

                                // Team turn
                                for (int x = 0; x < team.length; x++) {
                                    if (team[x].getHealth() > 0) {

                                        if (team[x].getAttack() > 20) {

                                            int target = rnd.nextInt(numberOfEnemies);
                                            System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                                    + enemies[target].getName() + " " + (target + 1));
                                            enemies[target].setHealth(enemies[target].getHealth()
                                                    - (team[x].getAttack() - enemies[target].getDefense()));
                                            if (x == 0) {
                                                team[x].setAttack(thanlinBaseAtk);
                                            } else if (x == 1) {
                                                team[x].setAttack(ordekaBaseAtk);
                                            }
                                            break;
                                        }
                                        int action = rnd.nextInt(3) + 1;

                                        // Teammate attacks
                                        if (action == 1) {
                                            int target = rnd.nextInt(numberOfEnemies);
                                            System.out.println(team[x].getName() + " attacks "
                                                    + enemies[target].getName() + " " + (target + 1));
                                            enemies[target].setHealth(enemies[target].getHealth()
                                                    - (team[x].getAttack() - enemies[target].getDefense()));
                                        } // End of team attacks
                                          // Teammate prepares to defend
                                        else if (action == 2) {
                                            System.out.println(team[x].getName() + " readies for an attack!");
                                            team[x].setDefense(team[x].getDefense() * 2);
                                        } // End of team defends
                                        else if (action == 3) {
                                            System.out.println(team[x].getName() + " prepares a heavy attack...");
                                            team[x].setAttack(team[x].getAttack() * 2);
                                        }
                                    }
                                } // End of for(teamLength)

                                // Ends the battle if all enemies dies
                                for (int x = 0; x < enemies.length; x++) {
                                    if (enemies[attackedEnemy].getHealth() <= 0) {
                                        System.out.println(
                                                "\nYou defeated the " + enemies[attackedEnemy].getName() + "! You got "
                                                        + enemies[x].getXpValue()
                                                        + " XP and " + enemies[attackedEnemy].getGoldValue()
                                                        + " gold.");
                                        user.setXp(user.getXp() + enemies[attackedEnemy].getXpValue());
                                        user.setGold(user.getGold() + enemies[attackedEnemy].getGoldValue());
                                        deadEnemies++;

                                        // Ends the battle if the team defeated all enemies
                                        if (deadEnemies == enemies.length) {
                                            System.out.println("\nYou defeated all the " + enemies[0].getName()
                                                    + "s! You revel in your victory for a moment before moving onward.");

                                            // Level player
                                            while (user.getXp() >= user.getXpLimit()) {
                                                levelUp(user, playerInput, keys);
                                            }
                                            battleState = false;
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            playerTurn = false;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please choose a valid number.(Ex: 1, 2, 3)");
                        }
                    } while (true);
                }

                // Dodge
                else if (getActions(playerInput) == 2) {
                    System.out.println("\nThere are too many enemies, you can't dodge them all!");
                    successfulDodge = false;

                    // Team turn
                    for (int x = 0; x < team.length; x++) {
                        if (team[x].getHealth() > 0) {

                            if (team[x].getAttack() > 20) {

                                int target = rnd.nextInt(numberOfEnemies);
                                System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                        + enemies[target].getName() + " " + (target + 1));
                                enemies[target].setHealth(enemies[target].getHealth()
                                        - (team[x].getAttack() - enemies[target].getDefense()));
                                if (x == 0) {
                                    team[x].setAttack(thanlinBaseAtk);
                                } else if (x == 1) {
                                    team[x].setAttack(ordekaBaseAtk);
                                }
                                break;
                            }
                            int action = rnd.nextInt(3) + 1;

                            // Teammate attacks
                            if (action == 1) {
                                int target = rnd.nextInt(numberOfEnemies);
                                System.out.println(
                                        team[x].getName() + " attacks " + enemies[target].getName() + " "
                                                + (target + 1));
                                enemies[target].setHealth(enemies[target].getHealth()
                                        - (team[x].getAttack() - enemies[target].getDefense()));
                            } // End of team attacks
                              // Teammate prepares to defend
                            else if (action == 2) {
                                System.out.println(team[x].getName() + " readies for an attack!");
                                team[x].setDefense(team[x].getDefense() * 2);
                            } // End of team defends
                            else if (action == 3) {
                                System.out.println(team[x].getName() + " prepares a heavy attack...");
                                team[x].setAttack(team[x].getAttack() * 2);
                            }
                        }
                    } // End of for(teamLength)

                }
                // Run
                else if (getActions(playerInput) == 3) {
                    System.out.println("\nYou try to run...\n");
                    try {
                        System.out.print(".");
                        Thread.sleep(1500);
                        System.out.print("\b..");
                        Thread.sleep(1500);
                        System.out.print("\b\b...\n");
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    if (rnd.nextInt(enemies[0].getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nYou're unable to run away!");
                        playerTurn = false;
                    } else {
                        System.out.println("\nYou successfully run away from the horde!");
                        Functions.movePlayer(-1, 0, user);
                        battleState = false;
                        break;
                    }
                }
                // Invalid input
                else {
                    System.out.println("\nInvalid action. Please try again.");
                }

                playerTurn = false;
                break;
            } // End of while(playerTurn)

            // Ends the battle if player runs
            if (battleState == false) {
                break;
            }

            // Delay
            Functions.delay(2000);

            // #region Enemy Turn
            // Enemy Turn
            while (playerTurn == false) {
                for (int x = 0; x < numberOfEnemies; x++) {

                    if (enemies[x].getHealth() > 0) {
                        // User failed dodge, enemy auto attacks
                        if (playerInput.toLowerCase().contains("dodge")) {
                            if (!successfulDodge) {
                                System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1)
                                        + " attacks you! You can't defend yourself!\n");
                                user.setHealth(user.getHealth() - enemies[x].getAttack());
                                playerTurn = true;
                                break;
                            }

                            if (successfulDodge) {
                                System.out.println(
                                        "\nYou dodge the " + enemies[x].getName() + " " + (x + 1) + "'s attack!");
                                successfulDodge = false;
                                playerTurn = true;
                                break;
                            }
                        }

                        // Enemy Attacks
                        if (rnd.nextInt(2) + 1 == 1) {

                            int target = rnd.nextInt(team.length) + 1;

                            // Enemy attacks player
                            if (target == 3) {
                                if (enemies[x].getAttack() < user.getDefense()) {
                                    System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1)
                                            + "'s attack bounces off your armor!");
                                }
                                System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1) + " attacks you!\n");
                                user.setHealth(user.getHealth() - (enemies[x].getAttack() - user.getDefense()));
                            }
                            // Enemy attacks team member
                            else if (target - 1 <= team.length) {
                                target -= 1;
                                System.out.println("\nThe " + enemies[x].getName() + " " + (x + 1) + " attacks "
                                        + team[target].getName() + "!");
                                team[target].setHealth(team[target].getHealth()
                                        - (enemies[x].getAttack() - team[target].getDefense()));
                            }
                        } // End of Enemy Attack

                        // Enemy misses
                        else {
                            System.out.println(
                                    "\n" + enemies[x].getName() + " " + (x + 1)
                                            + " stands by, waiting for it's chance to strike!");
                        }

                        Functions.delay(1500);
                    }
                } // End of while(playerTurn == false)
                playerTurn = true;
                break;
            } // End of enemy turn

            // Player dies
            if (user.getHealth() <= 0) {
                System.out.println("\nYou have been defeated! Game Over.");
                battleState = false;
                return false;
            } // End of player dies

            // Reset team's defense
            for (int x = 0; x < team.length; x++) {
                if (team[x].getDefense() > 8) {
                    team[x].setDefense(8);
                }
            } // End of defense reset
        }
        return true;
    }

    public static boolean bossBattle(String playerInput, boolean battleState, boolean gameState, Enemy enemy,
            Player user, Npc[] team, Scanner keys, Random rnd, int numberOfTeammates,
            String teamType) {
        boolean playerTurn = true, successfulDodge = false;
        int thanlinBaseAtk = team[0].getAttack(), ordekaBaseAtk = team[1].getAttack();
        if (enemy.getAgility() > user.getAgility()) {
            playerTurn = false;
        }
        while (battleState) {
            // Player Turn
            while (playerTurn) {
                // Delay
                Functions.delay(1500);

                System.out.println("\nThe " + enemy.getName() + "s stand before you!");

                // Prints enemy info
                if (enemy.getHealth() > 0) {
                    System.out.println(enemy.getName() + "\nHP:"
                            + enemy.getHealth() + "\nATK:" + enemy.getAttack() + " AGI:"
                            + enemy.getAgility());
                    // Delay
                    Functions.delay(2000);
                }

                // Teammates
                if(team[0].getHealth() <= 0 && team[1].getHealth() <=0) {
                    System.out.println("\nYou are alone in this fight...");
                } else {
                System.out.println("\nThe " + teamType + "s stand at your side.");

                for (int x = 0; x < team.length; x++) {
                    if (team[x].getHealth() > 0) {
                        if (x > 0) {
                            System.out.println();
                        }
                        System.out.print(team[x].toString());

                        Functions.delay(2000);
                    }
                }
            }

                // Prints player info
                System.out.println("\n" + user.getName() + " the " + user.getRank() + "\nLVL: " + user.getLevel()
                        + " XP: " + user.getXp() + "\nHP: " + user.getHealth() + " ATK: " + user.getAttack() + "\nDEF: "
                        + user.getDefense() + " AGI: " + user.getAgility()
                        + "\n\n What do you do? (Attack, Dodge, Run)");
                playerInput = keys.nextLine();

                // Attack
                if (getActions(playerInput) == 1) {
                    do {
                        // ! If user attack is lower than enemy defense
                        if (user.getAttack() < enemy.getDefense()) {
                            System.out.println("You try to swipe at the " + enemy.getName()
                                    + ", but their armor is too high!");
                            // Team turn
                            for (int x = 0; x < team.length; x++) {
                                if (team[x].getHealth() > 0) {

                                    if (team[x].getAttack() > 20) {

                                        System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                                + enemy.getName());

                                        if (enemy.getHealth()
                                                - (team[x].getAttack() - enemy.getDefense()) <= 0) {
                                            System.out.println(team[x].getName() + "'s attack was ineffective!");
                                            break;
                                        } else {
                                            enemy.setHealth(enemy.getHealth()
                                                    - (team[x].getAttack() - enemy.getDefense()));
                                            if (x == 0) {
                                                team[x].setAttack(thanlinBaseAtk);
                                            } else if (x == 1) {
                                                team[x].setAttack(ordekaBaseAtk);
                                            }
                                            break;
                                        }
                                    }
                                    int action = rnd.nextInt(3) + 1;

                                    // Teammate attacks
                                    if (action == 1) {
                                        System.out.println(team[x].getName() + " attacks "
                                                + enemy.getName());

                                        if (enemy.getHealth()
                                                - (team[x].getAttack() - enemy.getDefense()) <= 0) {
                                            System.out.println(team[x].getName() + "'s attack was ineffective!");
                                            break;
                                        } else {
                                            enemy.setHealth(enemy.getHealth()
                                                    - (team[x].getAttack() - enemy.getDefense()));
                                        }
                                    } // End of team attacks

                                    // Teammate prepares to defend
                                    else if (action == 2) {
                                        System.out.println(team[x].getName() + " readies for an attack!");
                                        team[x].setDefense(team[x].getDefense() * 2);
                                    } // End of team defends
                                    else if (action == 3) {
                                        System.out.println(team[x].getName() + " prepares a heavy attack...");
                                        team[x].setAttack(team[x].getAttack() * 2);
                                    }
                                }
                            } // End of for(teamLength)
                        } else {
                            System.out.println("\nYou attack the " + enemy.getName() + "!");
                            enemy.setHealth(enemy.getHealth()
                                    - (user.getAttack() - enemy.getDefense()));

                            // Team turn
                            for (int x = 0; x < team.length; x++) {
                                if (team[x].getHealth() > 0) {

                                    if (team[x].getAttack() > 20) {

                                        System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                                + enemy.getName());
                                        enemy.setHealth(enemy.getHealth()
                                                - (team[x].getAttack() - enemy.getDefense()));
                                        if (x == 0) {
                                            team[x].setAttack(thanlinBaseAtk);
                                        } else if (x == 1) {
                                            team[x].setAttack(ordekaBaseAtk);
                                        }
                                        break;
                                    }
                                    int action = rnd.nextInt(3) + 1;

                                    // Teammate attacks
                                    if (action == 1) {

                                        System.out.println(team[x].getName() + " attacks "
                                                + enemy.getName());
                                        enemy.setHealth(enemy.getHealth()
                                                - (team[x].getAttack() - enemy.getDefense()));
                                    } // End of team attacks
                                      // Teammate prepares to defend
                                    else if (action == 2) {
                                        System.out.println(team[x].getName() + " readies for an attack!");
                                        team[x].setDefense(team[x].getDefense() * 2);
                                    } // End of team defends
                                    else if (action == 3) {
                                        System.out.println(team[x].getName() + " prepares a heavy attack...");
                                        team[x].setAttack(team[x].getAttack() * 2);
                                    }
                                }
                            } // End of for(teamLength)

                            // Ends the battle if all enemies dies

                            if (enemy.getHealth() <= 0) {
                                System.out.println(
                                        "\nYou defeated the " + enemy.getName() + "! You got "
                                                + enemy.getXpValue()
                                                + " XP and " + enemy.getGoldValue()
                                                + " gold.");
                                user.setXp(user.getXp() + enemy.getXpValue());
                                user.setGold(user.getGold() + enemy.getGoldValue());

                                // Ends the battle if the team defeated all enemies

                                System.out.println("\nYou defeated the " + enemy.getName()
                                        + "! You revel in your victory for a moment before moving onward.");

                                // Level player
                                while (user.getXp() >= user.getXpLimit()) {
                                    levelUp(user, playerInput, keys);
                                }
                                battleState = false;
                                break;
                            }
                        } // End of Player attack
                        playerTurn = false;
                        break;
                    } while (true);
                }

                // Dodge
                else if (getActions(playerInput) == 2) {
                    if(rnd.nextInt(user.getAgility()) + 1 > enemy.getAgility()) {
                        System.out.println("\nYou deftly dodge the " + enemy.getName() + "'s attacks!");
                        successfulDodge = true;
                    } else {

                    System.out.println("\nThere are too many enemies, you can't dodge them all!");
                    successfulDodge = false;
                    }

                    // Team turn
                    for (int x = 0; x < team.length; x++) {
                        if (team[x].getHealth() > 0) {

                            if (team[x].getAttack() > 20) {

                                System.out.println(team[x].getName() + " unleashes a heavy blow on "
                                        + enemy.getName());
                                enemy.setHealth(enemy.getHealth()
                                        - (team[x].getAttack() - enemy.getDefense()));
                                if (x == 0) {
                                    team[x].setAttack(thanlinBaseAtk);
                                } else if (x == 1) {
                                    team[x].setAttack(ordekaBaseAtk);
                                }
                                break;
                            }
                            int action = rnd.nextInt(3) + 1;

                            // Teammate attacks
                            if (action == 1) {

                                System.out.println(
                                        team[x].getName() + " attacks " + enemy.getName());
                                enemy.setHealth(enemy.getHealth()
                                        - (team[x].getAttack() - enemy.getDefense()));
                            } // End of team attacks
                              // Teammate prepares to defend
                            else if (action == 2) {
                                System.out.println(team[x].getName() + " readies for an attack!");
                                team[x].setDefense(team[x].getDefense() * 2);
                            } // End of team defends
                            else if (action == 3) {
                                System.out.println(team[x].getName() + " prepares a heavy attack...");
                                team[x].setAttack(team[x].getAttack() * 2);
                            }
                        }
                    } // End of for(teamLength)

                }
                // Run
                else if (getActions(playerInput) == 3) {
                    System.out.println("\nYou try to run...\n");
                    try {
                        System.out.print(".");
                        Thread.sleep(1500);
                        System.out.print("\b..");
                        Thread.sleep(1500);
                        System.out.print("\b\b...\n");
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    if (rnd.nextInt(enemy.getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nYou're unable to run away!");
                        playerTurn = false;
                    } else {
                        System.out.println("\nYou successfully run away from the horde!");
                        Functions.movePlayer(-1, 0, user);
                        battleState = false;
                        break;
                    }
                }
                // Invalid input
                else {
                    System.out.println("\nInvalid action. Please try again.");
                }

                playerTurn = false;
                break;
            } // End of while(playerTurn)

            // Ends the battle if player runs
            if (battleState == false) {
                break;
            }

            // Delay
            Functions.delay(2000);

            // #region Boss Turn
            // Enemy Turn
            while (playerTurn == false) {

                if (enemy.getHealth() > 0) {
                    // User failed dodge, enemy auto attacks
                    if (playerInput.toLowerCase().contains("dodge")) {
                        if (!successfulDodge) {
                            System.out
                                    .println("\nThe " + enemy.getName() + " attacks you! You can't defend yourself!\n");
                            user.setHealth(user.getHealth() - enemy.getAttack());
                            playerTurn = true;
                            break;
                        }

                        if (successfulDodge) {
                            System.out.println(
                                    "\nYou dodge the " + enemy.getName() + "'s attack!");
                            successfulDodge = false;
                            playerTurn = true;
                            break;
                        }
                    }

                    // Enemy Attacks
                    if (rnd.nextInt(10) + 1 >= 2) {

                        int target = rnd.nextInt(team.length) + 1;

                        // Enemy attacks team member
                        if (target - 1 <= team.length) {
                            target -= 1;

                            //Default to player if attacked teammate is dead
                            if(team[target].getHealth() <= 0) {
                                if (enemy.getAttack() < user.getDefense()) {
                                System.out.println("\nThe " + enemy.getName() + "'s attack bounces off your armor!");
                            }
                            System.out.println("\nThe " + enemy.getName() + " attacks you!\n");
                            user.setHealth(user.getHealth() - (enemy.getAttack() - user.getDefense()));
                            }
                            System.out.println("\nThe " + enemy.getName() + " attacks "
                                    + team[target].getName() + "!");
                            team[target].setHealth(team[target].getHealth()
                                    - (enemy.getAttack() - team[target].getDefense()));

                            if (team[target].getHealth() <= 0) {
                                System.out.println(team[target].getName() + " has been defeated!");

                            }
                        }
                        // Enemy attacks player
                        else if (target == 3) {
                            if (enemy.getAttack() < user.getDefense()) {
                                System.out.println("\nThe " + enemy.getName() + "'s attack bounces off your armor!");
                            }
                            System.out.println("\nThe " + enemy.getName() + " attacks you!\n");
                            user.setHealth(user.getHealth() - (enemy.getAttack() - user.getDefense()));
                        }
                    } // End of Enemy Attack

                    // Enemy misses
                    else {
                        System.out.println(
                                "\n" + enemy.getName() + " stands by, waiting for it's chance to strike!");
                    }

                    Functions.delay(1500);
                }
                // End of while(playerTurn == false)
                playerTurn = true;
                break;
            } // End of enemy turn

            // #endregion

            // Player dies
            if (user.getHealth() <= 0) {
                System.out.println("\nYou have been defeated! Game Over.");
                battleState = false;
                return false;
            } // End of player dies

            // Reset team's defense
            for (int x = 0; x < team.length; x++) {
                if (team[x].getDefense() > 8) {
                    team[x].setDefense(8);
                }
            } // End of defense reset
        }
        return true;
    }

    public static boolean higherBattle(String playerInput, boolean battleState, boolean gameState, Enemy enemy,
            Player user, Scanner keys, Random rnd) {
        boolean playerTurn = true, successfulDodge = false;
        while (battleState) {
            // Player Turn
            while (playerTurn) {
                // Delay
                Functions.delay(1500);

                // Prints enemy info
                System.out.println("The " + enemy.getName() + " stands before you!\n" + enemy.getName() + "\nHP:"
                        + enemy.getHealth() + "\nATK:" + enemy.getAttack() + " AGI:" + enemy.getAgility());

                // Delay
                Functions.delay(1500);

                // Prints player info
                System.out.println("\n" + user.getName() + "\nLVL: " + user.getLevel() + " XP: " + user.getXp()
                        + "\nHP: " + user.getHealth() + " ATK: " + user.getAttack() + "\nDEF: " + user.getDefense()
                        + " AGI: " + user.getAgility() + "\n\n What do you do? (Attack, Dodge, Run)");
                playerInput = keys.nextLine();

                // Attack
                if (getActions(playerInput) == 1) {
                    System.out.println("\nYou attack the " + enemy.getName() + "!");
                    enemy.setHealth(enemy.getHealth() - (user.getAttack() - enemy.getDefense()));
                    playerTurn = false;
                    break;
                }
                // Dodge
                else if (getActions(playerInput) == 2) {
                    if (rnd.nextInt(enemy.getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nThe enemy is faster than that! You still get hit!");
                        successfulDodge = false;
                    } else {
                        System.out.println("\nYou move to dodge the attack!");
                        successfulDodge = true;
                    }
                    playerTurn = false;
                    break;
                }
                // Run
                else if (getActions(playerInput) == 3) {
                    System.out.println("\nYou try to run...\n");
                    try {
                        System.out.print(".");
                        Thread.sleep(1500);
                        System.out.print("\b..");
                        Thread.sleep(1500);
                        System.out.print("\b\b...\n");
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    if (rnd.nextInt(enemy.getAgility() + 1) >= user.getAgility()) {
                        System.out.println("\nYou're unable to run away!");
                        playerTurn = false;
                    } else {
                        System.out.println("\nYou successfully run away!");
                        battleState = false;
                        break;
                    }
                }
                // Invalid input
                else {
                    System.out.println("\nInvalid action. Please try again.");
                }

                playerTurn = false;
                break;
            } // End of while(playerTurn)

            // Ends the battle if player runs
            if (battleState == false) {
                break;
            }

            // Ends the battle if enemy dies
            if (enemy.getHealth() <= 0) {
                System.out.println("\nYou defeated the " + enemy.getName() + "! You got " + enemy.getXpValue()
                        + " XP and " + enemy.getGoldValue() + " gold.");
                user.setXp(user.getXp() + enemy.getXpValue());
                user.setGold(user.getGold() + enemy.getGoldValue());
                battleState = false;
                break;
            }
            // Delay
            Functions.delay(2000);

            // Enemy Turn
            while (playerTurn == false) {
                // User failed dodge, enemy auto attacks
                if (!successfulDodge) {
                    System.out.println("\nThe " + enemy.getName() + " attacks you! You can't defend yourself!\n");
                    user.setHealth(user.getHealth() - enemy.getAttack());
                    playerTurn = true;
                    break;
                }

                if (successfulDodge) {
                    System.out.println("\nYou dodge the " + enemy.getName() + "'s attack!");
                    successfulDodge = false;
                    playerTurn = true;
                    break;
                }

                // Enemy Attacks
                if (rnd.nextInt(2) + 1 == 1) {

                    System.out.println("\nThe " + enemy.getName() + " attacks you!\n");
                    user.setHealth(user.getHealth() - (enemy.getAttack() - user.getDefense()));

                } else {
                    System.out.println("\nThe " + enemy.getName() + " misses it's attack!\n");
                }

                playerTurn = true;
                break;
            } // End of while(playerTurn == false)

        }
        return true;
    }
}
