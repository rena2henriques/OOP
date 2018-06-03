# Object Oriented Programming
Project for the Course "Object Oriented Programming"

Considering a grid n×m, the objective is to find the best path, that is, the path with lowest cost between
an initial point, with coordinates (xi,yi), and a final point, with coordinates (xf,yf).
Each path has an associated cost that is determined by the number of edges traversed. In general, the
cost of each edge is 1, but there may be special areas where the cost is higher. There are n obst
obstacles at some points on the grid through which one can not proceed.

This project is a Java solution to the problem presented above using evolutive programming modeled and implemented with objects. The idea is to generate at instant zero a population of ν individuals, all placed at the initial point, and make it evolve until the final instant τ .The evolution of the population is ruled by discrete stochastic simulation, that is, based on a pending event container.
