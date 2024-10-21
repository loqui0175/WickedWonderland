public class SnowWhite extends Character {
    public SnowWhite() {
        super("Snow White", 200, 100);
    }


    @Override
    public void specialSkill1(Enemy enemy) {
        System.out.println("Snow White uses Poisoned Apple Strike!");
        enemy.receiveDamage(20);
        useMana(10);
    }

    @Override
    public void specialSkill2(Enemy enemy) {
        System.out.println("Snow White uses Enchanted Healing!");
        restoreHealth(25);
        useMana(15);
    }

    @Override
    public void specialSkill3(Enemy enemy) {
        System.out.println("Snow White uses Forest's Aid!");
        enemy.receiveDamage(30);
        useMana(20);
    }

    public void applyPoisonEffect(Enemy enemy) {
        System.out.println("The poison weakens " + enemy.getName() + " over time!");
        enemy.receiveDamage(15);
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
