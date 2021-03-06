package org.kakara.engine.input.key;

import org.kakara.engine.GameEngine;
import org.kakara.engine.events.event.CharacterPressEvent;
import org.kakara.engine.events.event.KeyPressEvent;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Handles Key input.
 */
public class KeyInput {

    private final GameEngine engine;

    public KeyInput(GameEngine engine) {
        this.engine = engine;
    }

    /**
     * When the engine is first started.
     */
    public void init() {
        glfwSetKeyCallback(engine.getWindow().getWindowHandler(), (window, key, scancode, action, mods) -> {
            if (action != GLFW_PRESS) return;
            engine.getGameHandler().getSceneManager().getCurrentScene().getEventManager().fireHandler(new KeyPressEvent(key));
        });
        glfwSetCharCallback(engine.getWindow().getWindowHandler(), (window, codepoint) -> engine.getGameHandler().getSceneManager().getCurrentScene().getEventManager().fireHandler(new CharacterPressEvent(codepoint)));
    }

    /**
     * If a key is currently pressed.
     *
     * @param keycode The keycode value.
     * @return If it is pressed.
     */
    public boolean isKeyPressed(int keycode) {
        return engine.getWindow().isKeyPressed(keycode);
    }

    /**
     * If a key is currently pressed.
     *
     * @param keyCode The keycode value.
     * @return If the key is pressed.
     */
    public boolean isKeyPressed(KeyCode keyCode) {
        return engine.getWindow().isKeyPressed(keyCode.getID());
    }
}
