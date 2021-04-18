package Game_Engine;

import java.awt.Color;

abstract class Shape {
    protected int[][] shape_layout;
    protected Color shape_color;
    protected String shape_name;

    Shape (int[][] shape_layout, Color shape_color, String shape_name) {
        this.shape_layout = shape_layout;
        this.shape_color = shape_color;
        this.shape_name = shape_name;
    }

    abstract int[][] getShape();

    abstract Color getColor();

    abstract String getName();
}