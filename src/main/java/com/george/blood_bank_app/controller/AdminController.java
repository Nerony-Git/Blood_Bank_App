package com.george.blood_bank_app.controller;

import com.george.blood_bank_app.dao.AdminDao;
import com.george.blood_bank_app.dao.BloodGroupDao;
import com.george.blood_bank_app.model.Admin;
import com.george.blood_bank_app.model.BloodGroup;

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
        "/admin_login", "/contact_us", "/about_us", "/admin_authenticate", "/admin_dashboard",
        "/admin_profile", "/admin_edit", "/update_admin", "/admin_register", "/new_admin",
        "/admin_password_change", "/admin_password"
})
public class AdminController extends HttpServlet {

    private AdminDao adminDao = new AdminDao();
    private BloodGroupDao bloodGroupDao = new BloodGroupDao();
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init(){
        adminDao = new AdminDao();
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
                case "/admin_login":
                    adminLogin(request, response);
                    break;
                case "/admin_logout":
                    adminLogout(request, response);
                    break;
                case "/contact_us":
                    contactUs(request, response);
                    break;
                case "/about_us":
                    aboutUs(request, response);
                    break;
                case "/admin_authenticate":
                    adminAuthenticate(request, response);
                    break;
                case "/admin_dashboard":
                    adminDashboard(request, response);
                    break;
                case "/admin_profile":
                    adminProfile(request, response);
                    break;
                case "/admin_edit":
                    adminEditProfile(request, response);
                    break;
                case "/update_admin":
                    adminUpdate(request, response);
                    break;
                case "/admin_register":
                    adminRegister(request, response);
                    break;
                case "/new_admin":
                    newAdmin(request, response);
                    break;
                case "/admin_password":
                    adminPassword(request, response);
                    break;
                case "/admin_password_change":
                    changePassword(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/login.jsp");
                    dispatcher.forward(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/login.jsp");
        dispatcher.forward(request, response);
    }

    private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        session.setAttribute("successMsg", "Successfully Logout");
        response.sendRedirect("admin_login");
    }

    private void contactUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/web/contact_us.jsp");
        dispatcher.forward(request, response);
    }

    private void aboutUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/web/about_us.jsp");
        dispatcher.forward(request, response);
    }

    private void adminDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void adminProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_profile.jsp");
        dispatcher.forward(request, response);
    }

    private void adminEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/edit_profile.jsp");
        dispatcher.forward(request, response);
    }

    private void adminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/change_password.jsp");
        dispatcher.forward(request, response);
    }

    private void adminAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Admin admin = adminDao.validateAdmin(username, password);
            String destPage = "/admin_login";
            HttpSession session = request.getSession();

            if (admin != null) {
                session.setAttribute("admin", admin);
                destPage = "/admin_dashboard";
            } else {
                session.setAttribute("errorMsg", "Invalid username or password.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException();
        }
    }

    private void adminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        boolean u;

        String adminID = request.getParameter("uID");
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

        Admin updatedAdmin = new Admin(adminID, firstName, lastName, otherName, gender, dob, contact, email, address, postalAddress, bloodGroup);
        u = adminDao.updateAdmin(updatedAdmin);
        HttpSession session = request.getSession();

        if (u) {
            Admin updatedAdminObject = adminDao.getAdminByID(adminID);
            session.setAttribute("successMsg", "Profile details updated successfully.");
            session.setAttribute("admin", updatedAdminObject);
            response.sendRedirect("admin_profile");
        } else {
            session.setAttribute("errorMsg", "Fialed to update profile details. Try again!");
            response.sendRedirect("admin_edit");
        }

    }

    private void adminRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/register.jsp");
        dispatcher.forward(request, response);
    }

    private void newAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Admin newAdmin = new Admin();
        newAdmin.setFirstName(firstName);
        newAdmin.setLastName(lastName);
        newAdmin.setOtherName(otherName);
        newAdmin.setUsername(username);
        newAdmin.setGender(gender);
        newAdmin.setDob(dob);
        newAdmin.setContact(contact);
        newAdmin.setEmail(email);
        newAdmin.setAddress(address);
        newAdmin.setPostalAddress(postalAddress);
        newAdmin.setBloodGroup(bloodGroup);
        newAdmin.setPassword(password);

        try {
            boolean u = adminDao.registerAdmin(newAdmin);

            if (u) {
                session.setAttribute("successMsg", "Admin registered successfully.");
                response.sendRedirect("admin_login");
            } else {
                session.setAttribute("errorMsg", "Failed to register admin.");
                response.sendRedirect("admin_register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminID = request.getParameter("uID");
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("password2");

        try {
            HttpSession session = request.getSession();

            if (adminDao.validateOldPassword(adminID, oldPassword)) {

                if (adminDao.changePassword(adminID, newPassword)) {
                    session.setAttribute("successMsg", "Password successfully changed.");
                    session.removeAttribute("admin");
                    response.sendRedirect("admin_login");
                } else {
                    session.setAttribute("errorMsg", "Failed to change password. Try again!");
                    response.sendRedirect("admin_password");
                }
            } else {
                session.setAttribute("errorMsg", "Old password does not match.");
                response.sendRedirect("admin_password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
