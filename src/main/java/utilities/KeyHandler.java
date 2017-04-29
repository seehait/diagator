package utilities;

import diagram.Diagram;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Key Handler
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class KeyHandler implements EventHandler<KeyEvent> {
    private Diagram diagram;

    /**
     * Constructs an instance.
     *
     * @param diagram diagram
     */
    public KeyHandler(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Handles key press event.
     *
     * @param event event
     */
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                diagram.toParent();
                break;
            case DOWN:
                diagram.toChild();
                break;
            case LEFT:
                diagram.toNextSiblings();
                break;
            case RIGHT:
                diagram.toPreviousSiblings();
                break;
            case SPACE:
                diagram.playLabel();
                break;
            case TAB:
                diagram.toggleRootSiblingsMode();
                break;
            case ENTER:
                diagram.playInfo();
                break;
            case ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
