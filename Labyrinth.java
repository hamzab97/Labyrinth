import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * 
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 *
 */
public class Labyrinth {
	//class represents the labyrinth
	//graph will be used to store the labyrinth and to find a solution for it
	
	//define variable to be used throuhgout the class
	private int rooms = 0, line, brickBomb, acidBomb;
	private Stack<Edge> s = new Stack<Edge>();
	private Stack<Node> sPath = new Stack<Node>();
	private Graph g;
	private HashMap<Integer, Node> nodeTracker = new HashMap<Integer, Node>();//hashmap key stores the name of the nodes and the value stores its location
	//in the file
	ArrayList<Node> path = new ArrayList<Node>();
	private Node entranceNode, exitNode;
	
	/**
	 * constructor for building a labyrinth from the contents of the input file
	 * if the input file does not exist, this method should throw a Labyrinth Exception
	 * @param inputFile
	 * @throws LabyrinthException 
	 */
	public Labyrinth (String inputFile) throws LabyrinthException {
		BufferedReader br = null;//initialize buffered reader and file reader to read input file and extract info
		FileReader fr = null;
		int count = 0, location = 0, nodeLocation = 0, nodeLocation2 = 0; //ionitialize integer variables
		Node nodeName, nodeName2;//initialize node variables
		try {//read file to insert rooms
			line = 0;
			fr = new FileReader(inputFile);//read file
			br = new BufferedReader(fr);
			String current;
			while ((current = br.readLine()) != null) {//store line in string current, while line is not null, eexecute code block
//				System.out.println(current + " line " + line);
				while (count < 5) {
					if (count ==3) {
						brickBomb = Integer.parseInt(current);//store value of brick bomb
//						System.out.println("number of brickbombs " + brickBomb);
					}
					if (count == 4) {
						acidBomb = Integer.parseInt(current);//store value of acid bomb
//						System.out.println("number of acid bombs " + acidBomb);
					}
					count ++;
					current = br.readLine();
//					System.out.println(current + " line " + line);
				}
				for (int i = 0; i <  current.length(); i ++) {
					if (current.charAt(i) == 'b') {//entrance of labyrinth node
						Node node = new Node(rooms);//create new node
						entranceNode = node;//mark the entrance of the maze
						location = (line*current.length()) + i;//get the location of the symbol in the file
						nodeTracker.put(location, node);//insert location as key and node as value in the hash table
//						System.out.printf("Node %d inserted\n", rooms);
						rooms++;//increment rooms
					}
					else if (current.charAt(i) == '+') {//new room
						Node node = new Node(rooms);//create new node
						location = (line*current.length()) + i;//get location and store in the hashtable
						nodeTracker.put(location, node);
//						System.out.printf("Node %d inserted\n", rooms);
						rooms++;
					}
					else if (current.charAt(i) == 'x') {//exit of labyrinth
						Node node = new Node(rooms);//create new node
						exitNode = node;//mark exit node in the file
						location = (line*current.length()) + i;//get location and store in hashtable
						nodeTracker.put(location, node);
//						System.out.printf("Node %d inserted\n", rooms);
						rooms++;//increment rooms
					}
					else
						continue;
				}
				line++;//increment line
			}
		}catch (Exception e) {//catch exception
			throw new LabyrinthException("No File");
		}finally {//finally close file
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {//read file again to insert  edges
			count = 0;
			line = 0;
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			String current;
			while ((current = br.readLine()) != null) {
				
				while (count < 5) {
//					System.out.println(count);
					count ++;
					current = br.readLine();
				}
				
				for (int i = 0; i <  current.length(); i ++) {
					if (current.charAt(i) == 'v') {//vertical normal brick wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (((line*current.length()))-current.length())+i;
						nodeLocation2 = (((line*current.length()))+current.length())+i;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('v')));
					}
					else if (current.charAt(i) == 'V') {//vertical thick brick wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (((line*current.length()))-current.length())+i;
						nodeLocation2 = (((line*current.length()))+current.length())+i;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('V')));//create new edge of type vertical thick brick wall
//						System.out.printf("%s between node %d and %d\n", current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else if (current.charAt(i) == 'M') {//vertical metal  wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (((line*current.length()))-current.length())+i;
						nodeLocation2 = (((line*current.length()))+current.length())+i;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('M')));
//						System.out.printf("%s between node %d and %d\n", current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else if (current.charAt(i) == '|') {//vertical corridor
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (((line*current.length()))-current.length())+i;
						nodeLocation2 = (((line*current.length()))+current.length())+i;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('|')));
//						System.out.printf("%s between node %d and %d\n", current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					//horizontal walls
					else if (current.charAt(i) == 'h') {//horizontal normal brick wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (line*current.length()) + i - 1;
						nodeLocation2 = (line*current.length()) + i+1;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('h')));//create new edge between 2 nodes of type normal brick wall and push it on the stack
//						System.out.printf("%s between node %d and %d\n",current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else if (current.charAt(i) == 'H') {//horizontal thick brick wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (line*current.length()) + i - 1;
						nodeLocation2 = (line*current.length()) + i+1;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('H')));//create new edge between 2 nodes of type thick brick wall
//						System.out.printf("%s between node %d and %d\n",current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else if (current.charAt(i) == 'm') {//horizontal metal  wall
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (line*current.length()) + i - 1;
						nodeLocation2 = (line*current.length()) + i+1;;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('m')));//create new edge of metal brick wall between 2 edges
//						System.out.printf("%s between node %d and %d\n",current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else if (current.charAt(i) == '-') {//horizontal corridor 
						/**
						 * get location of the 2 nodes that the edge is supposed to connect to
						 * find the two nodes in the hash table using the location as key
						 * create new edge with the following nodes and push it in the stack
						 */
						nodeLocation = (line*current.length()) + i - 1;
						nodeLocation2 = (line*current.length()) + i+1;
						nodeName = nodeTracker.get(nodeLocation);
						nodeName2 = nodeTracker.get(nodeLocation2);
						s.push(new Edge(nodeName, nodeName2, Character.toString('-')));//create new edge of type horizontal corridor between 2 edges
//						System.out.printf("%s between node %d and %d\n",current.charAt(i), nodeName.getName(),nodeName2.getName());
					}
					else
						continue;
				}
				line ++;//increment line
			}
		}catch (Exception e) {//handle exception
			System.out.println("No nodes found to connnect edges to");
			System.exit(0);
		}finally {//finally close file
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		getGraph();//call method get graph to construct the graph 
	}

	/**
	 * returns a reference to the graph represnting the labyrinth
	 * throws a labyrinth exception if the graph is not defined
	 * @return
	 */
	public Graph getGraph() {
		g = new Graph(rooms);//initailize graph
		while (!s.isEmpty()) {//while stack is not empty, pop the stack and store edge in the variable
			Edge edge = s.pop();
			try {
				g.insertEdge(edge.firstEndpoint(), edge.secondEndpoint(), edge.getType());//insert edge in graph
			} catch (GraphException e) {//throw exception
				e.printStackTrace();
			}
		}
		return g;
	}
	
	/**
	 * returns a java iterator containing the nodes along the path from the entrance to the exit of the labyrinth, if the path exists
	 * if the path does not exist, the method returns null
	 * 
	 * @return
	 * @throws GraphException 
	 */
	public Iterator<Node> solve() throws GraphException {
		if (pathDFS(g, entranceNode, exitNode) == false) {//checks if there is a path, no path return null
			System.out.println("no path found");
			return null;
		}
		Iterator <Node> itr = sPath.iterator();//return iterator on the path
		return itr;
	}
	
	
	private boolean pathDFS(Graph g, Node entrance, Node exit) throws GraphException {
		Node endPoint;//create new node variable
		entrance.setMark(true);//mark entrance node as true
		sPath.push(entrance);//push it on the stack
		if (entrance == exit)//if entrance node is the exit node, then return true
			return true;
		Iterator<Edge> itr = g.incidentEdges(entrance);//get iterator on all the incident edges of the node entrance
		while (itr.hasNext()) {//iterate through the incident edges
			Edge tempEdge = itr.next();//store edge variable
			if (tempEdge.getType().equals(Character.toString('h')) || tempEdge.getType().equals(Character.toString('v'))) {
				// check if edge type is horizontal/vertical normal brick wall
				brickBomb --;//subtract brick bomb by one
			}
			else if (tempEdge.getType().equals(Character.toString('H')) || tempEdge.getType().equals(Character.toString('V')))//check if edge type is horizontal/vertical thick brick wall
				brickBomb = brickBomb - 2;//update value of brickbomb
			else if (tempEdge.getType().equals(Character.toString('m')) || tempEdge.getType().equals(Character.toString('M')))//check if edge type is horizontal/vertical metal wall
				acidBomb --;//update value of acid bomb
			if (brickBomb < 0 || acidBomb < 0) {//check if either brick or acid bomb are under 0
				if (tempEdge.getType().equals(Character.toString('h')) || tempEdge.getType().equals(Character.toString('v'))) {
					// check if edge type is horizontal/vertical normal brick wall
					brickBomb ++;//add back the value of brickbomb
				}
				else if (tempEdge.getType().equals(Character.toString('m')) || tempEdge.getType().equals(Character.toString('M')))//check if edge type is horizontal/vertical metal wall
					acidBomb ++;//add back acid bomb
				else
					brickBomb = brickBomb + 2;//add back brick bomb
				continue;//continue on the code
			}
			else {//if the value of both acid bomb and brick bomb are positive, do the follwoing
				endPoint = tempEdge.secondEndpoint();//store the node at 2nd end point of edge in variable
				if (endPoint == entrance)//if the node stored is the same as the entrance
					endPoint = tempEdge.firstEndpoint();//update node value to the first end point
				if (endPoint.getMark() == false) {//if the node is not marked
					if (pathDFS(g, endPoint, exit))//recursively call the algorithm and see if it returns true, if it does, return true
						return true;
				}
			}
		}
		sPath.pop();//pop edge off the stack
		return false;//return false
	}
}
