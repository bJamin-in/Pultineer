public class Enemy {
    private int health;
    private int attack;
    private int defense;
    private int agility;
    private int xpValue;
    private int goldValue;

    private String name;

    //Getters
    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getAgility() {
        return agility;
    }

    public int getXpValue() {
        return xpValue;
    }

    public int getGoldValue() {
        return goldValue;
    }

    public String getName() {
        return name;
    }

    //Setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setXpValue(int xpValue) {
        this.xpValue = xpValue;
    }

    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enemy(int health, int attack, int defense, int agility, int xpValue, int goldValue, String name) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.xpValue = xpValue;
        this.goldValue = goldValue;
        this.name = name;
    }

    public Enemy() {
    }

    @Override
    public String toString() {
        return name + "\n" + "Health: " + health + "\n" + "Attack: " + attack + "\n" + "Defense: " + defense + "\n" + "Agility: " + agility;
    }

    
    
}
