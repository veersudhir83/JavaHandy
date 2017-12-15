package io.sudheer.practice.ldapsearch;

/** Marker base class for all exceptions thrown by the classes in this package.
 * 
 * @author Sudheer Veeravalli
 *
 */
public class AZLookupException extends Exception {

	public AZLookupException() {
		super();
	}

	public AZLookupException(String arg0) {
		super(arg0);
	}

	public AZLookupException(Throwable arg0) {
		super(arg0);
	}

	public AZLookupException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/*public AZLookupException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}*/
}
