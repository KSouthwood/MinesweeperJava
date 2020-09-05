# MinesweeperJava
Minesweeper program written as part of JetBrains Academy Java track

## About
Program a minesweeper game that'll work with multidimensional arrays. We'll also deal with algorithms for generating the playfield
and processing player moves. Also, we will work with collections and stacks.

#### Stage 1
Simply output to console a minefield with `X` as mines and `.` as safe spots.

#### Stage 2
Since it's no fun knowing where all the mines are in the game, we'll generate a new random configuration eveytime we play.

We'll let the player choose how many mines they wish to play with by inputting a number from the keyboard.

After asking the player, generate a new field and display it. The mines are still visible at this stage, and should be on a
9x9 size field.

#### Stage 3
In this stage, we'll now output the number of mines around a cell instead of the empty cell symbol. Most cells will have 8 neighbors,
unless it's on an edge, when it'll have 5 neighbors, or the corner, when it'll have only 3 neighbors.

#### Stage 4
In this stage, we create a simple game loop. After generating all the mines and filling the field with numbers that describe their positions,
make it that only the numbers are shown to the user. No mines should be shown! The user should only find all the mines on the field. They do
so by entering coordinates of the cell they wish to mark. The game ends when they have correctly marked all the mines.

#### Stage 5
For this stage, we will make it a real game. After asking the user how many mines they want on the field, display an empty grid. In addition
to entering the coordinates of the cell they want, they need to enter either 'mine' or 'free' to flag/unflag or clear it. They lose if they
try to clear a cell that contains a mine. They win by either correctly marking all mines with flags or clearing all non-mine cells.
