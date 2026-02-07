package gui;

import javafx.scene.text.Text;

public class DialogBox {

    /**
     * Returns Text to add to TextFlow.
     * 
     * @param contents Text to be displayed.
     * @return
     */
    public static Text getUserBox(String contents) {
        Text result = new Text(contents + "\n");
        result.getStyleClass().add("user");
        return result;
    }

    /**
     * Returns Text to add to Text Flow.
     * 
     * @param contents Text to be displayed.
     * @return
     */
    public static Text getOliverBox(String contents) {
        Text result = new Text(contents + "\n");
        result.getStyleClass().add("oliver");
        return result;
    }
}
