package Game_Engine;

public interface TetrominoRotation {
    public void rotateShape(int xCoordinates, int yCoordinates);
    public int[][] transposeMatrix(int[][] matrix);
    public void reverseMatrix(int[][] matrix);
}