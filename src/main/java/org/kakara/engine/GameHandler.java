package org.kakara.engine;

import org.jetbrains.annotations.NotNull;
import org.kakara.engine.gui.Window;
import org.kakara.engine.input.KeyInput;
import org.kakara.engine.input.MouseInput;
import org.kakara.engine.resources.ResourceManager;
import org.kakara.engine.scene.Scene;
import org.kakara.engine.scene.SceneManager;
import org.kakara.engine.sound.SoundManager;

/**
 * Handles the game information.
 */
public class GameHandler {

    private MouseInput mouseInput;
    private KeyInput keyInput;
    private SceneManager sceneManager;
    private SoundManager soundManager;
    private static GameHandler gameHandler;
    private GameEngine gameEngine;
    private ResourceManager resourceManager;

    public GameHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.mouseInput = new MouseInput(this);
        this.keyInput = new KeyInput(gameEngine);
        this.sceneManager = new SceneManager(this);
        soundManager = new SoundManager();
        GameHandler.gameHandler = this;
        resourceManager = new ResourceManager();
    }

    /**
     * Handles the initialization of this class.
     */
    protected void init() {
        mouseInput.init(gameEngine.getWindow());
        keyInput.init();

    }

    /**
     * Updates anything needed
     */
    protected void update() {
        mouseInput.update();
    }

    /**
     * Get the mouse inputs
     *
     * @return The mouse inputs.
     */
    public MouseInput getMouseInput() {
        return mouseInput;
    }

    /**
     * Get the key inputs.
     *
     * @return The key inputs.
     */
    public KeyInput getKeyInput() {
        return keyInput;
    }

    /**
     * Get the current window.
     *
     * @return The current window.
     */
    public Window getWindow() {
        return gameEngine.getWindow();
    }

    /**
     * Get the current instance of the game handler. (Is safe to use as long as the game is open).
     * @return The game handler
     */
    public static GameHandler getInstance() {
        return gameHandler;
    }

    /**
     * Get the scene manager.
     * @return The scene manager.
     */
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    /**
     * Get the game engine class
     * @return The game engine class.
     */
    public GameEngine getGameEngine() {
        return gameEngine;
    }

    /**
     * Get the sound manager.
     * @return The sound manager.
     */
    public SoundManager getSoundManager() {
        return soundManager;
    }

    /**
     * Get the resource manager.
     * @return The resource manager.
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * Get the current scene.
     * @return The current scene.
     */
    public @NotNull Scene getCurrentScene(){
        return getSceneManager().getCurrentScene();
    }

    /**
     * Close out the game safely.
     * <p>THIS IS THE RECOMMENDED WAY TO CLOSE THE GAME!</p>
     */
    public void exit() {
        gameEngine.exit();
    }
}
