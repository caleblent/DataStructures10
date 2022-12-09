package thePackage;

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
	
	@Override
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