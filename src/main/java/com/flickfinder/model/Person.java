package com.flickfinder.model;

public class Person {

	private int id;
	private String name;
	private int birthYear;

	public Person(int id, String name, int birthYear) {
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthYear=" + birthYear + "]";
	}
}
