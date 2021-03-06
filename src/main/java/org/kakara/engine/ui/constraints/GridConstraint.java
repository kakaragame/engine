package org.kakara.engine.ui.constraints;

import org.kakara.engine.GameHandler;
import org.kakara.engine.math.Vector2;
import org.kakara.engine.ui.components.UIComponent;
import org.kakara.engine.window.Window;

/**
 * Positions the component based upon a grid. (The grid is relative to the scale of the parent component).
 * <code>
 * component.addConstraint(new GridConstraint(5, 5, 2, 2));
 * </code>
 * The example above will center the component in a 5 x 5 grid.
 *
 * @since 1.0-Pre1
 */
public class GridConstraint implements Constraint {

    private final int rows;
    private final int columns;
    private final int xpos;
    private final int ypos;
    private Window window;
    private UIComponent component;

    /**
     * Create a new grid constraint
     *
     * @param columns The number of columns.
     * @param rows    The number of rows.
     * @param xpos    The x position.
     *                <p>Ranges from 0 (inclusive) to the columns value (exclusive)</p>
     * @param ypos    The y position.
     *                <p>Ranges from 0 (inclusive) to the rows value (exclusive)</p>
     */
    public GridConstraint(int columns, int rows, int xpos, int ypos) {
        this.rows = rows - 1;
        this.columns = columns - 1;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public void onAdd(UIComponent component) {
        window = GameHandler.getInstance().getGameEngine().getWindow();
        this.component = component;
    }

    @Override
    public void onRemove(UIComponent component) {
    }

    @Override
    public void update(UIComponent component) {
        Vector2 scale = component.getParent() != null ? component.getParent().getScale()
                : getWindowSize();
        component.setPosition((scale.x / columns) * xpos - component.getScale().x / 2, (scale.y / rows) * ypos - component.getScale().y / 2);
    }

    private Vector2 getWindowSize() {
        return component.getParentCanvas().isAutoScale() ? new Vector2(window.initialWidth, window.initialHeight)
                : new Vector2(window.getWidth(), window.getHeight());
    }
}
