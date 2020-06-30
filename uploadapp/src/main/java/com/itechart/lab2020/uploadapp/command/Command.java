package com.itechart.lab2020.uploadapp.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    protected CommandManager commandManager;

    protected abstract void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

}
