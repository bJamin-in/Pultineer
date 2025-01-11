package Funcs;

import java.util.Arrays;

import playerInfo.Player;

public class Functions {

    //Function Variables
    private String[] yesAnswers = { "yes", "okay", "alright", "accept", "yeah" }, contAnswers = { "continue", "forward" },
    noAnswers = { "no", "nevermind", "deny", "nah" }, backAnswers = { "back", "return", "reverse" };

    //#region Getters
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
    //#endregion Getters
    // Delay
    public static void delay(int x) {
        // Functionality: Sleeps the thread to delay output
        try {
            Thread.sleep(x);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }// End of delay(int x)

    // #region ArrayMethods
    // ^ getDirections: ARRAY METHODS
    // Move player in one of four different directions: 1 array
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

    // Move player in one of four different directions: 3 Arrays
    public static int get4Direction(String playerInput, Player user, String keyword1, String[] keywords2,
            String[] keywords3,
            String[] keywords4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        if (playerInput.toLowerCase().contains(keyword1)) {
            return 1;
        }
        // 2
        for (int x = 0; x < keywords2.length; x++) {
            if (playerInput.toLowerCase().contains(keywords2[x])) {
                return 2;
            }
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

    // Move player in one of four different directions: 4 Arrays
    public static int get4Direction(String playerInput, Player user, String keywords1[], String[] keywords2,
            String[] keywords3,
            String[] keywords4) {
        // Functionality: Recieve input from the player and send them in a specified
        // direction
        // 1
        for (int x = 0; x < keywords1.length; x++) {
            if (playerInput.toLowerCase().contains(keywords1[x])) {
                return 0;
            }
        }
        // 2
        for (int x = 0; x < keywords2.length; x++) {
            if (playerInput.toLowerCase().contains(keywords2[x])) {
                return 1;
            }
        }
        // 3
        for (int x = 0; x < keywords3.length; x++) {
            if (playerInput.toLowerCase().contains(keywords3[x])) {
                return 2;
            }
        }

        for (int x = 0; x < keywords4.length; x++) {
            // 4
            if (playerInput.toLowerCase().contains(keywords4[x])) {
                return 3;
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

    //Allows the player to answer a yes or no question with other answers similar to yes or no
    public static int yesOrNo(String playerInput){
        //Functionality: Allows the player to answer a yes/no type question with similar answers to yes or no

        Functions func = new Functions();
        for(int x = 0; x < func.yesAnswers.length; x++){
            if(playerInput.toLowerCase().contains(func.yesAnswers[x])){
                return 0;
            }
        }
        for (int x = 0; x < func.noAnswers.length; x++){
            if(playerInput.toLowerCase().contains(func.noAnswers[x])){
                return 1;
            }
        }
        return -1;
    }//End of get2Directions: 2 arrays

    public static boolean checkArray(String[] array, String input){
        int matchedWords = 0;
        for(int x = 0; x < array.length; x++){
            if(input.toLowerCase().contains(array[x])){
                matchedWords++;
            }
        }
        if(matchedWords == 1){
            return true;
        }
        else{
            return false;
        }
    }
    // #endregion ArrayMethods

    //Removes a specified element of an array
    public static String[][] removeArrayElement(String[][] array, int removedIndex){        
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
    }//End of removeArrayElement

    // Move player
    public static void movePlayer(int dx, int dy, Player user) {
        user.setPlayerX(user.getPlayerX() + dx);
        user.setPlayerY(user.getPlayerY() + dy);
    }// End of movePlayer
    
}// End of class
