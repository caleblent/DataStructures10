// stolen from: https://github.com/C-FWES/BinaryHeap/blob/main/BinaryHeap.java

package thePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BinaryHeap<T extends Comparable<T>> {
	Comparable c = null;
	// Parent to find children: 2k + 1 = left, 2k+2 = right
	// Children to find parent: (k-1)/2
	// Requirements: Each step needs to be filled except last step
	// Parent needs to be greater/less than the children
	private ArrayList<T> heapArray = new ArrayList<>();

	private boolean max = true;

	public BinaryHeap(Boolean maxFlag) { // constructor
		this.max = maxFlag;
	}

	public void add(T item) {
		heapArray.add(item);
		swim(heapArray.size() - 1);
	}

	public void addAll(List<T> list) {
		for (T item : list) {
			add(item);
		}
	}

	public T takeRoot() {
		if (heapArray.size() == 0) {
			return null;
		}
		T root = heapArray.get(0);
		T last = heapArray.remove(heapArray.size() - 1);

		if (heapArray.size() == 0) {
			return root;
		}

		heapArray.set(0, last);
		sink(0);
		return root;
	}

	private void swap(int i, int j) {
		T remember = heapArray.get(i);
		heapArray.set(i, heapArray.get(j));
		heapArray.set(j, remember);

	}

	private void swim(int i) {
		T child = heapArray.get(i);
		int parentIndex = (i - 1) / 2;
		T parent = heapArray.get(parentIndex);
		if (shouldSwap(child, parent)) {
			swap(i, parentIndex);
			if (parentIndex > 0) {
				swim(parentIndex);
			}
		}

	}

	private boolean shouldSwap(T child, T parent) {
		return max && child.compareTo(parent) >= 1 || !max && child.compareTo(parent) <= -1;
	}

	private void sink(int i) {
		T parent = heapArray.get(i);
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * i + 2;
		int selectedChildIndex = -1;
		T selectedChild = null;
		if (leftIndex > heapArray.size() - 1 && rightIndex > heapArray.size() - 1) {
			return;
		}
		if (rightIndex > heapArray.size() - 1) {
			selectedChildIndex = leftIndex;
			selectedChild = heapArray.get(leftIndex);
		} else {

			T left = heapArray.get(leftIndex);
			T right = heapArray.get(rightIndex);

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
}