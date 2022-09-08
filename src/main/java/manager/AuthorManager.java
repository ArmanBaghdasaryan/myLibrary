package manager;

import db.DBConnectionProvider;
import model.Author;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();


    public void add(Author author) {
        String sql = "insert into author(name,surname,email,age,author_pic) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setString(5, author.getAuthorPic());


            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAll() {
        String sql = "select * from author";
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getById(int id) {
        String sql = "select * from author where id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        return Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getInt("age"))
                .authorPic(resultSet.getString("author_pic"))
                .build();
    }

    public void removeAuthorById(int id) {
        String sql = "delete from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void edit(Author author) {
        String sql = "update author set `name`= ?,surname = ?,email = ?,age = ?,author_pic = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setString(5, author.getAuthorPic());
            ps.setInt(6, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
