public class SnowWhite extends Character {
    public SnowWhite() {
        super("Snow White", 200, 100);
    }

    public static final String SNOW_WHITE_ATTACKS = 
    "1. Poisoned Apple Strike\n2. Enchanted Healing\n3. Forest's Aid";

    @Override
    public void specialSkill1(Enemy enemy) {
        System.out.println("Snow White uses Poisoned Apple Strike!");
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
        useMana(20);
    }

    public void applyPoisonEffect(Enemy enemy) {
        System.out.println("The poison weakens " + enemy.getName() + " over time!");
        enemy.receiveDamage(10);
    }
}
