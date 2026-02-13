package chatbot;

import java.util.ArrayDeque;

import results.NoResult;
import results.Result;

/**
 * Implements undo and redo.
 */
public class History {
    ArrayDeque<Result> undoList;
    ArrayDeque<Result> redoList;
    
    /**
     * Initialises deques
     */
    public History() {
        this.undoList = new ArrayDeque<>();
        this.redoList = new ArrayDeque<>();
    }

    /**
     * Updates undo list
     * 
     * @param result
     */
    public void add(Result result) {
        if (result instanceof NoResult) {
            return;
        }
        redoList.clear();
        undoList.addLast(result);
        if (undoList.size() > 50) {
            undoList.removeFirst();
        }
    }

    /**
     * Executes undo
     */
    public void undo() {
        if (undoList.isEmpty()) {
            return;
        }
        Result result = undoList.removeLast();
        redoList.addLast(result.reverse());
    }

        /**
     * Executes undo
     */
    public void redo() {
        if (redoList.isEmpty()) {
            return;
        }
        Result result = redoList.removeLast();
        undoList.addLast(result.reverse());
    }
}
