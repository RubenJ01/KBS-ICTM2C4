package database.dao;

import database.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleDao {

    private static PeopleDao instance = null;

    private PeopleDao() {}

    public static PeopleDao getInstance() {
        if (instance == null) {
            instance = new PeopleDao();
        }
        return instance;
    }

    public List<Person> getAllPeople(Connection con) throws SQLException {
        String query = "SELECT * FROM people";
        List<Person> people = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    people.add(new Person(
                            rs.getInt("PersonID"),
                            rs.getString("FullName"),
                            rs.getString("PreferredName"),
                            rs.getString("SearchName"),
                            rs.getInt("IsPermittedToLogon"),
                            rs.getString("LogonName"),
                            rs.getInt("IsExternalLogonProvider"),
                            rs.getString("HashedPassword"),
                            rs.getInt("IsSystemUser"),
                            rs.getInt("IsEmployee"),
                            rs.getInt("IsSalesperson"),
                            rs.getString("UserPreferences"),
                            rs.getString("PhoneNumber"),
                            rs.getString("FaxNumber"),
                            rs.getString("EmailAddress"),
                            rs.getString("Photo"),
                            rs.getString("CustomFields"),
                            rs.getString("OtherLanguages"),
                            rs.getInt("LastEditedBy"),
                            rs.getDate("ValidFrom"),
                            rs.getDate("ValidTo")));
                }
            }
        }
        return people;
    }
}
