package Lab1;

public class FundraiserSimulation {

	private final static long runtime = 5 * 1000;

	public static void main(String args[]) {

		Plant juicer1 = new Plant(1);
		Plant juicer2 = new Plant(2);

		juicer1.startPlant();
		juicer2.startPlant();

		delay(runtime, "Plant malfunction");

		juicer1.stopPlant();
		juicer2.stopPlant();

		int totalProvided = 0;
		int totalProcessed = 0;
		int totalBottles = 0;
		int totalWasted = 0;
		totalProvided += juicer1.getProvidedOranges() + juicer2.getProvidedOranges();
		totalProcessed += juicer1.getProcessedOranges() + juicer2.getProcessedOranges();
		totalBottles += juicer1.getBottles() + juicer2.getBottles();
		totalWasted += juicer1.getWaste() + juicer2.getWaste();
		System.out.println("Total provided/processed = " + totalProvided + "/" + totalProcessed);
		System.out.println("Created " + totalBottles + ", wasted " + totalWasted + " oranges");
	}

	private static void delay(long time, String errMsg) {
		long sleepTime = Math.max(1, time);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(errMsg);
		}
	}
}
