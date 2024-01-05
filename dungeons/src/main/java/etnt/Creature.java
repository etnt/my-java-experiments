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

    public void printCreature() {
        if (name.equals("Dragon")) {
            System.out.println(dragon());
        } else if (name.equals("Troll")) {
            System.out.println(troll());
        }
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

    private static String dragon() {
        return 
        "        ___====-_  _-====___\"\n" +
        "        _--^^^#####//      \\\\#####^^^--_\n" +
        "     _-^##########// (    ) \\\\##########^-_\n" +
        "    -############//  |\\^^/|  \\\\############-\n" +
        "  _/############//   (@::@)   \\\\############\\_\n" +
        " /#############((     \\\\//     ))#############\\\n" +
        "-###############\\\\    (oo)    //###############-\n" +
        "-#################\\\\  / VV \\  //#################-\n" +
        "-###################\\\\/      \\//###################-\n" +
        "_#/|##########/\\######(   /\\   )######/\\##########|\\#_\n" +
        "|/ |#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|\n" +
        "`  |/  V  V  `   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '\n" +
        "`   `  `      `   / | |  | | \\   '      '  '   '\n" +
        "                 (  | |  | |  )\n" +
        "                __\\ | |  | | /__\n" +
        "               (vvv(VVV)(VVV)vvv)";
    }

    private static String troll() {
        return 
        "        .-''''." +
        "\n       /       \\" +
        "\n   __ /   .-.  .\\" +
        "\n  /  `\\  /   \\/  \\" +
        "\n  |  _ \\/   .==.==." +
        "\n  | (   \\  /____\\__\\" +
        "\n   \\ \\      (_()(_())" +
        "\n    \\ \\            '---._" +
        "\n     \\                   \\_" +
        "\n  /\\ |`       (__)________/" +
        "\n /  \\|     /\\___/" +
        "\n|    \\     \\||VV" +
        "\n|     \\     \\|'''," +
        "\n|      \\     ______)" +
        "\n\\       \\  /`" +
        "\n \\       \\(";
    }

}