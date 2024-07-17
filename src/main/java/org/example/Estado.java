package org.example;

import org.example.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estado {
    private  long [][] matrix = new long[4][4];

    public  void iniciar(){
        //setting all the values to 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    public long[][] getTablero(){
        return matrix;
    }
    public void agregarValor(){
        //creating a list of positions with 0 value
        List<Position> zeroPositions = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (matrix[x][y] == 0) {
                    zeroPositions.add(new Position(x,y));

                }
            }
        }
        if(!zeroPositions.isEmpty()){
            Random random = new Random();
            //selecting an aleatory position in array and setting the value of 2 into it
            int pickedArrayPosition =  random.nextInt(zeroPositions.size());
            Position randomPosition = zeroPositions.get(pickedArrayPosition);
            matrix[randomPosition.getX()][randomPosition.getY()] = 2;
            //removing the position to which the value of 2 has been set already
            zeroPositions.remove(pickedArrayPosition);
            if(!zeroPositions.isEmpty()){
                randomPosition = zeroPositions.get(random.nextInt(zeroPositions.size()));
                matrix[randomPosition.getX()][randomPosition.getY()] = 4;
            }
        }
    }
    public void moverIzquierda(){
        for (int i = 0; i < 4; i++) {
            shiftRowLeft(matrix[i]);
            mergeRowLeft(matrix[i]);
            shiftRowLeft(matrix[i]);
        }
    }

    public  void moverDerecha() {
        for (int i = 0; i < 4; i++) {
            shiftRowRight(matrix[i]);
            mergeRowRight(matrix[i]);
            shiftRowRight(matrix[i]);
        }
    }

    public void moverArriba() {
        for (int j = 0; j < 4; j++) {
            //copying the values of a column to a new array
            //to be able to process them with the same methods
            long[] column = new long[4];
            for (int i = 0; i < 4; i++) {
                column[i] = matrix[i][j];
            }
            shiftRowLeft(column);
            mergeRowLeft(column);
            shiftRowLeft(column);
            //copying result values to the original matrix
            for (int i = 0; i < 4; i++) {
                matrix[i][j] = column[i];
            }
        }
    }

    public void moverAbajo() {
        for (int j = 0; j < 4; j++) {
            //copying the values of a column to a new array
            //to be able to process them with the same methods
            long[] column = new long[4];
            for (int i = 0; i < 4; i++) {
                column[i] = matrix[i][j];
            }
            shiftRowRight(column);
            mergeRowRight(column);
            shiftRowRight(column);
            //copying result values to the original matrix
            for (int i = 0; i < 4; i++) {
                matrix[i][j] = column[i];
            }
        }
    }

    //start of util methods for mover(direction) methods
    private static void shiftRowLeft(long[] row) {

        int insertPos = 0;
        for (int i = 0; i < 4; i++) {
            if (row[i] != 0) {
                row[insertPos] = row[i];

                /*if the value was copied to a different position
                    then its necessary to delete value from an old position
                * */
                if (insertPos != i) {
                    row[i] = 0;
                }
                insertPos++;
            }
        }
    }

    private static void shiftRowRight(long[] row) {
        int insertPos = 3;
        for (int i = 3; i >= 0; i--) {
            if (row[i] != 0) {
                row[insertPos] = row[i];

                /*if the value was copied to a different position
                    then its necessary to delete value from an old position
                * */
                if (insertPos != i) {
                    row[i] = 0;
                }
                insertPos--;
            }
        }
    }

    private static void mergeRowLeft(long[] row) {
        for (int i = 0; i < 3; i++) {
            if (row[i] != 0 && row[i] == row[i + 1]) {
                row[i] *= 2;
                row[i + 1] = 0;
                i++; // Skip next tile to avoid double merging
            }
        }
    }

    private static void mergeRowRight(long[] row) {
        for (int i = 3; i > 0; i--) {
            if (row[i] != 0 && row[i] == row[i - 1]) {
                row[i] *= 2;
                row[i - 1] = 0;
                i--; // Skip next tile to avoid double merging
            }
        }
    }
    //the end of util methods for mover(direction) methods
    public int contarCeros() {
        int result = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (matrix[x][y] == 0) {
                    result ++;
                }
            }
        }
        return result;
    }
    public boolean esPosibleSumar(){
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                // Check horizontally
                if (y < 3 && matrix[x][y] != 0 && matrix[x][y] == matrix[x][y + 1]) {
                    return true;
                }
                // Check vertically
                if (x < 3 && matrix[x][y] != 0 && matrix[x][y] == matrix[x + 1][y]) {
                    return true;
                }
            }
        }
        return false;
    }
    public long puntos(){
        long sum = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                sum += matrix[x][y];
            }
        }
        return sum;
    }
}
