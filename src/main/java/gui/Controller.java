package gui;

import chatbot.Parser;
import chatbot.TaskList;
import chatbot.Ui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;


/**
 * Controller for the main GUI.
 */
public class Controller extends SplitPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextFlow dialogContainer;
    @FXML
    private TextArea userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView head;
    private Image img = new Image(this.getClass().getResourceAsStream("/images/face.png"));
    
    @FXML
    public void initialize() {
        TaskList.recover();
        //autoscroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //event handlers
        userInput.addEventHandler(KeyEvent.KEY_RELEASED, new keyPress());
        sendButton.setOnAction(new buttonPress());
        //initialise contents
        head.setImage(img);
        dialogContainer.getChildren().add(
                DialogBox.getOliverBox(
                        """
                        Oliver, King Of The Night, at your service!
                        I know "todo", "deadline", "event", "list", "mark", "unmark", "delete", "search" and "bye"!
                        What shall we do next?"""));

    }

    private class buttonPress implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            handleUserInput();
        }
    }

    private class keyPress implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                handleUserInput();
            }
        }
    }

    /**
     * Adds user input and response to dialog box.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String[] parts = input.split("\n");
        boolean shouldExit = false;

        ArrayList<String> inputs = new ArrayList<>();
        for (String s: parts) {
            if (!s.isEmpty()) {
                if (!Parser.parseUserInput(s)) {
                    shouldExit = true;
                }
                inputs.add(s);
            }
        }

        input = String.join("\n", inputs);
        String response = String.join("\n", Ui.getResponses());        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserBox(input),
                DialogBox.getOliverBox(response)
        );
        userInput.clear();

        if (shouldExit) {
            javafx.application.Platform.exit();
        }
    }
}
