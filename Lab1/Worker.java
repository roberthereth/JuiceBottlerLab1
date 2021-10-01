package Lab1;

public class Worker implements Runnable {

	private final Thread thread;
	private boolean running;
	private final Lab1.Orange.State[] STATES = new Lab1.Orange.State[] { Lab1.Orange.State.Fetched,
			Lab1.Orange.State.Peeled, Lab1.Orange.State.Squeezed, Lab1.Orange.State.Bottled,
			Lab1.Orange.State.Processed };
	private Lab1.Orange.State job;

	Worker(int threadNum) {
		thread = new Thread(this, "Worker[" + threadNum + "]");
		job = STATES[threadNum - 1];
	}

	public void startWorker() {
		running = true;
		thread.start();
	}

	public void stopWorker() {
		running = false;
	}

	public void doJob(Orange o) {
		o.runProcess();
	}

	public Lab1.Orange.State getJob() {
		return this.job;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " Processing oranges");
	}
}
