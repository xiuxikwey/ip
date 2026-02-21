package results;

import commands.Command;

/**
 * Represents result of a successful update.
 */
public class UpdateResult extends Result {
    private Command command;

    /**
     * Initialises result.
     * 
     * @param command
     */
    public UpdateResult(Command command) {
        this.command = command;
    }

    /**
     * Reverses this result and returns that result.
     */
    public Result reverse() {
        return command.run();
    }
}
