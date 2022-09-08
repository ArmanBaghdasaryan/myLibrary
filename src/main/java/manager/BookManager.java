package manager;

import db.DBConnectionProvider;
import model.Author;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    AuthorManager authorManager = new AuthorManager();

    public void add(Book book) {
        String sql = "insert into book(title,description,price,author_id,book_pic) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setString(5, book.getBookPic());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {
        String sql = "select * from book";
        List<Book> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                users.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Book getById(int id) {
        String sql = "select * from book where id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeBookById(int id) {
        String sql = "delete from book where id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getDouble("price"));
        int authorId = resultSet.getInt("author_id");
        Author author = authorManager.getById(authorId);
        book.setAuthor(author);
        book.setBookPic(resultSet.getString("book_pic"));

        return book;
    }

    public void edit(Book book) {
        String sql = "update book set title = ?,description = ?,price = ?,author_id = ?,book_pic = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setInt(5, book.getId());
            ps.setString(6, book.getBookPic());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}