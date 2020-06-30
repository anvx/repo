package com.itechart.lab2020.uploadapp.command;

import java.util.LinkedList;
import java.util.List;

public class CommandManager {
    private List<Command> commandHistory = new LinkedList<>();

    public void recordHistory(Command command) {
        commandHistory.add(0, command);
    }

    public static void printHistory(CommandManager manager) {
        System.out.println(manager.commandHistory);
    }
}
