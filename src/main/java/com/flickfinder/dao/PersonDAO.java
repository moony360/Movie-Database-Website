package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Person;
import com.flickfinder.model.Movie;
import com.flickfinder.util.Database;


public class PersonDAO {

	private final Connection connection;

	public PersonDAO() {
		Database database = Database.getInstance();
		this.connection = database.getConnection();
		if (this.connection == null) {
			System.err.println("Failed to initialise PersonDAO: Database connection is null");
		}
	}

	public List<Person> getAllPeople() throws SQLException {
		List<Person> people = new ArrayList<>();

		String query = "SELECT * FROM people LIMIT 50";
		try (PreparedStatement statement = connection.prepareStatement(query);

		ResultSet rs = statement.executeQuery()) {

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int birthYear = rs.getInt("birth");

			Person person = new Person(id, name, birthYear);
			people.add(person);
		}
		
		rs.close();
		statement.close();
		}	return people;
	}
	
	public Person getPersonById(int id) throws SQLException {
		String query = "SELECT * FROM people WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);

		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			String name = rs.getString("name");
			int birthYear = rs.getInt("birth");

			Person person = new Person(id, name, birthYear);
			return person;
		} else {
			return null;
		}
	}

	public List<Person> getPeopleByMovieId(int movieId) throws SQLException {
		List<Person> people = new ArrayList<>();
		String query = "SELECT * FROM People WHERE id IN (SELECT person_id FROM Stars WHERE movie_id = ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, movieId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int birthYear = rs.getInt("birth");
				Person person = new Person(id, name, birthYear);
				people.add(person);
			}
		} return people;
	}

	public List<Movie> getMoviesStarringPerson(int personId) throws SQLException {
		List<Movie> movies = new ArrayList<>();
		String query = "SELECT m.id, m.title, m.year FROM movies m INNER JOIN stars s ON m.id = s.movie_id WHERE s.person_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, personId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int year = rs.getInt("year");
				Movie movie = new Movie(id, title, year);
				movies.add(movie);
			}
		}
		return movies;
	}
}
