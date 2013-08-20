package eu.veldsoft.marias;

/**
 * Results container class.
 * 
 * @author Todor Balabanov
 * @email tdb@tbsoft.eu
 * @date 17 Jul 2013
 */
public class ResRow {
	/**
	 * 
	 */
	private final String value1;

	/**
	 * 
	 */
	private final String value2;

	/**
	 * 
	 */
	private boolean value3;

	/**
	 * Constructor.
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public ResRow(String value1, String value2, boolean value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public String getValue1() {
		return( value1 );
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public String getValue2() {
		return( value2 );
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public boolean isValue3() {
		return( value3 );
	}

	/**
	 * @param value3
	 *            The value3 to set.
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public void setValue3(boolean value3) {
		this.value3 = value3;
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public String first() {
		return(value1);
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public String second() {
		return(value2);
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Todor Balabanov
	 * @email tdb@tbsoft.eu
	 * @date 17 Jul 2013
	 */
	public boolean third() {
		return(value3);
	}
}