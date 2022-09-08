package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/users/add")
@MultipartConfig(

        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100

)
public class AddUserServlet extends HttpServlet {

    private static final String IMAGE_PATH = "\\C:\\Users\\DELL\\javaLibraryImages\\";


    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (userManager.getUserByEmail(email) != null) {
            req.setAttribute("msg", "User already exist");
            req.getRequestDispatcher("/WEB-INF/addUser.jsp").forward(req, resp);


        } else {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            Part profilePicPart = req.getPart("profilePic");
            String fileName = null;
            if (profilePicPart != null) {
                long nanoTime = System.nanoTime();
                fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
                profilePicPart.write(IMAGE_PATH + fileName);

            }

            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .profilePic(fileName)
                    .build();

            userManager.addUser(user);

            resp.sendRedirect("/login");
        }

    }
}
