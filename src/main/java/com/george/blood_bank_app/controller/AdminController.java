package com.george.blood_bank_app.controller;

import com.george.blood_bank_app.dao.*;
import com.george.blood_bank_app.model.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet({
        "/admin_login", "/contact_us", "/about_us", "/admin_authenticate", "/admin_dashboard",
        "/admin_profile", "/admin_edit", "/update_admin", "/admin_register", "/new_admin",
        "/admin_password_change", "/admin_password", "/admin_logout", "/users", "/view_donor",
        "/edit_donor", "/update_donor", "/view_donation", "/view_donations", "/edit_donation",
        "/update_donation", "/view_requests", "/view_request", "/edit_request", "/view_new_requests",
        "/update_request", "/view_camps", "/new_camp", "/add_camp", "/view_camp", "/edit_camp",
        "/update_camp"
})
public class AdminController extends HttpServlet {

    private AdminDao adminDao = new AdminDao();
    private UserDao userDao = new UserDao();
    private BloodGroupDao bloodGroupDao = new BloodGroupDao();
    private BloodDonationDao bloodDonationDao = new BloodDonationDao();
    private DonationCampDao donationCampDao = new DonationCampDao();
    private BloodRequestDao bloodRequestDao = new BloodRequestDao();
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
                case "/users":
                    adminUsers(request, response);
                    break;
                case "/view_donor":
                    viewDonor(request, response);
                    break;
                case "/edit_donor":
                    editDonor(request, response);
                    break;
                case "/update_donor":
                    donorUpdate(request, response);
                    break;
                case "/view_donations":
                    viewDonations(request, response);
                    break;
                case "/view_donation":
                    viewDonation(request, response);
                    break;
                case "/edit_donation":
                    editDonation(request, response);
                    break;
                case "/update_donation":
                    updateDonation(request, response);
                    break;
                case "/view_requests":
                    viewRequests(request, response);
                    break;
                case "/view_request":
                    viewRequest(request, response);
                    break;
                case "/edit_request":
                    editRequest(request, response);
                    break;
                case "/view_new_requests":
                    viewNewRequests(request, response);
                    break;
                case "/update_request":
                    updateRequest(request, response);
                    break;
                case "/view_camps":
                    viewDonationCamps(request, response);
                    break;
                case "/new_camp":
                    newDonationCamp(request, response);
                    break;
                case "/add_camp":
                    addNewDonationCamp(request, response);
                    break;
                case "/view_camp":
                    viewDonationCamp(request, response);
                    break;
                case "/edit_camp":
                    editDonationCamp(request, response);
                    break;
                case "/update_camp":
                    updateDonationCamp(request, response);
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
            /*String destPage = "/admin_login";*/
            HttpSession session = request.getSession();

            if (admin != null) {
                session.setAttribute("admin", admin);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard");
                dispatcher.forward(request, response);
                session.removeAttribute("successMsg");
                /*destPage = "/admin_dashboard";*/
            } else {
                session.setAttribute("errorMsg", "Invalid username or password.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_login");
                dispatcher.forward(request, response);
                session.removeAttribute("errorMsg");
            }

            /*RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);*/
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
            session.setAttribute("errorMsg", "Failed to update profile details. Try again!");
            response.sendRedirect("admin_edit");
        }

    }

    private void adminRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/register.jsp");
        dispatcher.forward(request, response);
    }

    private void adminUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<User> allDonors = userDao.getAllUsers();
        request.setAttribute("allDonors", allDonors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/users.jsp");
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
        String oldPassword = request.getParameter("password2");
        String newPassword = request.getParameter("password");

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

    private void viewDonor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donorID = request.getParameter("id");
        User donor = userDao.getDonorByID(donorID);
        request.setAttribute("donor", donor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_donor.jsp");
        dispatcher.forward(request, response);
    }

    private void editDonor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donorID = request.getParameter("id");
        User donor = userDao.getDonorByID(donorID);
        request.setAttribute("donor", donor);
        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/edit_donor.jsp");
        dispatcher.forward(request, response);
    }

    private void donorUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
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

        User updatedDonor = new User(donorID, firstName, lastName, otherName, gender, dob, contact, email, address, postalAddress, bloodGroup);
        u = userDao.updateUser(updatedDonor);
        HttpSession session = request.getSession();

        if (u) {
            User updatedUserObject = userDao.getDonorByID(donorID);
            session.setAttribute("successMsg", "Profile details updated successfully.");
            session.setAttribute("user", updatedUserObject);
            response.sendRedirect("view_donor?id=" + donorID);
            session.removeAttribute("successMsg");
        } else {
            session.setAttribute("errorMsg", "Failed to update profile details. Try again!");
            response.sendRedirect("edit_donor?id=" + donorID);
            session.removeAttribute("errorMsg");
        }

    }

    private void viewDonations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<BloodDonation> allBloodDonations = bloodDonationDao.getAllDonations();
        request.setAttribute("allBloodDonations", allBloodDonations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_donations.jsp");
        dispatcher.forward(request, response);
    }

    private void viewDonation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationID = request.getParameter("id");

        BloodDonation donation = bloodDonationDao.getDonationByID(donationID);
        request.setAttribute("donation", donation);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_donation.jsp");
        dispatcher.forward(request, response);
    }

    private void editDonation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationID = request.getParameter("id");

        BloodDonation donation = bloodDonationDao.getDonationByID(donationID);
        request.setAttribute("donation", donation);

        List<DonationCamp> donationCamps = donationCampDao.getAllCamps();
        request.setAttribute("donationCamps", donationCamps);

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/edit_donation.jsp");
        dispatcher.forward(request, response);
    }

    private void updateDonation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationCamp = request.getParameter("donationCamp");
        LocalDate donationDate = LocalDate.parse(request.getParameter("donationDate"), df);
        int bloodUnit = Integer.parseInt(request.getParameter("bloodUnit"));
        String donationID = request.getParameter("donationID");

        BloodDonation bloodDonation = new BloodDonation(donationID, donationCamp, donationDate, bloodUnit);
        boolean u = bloodDonationDao.updateDonation(bloodDonation);
        HttpSession session = request.getSession();

        if (u) {
            session.setAttribute("successMsg", "Donation details updated successfully");
            response.sendRedirect("view_donation?id=" + donationID);
        } else {
            session.setAttribute("errorMsg", "Failed to update donation details.");
            response.sendRedirect("edit_donation?id=" + donationID);
        }

    }

    private void viewRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<BloodRequest> allBloodRequests = bloodRequestDao.getAllRequests();
        request.setAttribute("allBloodRequests", allBloodRequests);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_requests.jsp");
        dispatcher.forward(request, response);
    }

    private void viewRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String requestID = request.getParameter("id");

        BloodRequest bloodRequest = bloodRequestDao.getRequestByID(requestID);
        request.setAttribute("bloodRequest", bloodRequest);

        String donorID = bloodRequest.getDonorID();
        User user = userDao.getDonorByID(donorID);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_request.jsp");
        dispatcher.forward(request, response);
    }

    private void editRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String requestID = request.getParameter("id");

        BloodRequest bloodRequest = bloodRequestDao.getRequestByID(requestID);
        request.setAttribute("bloodRequest", bloodRequest);

        List<BloodGroup> bloodGroups = bloodGroupDao.getAllBloodGroups();
        request.setAttribute("bloodGroups", bloodGroups);

        String donorID = bloodRequest.getDonorID();
        User user = userDao.getDonorByID(donorID);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/edit_request.jsp");
        dispatcher.forward(request, response);
    }

    private void viewNewRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String status = "Pending";

        List<BloodRequest> allNewBloodRequests = bloodRequestDao.getAllNewRequests(status);
        request.setAttribute("allNewBloodRequests", allNewBloodRequests);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_new_requests.jsp");
        dispatcher.forward(request, response);
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String bloodGroup = request.getParameter("bloodGroup");
        LocalDate requestDate = LocalDate.parse(request.getParameter("requestDate"), df);
        String status = request.getParameter("status");
        String requestResponse = request.getParameter("requestResponse");
        String requestID = request.getParameter("requestID");

        BloodRequest bloodRequest = new BloodRequest(requestID, bloodGroup, requestDate, status, requestResponse);
        System.out.println(bloodRequest.getRequestID());
        boolean u = bloodRequestDao.updateRequest(bloodRequest);
        HttpSession session = request.getSession();

        if (u) {
            session.setAttribute("successMsg", "Request details updated successfully.");
            response.sendRedirect("view_request?id=" + requestID);
        } else {
            session.setAttribute("errorMsg", "Failed to update request details.");
            response.sendRedirect("edit_request?id=" + requestID);
        }

    }

    private void viewDonationCamps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<DonationCamp> donationCamps = donationCampDao.getAllCamps();
        request.setAttribute("donationCamps",donationCamps);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_camps.jsp");
        dispatcher.forward(request, response);
    }

    private void newDonationCamp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/new_camp.jsp");
        dispatcher.forward(request, response);
    }

    private void addNewDonationCamp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String campName = request.getParameter("donationCampName");
        String organizers = request.getParameter("organizers");
        String address = request.getParameter("address");
        String postalAddress = request.getParameter("postalAddress");
        String details = request.getParameter("details");

        HttpSession session = request.getSession();

        DonationCamp newDonationCamp = new DonationCamp();
        newDonationCamp.setCampName(campName);
        newDonationCamp.setOrganizers(organizers);
        newDonationCamp.setAddress(address);
        newDonationCamp.setPostal_address(postalAddress);
        newDonationCamp.setDetails(details);

        try {
            boolean u = donationCampDao.newCamp(newDonationCamp);

            if (u) {
                session.setAttribute("successMsg", "New donation camp added successfully");
                response.sendRedirect("view_camps");
            } else {
                session.setAttribute("errorMsg", "Failed to add new donation camp. Try again.");
                response.sendRedirect("new_camp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewDonationCamp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationCampID = request.getParameter("id");

        DonationCamp donationCamp = donationCampDao.getDonationCampByID(donationCampID);
        request.setAttribute("donationCamp", donationCamp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/view_camp.jsp");
        dispatcher.forward(request, response);
    }

    private void editDonationCamp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationCampID = request.getParameter("id");

        DonationCamp donationCamp = donationCampDao.getDonationCampByID(donationCampID);
        request.setAttribute("donationCamp", donationCamp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/edit_camp.jsp");
        dispatcher.forward(request, response);
    }

    private void updateDonationCamp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String donationCampID = request.getParameter("donationCampID");
        String donationCampName = request.getParameter("donationCampName");
        String organizers = request.getParameter("organizers");
        String address = request.getParameter("address");
        String postalAddress = request.getParameter("postalAddress");
        String details = request.getParameter("details");

        DonationCamp updatedDonationCamp = new DonationCamp(donationCampID, donationCampName, organizers, address, postalAddress, details);
        boolean u = donationCampDao.updateDonationCamp(updatedDonationCamp);


        HttpSession session = request.getSession();

        if (u) {
            session.setAttribute("successMsg", "Donation camp details updated successfully.");
            response.sendRedirect("view_camp?id=" + donationCampID);
        } else {
            session.setAttribute("errorMsg", "Failed to update donation camp details. Try Again!");
            response.sendRedirect("edit_camp?id=" + donationCampID);
        }
    }

}
