package etnt;

import java.util.Scanner;

public class Dungeon {
    public static void main(String[] args) {
        Room room1 = new Room("Room 1", "You are in a dark room. There is a door to the east.");
        Room room2 = new Room("Room 2", "You are in a dark room. There is a door to the west and a door to the east.");
        Room room3 = new Room("Room 3", "You are in a dark room. There is a door to the west.");

        Door door1 = new Door(room1, room2);
        Door door2 = new Door(room2, room3);

        room1.addDoor("east", door1);
        room2.addDoor("west", door1);
        room2.addDoor("east", door2);
        room3.addDoor("west", door2);

        Room currentRoom = room1;
        System.out.println("You are in " + currentRoom.getName());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a direction (east or west) or 'quit' to exit:");
            String direction = scanner.nextLine();

            if (direction.equalsIgnoreCase("quit")) {
                break;
            }

            Room nextRoom = currentRoom.go(direction);
            if (nextRoom != null) {
                currentRoom = nextRoom;
                System.out.println("You go " + direction + ". You are now in " + currentRoom.getName());
                System.out.println(currentRoom.getDescription());
            } else {
                System.out.println("There is no door in that direction.");
            }
        }
        scanner.close();
    }
}