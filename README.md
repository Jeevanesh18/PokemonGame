# PokemonGame
:

**🕹️ Project Title: Pokémon Adventure – A Data Structure-Based Game**

**📌 Project Overview**
This project is a simplified Pokémon-style adventure game designed for a Data Structures course. The main objective is to demonstrate understanding and implementation of core data structures through a game that allows the player to:

Move around a map (likely represented by a 2D array or graph).

Encounter and battle other Pokémon.

Level up by gaining experience points (XP).

Collect defeated Pokémon.

Earn and display badges visually through a GUI component.

**👨‍🎓 Purpose**
The project applies object-oriented programming and core data structures to create an interactive simulation. It showcases:

Use of lists/arrays (ArrayList) to manage dynamic entities (Pokémon, badges).

Basic GUI programming with Swing (e.g., the BadgeFrame class).

Object-oriented design through classes like Player, Pokemon, and possibly Map.

**🔧 Main Features**
Player Class

Stores the player’s name and current location.

Keeps track of owned Pokémon and collected badges using ArrayList.

Pokemon Class

Represents a Pokémon with stats like health, type, level, XP, moves, strengths, and weaknesses.

Dynamically calculates health and XP needed for the next level based on current level.

Stores move damage and name, as well as strengths and weaknesses (stored as ArrayList of types).

Badge Display

BadgeFrame uses Java Swing to visually show a badge with an image and title when a player earns it.

Displays the image with resizing and a title in a customized frame.

Map (implied)

Although not shown in the provided code, you mentioned a "map the player can move on", likely implemented using a 2D array, graph, or matrix to represent the game world and navigation.

Battle Mechanism (implied)

The player can encounter wild Pokémon, battle them using moves (move1 and move2), and win XP.

After winning, the player levels up and can collect the defeated Pokémon.

**🧠 Data Structures Used**
ArrayList:

For storing Pokémon, badges, strengths, weaknesses — enabling dynamic and flexible collection management.

Possibly a 2D array or Graph (for the Map):

Used to represent and manage player movement and map layout.

Custom Objects (Player, Pokemon):

Organizes game data using object-oriented structures, encapsulating attributes and behaviors.

