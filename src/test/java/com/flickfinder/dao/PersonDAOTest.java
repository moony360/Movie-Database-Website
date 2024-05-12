package com.flickfinder.dao;

import com.flickfinder.util.Seeder;
import com.flickfinder.util.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Person;


class PersonDAOTest {

    private PersonDAO personDAO;

    private Seeder seeder;

    @BeforeEach
    void setUp() {
        var url = "jdbc:sqlite::memory:";
        seeder = new Seeder(url);
        Database.getInstance(seeder.getConnection());
        personDAO = new PersonDAO();
    }
    @Test
    void testGetAllPeople() {
        try {
            List<Person> people = personDAO.getAllPeople();
            assertNotNull(people);
            assertEquals(5, people.size());
        } catch (SQLException e) {
            fail("SQLException thrown");
            e.printStackTrace();
        }
    }
    @Test
    void testGetPersonById() {
        try {
            Person person = personDAO.getPersonById(1);
            assertNotNull(person);
            assertEquals(1, person.getId());
            assertEquals("Tim Robbins", person.getName());
            assertEquals(1958, person.getBirthYear());
        } catch (SQLException e) {
            fail("SQLException thrown");
            e.printStackTrace();
        }
    }
    @Test
    void testGetPersonByIdInvalidId() {
        try {
            Person person = personDAO.getPersonById(1000000);
            assertNull(person);
        } catch (SQLException e) {
            fail("SQLException thrown");
            e.printStackTrace();
        }
    }
    @AfterEach
    void tearDown(){
        seeder.closeConnection();
    }

}