package playerInfo;

public class PlayerInventory {

    //Variables
    private int emptySpaces = 9, armorValue, weaponValue, shieldValue;
    private String[] inventory = new String[emptySpaces];
    private String equippedArmor, equippedWeapon, equippedShield;

    //Getters
    public int getEmptySpaces() {
        return emptySpaces;
    }
    public String[] getInventory() {
        return inventory;
    }
    public String getEquipedArmor() {
        return equippedArmor;
    }
    public String getEquipedWeapon() {
        return equippedWeapon;
    }
    public int getArmorValue() {
        return armorValue;
    }
    public int getWeaponValue() {
        return weaponValue;
    }
    public int getShieldValue() {
        return shieldValue;
    }
    public String getEquippedShield() {
        return equippedShield;
    }

    //Setters
    public void setEmptySpaces(int emptySpaces) {
        this.emptySpaces = emptySpaces;
    }
    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }
    public void setEquipedArmor(String equippedArmor) {
        this.equippedArmor = equippedArmor;
    }
    public void setEquipedWeapon(String equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }
    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }
    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }
    public void setShieldValue(int shieldValue){
        this.shieldValue = shieldValue;
    }
    public void setEquippedShield(String equippedShield){
        this.equippedShield = equippedShield;
    }

    public void setArmor(int armorValue, String equippedArmor){
        this.armorValue = armorValue;
        this.equippedArmor = equippedArmor;
    }
    public void setShield(int shieldValue, String equippedShield){
        this.shieldValue = shieldValue;
        this.equippedShield = equippedShield;
    }

    public void setWeapon(int weaponValue, String equippedWeapon){
        this.weaponValue = weaponValue;
        this.equippedWeapon = equippedWeapon;
    }

    //Constructors
    public PlayerInventory(int emptySpaces, String[] inventory, String equippedArmor, String equippedWeapon, int armorValue, int weaponValue) {
        this.emptySpaces = emptySpaces;
        this.inventory = inventory;
        this.equippedArmor = equippedArmor;
        this.equippedWeapon = equippedWeapon;
        this.armorValue = armorValue;
        this.weaponValue = weaponValue;
    }

    public PlayerInventory() {
        this.emptySpaces = 9;
        this.inventory = new String[emptySpaces];
        this.equippedArmor = "None";
        this.equippedWeapon = "None";
        this.equippedShield = "None";
        this.armorValue = 0;
        this.weaponValue = 0;
        this.shieldValue = 0;
    }
    
    
}
