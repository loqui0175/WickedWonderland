import java.util.Random;

public class RandomEvent {

    // Random events array
    static String[] events = {
        "A mysterious stranger offers you a powerful item for a price.\nDo you accept the deal?",
        "You stumble upon a magical spring.\nDrinking from it restores 20 health points.",
        "A sudden illusion causes you to attack yourself!\nLose 10 health points.",
        "You find a hidden treasure chest!\nIt contains random items or gold.",
        "Forest spirits grant you a blessing.\nGain 10 mana points.",
        "You are ambushed by a group of enemies!\nEngage in a surprise battle.",
        "You pick up a cursed item that drains your health over time.\nLose 5 health points each turn until you find a way to remove the curse.",
        "A fairy offers to help you in your battle.\nFor the next battle, gain a bonus to your attack power.",
        "A thick fog surrounds you,\ncausing you to lose 5 health points each turn until you escape.",
        "You lose your way and wander for a while.\nMiss a turn while trying to find your way back.",
        "You encounter a friendly NPC who shares valuable information.\nGain knowledge that gives you a strategic advantage.",
        "You encounter an enchanted mirror.\nIt tells you a secret that helps you in your next encounter."
    };

    private Random random;

    // Constructor
    public RandomEvent() {
        random = new Random(); // Initialize Random object
    }

    // Method to display a random event and apply its effects
    public void displayRandomEvent(Character player) {
        int index = random.nextInt(events.length); // Pick a random event
        System.out.println(Text.centerText("RANDOM EVENT: " + events[index]));

        // Trigger specific effects for certain events
        switch (index) {
            case 1 -> player.heal(20); // Restores 20 health points
            case 2 -> player.takeDamage(10); // Lose 10 health points from illusion
            case 4 -> player.gainMana(10); // Gain 10 mana points
            case 6 -> player.takeDamage(5); // Cursed item, lose 5 health each turn
            // Additional cases for other event outcomes can be added here
        }
    }
}
