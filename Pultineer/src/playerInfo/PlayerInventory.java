package playerInfo;

public class PlayerInventory {

    //Variables
    private int emptySpaces = 9, armorValue, weaponValue;
    private String[] inventory = new String[emptySpaces];
    private String equipedArmor, equipedWeapon;

    //Getters
    public int getEmptySpaces() {
        return emptySpaces;
    }
    public String[] getInventory() {
        return inventory;
    }
    public String getEquipedArmor() {
        return equipedArmor;
    }
    public String getEquipedWeapon() {
        return equipedWeapon;
    }
    public int getArmorValue() {
        return armorValue;
    }
    public int getWeaponValue() {
        return weaponValue;
    }

    //Setters
    public void setEmptySpaces(int emptySpaces) {
        this.emptySpaces = emptySpaces;
    }
    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }
    public void setEquipedArmor(String equipedArmor) {
        this.equipedArmor = equipedArmor;
    }
    public void setEquipedWeapon(String equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }
    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }
    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }

    //Constructors
    public PlayerInventory(int emptySpaces, String[] inventory, String equipedArmor, String equipedWeapon, int armorValue, int weaponValue) {
        this.emptySpaces = emptySpaces;
        this.inventory = inventory;
        this.equipedArmor = equipedArmor;
        this.equipedWeapon = equipedWeapon;
        this.armorValue = armorValue;
        this.weaponValue = weaponValue;
    }

    public PlayerInventory() {
        this.emptySpaces = 9;
        this.inventory = new String[emptySpaces];
        this.equipedArmor = "None";
        this.equipedWeapon = "None";
        this.armorValue = 0;
        this.weaponValue = 0;
    }
    
    
}
