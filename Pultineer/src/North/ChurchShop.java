package North;
import playerInfo.*;

public class ChurchShop {
//Variables
private String message;
private String[][] churchShop = {{"Steel sword", "30", "20"}, {"Iron Chestpiece", "50", "20"}, {"Banded shield", "20", "7"}};

//Getters
public String getMessage() {
    return message;
}

public String getShopItem(int x, int y){
    return churchShop[x][y];
}

//Setters
public void setMessage(String message) {
    this.message = message;
}

//Constructors
public ChurchShop(){
    message = "You walk inside the church to find the regular elegant decor expected to be found inside a church. Off to the right, you see the \npseudo-armory where the church sells spare gear. You walk up to the counter and talk to the man there.";
}

//Parameterized
public ChurchShop(String message){
    this.message = message;
}

//Methods

public void printGoods(Player user) {

    // Wooden Sword
    System.out.println(churchShop[0][0] + " - " + churchShop[0][1] + " gold - +" + churchShop[0][2] + " ATK");
    // Leather Armor
    System.out.println(churchShop[1][0] + " - " + churchShop[1][1] + " gold - +" + churchShop[1][2] + " DEF");
    // Health Potion
    System.out.println(churchShop[2][0] + " - " + churchShop[2][1] + " gold - +" + churchShop[2][2] + " DEF");
    //Exit Message
    System.out.println("You have " + user.getGold() + " gold.(EXIT to leave)");
}//End of printGoods
}//End of class