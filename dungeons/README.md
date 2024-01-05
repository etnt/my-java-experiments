# Dungeons game
> A simple Dungeons game, developed using Github CodeSpace and the Github Co-Pilot

I wanted to learn a bit Java and at the same time explore how to
use the Github CodeSpaces and Co-Pilot.

So basically I ask questions to the Co-Pilot chat, which create the
code I'm asking for. Often I could use the generated code right away;
but sometimes, for more complicated parts of the code, I had to analyze
the code myself to figure out what was wrong (for example when generating
X number of Rooms with Y number of connected Doors).

All in all, pretty darn cool!

## Compile and Run

We are using Maven to build and run the game.

```shell
# Compile
> mvn compile

# Run
> mvn exec:java
```

## Example run

```shell
You are in Room 1

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
look
You are in a room that smells of old books.
There is a door to the east. It looks sturdy. There is a door to the south. It looks sturdy.
You see the following items: Potion

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
take Potion
You take the Potion.

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
go south
You go south. You are now in Room 3
You are in a room filled with strange symbols.
There is a door to the east. It's made of solid oak. There is a door to the south. It's slightly ajar. There is a door to the north. It looks sturdy. There is a door to the west. It looks sturdy.
You see the following items: Shield, Key, Sword, Potion

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
take Shield
You take the Shield.

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
take Sword
You take the Sword.

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
take Potion
You already have a Potion.

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
inventory
You are carrying: Potion, Shield, Sword

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
go south
You go south. You are now in Room 5
You are in a room that smells of old books.
There is a door to the north. It's slightly ajar. There is a door to the west. It looks sturdy.
You see a Troll.
You see the following items: Shield, Key, Potion

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
fight Troll
You killed the Troll!

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
health
You have 100 health points.
```