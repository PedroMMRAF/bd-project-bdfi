package bdfi;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

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
	private Gender gender;
	private String email;
	private String phone;
	private Show show; 		//primeira fase apenas tem um programa

	public PersonClass(String id, String name, int birthYear, Gender gender, String email, String phone) {
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
	public Gender getGender() {
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
}
