/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

class EducationInformation {
	private EducationInstitute university;
	private EducationInstitute school;

	public EducationInformation() {
		university = new EducationInstitute();
		school = new EducationInstitute();
	}

	public void setUniversity(String name, Date startDate, Date endDate, String qualification) {
		university.setAll(name, startDate, endDate, qualification);
	}

	public void setSchool(String name, Date startDate, Date endDate, String qualification) {
		school.setAll(name, startDate, endDate, qualification);
	}

	final public EducationInstitute getUniversity() {
		return university;
	}

	final public EducationInstitute getSchool() {
		return school;
	}
}