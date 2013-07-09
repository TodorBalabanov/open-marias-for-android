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

    public double getSum(int index) {
        //TODO To be done by Miro.
        return (0.0);
    }

    public void add(int index, double value) {
    }

    public void print() {
    }
}
