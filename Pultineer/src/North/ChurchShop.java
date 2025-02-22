package North;

public class ChurchShop {
//Variables
private String message;
private String[][] churchShop = {{"sword", "30", "20"}, {"armor", "50", "20"}, {"shield", "20", "7"}};

//Getters
public String getMessage() {
    return message;
}

//Setters
public void setMessage(String message) {
    this.message = message;
}

//Constructors
public ChurchShop(){}

//Parameterized
public ChurchShop(String message){
    this.message = message;
}

//Methods

public void printGoods() {

    // Wooden Sword
    System.out.println(churchShop[0][0] + " - " + churchShop[0][1] + " gold - +" + churchShop[0][2] + " ATK");
    // Leather Armor
    System.out.println(churchShop[1][0] + " - " + churchShop[1][1] + " gold - +" + churchShop[1][2] + " DEF");
    // Health Potion
    System.out.println(churchShop[2][0] + " - " + churchShop[2][1] + " gold - +" + churchShop[2][2] + " DEF");
}//End of printGoods
}//End of class