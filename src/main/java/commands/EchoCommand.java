package commands;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

public class EchoCommand extends Command {

    private Ui ui;
    private String input;
    
    /**
     * Initialises string to be echoed.
     * 
     * @param output
     */
    public EchoCommand(String input) {
        this.input = input;
    }
    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.ui = u;
    }

    /**
     * Echoes input.
     */
    public Result run() {
        ui.echo(input);
        return new NoResult();
    }
}
