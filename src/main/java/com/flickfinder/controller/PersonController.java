package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;
import io.javalin.http.Context;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Person;
import com.flickfinder.model.Movie;


public class PersonController {

	private final PersonDAO personDAO;

	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public void getAllPeople(Context ctx) {
		try {
			List<Person> people = personDAO.getAllPeople();
			ctx.json(people);
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	public void getPersonById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null){
				ctx.status(404);
				ctx.result("Person not found");
			} else {
				ctx.json(person);
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	public void getMoviesStarringPerson(Context ctx) {
		int personId = Integer.parseInt(ctx.pathParam("id"));
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(personId);
			ctx.json(movies);
		} catch (SQLException e) {
			ctx.status(500).result("Database error");
			e.printStackTrace();
		}
	}
}