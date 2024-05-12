package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.util.Database;

public class MovieDAO {

	private final Connection connection;

	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	public List<Movie> getAllMovies() throws SQLException {
		List<Movie> movies = new ArrayList<>();

		Statement statement = connection.createStatement();
		
		// I've set the limit to 10 for development purposes - you should do the same.
		ResultSet rs = statement.executeQuery("select * from movies LIMIT 50");
		
		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}
		return null;

	}
	public List<Movie> getRatingsByYear(int year) throws SQLException {
		List<Movie> movies = new ArrayList<>();
		String query = "SELECT * FROM ratings WHERE year = ? ORDER BY rating DESC";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, year);
			try (ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				float rating = rs.getFloat("rating");
				Movie movie = new Movie(id, title, year, rating);
				movies.add(movie);		
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} return movies;
	}

}
