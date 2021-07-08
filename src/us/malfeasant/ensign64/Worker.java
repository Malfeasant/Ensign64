package us.malfeasant.ensign64;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Worker {
	private final ScheduledExecutorService exec;
	private Mode mode = Mode.STEP;
	private Object modeLock = new Object();
	private ScheduledFuture<?> realTask;
	
	public enum Mode {
		STEP {
			@Override
			protected void start(Worker w) {
				w.exec.schedule(() -> w.run(1), 0, TimeUnit.MILLISECONDS);
			}
		},
		REAL {
			@Override
			protected void start(Worker w) {
				w.realTask = w.exec.scheduleAtFixedRate(() -> w.run(250), 0, 1000, TimeUnit.MILLISECONDS);	// TODO fire x cycles every y ms
			}
			@Override
			protected void stop(Worker w) {
				w.realTask.cancel(false);
			}
		},
		FAST {
			@Override
			protected void start(Worker w) {
				w.realTask = w.exec.scheduleAtFixedRate(() -> w.run(500), 0, 1, TimeUnit.MILLISECONDS);	// TODO fire x cycles
			}
			@Override
			protected void stop(Worker w) {
				w.realTask.cancel(false);
			}
		};
		
		protected void start(Worker w) {}
		protected void stop(Worker w) {}
	}
	
	public Worker() {
		exec = Executors.newSingleThreadScheduledExecutor();
	}
	
	private void run(int cycles) {
		System.out.print("Running...\t");
		try {
			Thread.sleep(cycles);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished " + cycles + " cycles.");
	}
	public void setMode(Mode newMode) {
		assert(newMode != null) : "Mode must not be null.";
		Mode oldMode = null;
		synchronized (modeLock) {
			oldMode = mode;
			mode = newMode;
		}
		oldMode.stop(this);
		newMode.start(this);
	}
	
	public void shutdown() {
		exec.shutdown();
	}
}
