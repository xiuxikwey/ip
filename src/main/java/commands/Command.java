package commands;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.Result;

/**
 * Command performs functions and returns Result when run.
 */
public abstract class Command {
    public abstract Result run();
    public abstract void bind(History h, Parser p, Storage s, TaskList t, Ui u);
}
