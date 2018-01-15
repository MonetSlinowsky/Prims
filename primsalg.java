import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

public class primsalg {
	//NOTE: Enter the number of nodes below (SIZE=number of nodes) before running the program
	final static int SIZE=10;

	public static void main(String[] args) throws FileNotFoundException {
	//The main method creates a graph from a file with the format v1, v2, weight
	//It then calls prim(), which runs Prim's algorithm and return a minimum spanning tree of the graph
	//It then prints the sum and writes the mst to a specified file
		
		
		Graph myG=new Graph(SIZE);
		String inFilename="/Users/monet/Documents/python/mst2.csv";
		String outputFile="/Users/monet/Documents/python/mstrslt.csv";
		myG.fillGraph(inFilename);
		
		long lStartTime = System.nanoTime();
		Graph mst= prim(myG);
		long lEndTime = System.nanoTime();
		long output=(lEndTime-lStartTime);
		
		System.out.println("Sum is " +mst.getSum());
		System.out.println(mst.toString());
		System.out.println("");
		System.out.println("Execution time is " + output);
		
		mst.printGraph(outputFile);
	}
	
	
	public static Graph prim(Graph myG) {

	//create an mstAdded set that keeps tracks of vertices already added to the MST
		Boolean mstAdded[]=new Boolean[SIZE];

	
	int[] dist =new int[SIZE]; //key value
	Graph mst=new Graph(SIZE);	
		
	//Initialize all vertices but a random one to have 'key value' 0, 
		//call this dist[]
	
	for(int i=0; i<SIZE; i++) {
		dist[i]=Integer.MAX_VALUE;
		mstAdded[i]=false; //no vertices have been added to the mst yet so initialize all to false
	}
	
	Random rand=new Random();
	int r=rand.nextInt(SIZE);
	dist[r]=0;
	//System.out.println(r);
		
	//while all vertices not in mstAdded
	while(true) {
		//Pick a vertex v not in the set with a minimum value
		int c=findMin(dist, mstAdded);
		
		//a minimum wasn't found because all nodes have been visited
		if(c< 0) 
			break;
			
	
		//System.out.println("c: " + c + " " + "dist " + dist[c]);
		//add v to mstAdded
		mstAdded[c]=true;
		
		//put that edge in mst
		mst.addEdge(c, myG.findNeighbourNum(c, dist[c]), dist[c]);
		
		//update key value of all adjacent vertices of v
		//this is done by iterating through all adjacent vertices a
		java.util.Iterator<neighbourNode> it = myG.returnIt(c);
		neighbourNode a=new neighbourNode();
		
		//if edge v-a is less than the key value in vertex a, update it 
		while(it.hasNext()) {
			a=it.next();
			if(a.getWeight() < dist[a.getvNum()])
				dist[a.getvNum()]=a.getWeight();
		}
		
	
	}
	return mst;
		
	}
	

	
public static int findMin(int[] dist, Boolean[] visited) {
		int i;
		int min=Integer.MAX_VALUE;
		int next=-1;
		for(i=0; i<visited.length; i++) {
			//System.out.println(i);
			if(dist[i] < min) {
				if(visited[i]==false) {
					min=dist[i];
					next=i;

				}
			}
			
			
		}//for
		
		return next; 
		
	}//findMin

}//class





