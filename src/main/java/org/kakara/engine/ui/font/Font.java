package org.kakara.engine.ui.font;

import org.kakara.engine.GameEngine;
import org.kakara.engine.resources.Resource;
import org.kakara.engine.scene.Scene;
import org.kakara.engine.ui.UserInterface;

import java.nio.ByteBuffer;

import static org.lwjgl.nanovg.NanoVG.nvgCreateFontMem;

/**
 * Handles the font for the UI.
 */
public class Font {

    private int font;
    private String name;
    private Resource fileName;

    private ByteBuffer thisNeedsToBeHereSoTheGarbageCollectorDoesNotComeAndGetMeTMDotCom;

    /**
     * Create a new font
     * @param name the name of the font.
     *             <p>It doesn't matter what it is, as long as you don't use the same name twice</p>
     * @param fileName The font resource.
     */
    public Font(String name, Resource fileName, Scene currentScene){
        this.name = name;
        this.fileName = fileName;
        currentScene.getHUD().addFont(this);
    }

    /**
     * Internal Use Only
     */
    public void init(UserInterface userInterface){
        try{
            ByteBuffer bb = fileName.getByteBuffer();
            font = nvgCreateFontMem(userInterface.getVG(), name, bb, 1);
            this.thisNeedsToBeHereSoTheGarbageCollectorDoesNotComeAndGetMeTMDotCom = bb;
        }catch(Exception ex){;
            GameEngine.LOGGER.error("Error: Could not load font: " + name);
        }
    }

    /**
     * Get the id of the font for use with NVG static functions.
     * @return The id of the font.
     */
    public int getFont(){
        return font;
    }

    /**
     * Get the name of the font.
     * @since 1.0-Pre3
     * @return The name of the font.
     */
    public String getName(){
        return name;
    }

    /**
     * Internal Use Only
     */
    public ByteBuffer getByteBuffer(){
        return thisNeedsToBeHereSoTheGarbageCollectorDoesNotComeAndGetMeTMDotCom;
    }
}