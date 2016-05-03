package jahv.jpahibernate.utils;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Class to hold utility methods
 * 
 * @author jose.hernandez
 * @since April 12, 2016
 * 
 */
public class Utils {

	/**
	 * Method to generate dynamic ids based on current time
	 * @return generated id
	 * @throws ParseException
	 */
	public static int generateIdBasedOnTime() {
		final Calendar calendar = Calendar.getInstance();
		final int hours = calendar.get(Calendar.HOUR_OF_DAY);
		final int minutes = calendar.get(Calendar.MINUTE);
		final int seconds = calendar.get(Calendar.SECOND);
		final int milliSeconds = calendar.get(Calendar.MILLISECOND);
		final String id = "" + hours + minutes + seconds + milliSeconds;
		return new Integer(id);
	}
}
