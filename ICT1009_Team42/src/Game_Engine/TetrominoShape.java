package Game_Engine;

import java.awt.Color;

public class TetrominoShape extends Shape {

    TetrominoShape(int[][] shape_layout, Color shape_color, String shape_name) {
		/*
		 * Params : int[][] shape_layout, Color shape_color, String shape_name. 
		 * 
		 * Desc : Default constructor. 
		 * 
		 * Returns : None.
		 */	  	
        super(shape_layout, shape_color, shape_name);
    }

    @Override
    int[][] getShape() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the shape of the tetromino. 
		 * 
		 * Returns : int[][] shape_layout.
		 */	      	
        return shape_layout;
    }

    @Override
    Color getColor() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the color of the tetromino. 
		 * 
		 * Returns : Color shape_color.
		 */	        	
        return shape_color;
    }

    @Override
    String getName() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the shape name of the tetromino. 
		 * 
		 * Returns : String shape_name.
		 */	         	
        return shape_name;
    }
}