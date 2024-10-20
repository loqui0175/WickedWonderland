public class Minion extends Enemy {
    public Minion() {
        super("Minion", 100, 20);
    }

    @Override
    public void attack(Character player) {
        System.out.println(name + " casts a dark spell!");
        player.receiveDamage(attackDamage);
    }
}

