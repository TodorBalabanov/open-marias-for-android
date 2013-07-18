package eu.veldsoft.marias;

import java.util.Map;

class Profiler {
	public Map<String, Double> totals;

	public Map<String, Integer> counts;

	public Map<String, Long> timers;

	public Profiler() {
	}

	/**
	 * If name provided, returns the time in ms of timer with given name.
	 * Otherwise returns the current time in ms.
	 */
	public long getTime(String name) {
		return (0);
	}

	public long getTime() {
		return (getTime(""));
	}

	/**
	 * Initializes arrays. If provided with name, it only resets the timer with
	 * that name.
	 */
	public void reset(String name) {
	}

	public void reset() {
		reset("");
	}

	public void start(String name) {
	}

	public void stop(String name) {
	}
}
