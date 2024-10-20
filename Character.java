

public abstract class Character {
    protected String name;
    protected int health;
    protected int mana;
    private int maxHealth;
    private int maxMana;

    public Character(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.maxHealth = health; // Set max health based on initial value
        this.maxMana = mana;     // Set max mana based on initial value
    }

    // Abstract methods to be implemented by subclasses for character-specific skills
    public abstract void specialSkill1(Enemy enemy);
    public abstract void specialSkill2(Enemy enemy);
    public abstract void specialSkill3(Enemy enemy);

    // Getter methods for name, health, and mana
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    // Setters for health and mana (with validation)
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth); // Ensure health does not exceed maxHealth
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, maxMana); // Ensure mana does not exceed maxMana
    }

    // Receive damage and reduce health
    public void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Prevent health from going below zero
        }
    }

    // Restore health but don't exceed maxHealth
    public void restoreHealth(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth; // Ensure health doesn't go over max
        }
    }

    // Use mana and reduce it
    public void useMana(int amount) {
        mana -= amount;
        if (mana < 0) {
            mana = 0; // Prevent mana from going below zero
        }
    }

    // Check if the character is alive
    public boolean isAlive() {
        return health > 0;
    }

    // Adjust health by a specified amount
    public void adjustHealth(int amount) {
        health += amount; 
        if (health < 0) {
            health = 0; // Prevent health from going below zero
        } else if (health > maxHealth) {
            health = maxHealth; // Ensure health doesn't exceed maxHealth
        }
    }

    // Adjust mana by a specified amount
    public void adjustMana(int amount) {
        mana += amount; 
        if (mana < 0) {
            mana = 0; // Prevent mana from going below zero
        } else if (mana > maxMana) {
            mana = maxMana; // Ensure mana doesn't exceed maxMana
        }
    }

    // Getter methods for max health and mana
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    // Restore full health and mana
    public void restoreFullHealth() {
        health = maxHealth;
    }

    public void restoreFullMana() {
        mana = maxMana;
    }

    // Methods to increase max health and max mana
    public void increaseMaxHealth(int amount) {
        maxHealth += amount;
        health = maxHealth; // Optional: Adjust current health to match new max
    }

    public void increaseMaxMana(int amount) {
        maxMana += amount;
        mana = maxMana; // Optional: Adjust current mana to match new max
    }

    // Implement the missing methods based on GameController's need
    public void increaseHealth(int amount) {
        adjustHealth(amount);
    }

    public void decreaseHealth(int amount) {
        adjustHealth(-amount);
    }

    public void increaseMana(int amount) {
        adjustMana(amount);
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(Text.centerText(name + " has been healed by " + amount + " points."));
    }

    // Method to take damage
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0; // Ensure health does not go below 0
        }
        System.out.println(Text.centerText(name + " has taken " + amount + " damage."));
    }

    // Method to gain mana
    public void gainMana(int amount) {
        mana += amount;
        System.out.println(Text.centerText(name + " has gained " + amount + " mana points."));
    }

}





