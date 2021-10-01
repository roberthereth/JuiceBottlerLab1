package Lab1;

import java.lang.Thread.State;

public class Plant implements Runnable {
	// How long do we want to run the juice processing
	public static final long PROCESSING_TIME = 5 * 1000;
	private static final int NUM_WORKERS = 5;

	private static void delay(long time, String errMsg) {
		long sleepTime = Math.max(1, time);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(errMsg);
		}
	}

	public final int ORANGES_PER_BOTTLE = 3;

	private final Thread thread;
	private int orangesProvided;
	private int orangesProcessed;
	private volatile boolean timeToWork;
	private Worker[] workers;

	Plant(int threadNum) {
		orangesProvided = 0;
		orangesProcessed = 0;
		thread = new Thread(this, "Plant[" + threadNum + "]");
		workers = new Worker[NUM_WORKERS];
		for (int i = 0; i < NUM_WORKERS; i++) {
			workers[i] = new Worker(i + 1);
			workers[i].startWorker();
		}
	}

	public void startPlant() {
		timeToWork = true;
		thread.start();
	}

	public void stopPlant() {
		timeToWork = false;
	}

	public void waitToStop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println(thread.getName() + " stop malfunction");
		}
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " Processing oranges");
		while (timeToWork) {
			processEntireOrange(new Orange());
			orangesProvided++;
			System.out.print(".");
		}
		System.out.println("");
		System.out.println(Thread.currentThread().getName() + " Done");
	}

	public void processEntireOrange(Orange o) {
		while (o.getState() != Orange.State.Bottled) {
			Lab1.Orange.State s = o.getState();
			for (Worker w : this.workers) {
				if (s == w.getJob())
					w.doJob(o);
			}
		}
		orangesProcessed++;
	}

	public int getProvidedOranges() {
		return orangesProvided;
	}

	public int getProcessedOranges() {
		return orangesProcessed;
	}

	public int getBottles() {
		return orangesProcessed / ORANGES_PER_BOTTLE;
	}

	public int getWaste() {
		return orangesProcessed % ORANGES_PER_BOTTLE;
	}
}