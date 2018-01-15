
public class neighbourNode {

	int vNum;
	int weight;
	

	neighbourNode(int vnum, int weight) {
		this.vNum=vnum;
		this.weight=weight;
		
	}
	
	public neighbourNode() {
		vNum=-1;
		weight=-1; 
	}

	public int getvNum() {
		return vNum;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String toString() {
		
		
		return  vNum + " " + weight + "\n";
		
		
	}
	
	public boolean equals(neighbourNode other) {
		if(vNum==other.vNum && weight==other.weight)
			return true;
					
		return false;
		
	}
	
	public boolean equals(int neighbour, int weight) {
		if(this.vNum==neighbour&& this.weight==weight)
			return true;
					
		return false;
	}
	
	public int compareTo(neighbourNode other) {
		//returns -1 if current neighbour node has a smaller weight, 1 if it has a larger weight and 0 if they are equal
		if(weight < other.weight) 
			return -1;
		if(weight > other.weight) 
			return 1;
		return 0;
		
	}
	
}
