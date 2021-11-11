package bdfi;

public class PersonClass implements Person {
	private String id;
	private String name;
	private String email;
	private String phone;
	private Gender gender;
	private int birthYear;

	public PersonClass(String id, String name, String email, String phone, Gender gender, int birthYear) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.birthYear = birthYear;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public int getBirthYear() {
		return birthYear;
	}

}
