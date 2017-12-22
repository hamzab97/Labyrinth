# Labyrinth
Graph Theory
The program will receive as input a file with a description of the labyrinth, and it will produce as output a path from the entrance to
the exit, if any exists. Think of the labyrinth as a set of rooms connected by corridors, some of which could
be closed by walls. There are 3 types of walls: normal brick walls (displayed by the program in red), thick
brick walls (displayed by the program in blue), and metal walls. The labyrinth might not have a path to the
exit; in that case your program is allowed to break some of the walls to try to reach the exit. The program is
given two types of bombs to break walls: brick bombs and acid bombs. A brick bomb can break a normal
brick wall, but not a metal wall; a thick brick wall needs 2 brick bombs to be broken. An acid bomb can
break a metal wall, but not a brick wall. =
Internally, the labyrinth will be stored as an undirected graph. Every node of the graph corresponds to a
room, and every edge corresponds to either a hall that can be used to go from one room to the other or to a
wall that the program might decide to break. There are two special nodes in this graph corresponding to the
entrance and the exit.
The graph is stored in an adjacency list and a modified DFS is used to find the exit to the labyrinth.
