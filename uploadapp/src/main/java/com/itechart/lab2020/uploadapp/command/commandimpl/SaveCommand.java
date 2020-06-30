package com.itechart.lab2020.uploadapp.command.commandimpl;

import com.itechart.lab2020.uploadapp.command.Command;
import com.itechart.lab2020.uploadapp.command.CommandManager;
import com.itechart.lab2020.uploadapp.command.ReadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveCommand extends Command implements ReadFile {

    public SaveCommand(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");

        String fileNameToDownload = req.getParameter("fileNameToDownload");

        resp.setHeader("Content-Disposition",
                "attachment;filename=" + fileNameToDownload);

        readFile(req, resp);
    }
}
