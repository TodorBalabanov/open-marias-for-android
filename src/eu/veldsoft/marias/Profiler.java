package eu.veldsoft.marias;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Profiler class.
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 11 Aug 2013
 */
class Profiler {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Profiler.class
			.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

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
	 * 
	 * @param name
	 *            Search for timer with name.
	 * @return Return used time or 0 if it is not presented.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public long getTime(String name) {
		long time = System.currentTimeMillis();

		if (name == "") {
			return (time);
		}

		if (timers.containsKey(name) == false) {
			LOGGER.info(name + " timer has not started");

			return (0);
		}

		// TODO Java map can not have more than one instance.
		// if(timers.count(name)>1) {
		// qDebug() << "timer stop warning: " << name << " has multiple starts";
		// }

		return (time - timers.get(name));
	}

	/**
	 * ...
	 * 
	 * @return Return used time or 0 if it is not presented.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public long getTime() {
		return (getTime(""));
	}

	/**
	 * Initializes arrays. If provided with name, it only resets the timer with
	 * that name.
	 * 
	 * @param name
	 *            If name is presented clear only its values, clear all
	 *            otherwise.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public void reset(String name) {
		if (name == "") {
			counts.clear();
			timers.clear();
			totals.clear();
		} else {
			if (totals.containsKey(name) == false
					&& timers.containsKey(name) == false) {
				LOGGER.info(name
						+ " cant be reset - it has no entry in totals neither in timers");
			} else {
				if (totals.containsKey(name) == false) {
					stop(name);
				}
				totals.put(name, 0.0D);
			}
		}
	}

	/**
	 * Reset all statistics.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public void reset() {
		reset("");
	}

	/**
	 * Start timer.
	 * 
	 * @param name
	 *            Name of statistics.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public void start(String name) {
		timers.put(name, System.currentTimeMillis());
	}

	/**
	 * Stop timer.
	 * 
	 * @param name
	 *            Name of statistics.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Aug 2013
	 */
	public void stop(String name) {
		long time = System.currentTimeMillis();

		if (timers.containsKey(name) == false) {
			LOGGER.info(name + " timer has not started");
			return;
		}

		// if(timers.count(name)>1) {
		// LOGGER.info( "timer stop warning: " + name + " has multiple starts");
		// }

		counts.put(name,
				((counts.get(name) == null) ? 0 : counts.get(name)) + 1);
		totals.put(name, ((totals.get(name) == null) ? 0 : totals.get(name))
				+ time - timers.get(name));

		/*
		 * this is weird - removing the timer has influence on qrand (check it
		 * by print a qrand() before and after this) this comment is deprecated
		 * due to custom random generator
		 */
		timers.remove(name);
	}
}
