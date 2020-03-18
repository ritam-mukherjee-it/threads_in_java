package race_condition;


class RaceCondition {

	public static void main(String[] args) throws InterruptedException {

		LongWrapper longWrapper = new LongWrapper(0L);

		/*this runnable increment the value 10000 times*/
		Runnable r = () -> {
			
			for (int i = 0 ; i < 10_000 ; i++) {
				longWrapper.incrementValue();
			}
		};

		/*we are creating 1000 threads to run the runnable instance 10000 times*/
		Thread[] threads = new Thread[10_000];
		for (int i = 0 ; i < threads.length ; i++) {
			threads[i] = new Thread(r);
			threads[i].start();
		}
		
		for (int i = 0 ; i < threads.length ; i++) {
			threads[i].join();
		}

		/*we are suppose to get result as 100000000 but not every time we will get desired result*/
		System.out.println("Value = " + longWrapper.getValue());
	}
}
