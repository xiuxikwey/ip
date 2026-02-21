package gui;

import chatbot.Oliver;

import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;


/**
 * Controller for the main GUI.
 */
public class Controller extends SplitPane {
    @FXML
    private TextFlow dialogContainer;
    @FXML
    private TextFlow commandContainer;
    @FXML
    private TextFlow taskContainer;
    @FXML
    private TextArea userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView headImage;

    private Image img = new Image(this.getClass().getResourceAsStream("/images/Face.png"));
    
    private InnerShadow invalidShadow = new InnerShadow(BlurType.valueOf("GAUSSIAN"),
            new Color(1, 0, 0, 0.5),
            10, 0.6, 0, 0);
    private InnerShadow validShadow = null;

    private Oliver oliver;
    private String WELCOME = 
            """
            Oliver, King Of The Night, at your service!
            Feel free to look at the command list!
            What shall we do next?""";
    private String COMMANDS = 
            """
            todo <task name>
            deadline <task name> /by <deadline>
            event <task name> /from <start> /to <end> 
            list
            mark <task number>
            unmark <task number>
            delete <task number>
            search <task name>
            undo
            redo
            bye""";

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
        commandContainer.getChildren().add(
            DialogBox.getOliverBox(COMMANDS));
        taskContainer.getChildren().add(
            DialogBox.getOliverBox(oliver.getTaskList()));
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
            } else {
                String input = userInput.getText();
                boolean valid = Stream.of(input.split("\n"))
                        .filter((str)-> !str.isBlank())
                        .map((str)->oliver.CheckInput(str))
                        .reduce(true, (a, b)-> a && b);
                //AI assistance used to find method setEffect.
                if (valid) {
                    userInput.setEffect(validShadow);
                } else {
                    userInput.setEffect(invalidShadow);
                }
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
                .filter((str) -> !str.isBlank())
                .forEach((str) -> {
                    oliver.takeInput(str);
                    String response = oliver.getResponse();
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserBox(str),
                            DialogBox.getOliverBox(response));
                });

        taskContainer.getChildren().clear();
        taskContainer.getChildren().add(
                DialogBox.getOliverBox(oliver.getTaskList()));

        userInput.clear();
        userInput.setEffect(validShadow);
    }
}
