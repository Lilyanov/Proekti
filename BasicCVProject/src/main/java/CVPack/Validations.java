/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

public class Validations {
	
	public static boolean date(int day, int month, int year) {
		if (day < 0) {
			System.out.println("Date is not correct!");
			return false;
		}
		
		if ((year > 0 && year < 2014) && (month > 0 && month < 13) && (day > 0 && day < 32)) {
			if (year % 4 == 0 && month == 2 && day > 28) {
					System.out.println("Date is not correct!");
					return false;
				}
				return true;
		}
		System.out.println("Date is not correct");
		return false;
	}
	
	public static boolean gender(String gender) {
		if ("male".equals(gender) || "female".equals(gender)) {
			return true;
		}
		return false;
	}
	
	public static boolean name(String name) {
		if (name.matches("[A-Z]([a-z]|[A-Z]|[\t, ])*")) {
			return true;
		}
		return false;
	}
	
	public static boolean phone(String phone) {
		if (phone.matches("[0-9]{6,}")) {
			return true;
		}
		return false;
	}
	
	public static boolean email(String email) {
		if (email.matches("([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]){3,10}\\.[a-z]{2,3}")) {
			return true;
		}
		return false;
	}
}
