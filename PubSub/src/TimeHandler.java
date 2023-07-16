import java.util.concurrent.atomic.AtomicLong; // A long value that may be updated atomically

public class TimeHandler {

	private final AtomicLong endTime;  

	public TimeHandler(long time) {
		this.endTime = new AtomicLong(System.currentTimeMillis() + time); 
	}

	// to schedule messages and runnables to be executed at some point in the future

	public boolean isTimeout() {
		return System.currentTimeMillis() > endTime.get();
	}

	public long getRemainingTime() {
		return Math.max(0, endTime.get() - System.currentTimeMillis());
	}

	public void incrementTime(long time) {
		endTime.addAndGet(time);
	}

}
