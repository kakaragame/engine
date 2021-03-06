package org.kakara.engine.voxels.layouts;

import org.kakara.engine.math.Vector3;
import org.kakara.engine.voxels.layouts.types.Indices;
import org.kakara.engine.voxels.layouts.types.Normal;
import org.kakara.engine.voxels.layouts.types.Texture;
import org.kakara.engine.voxels.layouts.types.Vertex;

import java.util.Arrays;
import java.util.List;

/**
 * The class that stores the layouts for the blocks in the render chunks
 */
public class BlockLayout implements Layout {

    private static final float ONE_THIRD = 1f / 3f;
    private static final float TWO_THIRD = 2f / 3f;

    @Override
    public Vertex getVertex(final Vector3 pos) {
        return new Vertex() {
            @Override
            public List<Float> getFront() {
                return Arrays.asList(-0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z,
                        -0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z);
            }

            @Override
            public List<Float> getBack() {
                return Arrays.asList(
                        -0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z,
                        -0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z,
                        0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z
                );
            }

            @Override
            public List<Float> getTop() {
                return Arrays.asList(-0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z,
                        -0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z);
            }

            @Override
            public List<Float> getBottom() {
                return Arrays.asList(-0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z,
                        -0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z);
            }

            @Override
            public List<Float> getRight() {
                return Arrays.asList(0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z,
                        0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z);
            }

            @Override
            public List<Float> getLeft() {
                return Arrays.asList(-0.5f + pos.x, 0.5f + pos.y, -0.5f + pos.z,
                        -0.5f + pos.x, -0.5f + pos.y, -0.5f + pos.z,
                        -0.5f + pos.x, -0.5f + pos.y, 0.5f + pos.z,
                        -0.5f + pos.x, 0.5f + pos.y, 0.5f + pos.z);
            }
        };
    }

    @Override
    public Texture getTextureCords() {
        return new Texture() {
            @Override
            public List<Float> getFront(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        0.25f / rows + xOffset, ONE_THIRD / rows + yOffset,
                        0.25f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.5f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.5f / rows + xOffset, ONE_THIRD / rows + yOffset
                );
            }

            @Override
            public List<Float> getBack(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        1f / rows + xOffset, ONE_THIRD / rows + yOffset,
                        1f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.75f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.75f / rows + xOffset, ONE_THIRD / rows + yOffset
                );
            }

            @Override
            public List<Float> getTop(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        0.25f / rows + xOffset, 0 + yOffset,
                        0.25f / rows + xOffset, ONE_THIRD / rows + yOffset,
                        0.5f / rows + xOffset, ONE_THIRD / rows + yOffset,
                        0.5f / rows + xOffset, 0 + yOffset
                );
            }

            @Override
            public List<Float> getBottom(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        0.25f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.25f / rows + xOffset, 1f / rows + yOffset,
                        0.5f / rows + xOffset, 1f / rows + yOffset,
                        0.5f / rows + xOffset, TWO_THIRD / rows + yOffset
                );
            }

            @Override
            public List<Float> getRight(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        0.5f / rows + xOffset, ONE_THIRD / rows + yOffset,
                        0.5f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.75f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.75f / rows + xOffset, ONE_THIRD / rows + yOffset
                );
            }

            @Override
            public List<Float> getLeft(float xOffset, float yOffset, int rows) {
                return Arrays.asList(
                        0 + xOffset, ONE_THIRD / rows + yOffset,
                        0 + xOffset, TWO_THIRD / rows + yOffset,
                        0.25f / rows + xOffset, TWO_THIRD / rows + yOffset,
                        0.25f / rows + xOffset, ONE_THIRD / rows + yOffset
                );
            }
        };
    }

    @Override
    public Normal getNormal() {
        return new Normal() {
            @Override
            public List<Float> getFront() {
                return Arrays.asList(
                        0f, 0f, 1f,
                        0f, 0f, 1f,
                        0f, 0f, 1f,
                        0f, 0f, 1f
                );
            }

            @Override
            public List<Float> getBack() {
                return Arrays.asList(
                        0f, 0f, -1f,
                        0f, 0f, -1f,
                        0f, 0f, -1f,
                        0f, 0f, -1f
                );
            }

            @Override
            public List<Float> getTop() {
                return Arrays.asList(
                        0f, 1f, 0f,
                        0f, 1f, 0f,
                        0f, 1f, 0f,
                        0f, 1f, 0f
                );
            }

            @Override
            public List<Float> getBottom() {
                return Arrays.asList(
                        0f, -1f, 0f,
                        0f, -1f, 0f,
                        0f, -1f, 0f,
                        0f, -1f, 0f
                );
            }

            @Override
            public List<Float> getRight() {
                return Arrays.asList(
                        1f, 0f, 0f,
                        1f, 0f, 0f,
                        1f, 0f, 0f,
                        1f, 0f, 0f
                );
            }

            @Override
            public List<Float> getLeft() {
                return Arrays.asList(
                        -1f, 0f, 0f,
                        -1f, 0f, 0f,
                        -1f, 0f, 0f,
                        -1f, 0f, 0f
                );
            }
        };
    }

    @Override
    public Indices getIndices() {
        return new Indices() {
            @Override
            public List<Integer> getFront(int i) {
                return Arrays.asList(i, i + 1, i + 2, i + 2, i + 3, i);
            }

            @Override
            public List<Integer> getBack(int i) {
                return Arrays.asList(i, i + 3, i + 2, i + 2, i + 1, i);
            }

            @Override
            public List<Integer> getTop(int i) {
                return Arrays.asList(i, i + 1, i + 2, i + 2, i + 3, i);
            }

            @Override
            public List<Integer> getBottom(int i) {
                return Arrays.asList(i, i + 3, i + 2, i + 2, i + 1, i);
            }

            @Override
            public List<Integer> getRight(int i) {
                return Arrays.asList(i, i + 1, i + 2, i + 2, i + 3, i);
            }

            @Override
            public List<Integer> getLeft(int i) {
                return Arrays.asList(i, i + 1, i + 2, i + 2, i + 3, i);
            }
        };
    }
}
