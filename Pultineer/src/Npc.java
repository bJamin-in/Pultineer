public class Npc {
//Variables

    private int health;
    private int attack;
    private int defense;
    private int agility;

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

    public void setName(String name) {
        this.name = name;
    }

//Constructors

    public Npc(int health, int attack, int defense, int agility, String name) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.name = name;
    }

    public Npc() {
    }

//toString

@Override
    public String toString() {
        return name + "\n" + "HP: " + health + " " + "ATK: " + attack + "\n" + "DEF: " + defense + " " + "AGI: " + agility;
    }

}//End of Class