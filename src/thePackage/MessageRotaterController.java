package thePackage;

import java.util.Timer;
import java.util.TimerTask;
import thePackage.MessageRotater.Node;

public class MessageRotaterController {
	MessageRotater mr = new MessageRotater();
	private Node cursor = mr.getHeadNode();
	
	public String updateCur() {
		return mr.get(cursor.data);
	}
	public String deleteCur() {
		mr.remove(cursor);
		return updateCur();
	}
	public String addMes(String mes) {
		mr.add(mes);
		String ret = updateCur();
		return ret;
	}
	public void play() {
		CursorSwap cursorSwap = new CursorSwap();
		Timer timer = new Timer();
		timer.schedule(cursorSwap, 1000, 3000); // 1s delay, then 3s fixed looping interval
	}
	
	public class CursorSwap extends TimerTask {
		@Override
		public void run() {
			System.out.println("swap cursor to next...");
			cursor = cursor.next;
			updateCur();
		}
	}

}



