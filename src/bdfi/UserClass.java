package bdfi;

public class UserClass implements User {
	private String Userid, name, email, phoneNumber, gender;
	int birthYear;

	public UserClass(String id, String name, String email, String phoneNumber, String gender, int birthYear) {
		this.Userid = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthYear = birthYear;
	}

	@Override
	public String getID() {
		return Userid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getBirthYear() {
		return birthYear;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getGender() {
		return gender;
	}

}
