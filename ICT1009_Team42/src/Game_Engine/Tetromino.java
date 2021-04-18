package Game_Engine;

import java.awt.Color;
import java.util.Random;

public class Tetromino {
	// Tetromino shapes 
    private final int[][] TETROMINO_SHAPE_I = {{1, 1, 1, 1}};
    private final int[][] TETROMINO_SHAPE_T = {{1, 1, 1},{0, 1, 0}};
    private final int[][] TETROMINO_SHAPE_L = {{1, 1, 1},{1, 0, 0}};
    private final int[][] TETROMINO_SHAPE_J = {{1, 1, 1},{0, 0, 1}};
    private final int[][] TETROMINO_SHAPE_S = {{0, 1, 1},{1, 1, 0}};
    private final int[][] TETROMINO_SHAPE_Z = {{1, 1, 0},{0, 1, 1}};
    private final int[][] TETROMINO_SHAPE_SQUARE = {{1, 1},{1, 1}};

    // Tetromino colors 
    private final Color TETROMINO_COLOR_I = Color.CYAN;
    private final Color TETROMINO_COLOR_T = Color.PINK;
    private final Color TETROMINO_COLOR_L = Color.ORANGE;
    private final Color TETROMINO_COLOR_J = Color.BLUE;
    private final Color TETROMINO_COLOR_S = Color.GREEN;
    private final Color TETROMINO_COLOR_Z = Color.RED;
    private final Color TETROMINO_COLOR_SQUARE = Color.YELLOW;

    // Tetromino names 
    private final String TETROMINO_NAME_I = "Tetromino_I";
    private final String TETROMINO_NAME_T = "Tetromino_T";
    private final String TETROMINO_NAME_L = "Tetromino_L";
    private final String TETROMINO_NAME_J = "Tetromino_J";
    private final String TETROMINO_NAME_S = "Tetromino_S";
    private final String TETROMINO_NAME_Z = "Tetromino_Z";
    private final String TETROMINO_NAME_SQUARE = "Tetromino_Square";

    private TetrominoShape[] tetroArr = new TetrominoShape[7];

    private final Random random = new Random();

    public Tetromino() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Default constructor. 
		 * 
		 * Returns : None.
		 */	      	
        TetrominoShape tetromino_I = new TetrominoShape(TETROMINO_SHAPE_I, TETROMINO_COLOR_I, TETROMINO_NAME_I); // I, Cyan
        TetrominoShape tetromino_T = new TetrominoShape(TETROMINO_SHAPE_T, TETROMINO_COLOR_T, TETROMINO_NAME_T); // T, Pink
        TetrominoShape tetromino_L = new TetrominoShape(TETROMINO_SHAPE_L, TETROMINO_COLOR_L, TETROMINO_NAME_L); // L, Orange
        TetrominoShape tetromino_J = new TetrominoShape(TETROMINO_SHAPE_J, TETROMINO_COLOR_J, TETROMINO_NAME_J); // J, Blue
        TetrominoShape tetromino_S = new TetrominoShape(TETROMINO_SHAPE_S, TETROMINO_COLOR_S, TETROMINO_NAME_S); // S, Green
        TetrominoShape tetromino_Z = new TetrominoShape(TETROMINO_SHAPE_Z, TETROMINO_COLOR_Z, TETROMINO_NAME_Z); // Z, Red
        TetrominoShape tetromino_SQUARE = new TetrominoShape(TETROMINO_SHAPE_SQUARE, TETROMINO_COLOR_SQUARE, TETROMINO_NAME_SQUARE); // SQUARE, Yellow

        tetroArr[0] = tetromino_I;
        tetroArr[1] = tetromino_T;
        tetroArr[2] = tetromino_L;
        tetroArr[3] = tetromino_J;
        tetroArr[4] = tetromino_S;
        tetroArr[5] = tetromino_Z;
        tetroArr[6] = tetromino_SQUARE;
    }

    public TetrominoShape getRandomTetromino () {
		/*
		 * Params : None. 
		 * 
		 * Desc : Returns a random Tetromino shape and color. 
		 * 
		 * Returns : TetrominoShape randomTetro
		 */	    	
        int index = random.nextInt(tetroArr.length);
        TetrominoShape randomTetro = tetroArr[index];
        return randomTetro;
    }
}