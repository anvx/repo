package com.itechart.lab2020.uploadapp.command;

import com.itechart.lab2020.uploadapp.command.commandimpl.OpenInBrowserCommand;
import com.itechart.lab2020.uploadapp.command.commandimpl.SaveCommand;
import com.itechart.lab2020.uploadapp.command.commandimpl.UploadCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CommandInvoker {
    private CommandManager manager;
    private HashMap<String, Command> commandMap = new HashMap<>();

    public CommandInvoker(CommandManager manager) {
        this.manager = manager;
        commandMap.put("upload", new UploadCommand(manager));
        commandMap.put("save", new SaveCommand(manager));
        commandMap.put("open", new OpenInBrowserCommand(manager));
    }

    public void invoke(String command, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Command executedCommand = commandMap.get(command);
        executedCommand.execute(req, resp);
        manager.recordHistory(executedCommand);
    }
}
