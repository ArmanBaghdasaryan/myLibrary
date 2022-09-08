package manager;


import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();

    public void addUser(User user) {
        String sql = "Insert into user (name,surname,email,password,profile_pic) Values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getProfilePic());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            System.out.println(user);
            System.out.println("User was added successfully! ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> users = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }


    public User getById(int id) {
        String sql = "SELECT * FROM user  WHERE id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user  WHERE email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user  WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User getUserFromResulSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .profilePic(resultSet.getString("profile_pic"))
                .build();

    }

    public void editUser(User user) {
        String sql = "update user set `name`= ?,surname = ?,email = ?,passwrd = ?,profile_pic = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getProfilePic());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


