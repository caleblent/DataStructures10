package thePackage;

public class MessageRotater {

	protected Node head; // the first node of the list
	protected Node tail; // the last node of the list
	protected Node location; // true if element found, else false
	protected int numElements; // Number of elements in this list
	protected boolean found; // true if element found, else false

	public MessageRotater()
	// default constructor
	{
		head = null;
		tail = null;
		numElements = 0;
		location = null;
	}

	public boolean isEmpty()
	// true if list is empty, else false
	{
		return (head == null);
	}

	public int size()
	// Determines the number of elements on this list
	{
		return numElements;
	}
	
	public Node getHeadNode() {
		return this.head;
	}

	protected void find(String target)
	// Searches list for an occurrence of an element. If successful, sets instance
	// variables
	// found to true, location to node containing the element, and previous
	// to the node that links to location. If not successful, sets found to false.
	{
		location = head;
		found = false;
		if (!isEmpty()) {
			do {
				if (location.data.equals(target)) // if they match
				{
					found = true;
					return;
				} else {
					location = location.next;
				}
			} while (location != tail.next);
		}

	}

	public boolean contains(String element)
	// Returns true if this list contains an element e such that
	// e.equals(element), otherwise returns false.
	{
		find(element);
		return found;
	}

	protected void findPosition(int position)
	// finds position in this list
	// Assumes Zero based indexing
	{
		int start = 0;
		location = head;
		found = false;

		if ((!isEmpty()) && (position >= 0) && (position <= size())) {
			do {
				// move search to the next node
				location = location.next;
				start++;

			} while ((location != head) && start < position);
			found = true;
		}

	}
	
	public String[] getAllMessages() {
		String[] ret = new String[numElements];
		Node curr = head;
		for (int i = 0; i < numElements; i++) {
			ret[i] = curr.data;
			curr = curr.next;
		}
		return ret;
	}

	public String get(String data)
	// Returns an element e from this list such that e.equals(element);
	// if no such element exists, returns null.
	{
		find(data);
		if (found)
			return location.data;
		else
			return null;
	}

	public void reset()
	// Initializes current position for an iteration through this list,
	// to the first element on this list.
	{
		location = head;
	}

	public void add(String data)
	// Adds element to (the end of) this list.
	{
		Node newNode = new Node(data); // Reference to the new node being added

		if (isEmpty()) // Adding into an empty list
		{
			head = newNode;
			tail = newNode;
			head.prev = tail;
			tail.next = head;
		} else // Adding into a non-empty list
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = head;
		}
		numElements++;
	}

	public boolean remove(String element)
	// Removes an element e from this list such that e.equals(element)
	// and returns true; if no such element exists, returns false.
	{
		find(element);
		if (found) {
			if (location == head && size() == 1) // removes the only existing element
													// empties the list
			{
				head = null;
				tail = null;

			} else if (location == head) // removes first node
			{
				head = head.next;
				head.prev = tail;
				tail.next = head;

			} else if (location == tail) // removes last node
			{

				tail = tail.prev;
				tail.next = head;
				head.prev = tail;
			} else { // removes node at location
				location.prev.next = location.next;
				location.next.prev = location.prev;
			}
			numElements--;
		}
		return found;
	}
	
	public boolean remove(Node element)
	// Removes an element e from this list such that e.equals(element)
	// and returns true; if no such element exists, returns false.
	{
		if (location == head && size() == 1) // removes the only existing element
												// empties the list
		{
			head = null;
			tail = null;

		} else if (location == head) // removes first node
		{
			head = head.next;
			head.prev = tail;
			tail.next = head;

		} else if (location == tail) // removes last node
		{

			tail = tail.prev;
			tail.next = head;
			head.prev = tail;
		} else { // removes node at location
			location.prev.next = location.next;
			location.next.prev = location.prev;
		}
		numElements--;
		
		return found;
	}

	public void removeAtPosition(int position)
	// removes the element in the specified position
	{
		if (position <= 0) { // removes front element
			head = head.next;
			head.prev = tail;
			tail.next = head;
		} else if (position >= size() - 1) { // remove last element

			tail = tail.prev;
			tail.next = head;
			head.prev = tail;

		} else {
			// general case
			// determines the position
			findPosition(position);

			// removes the element in the specified position
			location.prev.next = location.next;
			location.next.prev = location.prev;
		}
		numElements--;
	}

	public String toString()
	// prints the elements of the list in a nicely formated manner in forward order
	{
		String item = "List: [ ";

		Node current = head;

		if (!isEmpty()) {

			do {
				item += current.data + " ";
				current = current.next;
			} while (current != head);

		}
		item += "]";
		return item;

	}

	public String toStringReverse()
	// prints the elements of the list in a nicely formated manner in reverse order
	{

		String item = "List: [ ";

		Node current = tail;

		if (!isEmpty()) {
			do {
				item += current.data + " ";
				current = current.prev;
			} while (current != tail);
		}
		item += "]";
		return item;

	}

	public class Node {
		public Node prev;
		public Node next;
		public String data;
		public Node() {}
		public Node(String data) { this.data = data; }
	}

}
