package com.george.blood_bank_app.controller;

import com.george.blood_bank_app.dao.BloodGroupDao;
import com.george.blood_bank_app.dao.UserDao;
import com.george.blood_bank_app.model.Admin;
import com.george.blood_bank_app.model.BloodGroup;
import com.george.blood_bank_app.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet({
        "/user_login", "/user_authenticate", "/user_dashboard", "/user_profile", "/user_edit", 
        "/update_user", "/user_register", "/new_user", "/user_password_change", "/user_password"
})
public class UserController extends HttpServlet {

    private UserDao userDao = new UserDao();
    private BloodGroupDao bloodGroupDao = new BloodGroupDao();
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/user_login":
                    userLogin(request, response);
                    break;
                case "/user_authenticate":
                    userAuthenticate(request, response);
                    break;
                case "/user_logout":
                    userLogout(request, response);
                    break;
                case "/user_dashboard":
                    userDashboard(request, response);
                    break;
                case "/user_profile":
                    userProfile(request, response);
                    break;
                case "/user_edit":
                    userEditProfile(request, response);
                    break;
                case "/update_user":
                    userUpdate(request, response);
                    break;
                case "/user_register":
                    userRegister(request, response);
                    break;
                case "/new_user":
                    newUser(request, response);
                    break;
                case "/user_password":
                    userPassword(request, response);
                    break;
                case "/user_password_change":
                    changePassword(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/login.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/login.jsp");
        dispatcher.forward(request, response);
    }

    private void userLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("successMsg", "Successfully Logout");
        response.sendRedirect("user_login");
    }

    private void userDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void userProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/view_profile.jsp");
        dispatcher.forward(request, response);
    }

    private void userEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/edit_profile.jsp");
        dispatcher.forward(request, response);
    }

    private void userPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/change_password.jsp");
        dispatcher.forward(request, response);
    }

    private void userAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDao.validateUser(username, password);
            String destPage = "/user_login";
            HttpSession session = request.getSession();

            if (user != null) {
                session.setAttribute("user", user);
                destPage = "/";
            } else {
                session.setAttribute("errorMsg", "Invalid username or password.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void userUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        boolean u;

        String donorID = request.getParameter("uID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String otherName = request.getParameter("otherName");
        String gender = request.getParameter("gender");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"), df);
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String postalAddress = request.getParameter("postalAddress");
        String bloodGroup = request.getParameter("bloodGroup");

        User updatedUser = new User(donorID, firstName, lastName, otherName, gender, dob, contact, email, address, postalAddress, bloodGroup);
        u = userDao.updateUser(updatedUser);
        HttpSession session = request.getSession();

        if (u) {
            User updatedUserObject = userDao.getDonorByID(donorID);
            session.setAttribute("successMsg", "Profile details updated successfully.");
            session.setAttribute("user", updatedUserObject);
            response.sendRedirect("user_profile");
        } else {
            session.setAttribute("errorMsg", "Fialed to update profile details. Try again!");
            response.sendRedirect("user_edit");
        }

    }

    private void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/register.jsp");
        dispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String otherName = request.getParameter("otherName");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"), df);
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String postalAddress = request.getParameter("postalAddress");
        String bloodGroup = request.getParameter("bloodGroup");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setOtherName(otherName);
        newUser.setUsername(username);
        newUser.setGender(gender);
        newUser.setDob(dob);
        newUser.setContact(contact);
        newUser.setEmail(email);
        newUser.setAddress(address);
        newUser.setPostalAddress(postalAddress);
        newUser.setBloodGroup(bloodGroup);
        newUser.setPassword(password);

        try {
            boolean u = userDao.registerDonor(newUser);

            if (u) {
                session.setAttribute("successMsg", "Donor registered successfully.");
                response.sendRedirect("user_login");
            } else {
                session.setAttribute("errorMsg", "Failed to register donor.");
                response.sendRedirect("user_register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String donorID = request.getParameter("uID");
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("password2");

        try {
            HttpSession session = request.getSession();

            if (userDao.validateOldPassword(donorID, oldPassword)) {

                if (userDao.changePassword(donorID, newPassword)) {
                    session.setAttribute("successMsg", "Password successfully changed.");
                    session.removeAttribute("user");
                    response.sendRedirect("user_login");
                } else {
                    session.setAttribute("errorMsg", "Failed to change password. Try again!");
                    response.sendRedirect("user_password");
                }
            } else {
                session.setAttribute("errorMsg", "Old password does not match.");
                response.sendRedirect("user_password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
