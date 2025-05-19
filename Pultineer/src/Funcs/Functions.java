package Funcs;

import java.io.File;
import java.util.*;
import javax.sound.sampled.*;

import playerInfo.Player;

@SuppressWarnings("unused")
public class Functions {

    // Function Variables
    private String[] yesAnswers = { "yes", "okay", "alright", "accept", "yeah" },
            contAnswers = { "continue", "forward" },
            noAnswers = { "no", "nevermind", "deny", "nah" }, backAnswers = { "back", "return", "reverse" };

    // #region Getters
    public String[] getYesAnswers() {
        return yesAnswers;
    }

    public String[] getContAnswers() {
        return contAnswers;
    }

    public String[] getNoAnswers() {
        return noAnswers;
    }

    public String[] getBackAnswers() {
        return backAnswers;
    }

    // #endregion Getters
    // Delay
    public static void delay(int x) {
        // Functionality: Sleeps the thread to delay output
        try {
            Thread.sleep(x);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }// End of delay(int x)

    // Rank Up
    public static void rankUp(int rankNum, Player user) {
        // Functionality: Sets the player's rank and gives a description of the new rank
        String[] ranks = { "Wanderer", "Follower", "Disciple", "Squire", "Knight", "Paladin", "General", "Holy Knight",
                "Holy Knight Champion" };

        user.setRank(ranks[rankNum]);
        user.rankDescription(user);
    }

    // #region ArrayMethods
    // ^ getDirections: ARRAY METHODS

    // ~ Four Directions
    // Move player in one of four different directions: 1 array
    public static int get4Direction(String playerInput, String kw1, String kw2, String kw3,
            String[] kws) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(kw1)) {
            return 0;
        }
        // 2
        else if (playerInput.toLowerCase().contains(kw2)) {
            return 1;
        }
        // 3
        else if (playerInput.toLowerCase().contains(kw3)) {
            return 2;
        }

        for (int x = 0; x < kws.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(kws[x])) {
                return 3;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // Move player in one of four different directions: 2 Arrays
    public static int get4Direction(String playerInput, String kw1, String kw2,
            String[] kws3,
            String[] kws4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(kw1)) {
            return 1;
        }
        // 2
        else if (playerInput.toLowerCase().contains(kw2)) {
            return 2;
        }
        // 3
        for (int x = 0; x < kws3.length; x++) {
            if (playerInput.toLowerCase().contains(kws3[x])) {
                return 3;
            }
        }

        for (int x = 0; x < kws4.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(kws4[x])) {
                return 4;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // Move player in one of four different directions: 3 Arrays
    public static int get4Direction(String playerInput, String kw1, String[] kws2,
            String[] kws3,
            String[] kws4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(kw1)) {
            return 1;
        }
        // 2
        for (int x = 0; x < kws2.length; x++) {
            if (playerInput.toLowerCase().contains(kws2[x])) {
                return 2;
            }
        }
        // 3
        for (int x = 0; x < kws3.length; x++) {
            if (playerInput.toLowerCase().contains(kws3[x])) {
                return 3;
            }
        }

        for (int x = 0; x < kws4.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(kws4[x])) {
                return 4;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // Move player in one of four different directions: 4 Arrays
    public static int get4Direction(String playerInput, String kws1[], String[] kws2,
            String[] kws3,
            String[] kws4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        for (int x = 0; x < kws1.length; x++) {
            if (playerInput.toLowerCase().contains(kws1[x])) {
                return 0;
            }
        }
        // 2
        for (int x = 0; x < kws2.length; x++) {
            if (playerInput.toLowerCase().contains(kws2[x])) {
                return 1;
            }
        }
        // 3
        for (int x = 0; x < kws3.length; x++) {
            if (playerInput.toLowerCase().contains(kws3[x])) {
                return 2;
            }
        }

        for (int x = 0; x < kws4.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(kws4[x])) {
                return 3;
            }
        }
        // Invalid input
        System.out.println("\nInvalid direction. Please try again.");
        Functions.delay(1500);
        return 0;
    }// End of get4Direction

    // ~ Three Directions
    // Move player in one of three different directions: 1 Array
    public static int get3Direction(String playerInput, String kw1, String kw2, String[] kws) {
        // Functionality: Recieves input and sends the player in one of three directions
        // 1
        if (playerInput.toLowerCase().contains(kw1)) {
            return 1;
        }
        // 2
        else if (playerInput.toLowerCase().contains(kw2)) {
            return 2;
        }
        // 3
        for (int x = 0; x < kws.length; x++) {
            if (playerInput.toLowerCase().contains(kws[x])) {
                return 3;
            }
        }
        // Invalid Input
        return 4;
    }// End of get3Direction

    // Move player in one of three different directions: 2 Array
    public static int get3Direction(String playerInput, String kw1, String[] kws2, String[] kws) {
        // Functionality: Recieves input and sends the player in one of three directions
        // 1
        if (playerInput.toLowerCase().contains(kw1)) {
            return 1;
        }
        // 2
        for (int x = 0; x < kws2.length; x++) {
            if (playerInput.toLowerCase().contains(kws2[x])) {
                return 3;
            }
        }
        // 3
        for (int x = 0; x < kws.length; x++) {
            if (playerInput.toLowerCase().contains(kws[x])) {
                return 3;
            }
        }
        // Invalid Input
        return 4;
    }// End of get3Direction

    // Allows the player to answer a yes or no question with other answers similar
    // to yes or no
    public static int yesOrNo(String playerInput) {
        // Functionality: Allows the player to answer a yes/no type question with
        // similar answers to yes or no

        Functions func = new Functions();
        for (int x = 0; x < func.yesAnswers.length; x++) {
            if (playerInput.toLowerCase().contains(func.yesAnswers[x])) {
                return 0;
            }
        }
        for (int x = 0; x < func.noAnswers.length; x++) {
            if (playerInput.toLowerCase().contains(func.noAnswers[x])) {
                return 1;
            }
        }
        return -1;
    }// End of get2Directions: 2 arrays

    public static boolean checkArray(String[] array, String input) {
        int matchedWords = 0;
        for (int x = 0; x < array.length; x++) {
            if (input.toLowerCase().contains(array[x])) {
                matchedWords++;
            }
        }
        if (matchedWords == 1) {
            return true;
        } else {
            return false;
        }
    }
    // #endregion ArrayMethods

    // Get direction out of five options
    public static int get5directions(String input, String kw1, String kw2, String kw3, String kw4, String kw5) {
        // Functionality: Recieves input and returns a number depending on the
        // directions that matches the input
        // 1
        if (input.toLowerCase().contains(kw1)) {
            return 1;
        }

        // 2
        else if (input.toLowerCase().contains(kw2)) {
            return 2;
        }

        // 3
        else if (input.toLowerCase().contains(kw3)) {
            return 3;
        }

        // 4
        else if (input.toLowerCase().contains(kw4)) {
            return 4;
        }

        // 5
        else if (input.toLowerCase().contains(kw5)) {
            return 5;
        } else
            return 0;
    }

    // Removes a specified element of an array
    public static String[][] removeArrayElement(String[][] array, int removedIndex) {
        // Create a new array with one less row
        String[][] updatedShopGoods = new String[array.length - 1][];

        int newIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != removedIndex) {
                // Copy rows that are not being removed
                updatedShopGoods[newIndex++] = array[i];
            }
        }

        return updatedShopGoods;
    }// End of removeArrayElement

    public static String stringBreakdown(String input) {
        /*
         *  Functionality: 
         *      Takes a string and manually breaks it down into individual characters
         */
        String output = "";
        for (int x = 0; x < input.length(); x++) {
            output = output + " " + input.charAt(x);
        }
        return output;
    }

    public static void createSound(String input, int clipNum, int speed) {
    //* Functionality:
    //*     Adds a specified sound effect to text while delaying the output of the text
    //*     Overloaded function: This specific function has an additional variable that gives the option of how much
    //*     of a delay is wanted
        try {
            String keyPath = "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Pultineer\\src\\Audio\\keyClick.wav";
        String talkingPath = "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Pultineer\\src\\Audio\\talkingSound.wav";

        AudioInputStream keyClick = AudioSystem.getAudioInputStream(new File(keyPath).getAbsoluteFile());
        AudioInputStream talkingSounds = AudioSystem.getAudioInputStream(new File(talkingPath).getAbsoluteFile());

        Clip narration = AudioSystem.getClip();
        Clip talking = AudioSystem.getClip();

        narration.open(keyClick);
        talking.open(talkingSounds);
        
        int charCount = 0;
        Random rnd = new Random();
        if (clipNum == 1) {
            narration.loop(Clip.LOOP_CONTINUOUSLY);
            narration.start();

            for (int x = 0; x < input.length(); x++) {
                if(input.charAt(x) == '~'){
                    // System.out.println();
                    input = replaceChar(input, x, ' ');
                    charCount = 0;
                }

                else if (charCount > 110) {
                    System.out.println();
                    charCount = 0;
                }

                charCount++;
                System.out.print(input.charAt(x));
                Functions.delay(speed);
            }

            narration.stop();
        }
        else if(clipNum == 2){
            talking.loop(Clip.LOOP_CONTINUOUSLY);
            talking.start();
            for (int x = 0; x < input.length(); x++) {
                if(input.charAt(x) == '~'){
                    input = replaceChar(input, x, ' ');
                    charCount = 0;
                    System.out.println();
                }
                if (charCount > 110) {
                    System.out.println();
                    charCount = 0;
                }
                charCount++;
                System.out.print(input.charAt(x));
                Functions.delay(speed);
            }
            talking.stop();
        }
        narration.close();
        talking.close();
        } catch (Exception e) {
            System.exit(404);
        }
        
    }

    public static String replaceChar(String input, int charNum, Character replacement){
        /* Functionality:  
        *     Converts a string to a string buffer
        *     String buffer replaces character at specified location with specified character replacement
        *     Re-converts back from string buffer to string and returns string with replaced character
        */
        String output = "";
        StringBuffer stringReplacer = new StringBuffer(input);

        stringReplacer.setCharAt(charNum, replacement);
        output = stringReplacer.toString();
        return output;
    } 

    // Text Sound(No variable speed)
    public static void createSound(String input, int clipNum) {
        try {
            String keyPath = "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Pultineer\\src\\Audio\\keyClick.wav";
        String talkingPath = "C:\\Users\\benja\\OneDrive\\Documents\\GitHub\\Pultineer\\Pultineer\\src\\Audio\\talkingSound.wav";

        AudioInputStream keyClick = AudioSystem.getAudioInputStream(new File(keyPath).getAbsoluteFile());
        AudioInputStream talkingSounds = AudioSystem.getAudioInputStream(new File(talkingPath).getAbsoluteFile());

        Clip narration = AudioSystem.getClip();
        Clip talking = AudioSystem.getClip();

        narration.open(keyClick);
        talking.open(talkingSounds);
        int charCount = 0;
        if(clipNum == 1){
        for (int x = 0; x < input.length(); x++) {
            if(input.charAt(x) == '~'){
                    input = replaceChar(input, x, ' ');
                    charCount = 0;
                    // System.out.println();
                }
            if (charCount > 110) {
                System.out.println();
                charCount = 0;
            }
            charCount++;
            narration.start();
            System.out.print(input.charAt(x));
            Functions.delay(50);
            narration.stop();
            narration.setFramePosition(0);
        }
        }
        if(clipNum == 2){
        for (int x = 0; x < input.length(); x++) {
            if(input.charAt(x) == '~'){
                    input = replaceChar(input, x, ' ');
                    charCount = 0;
                    System.out.println();
                }
            if (charCount > 110) {
                System.out.println();
                charCount = 0;
            }
            charCount++;
            talking.start();
            System.out.print(input.charAt(x));
            Functions.delay(50);
            talking.stop();
            talking.setFramePosition(0);
        }
        }
        narration.close();
        talking.close();
        } catch (Exception e) {
            System.exit(404);
        }
        
    }

    // Move player
    public static void movePlayer(int dx, int dy, Player user) {
        user.setPlayerX(user.getPlayerX() + dx);
        user.setPlayerY(user.getPlayerY() + dy);
    }// End of movePlayer

}// End of class
