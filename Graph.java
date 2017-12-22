import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 *
 */
public class Graph {
	ArrayList<LinkedList<Edge>> graph = new ArrayList<LinkedList<Edge>>();//starting node of edges swould be the node
	//this class represents an undirected graph
	//use adjacency matrix or adjacency list representation of the graph

	/**
	 * constructor
	 * creates an empty graph with n nodes and no edges
	 *
	 */
	public Graph (int n){//creating an array of linked lists
		//number of linkedlists = number of nodes
		for (int i = 0; i < n; i ++) {
			graph.add(new LinkedList<Edge>());
		}
	}

	/**
	 * adds to the graph an edge connecting u and v
	 * the type for this new edge is indicated by the parameter edgeType
	 * method throws graphException if either node does not exist or if there is already an edge connecting wiht the given vertices
	 * @param u
	 * @param v
	 * @param edgeType
	 * @throws GraphException
	 */
	public void insertEdge (Node u, Node v, String edgeType) throws GraphException {

		Edge edge = new Edge (u, v, edgeType);//create new edge
		int index = u.getName();//get name of node u and store it
		if (index >= graph.size()) {//check to see if the node is in the graph

			throw new GraphException("Index out of bounds");
		}
		LinkedList<Edge> list = graph.get(u.getName());//access linked list at index 

		if (list.isEmpty()) {//if list is emtpy, add edge as the first data in the linked list
			list.addFirst(edge);
		}
		else {//if list is not emtpy, do the following
			if (list.contains(edge))//if edge already exists in the linked list, throw exception
				throw new GraphException("already exists");
			else if (!list.contains(edge))//otherwise add the edge to the linkedlist
				list.add(edge);
		}
		index = v.getName();//assign value of name of node v and check if its in the graph, else throw an exception
		if (index >= graph.size()) {
			throw new GraphException("Index out of bounds");
		}
		list = graph.get(v.getName());//access linked list at name of v

		if (list.isEmpty()) {//if list is empty, add edge as first element

			list.addFirst(edge);
		}
		else {
			if (list.contains(edge))//if edge already exists, throw exception
				throw new GraphException("already exists");
			else if (!list.contains(edge))//otherwise ad edge
				list.add(edge);
		}
	}

	/**
	 * returns the node with the specified name
	 * if no node with this name exists, the method should throw a graph exception
	 * @param name
	 * @return
	 * @throws GraphException 
	 */
	public Node getNode(int name) throws GraphException {
		if (name >= graph.size())//if name of ndoe is more than graph size, node doenst exist, throw exception
			throw new GraphException("Node doesnt exist");
		else {
			return new Node(name); //else return the node with that name
		}
	}

	/**
	 * returns a java iterator storing all the edges incident on node u
	 * returns null if u does not have any edges incident on it
	 * @param u
	 * @return -a java iterator storing all the edges incident on node u
	 * @throws GraphException 
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		int index = u.getName();//get name of u
		if (index >= graph.size())//check if its in the grpah, if not, throw exception
			throw new GraphException("Node non existent");
		LinkedList<Edge> list = graph.get(index);//get list at that index
		if (list.isEmpty())//if list empty, return null
			return null;
		else {
			Iterator<Edge> itr = list.iterator();//if list not empty, call iterator on list and return iterator
			return itr;
		}
	}

	/**
	 * returns the edge connecting nodes u and v
	 * the method throws a graph exception if there is no edge between u and v
	 * @param u
	 * @param v
	 * @return
	 * @throws GraphException 
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		if (u.getName() >= graph.size() || v.getName() >= graph.size())//if either of the nodes are not in the graph, throw exception
			throw new GraphException("Nodes not in graph");
		else {
			LinkedList<Edge> list = graph.get(u.getName());//get linked list at node u
			if (list.isEmpty())//if list empty, throw expcetion
				throw new GraphException("No Edges");
			else {//else iterate through the list, if edge with node u and v is found, return that edge
				Iterator<Edge> itr = list.iterator();
				while (itr.hasNext()) {
					Edge temp = itr.next();
					int tempu = temp.firstEndpoint().getName();
					int tempv = temp.secondEndpoint().getName();
					if (tempu == v.getName() || tempv == v.getName())
						return temp;
				}
			}
		}
		return null;
	}

	/**
	 * returns true if and only if nodes u and v are adjacent
	 * throws a graph exception if u or v are not nodes of the graph
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean areAdjacent (Node u, Node v) throws GraphException{
		if (u.getName() >= graph.size() || v.getName() >= graph.size())//if either nodes not in graph, throw exception
			throw new GraphException("Nodes not in the graph");
		else {//else get linked list at that index and check if its empty, if not then start iterator on the linked list
			LinkedList<Edge> list = graph.get(u.getName());
			if (list.isEmpty())
				return false;
			else {
				//iterate through the linked list until the name of one of the nodes at the ends of the edge matches v, then return true
				//else return false
				Iterator<Edge> itr = list.iterator();
				while (itr.hasNext()) {
					Edge tempEdge = itr.next();
					int edgeU = tempEdge.firstEndpoint().getName();
					int edgeV = tempEdge.secondEndpoint().getName();
					if (edgeU == v.getName() || edgeV == v.getName())
						return true;
				}
			}
		}
		return false;
	}
}
