package org.kakara.engine.ui.properties;

import org.kakara.engine.GameHandler;
import org.kakara.engine.gui.Window;
import org.kakara.engine.math.Vector2;
import org.kakara.engine.ui.components.Component;

/**
 * Positions the component based upon a grid. (The grid is relative to the scale of the parent component).
 * <code>
 *     component.addProperty(new GridProperty(5, 5, 2, 2));
 * </code>
 * The example above will center the component in a 5 x 5 grid.
 * @since 1.0-Pre1
 */
public class GridProperty implements ComponentProperty {

    private int rows;
    private int columns;
    private int xpos;
    private int ypos;
    private Window window;

    /**
     * Create a new grid property
     * @param columns The number of columns.
     * @param rows The number of rows.
     * @param xpos The x position.
     *             <p>Ranges from 0 (inclusive) to the columns value (exclusive)</p>
     * @param ypos The y position.
     *             <p>Ranges from 0 (inclusive) to the rows value (exclusive)</p>
     */
    public GridProperty(int columns, int rows, int xpos, int ypos){
        this.rows = rows -1;
        this.columns = columns-1;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    @Override
    public void onAdd(Component component) {
        window = GameHandler.getInstance().getGameEngine().getWindow();
    }

    @Override
    public void onRemove(Component component) {}

    @Override
    public void update(Component component) {
        Vector2 scale = component.getParent() != null ? component.getParent().getScale()
                : new Vector2(window.initalWidth, window.initalHeight);
        component.setPosition((scale.x/columns) * xpos - component.getScale().x/2, (scale.y/rows) * ypos - component.getScale().y/2);
    }
}