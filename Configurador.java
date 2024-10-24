public class Configurador {

    private static int n;
    private final int b;
    private final int c;
    private static char[][] board;  // '.' para vazio, 'B' para Bigode, 'C' para Capeta
    private int count = 0;

    public Configurador(int n, int b, int c) {
        Configurador.n = n;
        this.b = b;
        this.c = c;
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
    }

    public int calcularConfiguracoes() {
        placeBigode(0, b, c);
        return count;
    }

    private void placeBigode(int pos, int remainingBigodes, int remainingCapetas) {
        if (remainingBigodes == 0) {
            placeCapeta(0, remainingCapetas);
            return;
        }

        for (int i = pos; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] == '.') {
                board[row][col] = 'B';
                if (isValidAdjacente(row, col, 'B')) {
                    placeBigode(i + 1, remainingBigodes - 1, remainingCapetas);
                }
                board[row][col] = '.';
            }
        }
    }

    private void placeCapeta(int pos, int remainingCapetas) {
        if (remainingCapetas == 0) {
            if (isValid()) {
                count++;
            }
            return;
        }

        for (int i = pos; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (board[row][col] == '.') {
                board[row][col] = 'C';
                if (isValidAdjacente(row, col, 'C')) {
                    placeCapeta(i + 1, remainingCapetas - 1);
                }
                board[row][col] = '.';
            }
        }
    }

    private boolean isValidAdjacente(int x, int y, char pistoleiro) {
        int[] dx = {-1, -1,  0, 1, 1,  1,  0, -1};
        int[] dy = { 0,  1,  1, 1, 0, -1, -1, -1};

        for (int dir = 0; dir < 8; dir++) {
            int i = x + dx[dir];
            int j = y + dy[dir];
            if (i >= 0 && i < n && j >= 0 && j < n) {
                if (board[i][j] == pistoleiro) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char pistoleiro = board[i][j];
                if (pistoleiro == 'B' || pistoleiro == 'C') {
                    if (!pistoleiroValido(i, j, pistoleiro)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean pistoleiroValido(int x, int y, char pistoleiro) {
        char oponente = (pistoleiro == 'B') ? 'C' : 'B';
        int countOponentes = 0;

        // Direções: N, NE, E, SE, S, SW, W, NW
        int[] dx = {-1, -1,  0, 1, 1,  1,  0, -1};
        int[] dy = { 0,  1,  1, 1, 0, -1, -1, -1};

        for (int dir = 0; dir < 8; dir++) {
            int i = x + dx[dir];
            int j = y + dy[dir];
            while (i >= 0 && i < n && j >= 0 && j < n) {
                if (board[i][j] == pistoleiro) {
                    return false;
                } else if (board[i][j] == oponente) {
                    countOponentes++;
                    break;
                }
                i += dx[dir];
                j += dy[dir];
            }
        }
        return countOponentes >= 2;
    }
}
