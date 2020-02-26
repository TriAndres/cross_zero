import java.util.Random;
import java.util.Scanner;

public class CrossZero {
    static Type[][] cells;

    public enum Type {
        X, O, NONE
    }

    public static void main(String[] args) {
        intPoint();
        outPoint();
        while (true) {
            inUserX();
            outPoint();
            if (checkWin(Type.X)) {
                System.out.println("Победил игрок Х");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ходов нет");
                break;
            }
            // inUserO();
            inComputeO();
            outPoint();
            if (checkWin(Type.O)) {
                System.out.println("Победил игрок O");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ходов нет");
                break;
            }
        }
    }

    private static void intPoint() {
        cells = new Type[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = Type.NONE;
            }
        }
    }

    private static void outPoint() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (cells[j][i]) {
                    case NONE:
                        System.out.print(" . ");
                        break;
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                }
            }
            System.out.println();
        }
    }

    private static void inUserX() {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Enter User X (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        cells[y][x] = Type.X;
    }

    private static void inUserO() {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Enter User O (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        cells[y][x] = Type.O;
    }

    private static void inComputeO() {
        Random random = new Random();
        int x, y;
        do {
            System.out.println("Enter comp O(1..3):");
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        cells[y][x] = Type.O;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3 || y >= 3) {
            return false;
        }
        return cells[y][x] == Type.NONE;
    }

    static boolean checkWin(Type t) {
        for (int i = 0; i < 3; i++) {
            if ((cells[i][0] == t && cells[i][1] == t && cells[i][2] == t) ||
                    (cells[0][i] == t && cells[1][i] == t && cells[2][i] == t)) {
                return true;
            }
            if ((cells[0][0] == t && cells[1][1] == t && cells[2][2] == t) ||
                    (cells[2][0] == t && cells[1][1] == t && cells[0][2] == t)) {
                return true;
            }
        }
        return false;
    }

    static boolean isTableFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == Type.NONE) {
                    return false;
                }
            }
        }
        return true;
    }
}
