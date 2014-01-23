/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
 */

package CVPack;



import java.util.InputMismatchException;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVWriter;

class CV implements basicCV {
	private Scanner scan;
	private PersonalInformation personal;
	private EducationInformation education;
	boolean school, university;

	public CV() {
		personal = new PersonalInformation();
		education = new EducationInformation();
		scan = new Scanner(System.in);
		school = university = false;
	}
	
	protected void finalize() {
		scan.close();
	}
	
	public PersonalInformation getPersonalInformation() {
		return personal;
	}

	public EducationInformation getEducationInformation() {
		return education;
	}

	private void setDate(Date date) {
		Scanner local_scan = new Scanner(System.in);
		int day = 0, month = 0, year = 0;

		try {
			System.out.print("Enter day: ");	day = local_scan.nextInt();
			System.out.print("Enter month: ");	month = local_scan.nextInt();
			System.out.print("Enter year: ");	year = local_scan.nextInt();
		} catch (InputMismatchException ex) {
			day = -1;
		}
		
		// until validation is false set_date() is called again
		if (Validations.date(day, month, year))
			date.setDate(day, month, year);
		else {
			setDate(date);
			return;
		}
	}
	
	private String convertDateToString(Date date) {
		return "" + date.getDay() + "." + date.getMonth() + "." + date.getYear();
	}

	private void setPersonalInformation() {
		String information;
		Date date = new Date();
		boolean flag = false;
		
		// insert first name
		do {
			if(flag) {
				System.out.println("Invalid first name.Insert again!");
			}
			
			System.out.print("First name: ");
			information = scan.nextLine();
			flag = true;
		} while (!Validations.name(information));

		personal.setFirstName(information);
		flag = false;

		// insert last name
		do {
			if(flag) {
				System.out.println("Invalid last name.Insert again!");
			}
			System.out.print("Last name: ");
			information = scan.nextLine();
			flag = true;
		} while (!Validations.name(information));

		personal.setLastName(information);
		flag = false;

		// insert address
		System.out.print("Address: ");
		information = scan.nextLine();
		personal.setAddress(information);

		// insert phone
		do {
			if(flag) {
				System.out.println("Invalid phone.Insert again!");
			}
			System.out.print("Phone: ");
			information = scan.nextLine();
			flag = true;
		} while (!Validations.phone(information));

		personal.setPhone(information);
		flag = false;

		// insert mail
		do {
			if(flag) {
				System.out.println("Invalid email.Insert again!");
			}
			System.out.print("Email: ");
			information = scan.nextLine();
			flag = true;
		} while (!Validations.email(information));

		personal.setEmail(information);
		flag = false;

		// insert nationality
		System.out.print("Nationality: ");
		information = scan.nextLine();	personal.setNationality(information);

		System.out.println("Enter Date of birth(dd-mm-yy): ");
		setDate(date);
		personal.setDateOfBirth(date);

		// insert gender
		do {
			if(flag) {
				System.out.println("Invalid gender.Insert again!");
			}
			System.out.print("Gender:(male/female) ");
			information = scan.nextLine();
			flag = true;
		} while (!Validations.gender(information));

		personal.setGender(information);

		System.out.println();
	}

	private void setEducationInstitution(String institution) {
		String name, qualification;
		Date startDate = new Date(1, 1, 1);
		Date endDate = new Date();
		String answer;
		boolean flag = false;

		System.out.print("Do you have " + institution + " degree(y/n): ");
		answer = scan.nextLine();
		
		if(answer.equals("y")) {
			// insert institution name
			do {
				if(flag) {
					System.out.println("Invalid " + institution + " name.Insert again!");
				}
				System.out.print(institution + " name: ");
				name = scan.nextLine();
				flag = true;
			} while(!Validations.name(name));

			flag = false;

			// insert institution start and end dates
			while (startDate.compare(endDate) < 0) {
				if (flag) {
					System.out.println("Cannot start date > end date.Enter dates again!");
				}
				
				System.out.println("Start " + institution + " date(dd-mm-yy): ");
				setDate(startDate);

				System.out.println("End " + institution + " date(dd-mm-yy): ");
				setDate(endDate);

				flag = true;
			}
		
			System.out.print(institution + " qualification awarded: ");
			qualification = scan.nextLine();
			
			// set for given institution
			if("School".equals(institution)) {
				System.out.println("------------------------------");
				education.setSchool(name, startDate, endDate, qualification);
				school = true;
			}
			if("University".equals(institution)) {
				education.setUniversity(name, startDate, endDate, qualification);
				university = true;
			}
				
		}

		System.out.println();
	}

	public void setCV() {
		System.out.println("Please insert your personal information:");
		setPersonalInformation();
		
		System.out.println("Please insert information about your education:");
		setEducationInstitution("School");
		if(school) {
			setEducationInstitution("University");
		}
	}

	public void showCV() {
		System.out.println("{");
		System.out.println("\tCV:{");

		System.out.println(personal.toString());
		if(school) {
			System.out.println((education.getSchool().toString("School")));
		}
		if(university) {
			System.out.println((education.getUniversity().toString("University")));
		}
		System.out.println("\t}");
		System.out.println("}");

	}

	public void exportInFile(CSVWriter writer) {
		String value[] = {
				personal.getFirstName(),
				personal.getLastName(),
				convertDateToString(personal.getDateOfBirth()),
				personal.getEmail(),
				personal.getPhone(),
				personal.getAddress(),
				personal.getNationality(),
				personal.getGender(),
				// university information
				education.getUniversity().getName(),
				convertDateToString(education.getUniversity().getStartDate()),
				convertDateToString(education.getUniversity().getEndDate()),
				education.getUniversity().getQualification(),
				// school information
				education.getSchool().getName(),
				convertDateToString(education.getSchool().getStartDate()),
				convertDateToString(education.getSchool().getEndDate()),
				education.getSchool().getQualification()
				};
		writer.writeNext(value);
	}
}
