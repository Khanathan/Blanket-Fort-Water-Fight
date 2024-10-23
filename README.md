# Blanket Fort Water Fight
A Battleship-like game where the player plays against 5 Computer enemies.

Each enemy has a fort of 5 cells. Each round the enemies will shoot at the player and score points based on how many cells their fort has left (more intact forts score more points).

Each round the Player chooses a cell to shoot at, the game will let the player know if they hit an enemy fort cell or not.

Win condition: The player destroys all enemies before the enemies score a total of 2500 points.
Loss condition: The enemies collectively score 2500 points.

Enemy forts are randomly placed on the 2D grid.

## Random enemy fort generation:

Enemy forts are procedurally generated cell by cell. An origin cell is first randomly selected, each subsequent cell will be chosen, at random, from available cells which are adjacent to an existing cell of that fort.

## Demo Screenshots:

Choosing a cell that resulted in a Miss:

![round 1](https://github.com/user-attachments/assets/a56d613a-0872-468d-b21e-f905d433e08b)


Choosing a cell that resulted in a Hit:

![round hit](https://github.com/user-attachments/assets/edb5606f-3526-49c4-951e-43ceb68519f7)


A match loss. The map is revealed post-match, showing enemy forts and cells which the Player successfully shot at:

![game loss](https://github.com/user-attachments/assets/eb3885bd-dd3a-487e-8c90-0d51542b6d99)

## Folder structure
* Playground: for experimenting and prototyping
* WaterFightGame: main project folder
* docs: documentation for program design
