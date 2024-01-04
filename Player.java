import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.go(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
        }
    }

    public void pickUpItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);
        if (item != null) {
            inventory.add(item);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}