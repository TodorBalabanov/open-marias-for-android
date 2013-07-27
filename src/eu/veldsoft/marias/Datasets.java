package eu.veldsoft.marias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for ...
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 06 Jul 2013
 */
class Datasets {
	/**
	 * Logger for debug.
	 */
	private final static Logger LOGGER = Logger.getLogger(Card.class.getName());
	static {
		LOGGER.setLevel(Level.INFO);
	}

	/**
	 * Data set container.
	 */
	private Map<Integer, List<Double>> data = new HashMap<Integer, List<Double>>();

	/**
	 * Constructor without parameters.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 09 Jul 2013
	 */
	public Datasets() {
	}

	/**
	 * Clear data set.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 04 Jul 2013
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * Average value getter.
	 * 
	 * @param index
	 *            Index of element into data set.
	 * @return Calculated average value.
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 06 Jul 2013
	 */
	public double getAvg(int index) {
		if (data.get(index).size() == 0) {
			return 0.0;
		}

		return (getSum(index) / data.get(index).size());
	}

    /**
     * Sum value getter.
     *
     * @param index Index of element into data set.
     * @return Calculated Sum value.
     *
     * @author Miroslav Gyonov
     * @email mirkoslavcho1@abv.bg
     * @date 17 Jul 2013
     */
    public double getSum(int index) {
        double sum = 0;
        List<Double> list = data.get(index);

        for(Double value : list) {
            sum += value;
        }

        return( sum );
    }

    /**
	 * Add index in arraylist.
	 * 
	 * @param index
	 *            Index of element into data set.
	 * @param value
	 *            Value to be added.
	 * @author Vencislav Medarov
	 * @email venci932@gmail.com
	 * @date 11 Jul 2013
	 */
	public void add(int index, double value) {
		if (data.containsKey(index) == false) {
			data.put(index, new ArrayList<Double>());
		}
		
		data.get(index).add(value);
	}

	/**
	 * Print information for debug only.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 11 Jul 2013
	 */
	public void print() {
		Collection<List<Double>> vlaues = data.values();

		int i = 0;
		for (List<Double> list : vlaues) {
			for (Double value : list) {
				LOGGER.info(" Expected reward " + i + ": " + value);
			}
			i++;
		}

	}
}
