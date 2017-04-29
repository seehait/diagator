package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import main.AppStateManager;

/**
 * Key Handler
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class KeyHandler implements EventHandler<KeyEvent> {
    private AppStateManager appStateManager;

    /**
     * Constructs an instance.
     *
     * @param appStateManager appStateManager
     */
    public KeyHandler(AppStateManager appStateManager) {
        this.appStateManager = appStateManager;
    }

    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                appStateManager.toParent();
                break;
            case DOWN:
                appStateManager.toChild();
                break;
            case LEFT:
                appStateManager.toNextSiblings();
                break;
            case RIGHT:
                appStateManager.toPreviousSiblings();
                break;
            case SPACE:
                appStateManager.playAudio();
                break;
            case TAB:
                appStateManager.toggleRootSiblingsMode();
                break;
            case ENTER:
                appStateManager.playInfo();
                break;
            case ESCAPE:
                appStateManager.exit();
                break;
            default:
                break;
        }
    }
}
