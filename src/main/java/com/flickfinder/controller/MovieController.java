package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

public class MovieController {

	private final MovieDAO movieDAO;
	private final PersonDAO personDAO;

	public MovieController(MovieDAO movieDAO, PersonDAO personDAO) {
		this.movieDAO = movieDAO;
		this.personDAO = personDAO;
	}

	public void getAllMovies(Context ctx) {
		try {
			ctx.json(movieDAO.getAllMovies());
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	public void getPeopleByMovieId(Context ctx) {
		int movieId = Integer.parseInt(ctx.pathParam("id"));
		try { 
			List<Person> people = personDAO.getPeopleByMovieId(movieId);
			if (people.isEmpty()){
				ctx.status(400);
				ctx.result("No one found for the movie");

			} else {
				ctx.json(people);
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	public void getRatingsByYear(Context ctx) {
		int year = Integer.parseInt(ctx.pathParam("year"));
		try {
			List<Movie> movies = movieDAO.getRatingsByYear(year);
			ctx.json(movies);
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

}