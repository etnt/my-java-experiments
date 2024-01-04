package etnt;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Dungeon {
    public static void main(String[] args) {
        Room room1 = new Room("Room 1", "You are in a dark room. There is a door to the east.");
        room1.addItem(new Item("key"));
        room1.addItem(new Item("sword"));

        Room room2 = new Room("Room 2", "You are in a dark room. There is a door to the west and a door to the east.");
        room2.addItem(new Item("potion"));
        room2.setCreature(new Creature("Dragon", 200));

        Room room3 = new Room("Room 3", "You are in a dark room. There is a door to the west.");
        room3.addItem(new Item("coins"));
        room3.setCreature(new Creature("Troll", 100));

        Door door1 = new Door(room1, room2);
        Door door2 = new Door(room2, room3);

        room1.addDoor("east", door1);
        room2.addDoor("west", door1);
        room2.addDoor("east", door2);
        room3.addDoor("west", door2);

        Player player = new Player(100);  // player starts with 100 health points

        Room currentRoom = room1;
        System.out.println("You are in " + currentRoom.getName());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("quit")) {
                break;
            } else if (command.startsWith("go ")) {
                String direction = command.substring(3);
                Room nextRoom = currentRoom.go(direction);
                if (nextRoom != null) {
                    currentRoom = nextRoom;
                    System.out.println("You go " + direction + ". You are now in " + currentRoom.getName());
                    System.out.println(currentRoom.getDescription());
                    Creature creature = currentRoom.getCreature();
                    if (creature != null) {
                        System.out.println("You see a " + creature.getName() + ".");
                    }
                    System.out.println("You see the following items: " + currentRoom.getItems().stream().map(Item::getName).collect(Collectors.joining(", ")));
                } else {
                    System.out.println("There is no door in that direction.");
                }
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);
                Item item = currentRoom.removeItem(itemName);
                if (item != null) {
                    player.addItem(item);
                    System.out.println("You take the " + itemName + ".");
                } else {
                    System.out.println("There is no " + itemName + " here.");
                }
            } else if (command.startsWith("drop ")) {
                String itemName = command.substring(5);
                if (player.removeItem(itemName)) {
                    currentRoom.addItem(new Item(itemName));
                    System.out.println("You drop the " + itemName + ".");
                } else {
                    System.out.println("You don't have a " + itemName + ".");
                }
            } else if (command.equalsIgnoreCase("inventory")) {
                System.out.println("You are carrying: " + player.getInventory().stream().map(Item::getName).collect(Collectors.joining(", ")));
            } else if (command.equalsIgnoreCase("health")) {
                System.out.println("You have " + player.getHealthPoints() + " health points.");
            } else if (command.equalsIgnoreCase("look")) {
                System.out.println(currentRoom.getDescription());
                System.out.println("You see the following items: " + currentRoom.getItems().stream().map(Item::getName).collect(Collectors.joining(", ")));
            } else if (command.startsWith("fight ")) {
                String creatureName = command.substring(6);
                Creature creature = currentRoom.getCreature();
                if (creature != null && creature.getName().equalsIgnoreCase(creatureName)) {
                    int damage = player.hasItem("sword") ? 100 : 50;  // player deals 100 damage with a sword, 50 without
                    player.dealDamage(creature, damage);
                    if (creature.getHealthPoints() <= 0) {
                        System.out.println("You killed the " + creature.getName() + "!");
                        currentRoom.setCreature(null);
                    } else {
                        creature.dealDamage(player, 20);  // creature deals 20 damage
                        if (player.getHealthPoints() <= 0) {
                            System.out.println("You were killed by the " + creature.getName() + "!");
                            break;
                        } else {
                            System.out.println("You hit the " + creature.getName() + ". It has " + creature.getHealthPoints() + " health points left.");
                            System.out.println("The " + creature.getName() + " hits you. You have " + player.getHealthPoints() + " health points left.");
                        }
                    }
                } else {
                    System.out.println("There is no " + creatureName + " here.");
                }
            } else if (command.startsWith("use ")) {
                String itemName = command.substring(4);
                if (player.useItem(itemName)) {
                    if (itemName.equalsIgnoreCase("potion")) {
                        player.setHealthPoints(100);  // restore player's health points to 100
                        System.out.println("You use the " + itemName + ". Your health points are now " + player.getHealthPoints() + ".");
                    } else {
                        System.out.println("You can't use the " + itemName + ".");
                    }
                } else {
                    System.out.println("You don't have a " + itemName + ".");
                }
            } else if (command.equalsIgnoreCase("location")) {
                System.out.println("You are currently in " + currentRoom.getName());
            } else {
                System.out.println("I don't understand that command.");
            }
        }
        scanner.close();
    }
}