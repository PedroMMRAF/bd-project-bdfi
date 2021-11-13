package bdfi;

/**
 * 
 * @author Guilherme Santana 60182
 * @author Pedro Fernandes 60694
 *
 */
public class PersonClass implements Person {
	private String id;
	private String name;
	private int birthYear;
	private String gender;
	private String email;
	private String phone;
	private Show show; 		//primeira fase apenas tem um programa

	public PersonClass(String id, String name, int birthYear, String gender, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.birthYear = birthYear;
		show = null;
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
	public int getBirthYear() {
		return birthYear;
	}

	@Override
	public String getGender() {
		return gender;
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
	public void addShow(Show show) {
		this.show = show;
	}

	@Override
	public Show showsIterator() {
		return show;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PersonClass))
			return false;
		PersonClass other = (PersonClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
