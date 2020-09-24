 import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/**
 * A Graph object. The graph can be either directed or undirected.
 * @author Skye Guegan
 * @version 9/27/2018
 */
public class Graph<V> implements GraphIfc<V> {      

    private Map<V, List<V>> list;
    
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>();
        System.out.println(graph.containsNode(1));
        graph.addVertex(1);
        System.out.println(graph.containsNode(1));
        graph.addVertex(2);
        System.out.println(graph.degree(1));
        System.out.println(graph.toString());
        graph.addEdge(1,2);
        System.out.println(graph.toString());
        graph.addVertex(3);
        System.out.println(graph.edgeExists(1,3));
        graph.addEdge(1,3);
        System.out.println(graph.toString());
        System.out.println(graph.degree(1));
        System.out.println(graph.edgeExists(1,3));
        graph.clear();
        System.out.println(graph.toString());
    }

    /**
     *  Constructs an empty graph
     */

    public Graph() {
        list = new HashMap<V, List<V>>();
    }

    /**
     * Returns the number of vertices in the graph
     * @return The number of vertices in the graph
     */
    public int numVertices(){
        int x;
        x= list.size();
        return x;
    }

    /**
     * Returns the number of edges in the graph
     * @return The number of edges in the graph
     */
    public int numEdges(){
        int x=0;
        Iterator<V> iterator;
        iterator =getVertices().iterator();//creates iterator for the vertices
        for(int i=0; i<numVertices(); i++){
            V vertex;
            vertex= iterator.next();//returns next vertex
            List<V> neighbors;
            neighbors = getNeighbors(vertex);            
            x = x + neighbors.size();
        }
        return x;
    }

    /**
     * Removes all vertices from the graph
     */
    public void clear(){
        list.clear();
    }

    /** 
     * Adds a vertex to the graph. This method has no effect if the vertex already exists in the graph. 
     * @param v The vertex to be added
     */
    public void addVertex(V v){
        List<V> neighbors= new ArrayList<V>(); 
        list.put(v,neighbors); 
    }

    /**
     * Adds an edge between vertices u and v in the graph. 
     * @param u A vertex in the graph
     * @param v A vertex in the graph
     * @throws IllegalArgumentException if either vertex does not occur in the graph.
     */
    public void addEdge(V u, V v){
        if(containsNode(v) && containsNode(u)){
            List<V> neighbors;
            neighbors = list.get(u);
            neighbors.add(v);
        }
        else{
            throw new AssertionError("Both vertices not in graph");
        }
    }

    /**
     * Returns the set of all vertices in the graph.
     * @return A set containing all vertices in the graph
     */
    public Set<V> getVertices(){
        Set<V> vertices;
        vertices=list.keySet();
        return vertices;
    }

    /**
     * Returns the neighbors of v in the graph. A neighbor is a vertex that is connected to
     * v by an edge. If the graph is directed, this returns the vertices u for which an 
     * edge (v, u) exists.
     *  
     * @param v An existing node in the graph
     * @return All neighbors of v in the graph.         
     */
    public List<V> getNeighbors(V v){
        List<V> neighbors;
        neighbors = list.get(v);
        return neighbors;
    }

    /**
     * Determines whether the given vertex is already contained in the graph. The comparison
     * is based on the <code>equals()</code> method in the class V. 
     * 
     * @param v The vertex to be tested.
     * @return True if v exists in the graph, false otherwise.
     */
    public boolean containsNode(V v){
        boolean x;
        x= list.containsKey(v);
        return x;
    }

    /**
     * Determines whether an edge exists between two vertices. In a directed graph,
     * this returns true only if the edge starts at v and ends at u. 
     * @param v A node in the graph
     * @param u A node in the graph
     * @return True if an edge exists between the two vertices
     * @throws IllegalArgumentException if either vertex does not occur in the graph
     */
    public boolean edgeExists(V v, V u){
        if(containsNode(v) && containsNode(u)){
            List<V> neighbors;
            neighbors = list.get(v);
            boolean exists;
            exists= neighbors.contains(u);
            return exists;
        }
        else{
            throw new AssertionError("Both vertices not in graph");
        }
    }

    /**
     * Returns the degree of the vertex. In a directed graph, this returns the outdegree of the
     * vertex. 
     * @param v A vertex in the graph
     * @return The degree of the vertex
     * @throws IllegalArgumentException if the vertex does not occur in the graph
     */
    public int degree(V v){
        if(containsNode(v)){
            List<V> neighbors;
            neighbors = list.get(v);
            int size;
            size= neighbors.size();
            return size;
        }
        else{
            throw new AssertionError("Vertex does not exist");
        }
    }

    /**
     * Returns a string representation of the graph. The string representation shows all
     * vertices and edges in the graph. 
     * @return A string representation of the graph
     */
    public String toString(){
        String string;
        string="{";
        Iterator<V> iterator;
        iterator =getVertices().iterator();//creates iterator for the vertices
        for(int i=0; i<numVertices(); i++){//goes through all of the vertices
            V vertex;
            vertex= iterator.next();//returns next vertex
            List<V> neighbors;
            neighbors = getNeighbors(vertex);            
            string= string + "vertex: " + vertex + " => neighbors: ( " ;
            for(int j=0; j<neighbors.size(); j++){//goes through the edges of the current vertex
                string= string + neighbors.get(j) + " ";
            }
            string= string + ") ";
        }
        string= string+"}";
        return string;
    }
}
