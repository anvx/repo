package com.itechart.lab2020.uploadapp;

import com.itechart.lab2020.uploadapp.command.CommandInvoker;
import com.itechart.lab2020.uploadapp.command.CommandManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {

    private static final String SAVE_DIRECTORY = "uploadDirectory";
    private static final String ERROR = "errorMessage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String appPath = req.getServletContext().getRealPath("");
            File file = new File(appPath + File.separator + SAVE_DIRECTORY);
            String[] list = file.list();
            req.setAttribute("filesInUploadDir", list);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/downloadFileFromServer.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute(ERROR, "Something went wrong. Please try again.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/uploadFile.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CommandManager manager = new CommandManager();
        CommandInvoker invoker = new CommandInvoker(manager);

        String buttonName = req.getParameter("button");
        invoker.invoke(buttonName, req, resp);

    }
}
