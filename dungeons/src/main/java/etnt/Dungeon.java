package etnt;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Dungeon {
    public static void main(String[] args) {
        int numberOfRooms = 9;  // change this to configure the number of rooms
        Room[] rooms = generateRooms(numberOfRooms);

        Player player = new Player(100);  // player starts with 100 health points

        Room currentRoom = rooms[0];
        System.out.println("You are in " + currentRoom.getName());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
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
                    System.out.println(currentRoom.getDoorDescriptions());
                    printRoomCreature(currentRoom);
                    printRoomItems(currentRoom);
                } else {
                    System.out.println("There is no door in that direction.");
                }
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);
                Item item = currentRoom.removeItem(itemName);
                if (item != null) {
                    if (player.takeItem(item)) {
                        System.out.println("You take the " + itemName + ".");
                    } else {
                        System.out.println("You already have a " + itemName + ".");
                    }
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
                System.out.println(currentRoom.getDoorDescriptions());
                printRoomCreature(currentRoom);
                printRoomItems(currentRoom);
            } else if (command.startsWith("fight ")) {
                String creatureName = command.substring(6);
                Creature creature = currentRoom.getCreature();
                if (creature != null && creature.getName().equalsIgnoreCase(creatureName)) {
                    int damage = player.hasItem("Sword") ? 100 : 50;  // player deals 100 damage with a sword, 50 without
                    player.dealDamage(creature, damage);
                    if (creature.getHealthPoints() <= 0) {
                        System.out.println("You killed the " + creature.getName() + "!");
                        currentRoom.setCreature(null);
                    } else {
                        damage = player.hasItem("Shield") ? 10 : 20;  // creature deals 10 damage when player has got a shield, 20 without
                        creature.dealDamage(player, damage);  // creature deals 20 damage
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

    public static void printRoomItems(Room currentRoom) {
        List<Item> roomItems = currentRoom.getItems();
        if (roomItems.isEmpty()) {
            System.out.println("There are no items in this room.");
        } else {
            System.out.println("You see the following items: " + roomItems.stream().map(Item::getName).collect(Collectors.joining(", ")));
        }
    }

    public static void printRoomCreature(Room currentRoom) {
        Creature creature = currentRoom.getCreature();
        if (creature != null) {
            System.out.println("You see a " + creature.getName() + ".");
            creature.printCreature();
        }
    }

    public static Room[] generateRooms(int numberOfRooms) {
        Room[] rooms = new Room[numberOfRooms];
        Random random = new Random();

        Item[] items = {new Item("Sword"), new Item("Potion"), new Item("Shield"), new Item("Key")};

        // First, create the rooms
        String[] roomDescriptions = {"You are in a dark, gloomy room.", "You are in a brightly lit room.", "You are in a room filled with strange symbols.", "You are in a room with high, vaulted ceilings.", "You are in a room that smells of old books."};
        for (int i = 0; i < numberOfRooms; i++) {
            String description = roomDescriptions[random.nextInt(roomDescriptions.length)];
            rooms[i] = new Room("Room " + (i + 1), description);

            // 50% chance to add a creature to the room
            if (random.nextBoolean()) {
                Creature creature = random.nextBoolean() ? new Creature("Dragon", 200) : new Creature("Troll", 100);
                rooms[i].setCreature(creature);
            }

            // Add a random number of items to the room.
            // Avoid adding duplicate items.
            int numberOfItems = random.nextInt(items.length + 1);
            Set<Item> uniqueItems = new HashSet<>();
            while (uniqueItems.size() < numberOfItems) {
                Item item = items[random.nextInt(items.length)];
                uniqueItems.add(item);
            }
            rooms[i].addAllItems(uniqueItems);
        }

        // Then, create the doors
        String[] doorDescriptions = {"It looks sturdy.", "It's slightly ajar.", "It's made of solid oak.", "It's covered in moss.", "It's glowing faintly."};

        for (int i = 0; i < numberOfRooms; i++) {
            int numberOfDoors = random.nextInt(4) + 1;
            for (int j = 1; j <= numberOfDoors; j++) {
                String direction = "";
                String oppositeDirection = "";
                switch (j) {
                    case 0: direction = "north"; oppositeDirection = "south"; break;
                    case 1: direction = "east"; oppositeDirection = "west"; break;
                    case 2: direction = "south"; oppositeDirection = "north"; break;
                    case 3: direction = "west"; oppositeDirection = "east"; break;
                }
                if (i + j < numberOfRooms && rooms[i].getDoor(direction) == null && rooms[i + j].getDoor(oppositeDirection) == null) {
                    String doorDescription = doorDescriptions[random.nextInt(doorDescriptions.length)];
                    Door door = new Door(rooms[i], rooms[i + j]);
                    rooms[i].addDoor(direction, door, doorDescription);
                    rooms[i + j].addDoor(oppositeDirection, door, doorDescription);
                }
            }
        }
        return rooms;
    }
}