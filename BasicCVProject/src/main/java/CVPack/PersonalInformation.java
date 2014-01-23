/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

class PersonalInformation {
	private String firstName, lastName, address, email, phone, nationality,
			gender;
	private Date birthDate;

	public PersonalInformation() {
		firstName = lastName = address = phone = nationality = gender = "";
		birthDate = new Date();
	}

	// setter methods

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setDateOfBirth(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// getter methods

	final public String getFirstName() {
		return firstName;
	}

	final public String getLastName() {
		return lastName;
	}

	final public String getAddress() {
		return address;
	}

	final public String getPhone() {
		return phone;
	}

	final public Date getDateOfBirth() {
		return birthDate;
	}

	final public String getEmail() {
		return email;
	}

	final public String getNationality() {
		return nationality;
	}

	final public String getGender() {
		return gender;
	}

	final public String toString() {
		return "\t\tFirst name: " + getFirstName() + "\n" 
				+ "\t\tLast name: " + getLastName() + "\n"
				+ "\t\tDate of birth: "
				+ getDateOfBirth().getDay() + "."
				+ getDateOfBirth().getMonth() + "."
				+ getDateOfBirth().getYear() + "\n"
				+ "\t\tEmail: " + getEmail() + "\n"
				+ "\t\tPhone: " + getPhone() + "\n"
				+ "\t\tAddress: " + getAddress() + "\n"
				+ "\t\tNationality: " + getNationality() + "\n"
				+ "\t\tGender: " + getGender();
	}
}
