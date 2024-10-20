public class EnemyMinion extends Enemy {

   
    public EnemyMinion() {
        super("Enemy Minion", 40, 15);
    }

    @Override
    public void attack(Character player) {
        System.out.println(Text.centerText(80 ,name + " attacks with royal fury!"));
        player.receiveDamage(attackDamage);
    }
    
}


