package eu.veldsoft.marias;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

/**
 * Class for ...
 *
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 06 Jul 2013
 */
class Datasets {
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
     * @param index Index of element into data set.
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
     * @param index Index of element into data set.
     * @return Calculated Sum value.
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

    public void add(int index, double value) {
    }

    public void print() {
    }
}
