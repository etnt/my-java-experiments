package etnt;

import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private HashMap<String, Door> doors;
    private ArrayList<Item> items;
    private Creature creature;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.doors = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void addDoor(String direction, Door door) {
        doors.put(direction, door);
    }

    public Room go(String direction) {
        Door door = doors.get(direction);
        if (door != null) {
            return door.getOtherRoom(this);
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}