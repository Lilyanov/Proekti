/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

class EducationInstitute {
	private String name, qualification;
	Date startDate, endDate;

	public EducationInstitute() {
		name = qualification = "";
		startDate = new Date();
		endDate = new Date();
	}

	// setter methods

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setAll(String name, Date startDate, Date endDate, String qualification) {
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		setQualification(qualification);
	}

	// getter methods

	final public String getName() {
		return name;
	}

	final public Date getStartDate() {
		return startDate;
	}

	final public Date getEndDate() {
		return endDate;
	}

	final public String getQualification() {
		return qualification;
	}

	public String toString(String institution) {
		return	"\t\t" + institution + ":{\n"
				+ "\t\t\t" + institution + " name: " + getName() + "\n"
				+ "\t\t\t" + institution + " start date: "
				+ getStartDate().getDay() + "."
				+ getStartDate().getMonth() + "."
				+ getStartDate().getYear() + "\n"
				+ "\t\t\t" + institution + " end date: "
				+ getEndDate().getDay() + "."
				+ getEndDate().getMonth() + "."
				+ getEndDate().getYear() + "\n"
				+ "\t\t\t" + institution + " Qualification: " + getQualification() + "\n"
				+ "\t\t}";
	}
}