package etnt;

public class Creature {
    private String name;
    private int healthPoints;

    public Creature(String name, int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void dealDamage(Player player, int damage) {
        player.setHealthPoints(player.getHealthPoints() - damage);
    }
}