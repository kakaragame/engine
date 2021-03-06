package org.kakara.engine.scene;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kakara.engine.Camera;
import org.kakara.engine.GameEngine;
import org.kakara.engine.GameHandler;
import org.kakara.engine.events.EventManager;
import org.kakara.engine.gameitems.GameItem;
import org.kakara.engine.gameitems.ItemHandler;
import org.kakara.engine.gameitems.SkyBox;
import org.kakara.engine.gameitems.particles.ParticleEmitter;
import org.kakara.engine.gameitems.particles.ParticleHandler;
import org.kakara.engine.input.controller.ControllerManager;
import org.kakara.engine.lighting.LightHandler;
import org.kakara.engine.lighting.PointLight;
import org.kakara.engine.lighting.SpotLight;
import org.kakara.engine.physics.collision.CollisionManager;
import org.kakara.engine.ui.UICanvas;
import org.kakara.engine.ui.UserInterface;
import org.kakara.engine.utils.Time;
import org.kakara.engine.voxels.VoxelChunk;
import org.kakara.engine.weather.Fog;

/**
 * Primary Scene to derive from.
 * <p>This class does not take care of the rendering. If extending this class, then you must do that yourself.
 * See the source code of {@link AbstractGameScene} for assistance.</p>
 */
public abstract class AbstractScene implements Scene {
    protected final UserInterface userInterface = new UserInterface(this);
    private final Camera camera = new Camera();
    private final ItemHandler itemHandler = new ItemHandler();
    private final LightHandler lightHandler = new LightHandler();
    private final ParticleHandler particleHandler = new ParticleHandler();
    private final EventManager eventManager = new EventManager();
    private final CollisionManager collisionManager;
    protected GameHandler gameHandler;
    private SkyBox skyBox;
    private boolean mouseStatus;
    private Fog fog;

    protected AbstractScene(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.eventManager.registerHandler(this);
        this.collisionManager = new CollisionManager(gameHandler);
        fog = Fog.NOFOG;
        try {
            userInterface.init(gameHandler.getWindow());
        } catch (Exception ex) {
            GameEngine.LOGGER.error("Unable to load HUD", ex);
        }
    }

    @Override
    public boolean getCursorStatus() {
        return mouseStatus;
    }

    @Override
    public void setCursorStatus(boolean status) {
        mouseStatus = status;
        gameHandler.getWindow().setCursorVisibility(status);
    }

    @Override
    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public LightHandler getLightHandler() {
        return lightHandler;
    }

    @Override
    public UserInterface getUserInterface() {
        return userInterface;
    }

    @Override
    public ParticleHandler getParticleHandler() {
        return particleHandler;
    }

    @Override
    public Camera getCamera() {
        return camera;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public @NotNull CollisionManager getCollisionManager() {
        return collisionManager;
    }

    @Override
    public ControllerManager getControllerManager() {
        return gameHandler.getControllerManager();
    }

    @Override
    public float getDeltaTime() {
        return Time.getDeltaTime();
    }

    /**
     * Register an object to the scene event manager.
     * <p>Scenes are automatically added. Do not add the scene to this list.</p>
     *
     * @param obj The object to handle.
     * @since 1.0-Pre1
     */
    public void registerEventObject(Object obj) {
        if (obj instanceof Scene) {
            GameEngine.LOGGER.warn("Unable to register event object: Scenes are automatically added to the event list.");
            return;
        }
        this.eventManager.registerHandler(obj);
    }

    /**
     * Add a game item to the scene.
     * <p>This does not work for RenderChunks. See {@link AbstractGameScene#add(VoxelChunk)}</p>
     * <p>This functionality works the same as {@link org.kakara.engine.gameitems.ItemHandler#addItem(GameItem)}</p>
     *
     * @param gameItem The game item to add
     */
    public void add(GameItem gameItem) {
        itemHandler.addItem(gameItem);
    }

    /**
     * Add a point light to the scene
     * <p>This functionality works the same as {@link org.kakara.engine.lighting.LightHandler#addPointLight(PointLight)}</p>
     *
     * @param pointLight The point light to add
     */
    public void add(PointLight pointLight) {
        lightHandler.addPointLight(pointLight);
    }

    /**
     * Add a spot light to the scene
     * <p>This functionality works the same as {@link org.kakara.engine.lighting.LightHandler#addSpotLight(SpotLight)}</p>
     *
     * @param spotLight The spot light to add
     */
    public void add(SpotLight spotLight) {
        lightHandler.addSpotLight(spotLight);
    }

    /**
     * Add a hud item to the scene.
     * <p>This functionality works the same as {@link UserInterface#addItem(UICanvas)}</p>
     *
     * @param UICanvas The hud item to add.
     */
    public void add(UICanvas UICanvas) {
        userInterface.addItem(UICanvas);
    }

    /**
     * Add a particle to the scene.
     * <p>This functionality works the same as {@link org.kakara.engine.gameitems.particles.ParticleHandler#addParticleEmitter(ParticleEmitter)}</p>
     *
     * @param emitter The particle emitter to add.
     */
    public void add(ParticleEmitter emitter) {
        particleHandler.addParticleEmitter(emitter);
    }

    /**
     * Remove an item from the scene
     * <p>This does not work for RenderChunks. See {@link AbstractGameScene#add(VoxelChunk)}</p>
     * <p>This functionality works the same as {@link org.kakara.engine.gameitems.ItemHandler#removeItem(GameItem)}</p>
     *
     * @param item The item to remove.
     * @since 1.0-Pre1
     */
    public void remove(GameItem item) {
        itemHandler.removeItem(item);
    }

    /**
     * Remove a point light from the scene.
     * <p>This functionality works the same as {@link org.kakara.engine.lighting.LightHandler#removePointLight(PointLight)}</p>
     *
     * @param pointLight The point light to remove.
     * @since 1.0-Pre1
     */
    public void remove(PointLight pointLight) {
        lightHandler.removePointLight(pointLight);
    }

    /**
     * Remove a spot light from the scene
     * <p>This functionality works the same as {@link org.kakara.engine.lighting.LightHandler#removeSpotLight(SpotLight)}</p>
     *
     * @param spotLight The spot light to remove.
     * @since 1.0-Pre1
     */
    public void remove(SpotLight spotLight) {
        lightHandler.removeSpotLight(spotLight);
    }

    /**
     * Remove a hud item from the scene
     * <p>This functionality works the same as {@link UserInterface#removeItem(UICanvas)}</p>
     *
     * @param UICanvas The hud item to remove.
     * @since 1.0-Pre1
     */
    public void remove(UICanvas UICanvas) {
        userInterface.removeItem(UICanvas);
    }

    /**
     * Remove a particle emitter from the scene
     * <p>This functionality works the same as {@link org.kakara.engine.gameitems.particles.ParticleHandler#removeParticleEmitter(ParticleEmitter)}</p>
     *
     * @param particleEmitter The particle emitter to remove.
     * @since 1.0-Pre1
     */
    public void remove(ParticleEmitter particleEmitter) {
        particleHandler.removeParticleEmitter(particleEmitter);
    }

    /**
     * Get the current skybox
     *
     * @return The current skybox
     */
    public SkyBox getSkyBox() {
        return skyBox;
    }

    /**
     * Set the current skybox
     *
     * @param skyBox The skybox
     */
    public void setSkyBox(@Nullable SkyBox skyBox) {
        this.skyBox = skyBox;
    }

    /**
     * Get the current fog
     *
     * @return The current fog
     */
    public Fog getFog() {
        return fog;
    }

    /**
     * Set the current fog
     *
     * @param fog The fog (Use Fog.NOFOG to remove fog).
     */
    public void setFog(@NotNull Fog fog) {
        this.fog = fog;
    }

    @Override
    public void handleException(Exception exception) {
        GameEngine.LOGGER.error(String.format("Exception Thrown inside %s", getClass().getName()), exception);
        //End the Game...
        gameHandler.exit();
    }
}
