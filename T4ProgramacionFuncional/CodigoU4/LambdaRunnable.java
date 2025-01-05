package concurrente;

public class LambdaRunnable {

	public static void main(String[] args) {
		Runnable hilo1 = () -> {
			for(int i=1; i<=10; i++) {
				System.out.println("H1:\t" + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
			}
		};//final de Runnable hilo1
		
		Runnable hilo2 = () -> {
			for(int i=10; i>=1; i--) {
				System.out.println("H2:\t" + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
			}
		};//final de Runnable hilo2
		Thread t1 = new Thread(hilo1);
		Thread t2 = new Thread(hilo2);
		t1.start();
		t2.start();
	}

}
