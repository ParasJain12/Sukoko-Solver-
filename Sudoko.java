public class Sudoko {

    public static boolean sudokoSolver(int sudoko[][], int row, int col) {
        //base case
        if (row == 9) {
            return true;
        }
        //recursion
        int nextRow = row, nextCol = col + 1;
        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }
        if (sudoko[row][col] != 0) {
            return sudokoSolver(sudoko, nextRow, nextCol);
        }
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoko, row, col, digit)) {
                sudoko[row][col] = digit;
                if (sudokoSolver(sudoko, nextRow, nextCol)) {   //solution exists
                    return true;
                }
                sudoko[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int sudoko[][], int row, int col, int digit) {
        //column
        for (int i = 0; i <= 8; i++) {
            if (sudoko[i][col] == digit) {
                return false;
            }
        }
        //row
        for (int j = 0; j <= 8; j++) {
            if (sudoko[row][j] == digit) {
                return false;
            }
        }
        //grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoko[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void printBoard(int sudoko[][]){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(sudoko[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int sudoko[][] = {{0,0,7,5,2,0,0,6,0},
                          {0,0,2,0,0,9,0,0,8},
                          {0,0,6,4,0,7,0,0,0},
                          {7,6,8,0,0,5,0,0,9},
                          {0,3,1,0,0,0,4,5,0},
                          {4,0,0,3,0,0,7,8,1},
                          {0,0,0,8,0,4,3,0,0},
                          {1,0,0,2,0,0,8,0,0},
                          {0,5,0,0,1,3,6,0,0}};
        if (sudokoSolver(sudoko, 0, 0)) {
            System.out.println("Solution exists");
            printBoard(sudoko);
        } else {
            System.out.println("Solution not exists");
        }
    }
}
