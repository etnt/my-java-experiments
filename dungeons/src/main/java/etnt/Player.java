package etnt;

import java.util.ArrayList;

import java.util.ArrayList;

public class Player {
    private ArrayList<Item> inventory;
    private int healthPoints;

    public Player(int healthPoints) {
        this.inventory = new ArrayList<>();
        this.healthPoints = healthPoints;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean takeItem(Item item) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(item.getName())) {
                return false;  // player already has the item
            }
        }
        inventory.add(item);
        return true;
    }

    public boolean removeItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                inventory.remove(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void dealDamage(Creature creature, int damage) {
        creature.setHealthPoints(creature.getHealthPoints() - damage);
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean useItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                return true;
            }
        }
        return false;
    }
}