package org.kakara.engine.ui.components;

import org.kakara.engine.GameHandler;
import org.kakara.engine.events.EventHandler;
import org.kakara.engine.events.event.MouseClickEvent;
import org.kakara.engine.events.event.MouseReleaseEvent;
import org.kakara.engine.math.Vector2;
import org.kakara.engine.ui.UserInterface;
import org.kakara.engine.ui.events.UIClickEvent;
import org.kakara.engine.ui.events.UIHoverEnterEvent;
import org.kakara.engine.ui.events.UIHoverLeaveEvent;
import org.kakara.engine.ui.events.UIReleaseEvent;

/**
 * An empty UI Component to contain other components.
 */
public class Panel extends GeneralComponent {

    private boolean isHovering;

    public Panel() {
        isHovering = false;
    }

    @Override
    public void init(UserInterface userInterface, GameHandler handler) {
        pollInit(userInterface, handler);
        userInterface.getScene().getEventManager().registerHandler(this);
    }

    @Override
    public void render(Vector2 relative, UserInterface userInterface, GameHandler handler) {
        if (!isVisible()) return;

        pollRender(relative, userInterface, handler);

        boolean isColliding = UserInterface.isColliding(getTruePosition(), getTrueScale(), new Vector2(handler.getMouseInput().getPosition()));
        if (isColliding && !isHovering) {
            isHovering = true;
            triggerEvent(UIHoverEnterEvent.class, handler.getMouseInput().getCurrentPosition());
        } else if (!isColliding && isHovering) {
            isHovering = false;
            triggerEvent(UIHoverLeaveEvent.class, handler.getMouseInput().getCurrentPosition());
        }
    }

    @EventHandler
    public void onClick(MouseClickEvent evt) {
        if (UserInterface.isColliding(position, scale, new Vector2(evt.getMousePosition()))) {
            triggerEvent(UIClickEvent.class, position, evt.getMouseClickType());
        }
    }

    @EventHandler
    public void onRelease(MouseReleaseEvent evt){
        if(UserInterface.isColliding(getTruePosition(), scale, new Vector2(evt.getMousePosition()))){
            triggerEvent(UIReleaseEvent.class, position, evt.getMouseClickType());
        }
    }
}
