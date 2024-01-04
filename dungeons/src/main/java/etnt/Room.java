package etnt;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Set;

public class Room {
    private String name;
    private String description;
    
    private HashMap<String, Door> doors = new HashMap<>();
    private HashMap<String, String> doorDescriptions = new HashMap<>();

    private ArrayList<Item> items = new ArrayList<>();
    private Creature creature;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addDoor(String direction, Door door, String description) {
        doors.put(direction, door);
        doorDescriptions.put(direction, description);
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

    public void addAllItems(Set<Item> items) {
        this.items.addAll(items);
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

    public String getDoorDescriptions() {
        return doorDescriptions.entrySet().stream()
            .map(entry -> "There is a door to the " + entry.getKey() + ". " + entry.getValue())
            .collect(Collectors.joining(" "));
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}