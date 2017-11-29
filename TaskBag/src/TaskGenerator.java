import java.util.PriorityQueue;

public class TaskGenerator extends Thread {

	PriorityQueue<Task> taskBag;
	
	public TaskGenerator(PriorityQueue<Task> tb) {
		taskBag = tb;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				taskBag.add(new Task());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
