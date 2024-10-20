

public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attackDamage;

    public Enemy(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public abstract void attack(Character player);

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void receiveDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

