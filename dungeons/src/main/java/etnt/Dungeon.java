package etnt;

public class Dungeon {
    public static void main(String[] args) {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");

        Door door1 = new Door(room1, room2);
        Door door2 = new Door(room2, room3);

        room1.addDoor("east", door1);
        room2.addDoor("west", door1);
        room2.addDoor("east", door2);
        room3.addDoor("west", door2);

        Room currentRoom = room1;
        System.out.println("You are in " + currentRoom.getName());

        currentRoom = currentRoom.go("east");
        System.out.println("You go east. You are now in " + currentRoom.getName());

        currentRoom = currentRoom.go("east");
        System.out.println("You go east. You are now in " + currentRoom.getName());
    }
}