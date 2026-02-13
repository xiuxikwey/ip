package commands;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;

import javafx.application.Platform;

import results.NoResult;
import results.Result;

public class ExitCommand extends Command {

    /**
     * Not applicable for this command
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        return;
    }

    /**
     * Closes program immediately.
     */
    public Result run() {
        Platform.exit();
        return new NoResult();
    }
}
