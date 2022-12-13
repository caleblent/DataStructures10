package thePackage;

/**
 * The data structure that the Animal Guessing Game uses to hold data.
 * 
 * If the last char in the (String) data field is a question mark, the data
 * is interpreted as a 'question'. If not, it is considered an 'animal'. 
 * 
 * The game works as such, iterating through a tree of AnimalNodes containing
 * questions until it reaches a leaf node, which has an animal guess.
 * 
 * The user is asked if this leaf node is the animal they were thinking of.
 * If it is, the game restarts by going to the root node. If it is NOT,
 * the game prompts the user for the animal they were thinking of and a 
 * question which can be used to differentiate the two.
 * 
 * This data is added to AnimalNodes, inserted into the tree into appropriate
 * places, and the game restarts.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Can be used in conjunction with MachineLearning, which is the console 
 * implementation of the game
 * 
 * Can be used in conjunction with AnimalController, which is the GUI 
 * implementation of the game
 * 
 * @author Caleb Lent
 */
public class AnimalNode
{
	// attributes of an AnimalNode:
	private AnimalNode left;
	private String data;
	private AnimalNode right;

	// constructor
	public AnimalNode(AnimalNode left, String data, AnimalNode right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public void setLeft(AnimalNode left) {
		this.left = left;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public void setRight(AnimalNode right) {
		this.right = right;
	}
	
	public AnimalNode getLeft() {
		return this.left;
	}
	
	public String getData() {
		return this.data;
	}
	
	public AnimalNode getRight() {
		return this.right;
	}
	
	public boolean isQuestion() {
		return this.data.charAt(data.length()-1) == '?';
	}
	
	public void insertLeft(String newAnimal, String newQuestion) {
		// 1. new left node (with this data)
		// 2. new right node (with new user inputed animal data)
		// 3. update data in this node
		
		AnimalNode left = new AnimalNode(null, this.data, null);
		AnimalNode right = new AnimalNode(null, newAnimal, null);
		this.left = left;
		this.data = newQuestion;
		this.right = right;
	}
	
	public void insertRight(String newAnimal, String newQuestion) {
		// 1. new right node (with this data)
		// 2. new left node (with new user inputed animal data)
		// 3. update data in this node
		
		AnimalNode right = new AnimalNode(null, this.data, null);
		AnimalNode left = new AnimalNode(null, newAnimal, null);
		this.left = left;
		this.data = newQuestion;
		this.right = right;
	}
	
//	@Override
	public String toString() {
		String str = "";
		
		if (this.left == null) {
			str += "NULL";
		} else {
			str += this.left.getData();
		}
		str += " <-- " + this.data + " --> ";
		if (this.right == null) {
			str += "NULL";
		} else {
			str += this.right.getData();
		}
		
		return str;
	}
	
//	@Override
//	public String toString() {
//		String str = "";
//		
//		str += "       " + this.data + "       \n";
//		if (this.left == null) {
//			str += "NULL";
//		} else {
//			str += this.left.getData();
//		}
//		str += "          ";
//		if (this.right == null) {
//			str += "NULL";
//		} else {
//			str += this.right.getData();
//		}
//		
//		return str;
//	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
}