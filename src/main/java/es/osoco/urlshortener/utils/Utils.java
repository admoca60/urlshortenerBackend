package es.osoco.urlshortener.utils;

/**
 * @author amolinca
 *
 */
public class Utils {
	public static boolean checkValidHashCode(String hashCode){
		final String hashCodePattern = "[a-zA-Z0-9]{1,8}";
		return hashCode.matches(hashCodePattern);
	}
}
