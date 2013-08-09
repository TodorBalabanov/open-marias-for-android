package eu.veldsoft.marias;

import java.util.Map;

class Profiler {
	/**
	 * 
	 */
	public Map<String, Double> totals;

	/**
	 * 
	 */
	public Map<String, Integer> counts;

	/**
	 * 
	 */
	public Map<String, Long> timers;

	/**
	 * If name provided, returns the time in ms of timer with given name.
	 * Otherwise returns the current time in ms.
	 */
	public long getTime(String name) {
		// TODO To be done by ...
		return (0);
	}

	/**
	 * 
	 * @return
	 */
	public long getTime() {
		// TODO To be done by ...
		return (getTime(""));
	}

	/**
	 * Initializes arrays. If provided with name, it only resets the timer with
	 * that name.
	 * 
	 * @param name
	 */
	public void reset(String name) {
		// TODO To be done by ...
	}

	/**
	 * 
	 */
	public void reset() {
		// TODO To be done by ...
		reset("");
	}

	/**
	 * 
	 * @param name
	 */
	public void start(String name) {
		// TODO To be done by ...
	}

	/**
	 * 
	 * @param name
	 */
	public void stop(String name) {
		// TODO To be done by ...
	}
}
