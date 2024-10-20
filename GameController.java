import java.util.Random;
import java.util.Scanner;

public class GameController extends Dialogue implements GameInterface {
    private Character player;
    private Enemy enemy;
    private final Scanner scanner;
    private final Random random;
    private final RandomEvent randomEvent; // Only declare it once

    public GameController() {
        scanner = new Scanner(System.in);
        random = new Random();
        randomEvent = new RandomEvent(); // Initialize RandomEvent instance
    }

    @Override
    public void startGame() {
        showMenu();
    }

    // Default character is set to Cinderella
    public void setDefaultCharacter() {
        player = new Cinderella(); // Set default character here
    }

    @Override
    public void showMenu() {
        for (String line : gameIntro) {
            System.out.println(Text.centerBox(line));
            scanner.nextLine();
        }

        System.out.println(Text.centerText("WELCOME TO WICKED WONDERLAND!\n1. Start Game\n2. Exit\nChoose an option: "));
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (choice == 1) {
            setDefaultCharacter(); // Automatically set the default character
            System.out.println(Text.centerText("Choose Your World:\n1. Cinderella: The Shattered Palace\n2. Mad Wonderland\n3. Snow White: Not So White"));
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> worldOne();
                case 2 -> worldTwo(); // Placeholder for Mad Wonderland
                case 3 -> worldThree();
                default -> System.out.println("Please choose a valid world.");
            }
        } else {
            System.out.println(Text.centerText("Goodbye!"));
        }
    }
    public void worldOne() {
        boolean continueAdventure = true;
    
        while (continueAdventure) {
            // Set Prince Henry as the first enemy
            enemy = new PrinceHenry();
            System.out.println(Text.centerBox("Entering Cinderella: The Shattered Palace..."));
            displayDialogue(introCinderella);
            continueAdventure = battleSequence();
    
            // If Prince Henry is defeated, continue with the minion battle
            if (continueAdventure) {
                enemy = new EnemyMinion(); // Set next enemy as Dark Minion
                continueAdventure = battleSequence(); // Continue battling the minion
            }
    
            if (!continueAdventure) {
                System.out.println(Text.centerText("You have been defeated. Restarting game..."));
                // Restart the game if the player is defeated
                showMenu();  // Show the menu to restart the game
            }
        }
    }

    public void worldTwo() {
        // Placeholder for Mad Wonderland implementation
        System.out.println(Text.centerBox("Entering Mad Wonderland...")); // Add actual dialogue and implementation
        boolean continueAdventure = true;
        while (continueAdventure) {
            // Implement the Mad Wonderland gameplay here
            continueAdventure = false; // Set this appropriately based on your gameplay logic
        }
    }

    public void worldThree() {
        boolean continueAdventure = true;
        while (continueAdventure) {
            enemy = new EvilQueen(); // Use EvilQueen as enemy for Snow White's world
            System.out.println(Text.centerBox("Entering Snow White: The Enchanted Forest..."));
            displayDialogue(introSnowWhite);
            continueAdventure = journeySnowWhite();
        }
    }

    public boolean journeyCinderella() {
        boolean firstLineDisplayed = false;

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            if (!firstLineDisplayed) {
                displayDialogue(battleStart);
                firstLineDisplayed = true;
            }

            // Cinderella's unique attack options
            String attackOptions = "1. Glass Shard Strike\n2. Enchanted Resilience\n3. Illusionary Escape";

            System.out.println(Text.centerText("CHOOSE YOUR ATTACK:\n" + attackOptions + "\nChoose your action: "));
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (player.getMana() <= 0 && action != 1) {
                System.out.println(Text.centerText("You don't have enough mana. You can only use basic attack!"));
                action = 1;
            }

            switch (action) {
                case 1 -> {
                    player.specialSkill1(enemy); // Glass Shard Strike
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack

                }
                case 2 -> {
                    player.specialSkill2(enemy); // Enchanted Resilience
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                case 3 -> {
                    player.specialSkill3(enemy); // Illusionary Escape
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                default -> {
                    System.out.println(Text.centerText("Invalid action!"));
                    continue;
                }
            }

            displayPlayerStatus(); // Show player status after the attack
            displayenemyStatus();

            if (enemy.isAlive()) {
                enemy.attack(player);
                randomEvent.displayRandomEvent(player); // Trigger random event after enemy attack
                displayPlayerStatus(); // Show status after enemy's turn
            }

            if (!player.isAlive()) {
                System.out.println(Text.centerText(player.getName() + " has been defeated..."));
                return false;
            }

            if (!enemy.isAlive()) {
                System.out.println(Text.centerText(enemy.getName() + " has been defeated!\nYou can continue your adventure!"));
                return true;
            }
        }
        return true;
    }

    public boolean journeySnowWhite() {
        boolean firstLineDisplayed = false;

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            if (!firstLineDisplayed) {
                displayDialogue(battleStart);
                firstLineDisplayed = true;
            }

            // Snow White's unique attack options
            String attackOptions = "1. Poisoned Apple Strike\n2. Enchanted Healing\n3. Forest's Aid";

            System.out.println(Text.centerText("CHOOSE YOUR ATTACK:\n" + attackOptions + "\nChoose your action: "));
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (player.getMana() <= 0 && action != 1) {
                System.out.println(Text.centerText("You don't have enough mana. You can only use basic attack!"));
                action = 1;
            }

            switch (action) {
                case 1 -> {
                    player.specialSkill1(enemy); // Poisoned Apple Strike
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                case 2 -> {
                    player.specialSkill2(enemy); // Enchanted Healing
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                case 3 -> {
                    player.specialSkill3(enemy); // Forest's Aid
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                default -> {
                    System.out.println(Text.centerText("Invalid action!"));
                    continue;
                }
            }

            displayPlayerStatus(); // Show player status after the attack

            if (enemy.isAlive()) {
                enemy.attack(player);
                randomEvent.displayRandomEvent(player); // Trigger random event after enemy attack
                displayPlayerStatus(); // Show status after enemy's turn
            }

            if (!player.isAlive()) {
                System.out.println(Text.centerText(player.getName() + " has been defeated..."));
                return false;
            }

            if (!enemy.isAlive()) {
                System.out.println(Text.centerText(enemy.getName() + " has been defeated!\nYou can continue your adventure!"));
                return true;
            }
        }
        return true;
    }

    public boolean battleSequence() {
        boolean firstLineDisplayed = false;
    
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println();
            if (!firstLineDisplayed) {
                displayDialogue(battleStart);
                firstLineDisplayed = true;
            }


            String attackOptions = "1. Glass Shard Strike\n2. Enchanted Resilience\n3. Illusionary Escape";
    
            System.out.println(Text.centerText("CHOOSE YOUR ATTACK:\n" + attackOptions + "\nChoose your action: "));
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            if (player.getMana() <= 0 && action != 1) {
                System.out.println(Text.centerText("You don't have enough mana. You can only use basic attack!"));
                action = 1;
            }
    
            switch (action) {
                case 1 -> {
                    player.specialSkill1(enemy); // Glass Shard Strike
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                case 2 -> {
                    player.specialSkill2(enemy); // Enchanted Resilience
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                case 3 -> {
                    player.specialSkill3(enemy); // Illusionary Escape
                    randomEvent.displayRandomEvent(player); // Trigger random event after player attack
                }
                default -> {
                    System.out.println(Text.centerText("Invalid action!"));
                    continue;
                }
            }
    
            displayPlayerStatus(); // Show player status after the attack
            displayenemyStatus();
    
            if (enemy.isAlive()) {
                enemy.attack(player);
                randomEvent.displayRandomEvent(player); // Trigger random event after enemy attack
                displayPlayerStatus(); // Show status after enemy's turn
            }
    
            if (!player.isAlive()) {
                System.out.println(Text.centerText(player.getName() + " has been defeated..."));
                return false;  // Player defeated
            }
    
            if (!enemy.isAlive()) {
                System.out.println(Text.centerText(enemy.getName() + " has been defeated!\nYou can continue your adventure!"));
                return true;  // enemy defeated, continue game
            }
        }
        return true;
    }

    @Override
    public boolean journey() {
        // This method can remain if you want to generalize other characters or worlds in the future
        return true;
    }

    private void displayPlayerStatus() {
        System.out.println(Text.centerText(60,"Player Status: \nHealth: " + player.getHealth()+"\nMana: " + player.getMana()));
    }

    private void displayenemyStatus() {
        System.out.println(Text.centerText(60,"enemy Status: \nHealth: " + enemy.getHealth()));
    }

    public class RandomEvent {
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

        public void displayRandomEvent(Character player) {
            int index = random.nextInt(events.length);
            System.out.println(Text.centerText("RANDOM EVENT: " + events[index]));

            // Trigger specific effects for certain events
            switch (index) {
                case 1 -> player.heal(20); // Restores 20 health points
                case 2 -> player.takeDamage(10); // Lose 10 health points from illusion
                case 4 -> player.gainMana(10); // Gain 10 mana points
                case 6 -> player.takeDamage(5); // Cursed item, lose 5 health each turn
            }
        }
    }

}