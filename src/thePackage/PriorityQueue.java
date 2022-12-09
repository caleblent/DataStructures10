// modified from this original: https://github.com/C-FWES/BinaryHeap/blob/main/BinaryHeap.java

package thePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PriorityQueue {
	// Parent to find children: 2k + 1 = left, 2k+2 = right
	// Children to find parent: (k-1)/2
	// Requirements: Each step needs to be filled except last step
	// Parent needs to be greater/less than the children
	private ArrayList<Task> heapArray;
	
//	private int size;

	// constructor
	public PriorityQueue() {
		heapArray = new ArrayList<>();
//		size = 0;
	}
	
//	public int getSize() {
//		return this.size;
//	}

	/**
	 * Take a Task object and adds it to the heapArray
	 * 
	 * @param item
	 */
	private void add(Task item) {
//		size++;
		heapArray.add(item);
		swim(heapArray.size() - 1);
	}
	
	/**
	 * Packages the description and priority number as one Task object, then sends
	 * it to the private add() method to evaluate
	 * 
	 * @param description
	 * @param priority
	 */
	public void add(String description, int priority) {
		add(new Task(description, priority));
	}

	public void addAll(List<Task> list) {
		for (Task item : list) {
			add(item);
		}
	}
	
	public String getTopTaskDescription() {
		if (heapArray.size() == 0)
			return "*No hacking tasks currently available*";
		return heapArray.get(0).description;
	}
	public int getTopTaskPriority() {
		return heapArray.get(0).priority;
	}
	public Task getTopTask() {
		return heapArray.get(0);
	}
	
	public ArrayList<Task> getAllTasks() {
		return heapArray;
	}
	
	public void deleteTopTask() {
		if (heapArray.size() == 0) {
			return;
		} else if (heapArray.size() == 1) {
			heapArray.remove(0);
		} else {
			heapArray.remove(0);
	        heapArray.add(0, heapArray.get(heapArray.size()-1));
	        heapArray.remove(heapArray.size()-1);
	        sink(0);
		}
        
    }

	public Task takeRoot() {
		if (heapArray.size() == 0) {
			return null;
		}
		Task root = heapArray.get(0);
		Task last = heapArray.remove(heapArray.size() - 1);

		if (heapArray.size() == 0) {
			return root;
		}

		heapArray.set(0, last);
		sink(0);
		return root;
	}

	private void swap(int i, int j) {
		Task remember = heapArray.get(i);
		heapArray.set(i, heapArray.get(j));
		heapArray.set(j, remember);

	}

	private void swim(int i) {
		Task child = heapArray.get(i);
		int parentIndex = (i - 1) / 2;
		Task parent = heapArray.get(parentIndex);
		if (shouldSwap(child, parent)) {
			swap(i, parentIndex);
			if (parentIndex > 0) {
				swim(parentIndex);
			}
		}

	}

//	private boolean shouldSwap(T child, T parent) {
//		return max && child.compareTo(parent) >= 1 || !max && child.compareTo(parent) <= -1;
//	}
	
	private boolean shouldSwap(Task child, Task parent) {
		return child.priority - parent.priority < 0;
	}

	private void sink(int i) {
		Task parent = heapArray.get(i);
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * i + 2;
		int selectedChildIndex = -1;
		Task selectedChild = null;
		if (leftIndex > heapArray.size() - 1 && rightIndex > heapArray.size() - 1) {
			return;
		}
		if (rightIndex > heapArray.size() - 1) {
			selectedChildIndex = leftIndex;
			selectedChild = heapArray.get(leftIndex);
		} else {

			Task left = heapArray.get(leftIndex);
			Task right = heapArray.get(rightIndex);

			if (shouldSwap(left, right)) {
				selectedChildIndex = leftIndex;
				selectedChild = left;
			} else {
				selectedChildIndex = rightIndex;
				selectedChild = right;
			}
		}
		if (shouldSwap(selectedChild, parent)) {
			swap(selectedChildIndex, i);
			sink(selectedChildIndex);
		}
	}
	
	public void print() {
		for (int i = 0; i < heapArray.size(); i++) {
			System.out.print(heapArray.get(i).description + " ");
		}
	}
	
	public class Task {
		public String description;
		public int priority;
		public Task(String desc, int priority) {
			this.description = desc;
			this.priority = priority;
		}
		public int compareTo(Task o) {
			if (o == null)
				return Integer.MIN_VALUE;
			
			return this.priority - o.priority;
		}
	}
}