/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

import au.com.bytecode.opencsv.CSVWriter;

public interface basicCV {
	public void setCV();

	public void showCV();

	public void exportInFile(CSVWriter writer);

}
