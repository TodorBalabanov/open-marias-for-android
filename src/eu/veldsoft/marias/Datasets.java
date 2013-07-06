package eu.veldsoft.marias;

import java.util.Map;
import java.util.List;

/**
 * Class for ...
 * 
 * @author Todor Balabanov
 * 
 * @email tdb@tbsoft.eu
 * 
 * @date 06 Jul 2013
 */
class Datasets {
	// TODO Find way to create container.
	private Map<Integer, List<Double>> data = null;

	public Datasets() {
	}

	/**
	 * Clear data set.
	 * 
	 * @author Todor Balabanov
	 * 
	 * @email tdb@tbsoft.eu
	 * 
	 * @date 04 Jul 2013
	 */
	public void clear() {
		data.clear();
	}

	public double getAvg(int index) {
		return (0.0);
	}

	public double getSum(int index) {
		return (0.0);
	}

	public void add(int index, double value) {
	}

	public void print() {
	}
}
