package servlet;

import manager.AuthorManager;
import model.Author;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/author/edit")
public class AuthorEditServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        Author author = authorManager.getById(authorId);
        request.setAttribute("author", author);
        request.getRequestDispatcher("/WEB-INF/editAuthor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));


        Author author = Author.builder()
                .id(authorId)
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .build();

        authorManager.edit(author);
        res.sendRedirect("/author");

    }
}

