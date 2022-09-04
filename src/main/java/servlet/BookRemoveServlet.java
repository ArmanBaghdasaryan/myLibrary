package servlet;

import manager.BookManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/book/remove")
public class BookRemoveServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        bookManager.removeBookById(bookId);
        response.sendRedirect("/book");
    }


}
