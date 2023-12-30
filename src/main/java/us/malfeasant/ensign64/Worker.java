package us.malfeasant.ensign64;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import us.malfeasant.ensign64.config.Configuration;

/**
 * Manages the thread (technically a pool, albeit a pool of one) that runs the simulation.
 * Allows setting of a speed- single step, realtime, or fast.  Realtime goal will be as
 * close to exact as practical- we'll run periodically, figuring out how many cycles to run
 * dynamically.  Fast schedules a bunch of cycles without delay- being single threaded,
 * a new batch begins only when the last batch completes.
 *
 * @author Malfeasant
 */
public class Worker {
	private final ScheduledExecutorService exec;
	private Mode mode = Mode.STEP;
	private ScheduledFuture<?> scheduled;	// TODO: more specific type?
	private final Configuration config;
	
	public enum Mode {
		STEP {
			@Override
			protected void start(Worker w) {
				w.exec.schedule(() -> w.run(1), 0, TimeUnit.NANOSECONDS);
			}
		},
		REAL {
			@Override
			protected void start(Worker w) {
				w.scheduled = w.exec.scheduleAtFixedRate(() -> w.run(250), 0, 1000000000, TimeUnit.NANOSECONDS);	// TODO fire x cycles every y ms
			}
		},
		FAST {
			@Override
			protected void start(Worker w) {
				w.scheduled = w.exec.scheduleAtFixedRate(() -> w.run(500), 0, 1, TimeUnit.NANOSECONDS);	// TODO fire x cycles
			}
		};
		
		protected void start(Worker w) {}
		private void stop(Worker w) {
			w.scheduled.cancel(false);
			w.scheduled = null;
		}
	}
	
	public Worker(Configuration c) {
		exec = Executors.newSingleThreadScheduledExecutor();
		config = c;
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
		mode.stop(this);
		mode = newMode;
		mode.start(this);
	}
	
	/**
	 * Shuts down the ExecutorService.  Scheduling anything after this will throw an exception.
	 * Must be called on quit, otherwise thread will live forever.
	 */
	public void shutdown() {
		exec.shutdown();
	}
}
