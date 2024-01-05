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

## Example of the Prompts I wrote to the Co-Pilot chatbot

1. I want to create a dungeons game where I can walk between rooms connected via doors how should the code look like for this in Java?
2. Add a player that can pick up items located in the rooms.
3. How should I setup Maven to handle this code?
4. Create a loop prompting for user input in order to navigate among the rooms.
5. Add a way of expressing what can be seen inside a room when entering it.
6. Add functionality for putting items in a room; for example a key, a sword, some coins.
7. I want to make use of a player object to store the inventory and other things like health points for example.
8. Add a look command.
9. How can I implement various creatures e.g Dragons and Trolls that each has its own health point and are located in a room?
10. How can I implement combat mechanics between the player and creatures in the game?
11. ..etc...

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

## With some ASCII art

```shell
You are in Room 1

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
look
You are in a room that smells of old books.
There is a door to the . It's covered in moss. There is a door to the east. It looks sturdy. There is a door to the south. It's covered in moss. There is a door to the west. It's slightly ajar.
You see a Troll.
        .-''''.
       /       \
   __ /   .-.  .\
  /  `\  /   \/  \
  |  _ \/   .==.==.
  | (   \  /____\__\
   \ \      (_()(_())
    \ \            '---._
     \                   \_
  /\ |`       (__)________/
 /  \|     /\___/
|    \     \||VV
|     \     \|''',
|      \     ______)
\       \  /`
 \       \(
You see the following items: Potion, Shield, Key

Enter a command (go <direction>, look, take <item>, drop <item>, use <item>, inventory, health, fight <creature>, location, quit):
go south
You go south. You are now in Room 3
You are in a brightly lit room.
There is a door to the east. It looks sturdy. There is a door to the north. It's covered in moss. There is a door to the west. It's slightly ajar.
You see a Dragon.
        ___====-_  _-====___"
        _--^^^#####//      \\#####^^^--_
     _-^##########// (    ) \\##########^-_
    -############//  |\^^/|  \\############-
  _/############//   (@::@)   \\############\_
 /#############((     \\//     ))#############\
-###############\\    (oo)    //###############-
-#################\\  / VV \  //#################-
-###################\\/      \//###################-
_#/|##########/\######(   /\   )######/\##########|\#_
|/ |#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
`  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
`   `  `      `   / | |  | | \   '      '  '   '
                 (  | |  | |  )
                __\ | |  | | /__
               (vvv(VVV)(VVV)vvv)
You see the following items: Potion, Shield, Sword, Key
```