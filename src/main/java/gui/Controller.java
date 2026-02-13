package gui;

import chatbot.Oliver;

import java.util.stream.Stream;

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
    private ImageView headImage;
    private Image img = new Image(this.getClass().getResourceAsStream("/images/Face.png"));
    
    private Oliver oliver;
    private String WELCOME = 
            """
            Oliver, King Of The Night, at your service!
            I know "todo", "deadline", "event", "list", "mark",
            "unmark", "delete", "search", "undo", "redo" and "bye"!
            What shall we do next?""";

    @FXML
    public void initialize() {
        this.oliver = new Oliver();
        //event handlers
        userInput.addEventHandler(KeyEvent.KEY_RELEASED, new keyPress());
        sendButton.setOnAction(new buttonPress());
        //initialise contents
        headImage.setImage(img);
        dialogContainer.getChildren().add(
                DialogBox.getOliverBox(WELCOME));

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
        Stream.of(input.split("\n"))
            .filter((str)-> !str.isBlank())
            .forEach((str)->oliver.takeInput(str));

        String printableInput = String.join("\n",
                input.split("\n"));
        String response = oliver.getResponse();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserBox(printableInput),
                DialogBox.getOliverBox(response)
        );
        userInput.clear();
    }
}
