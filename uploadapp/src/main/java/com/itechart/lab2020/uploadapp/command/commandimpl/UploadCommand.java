package com.itechart.lab2020.uploadapp.command.commandimpl;

import com.itechart.lab2020.uploadapp.command.Command;
import com.itechart.lab2020.uploadapp.command.CommandManager;
import com.itechart.lab2020.uploadapp.validator.ValidationFactory;
import com.itechart.lab2020.uploadapp.validator.ValidatorType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class UploadCommand extends Command {

    private static final String SAVE_DIRECTORY = "uploadDirectory";
    private static final String ERROR = "errorMessage";

    public UploadCommand(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String fullSavePath = getRealPathForSaveFile(req);

            createDirectoryIfNotExist(fullSavePath);

            writeFileToFileSystem(req, fullSavePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFileToFileSystem(HttpServletRequest req, String fullSavePath)
            throws IOException, ServletException {
        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);

            if (fileName != null && fileName.length() > 0) {
                req.setAttribute("fileName", fileName);

                if (isAppropriateFileExtension(req) && isFileUnique(req)) {

                    String filePath = fullSavePath + File.separator + fileName;
                    part.write(filePath);

                }
            } else {

                if (req.getAttribute("fileName") == null) {
                    req.setAttribute(ERROR, "Please, select a File.");
                }

            }
        }
    }

    private boolean isFileUnique(HttpServletRequest req) {
        if (ValidationFactory.
                createValidator(ValidatorType.FILE_UNIQUE).
                validate(req)) {
            return true;
        } else {
            req.setAttribute(ERROR, "File cannot be uploaded. File exist.");
            return false;
        }
    }

    private boolean isAppropriateFileExtension(HttpServletRequest req) {
        if (ValidationFactory.
                createValidator(ValidatorType.FILE_EXTENSION).
                validate(req)) {
            return true;
        } else {
            req.setAttribute(ERROR, "File cannot be uploaded. Please, check extension.");
            return false;
        }
    }

    private void createDirectoryIfNotExist(String fullSavePath) {
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
    }

    private String getRealPathForSaveFile(HttpServletRequest req) {
        String appPath = req.getServletContext().getRealPath("");
        appPath = appPath.replace("\\", File.separator);

        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + File.separator + SAVE_DIRECTORY;
        }
        return fullSavePath;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf('=') + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", File.separator);
                int i = clientFileName.lastIndexOf(File.separator);
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}
