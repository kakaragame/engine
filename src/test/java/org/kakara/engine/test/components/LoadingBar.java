package org.kakara.engine.test.components;

import org.kakara.engine.GameHandler;
import org.kakara.engine.math.Vector2;
import org.kakara.engine.ui.UserInterface;
import org.kakara.engine.ui.components.GeneralComponent;
import org.kakara.engine.ui.components.shapes.Rectangle;
import org.kakara.engine.ui.components.text.Text;
import org.kakara.engine.ui.font.Font;
import org.kakara.engine.utils.RGBA;

/**
 * This is an example component. All components must extend GeneralComponent.
 */
public class LoadingBar extends GeneralComponent {
    private float percent;

    private final Text percentText;
    private final Rectangle outer;
    private final Rectangle inner;

    /*
     * Be sure to put super(); here.
     * If this component is made from other components, then here is where you define those
     * components and add them to this component. (this.add())
     */
    public LoadingBar(Vector2 position, Vector2 scale, Font font) {
        this.position = position;
        this.scale = scale;

        RGBA foregroundColor = new RGBA(0, 150, 150, 1);
        RGBA backgroundColor = new RGBA(255, 10, 10, 1);
        percentText = new Text("0.00%", font);
        percentText.setPosition(scale.x / 2, scale.y / 2 + 10);
        outer = new Rectangle(new Vector2(0, 0), new Vector2(scale.x, scale.y), backgroundColor);
        inner = new Rectangle(new Vector2(0, 0), new Vector2(0, scale.y - 5), foregroundColor);

        this.add(outer);
        this.add(inner);
        this.add(percentText);
    }

    public float getPercent() {
        return percent;
    }

    /*
        Simple getters and setters
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void setForegroundColor(RGBA color) {
        inner.setColor(color);
    }

    public void setBackgroundColor(RGBA color) {
        outer.setColor(color);
    }

    public void setTextColor(RGBA color) {
        percentText.setColor(color);
    }

    /*
        This is the first override function. This one will be auto-generated
        by intellij.

        This is where anything like nvgImage would be generated. This is called after the Scene is active, and the
        hud is operational. If you need to use nvg to generate content then you will need this.

        If you want the engine to handle the heavy lifting for you then call pollInit();
        To see what pollInit really does then check the source code.
     */
    @Override
    public void init(UserInterface userInterface, GameHandler handler) {
        pollInit(userInterface, handler);
    }

    /*
        This one will not be auto-generated by intellij. This is
        not required if you are using pre-made components as the engine will
        take of it for you. You will almost always have this method though.

        This is called every time the computer goes to render the UI.
        Like the init make sure you has the pollRender(); If you are using nvg in the render method than make sure
        to put the pollRender method at the end of the render that way child components are rendered ontop of
        the component you are making. The pollRender() takes care of the auto scaling and position for you. pollRender()
        also renders the child components for you. If you want to do these activates yourself then just don't call the
        pollRender method.

        Vector2 relative - This is the relative position. You component could be inside of another component. For example,
        if your component was inside of a panel it could have the position being 0, 0; however, the panel is at 120, 30.
        The relative will be the actual position of the component (NOTE: This does not take in to account scale).

        To get the auto scaled position and scale then use the getTruePosition()
        and getTrueScale() methods respectively. Note: These methods return the absolute position, not relative
        position. If you were using nvg to draw then you would use those two methods for x, y and w, h.
     */
    @Override
    public void render(Vector2 relative, UserInterface userInterface, GameHandler handler) {
        pollRender(relative, userInterface, handler);
        if (percent <= 1)
            inner.setScale(outer.scale.x * percent, scale.y);
        percentText.setText(Math.round(percent * 100) + "%");

        /*
            Now time to trigger a custom event. See the LoadingBarCompleteEvent interface to see how to create
            a custom event. (MAJOR NOTE: Never, ever, create an event that is called more than once per three seconds.)

            The GeneralComponent comes with a handy method to trigger an event called triggerEvent.
         */
        if (percent >= 1) {
            triggerEvent(LoadingBarCompleteEvent.class, percent);
            // The following prevent this event from calling every frame after 1. Make sure that an event can never
            // be triggered every frame!
            percent = 0;
        }
    }
}
