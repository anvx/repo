package com.itechart.lab2020.uploadapp.command.commandimpl;

import com.itechart.lab2020.uploadapp.command.Command;
import com.itechart.lab2020.uploadapp.command.CommandManager;
import com.itechart.lab2020.uploadapp.command.ReadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenInBrowserCommand extends Command implements ReadFile {

    public OpenInBrowserCommand(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    protected void execute(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");

        readFile(req, resp);
    }
}
