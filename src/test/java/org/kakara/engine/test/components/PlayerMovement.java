package org.kakara.engine.test.components;

import org.kakara.engine.GameHandler;
import org.kakara.engine.components.Component;
import org.kakara.engine.input.Input;
import org.kakara.engine.input.controller.ids.ControllerID;
import org.kakara.engine.input.controller.ids.GamePadButton;
import org.kakara.engine.input.key.KeyCode;
import org.kakara.engine.input.key.KeyInput;
import org.kakara.engine.physics.collision.PhysicsComponent;

/**
 * This component handles player movement.
 *
 * <p>Here is an example of the use of a component.</p>
 */
public class PlayerMovement extends Component {
    private PhysicsComponent physComp;
    private KeyInput ki;

    @Override
    public void start() {
        physComp = getComponent(PhysicsComponent.class);
        ki = GameHandler.getInstance().getKeyInput();
    }

    @Override
    public void update() {
        physComp.setVelocityX(0);
        physComp.setVelocityZ(0);
        if (ki.isKeyPressed(KeyCode.RIGHT_ARROW) || Input.isGamePadButtonDown(ControllerID.CONTROLLER_ONE, GamePadButton.DPAD_RIGHT)) {
            physComp.setVelocityX(3f);
        }
        if (ki.isKeyPressed(KeyCode.LEFT_ARROW) || Input.isGamePadButtonDown(ControllerID.CONTROLLER_ONE, GamePadButton.DPAD_LEFT)) {
            physComp.setVelocityX(-3f);
        }
        if (ki.isKeyPressed(KeyCode.UP_ARROW) || Input.isGamePadButtonDown(ControllerID.CONTROLLER_ONE, GamePadButton.DPAD_UP)) {
            physComp.setVelocityZ(-3f);
        }
        if (ki.isKeyPressed(KeyCode.DOWN_ARROW) || Input.isGamePadButtonDown(ControllerID.CONTROLLER_ONE, GamePadButton.DPAD_DOWN)) {
            physComp.setVelocityZ(3f);
        }
        if (ki.isKeyPressed(KeyCode.N)) {
            getGameItem().transform.translateBy(0, 0.1f, 0);
        }
        if (ki.isKeyPressed(KeyCode.M)) {
            getGameItem().transform.translateBy(0, -0.1f, 0);
        }
    }
}
