



public class EvilQueen extends Enemy {
    public EvilQueen() {
        super("Evil Queen", 100, 20);
    }

    @Override
    public void attack(Character player) {
        System.out.println(name + " casts a dark spell!");
        player.receiveDamage(attackDamage);
    }
}

