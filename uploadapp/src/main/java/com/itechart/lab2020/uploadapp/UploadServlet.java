package com.itechart.lab2020.uploadapp;

import com.itechart.lab2020.uploadapp.command.CommandInvoker;
import com.itechart.lab2020.uploadapp.command.CommandManager;
import com.itechart.lab2020.uploadapp.validator.ValidationFactory;
import com.itechart.lab2020.uploadapp.validator.ValidatorType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/upload", ""})
@MultipartConfig(maxFileSize = 1024 * 1024 * 2,
        maxRequestSize = 1024 * 1024 * 2)
public class UploadServlet extends HttpServlet {

    private static final String HOME_PATH_JSP = "/WEB-INF/pages/uploadFile.jsp";
    private static final String ERROR = "errorMessage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher(HOME_PATH_JSP);
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            boolean isFileSizeAppropriate = ValidationFactory.
                    createValidator(ValidatorType.FILE_MAX_SIZE).
                    validate(req);

            if (isFileSizeAppropriate) {
                CommandManager manager = new CommandManager();
                CommandInvoker invoker = new CommandInvoker(manager);

                String buttonName = req.getParameter("button");
                invoker.invoke(buttonName, req, resp);
            }

            if (req.getAttribute(ERROR) == null) {
                resp.sendRedirect(req.getContextPath() + "/uploadFileResult");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher(HOME_PATH_JSP);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute(ERROR, "Something went wrong. Please try again.");
            RequestDispatcher dispatcher = req.getRequestDispatcher(HOME_PATH_JSP);
            dispatcher.forward(req, resp);
        }

    }
}

