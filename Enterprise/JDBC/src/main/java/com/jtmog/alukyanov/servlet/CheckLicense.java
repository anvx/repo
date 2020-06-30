package com.jtmog.alukyanov.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckLicense extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CheckLicense.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("views/checklicense.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
