import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Graph {

private LinkedList<neighbourNode>[] aList;
int n; //number of vertices

public Graph(int n0) {
	
	n=n0;
	aList=new LinkedList[n];
	for(int i=0; i<n; i++) {
		aList[i]=new LinkedList<neighbourNode>();
		
	}
	
}

public void fillGraph(String filename) throws FileNotFoundException {
	
	String line = "";
    String separator = ",";
    String[] hold;
    
    //New read object
    BufferedReader br = new BufferedReader(new FileReader(filename));
    int j=0;
    
    try {
		while ((line = br.readLine()) != null) {
		//Read in a line. each line need to be added to add edge
		j++;

		    // Use comma as separator and put into array hold[]
		    hold = line.split(separator);
		    //Turn letter to number and send to addEdge
		    //System.out.println((int) hold[0].charAt(0)-65);
		    //System.out.println((int) hold[1].charAt(0) -65);
		    addEdge(((int) hold[0].charAt(0) -65), (int) hold[1].charAt(0) -65, Integer.parseUnsignedInt(hold[2]));
		}
		
		
		
	} catch (IOException e) {
		
		e.printStackTrace();
		System.out.println("Error in reading file.");
		}
	
}

public void printGraph(String filename) {
	
	String alphabet[]={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O","P","Q"};
	 PrintWriter pw = null;
	try {
		pw = new PrintWriter(filename);
	} catch (FileNotFoundException e) {
		System.out.println("File not found exception (writing)");
		e.printStackTrace();
	}
	
for(int i=0; i< aList.length; i++) {
		
		Iterator<neighbourNode> it = returnIt(i);
		while(it.hasNext()) {
			neighbourNode n=it.next();
			pw.print(alphabet[i] + "," + alphabet[n.getvNum()] + "," +n.getWeight() + "\n");
		}//while
	}//for

pw.close();
	
	
}

public void addEdge(int start, int end, int weight) {
	aList[start].add((new neighbourNode(end,weight)));
	aList[end].add((new neighbourNode(start,weight)));
}

public String toString() {
	String alphabet[]={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O","P","Q"};
	String rslt="";
	for(int i=0; i< aList.length; i++) {
		
		Iterator<neighbourNode> it = returnIt(i);
		while(it.hasNext()) {
			neighbourNode n=it.next();
			rslt=rslt + alphabet[i] + " " + alphabet[n.getvNum()] + " " +n.getWeight() + "\n";
		}
	}
	
	return rslt;

}//toString

public int findNeighbourNum(int curNode, int dist) {
	//IMPORTANT NOTE: For the purposes of the algorithm, if our current node has two neighbours for which the weight between
	//them is the same, it should not matter. 
	
	if(dist==0) 
		//origin node
		return curNode;

	
	 Iterator<neighbourNode> it = returnIt(curNode);
	
	while(it.hasNext()) {
		neighbourNode temp=it.next();

		if(temp.weight==dist) {
			return temp.getvNum();
		}
	}
	//if the node doesn't exist
	System.out.println("Node could not be found");
	return -1;
	
}

public Iterator<neighbourNode> returnIt(int curNode) {
	return aList[curNode].iterator();
	
}

public int getSum() {
	
	int sum=0;
	for(int i=0; i<n; i++) {
		sum+=aList[i].remove().getWeight();
	}
	return sum;
}
	 
}
	
	
	

