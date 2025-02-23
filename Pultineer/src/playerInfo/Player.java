package playerInfo;
public class Player {

    //Variables
    private String name, rank;

    private int health, totalHealth, attack, defense, agility, level, xp, gold, xpLimit;

    protected int playerX, playerY, guardFavor;

    //NPC, Quest, and Location Related variables
    protected boolean hasMetGherald = false, hasSnuckAround = false, goneThroughHole = false,
     questAccepted = false, sideQuestAccepted = false, sideQuestItem = false, hasQuestItem = false, boardUnlocked = false;
    
    //#region Getters
    //Stats
    public String getName() {
        return name;
    }
    public String getRank() {
        return rank;
    }
    public int getHealth() {
        return health;
    }
    public int getTotalHealth() {
        return totalHealth;
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
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }
    public int getGold() {
        return gold;
    }
    public int getXpLimit() {
        return xpLimit;
    }
    public int getPlayerY() {
        return playerY;
    }
    public int getPlayerX() {
        return playerX;
    }

    //NPC's
    public int getGuardFavor() {
        return guardFavor;
    }
    public boolean getHasMetGherald() {
        return hasMetGherald;
    }

    //Locations
    public boolean getHasSnuckAround() {
        return hasSnuckAround;
    }
    public boolean getGoneThroughHole() {
        return goneThroughHole;
    }
    public boolean getBoardUnlocked() {
        return boardUnlocked;
    }

    //Quest
    public boolean getQuestAccepted() {
        return questAccepted;
    }
    public boolean getHasQuestItem() {
        return hasQuestItem;
    }

    public boolean getSideQuestAccepted(){
        return sideQuestAccepted;
    }

    public boolean getSideQuestItem(){
        return sideQuestItem;
    }
    //#endregion Getters
    //#region Setters
    //Setters
    //Player stats
    public void setName(String name) {
        this.name = name;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
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
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setXpLimit(int xpLimit) {
        this.xpLimit = xpLimit;
    }
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    //NPC's
    public void setGuardFavor(int guardFavor) {
        this.guardFavor = guardFavor;
    }
    public void setHasMetGherald(boolean hasMetGherald) {
        this.hasMetGherald = hasMetGherald;
    }

    //Locations
    public void setHasSnuckAround(boolean hasSnuckAround) {
        this.hasSnuckAround = hasSnuckAround;
    }
    public void setGoneThroughHole(boolean goneThroughHole) {
        this.goneThroughHole = goneThroughHole;
    }
    public void setBoardUnlocked(boolean boardUnlocked){
        this.boardUnlocked = boardUnlocked;
    }

    //Quests
    public void setQuestAccepted(boolean questAccepted) {
        this.questAccepted = questAccepted;
    }
    public void setHasQuestItem(boolean hasQuestItem) {
        this.hasQuestItem = hasQuestItem;
    }

    public void setSideQuestAccepted(boolean sideQuestAccepted){
        this.sideQuestAccepted = sideQuestAccepted;
    }
    public void setSideQuestItem(boolean sideQuestItem){
        this.sideQuestItem = sideQuestItem;
    }
    //#endregionSetters
    //#region Constructors
    //Constructors
    //Parameterized
    public Player(String name, String rank, int health, int totalHealth, int attack, int defense, int agility, int level, int xp, int gold, int xpLimit, int playerX, int playerY) {
        this.name = name;
        this.health = health;
        this.totalHealth = totalHealth;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.level = level;
        this.xp = xp;
        this.gold = gold;
        this.xpLimit = xpLimit;
        this.playerX = playerX;
        this.playerY = playerY;
        this.rank = rank;
        this.guardFavor = 0;
    }

    public Player(String name, String rank) {
        this.name = name;
        this.rank = rank;
        health = 10;
        totalHealth = 10;
        attack = 1;
        defense = 0;
        agility = 2;
        level = 1;
        xp = 0;
        gold = 0;
        xpLimit = 10;
        playerX = 0;
        playerY = 0;
        guardFavor = 0;
    }


    //^ FOR BATTLE CLASS DO NOT MODIFIY
    public Player(){}

    //#endregion Constructors
    
    @Override
    public String toString() {
        return name + " the " + rank + "\nHealth: " + health + "\nAttack: " + attack + "\nDefense: " + defense + "\nAgility: " + agility + "\nXP: " + xp + "\nLevel: " + level + "\nGold: " + gold;
    }

    public void equipArmor(PlayerInventory inv, Player user){
        //Fuctionality: Equips armor and applies the defensive buff to the player
        if(!inv.getEquipedArmor().equals("None")){
            user.setDefense(inv.getArmorValue() + user.getDefense());
        }
    }

    public void equipWeapon(PlayerInventory inv, Player user){
        //Fuctionality: Equips weapon and applies the attacking buff to the player
        if(!inv.getEquipedWeapon().equals("None")){
            user.setAttack(inv.getWeaponValue() + user.getAttack());
        }
    }

    public void rankDescription(Player user){
        //Fuctionality: Gives a short description for each rank the player can achieve
        //Follower
        if(user.getRank().equals("Follower")){
            System.out.println("You have become a follower. Knowing little of Eryndros, the god of craftsmanship, but showing the strive to learn, \nyour efforts have been acknowledged.");
        }

        //Disciple
        if(user.getRank().equals("Disciple")){
            System.out.println("You are now a Disciple of the religion. You show great commitment and promise to Eryndros, as you start to preach the \nword of the god to those around you.");
        }

        //Squire
        if(user.getRank().equals("Squire")){
            System.out.println("You have become a Squire. You have shown great dedication and knowledge to the Church, and have been given the honor of serving the Church's conquerers.");
        }

        //Knight
        if(user.getRank().equals("Knight")){
            System.out.println("You have become a Knight. You have shown your servitute to the Church of Eryndros, and have been given the privilge of serving as one of the Church's conquerers.");
        }

        //Paladin
        if(user.getRank().equals("Paladin")){
            System.out.println("You have become a Paladin. You have proven your skill in combat, and have been given command over a small squadron of Knights and Squires.");
        }

        //General
        if(user.getRank().equals("General")){
            System.out.println("You have become a General. You have been proven competent with your leadership capabilities, and have been given command over a battalion of Paladins and Knights.");
        }

        //Holy Knight
        if(user.getRank().equals("Holy Knight")){
            System.out.println("You have become a Holy Knight. You have proven your skill with a sword and might with your mind. You are now a part \nof the Eryndros' secret order of Holy Knights.");
        }

        //Holy Knight Champion
        if(user.getRank().equals("Holy Knight Champion")){
            System.out.println("You are now the Holy Knight Champion. You have bested Eryndros' Champion, and have now taken his place.");
        }
    }

}//End of class
