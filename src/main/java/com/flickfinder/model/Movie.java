package com.flickfinder.model;

public class Movie {

	private int id;
	private String title;
	private int year;
	private float rating;

	public Movie(int id, String title, int year) {
		this.id = id;
		this.title = title;
		this.year = year;
	}
	
	public Movie(int id, String title, int year, float rating) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", rating=" + rating + "]";
	}

}