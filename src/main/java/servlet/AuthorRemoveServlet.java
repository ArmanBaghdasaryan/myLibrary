package servlet;

import manager.AuthorManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/author/remove")
public class AuthorRemoveServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        authorManager.removeAuthorById(authorId);
        response.sendRedirect("/author");
    }


}
