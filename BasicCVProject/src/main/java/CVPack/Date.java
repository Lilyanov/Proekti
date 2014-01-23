/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

class Date {
	
	private int day, month, year;
	
	public Date() {
		day = month = year = 0;
	}
	
	public Date(int day, int month, int year) {
		setDate(day, month, year);
	}
	
	//set data
	void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int compare(Date other) {
		if (this.year > other.year) {
			return -1;
		} else if (this.year < other.year) {
			return 1;
		} else {
			if (this.month > other.month) {
				return -1;
			} else if (this.month < other.month) {
				return 1;
			} else {
				if (this.day > other.day) {
					return -1;
				} else if (this.day < other.day) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}
	
	//getter methods
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}

}
