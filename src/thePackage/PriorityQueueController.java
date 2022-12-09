package thePackage;

public class PriorityQueueController {
	PriorityQueue pq = new PriorityQueue();
	public String updateTask() {
		String ret = pq.getTopTaskDescription();
		return ret;
	}
	public String deleteCurTask() {
		pq.deleteTopTask();
		String ret = updateTask();
		return ret;
	}
	public String addTask(String mes, int prior) {
		pq.add(mes, prior);
		String ret = updateTask();
		return ret;
	}
}
