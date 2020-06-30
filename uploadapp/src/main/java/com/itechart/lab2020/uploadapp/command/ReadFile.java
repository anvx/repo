package com.itechart.lab2020.uploadapp.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

public interface ReadFile {

    int BYTES_DOWNLOAD = 1024;

    default void readFile(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext ctx = req.getServletContext();
        final String fileNameToDownload = req.getParameter("fileNameToDownload");
        InputStream is = ctx.getResourceAsStream("/uploadDirectory/" + fileNameToDownload);

        int read = 0;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        try (OutputStream os = resp.getOutputStream()) {

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
        }
    }

}
