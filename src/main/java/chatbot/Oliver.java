package chatbot;

import commands.Command;
import commands.LoadFromStorageCommand;
import results.Result;

/**
 * Handles high-level program flow for backend operations.
 * Takes input from and returns responses to gui.Controller.
 */
public class Oliver {
    private History history;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises classes in Oliver.
     */
    public Oliver() {
        this.history = new History();
        this.parser = new Parser();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new Ui();

        Command init = new LoadFromStorageCommand();
        init.bind(history, parser, storage, taskList, ui);
        init.run();
    }

    /**
     * Takes one line of user input and executes it.
     * Result can be obtained from Oliver.getResponse()
     * 
     * @param input
     */
    public void takeInput(String input) {
        Command command = parser.parseUserInput(input);
        command.bind(history, parser, storage, taskList, ui);
        Result result = command.run();
        history.add(result);
    }

    public String getResponse() {
        return ui.getResponse();
    };
}
