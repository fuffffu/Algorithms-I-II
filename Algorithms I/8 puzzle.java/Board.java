import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
    private final int[][] board;
    private final int n;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.copyOf(tiles[i], tiles[i].length); // 使用 Arrays.copyOf 复制每一行
        }
    }

    // string representation of this board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");//细节产生美
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != hamming && board[i][j] != 0) {
                    count++;
                }
                hamming++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    int targetRow = (board[i][j] - 1) / n;
                    int targetCol = (board[i][j] - 1) % n;
                    count += Math.abs(targetRow - i) + Math.abs(targetCol - j);
                }
            }
        }
        return count;
    }


    // is this board the goal board?
    public boolean isGoal() {
        int goal = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    if (board[i][j] != 0) return false;
                } else if (board[i][j] != goal) {
                    return false;
                }
                goal++;
            }
        }
        return true;
    }


    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (this == y) return true;  // 先判断是否是同一个对象
        if (y == null || getClass() != y.getClass()) return false;  // 判断类型
        Board other = (Board) y;  // 强制转换
        if (this.n != other.n) return false;  // 判断维度是否相同
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.board[i][j] != other.board[i][j]) return false;  // 对应位置的数字是否相同
            }
        }
        return true;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int blankRow = -1;
        int blankCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
        int[][] directions = {
                {-1, 0}, // 上
                {1, 0},  // 下
                {0, -1}, // 左
                {0, 1}   // 右
        };

        for (int[] dir : directions) {
            int newRow = blankRow + dir[0];
            int newCol = blankCol + dir[1];

            // 检查是否在棋盘内
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                // 创建新棋盘，交换空格和相邻位置的格子
                Board neighbor = new Board(this.board);
                neighbor.swap(blankRow, blankCol, newRow, newCol); // 假设有 swap 方法来交换两格
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    // twin 和 swap 不是一回事，它们的功能也不同
    private void swap(int r1, int c1, int r2, int c2) {
        int temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }


   // 交换任意两个非零块会改变序列的奇偶性。因为如果一个棋盘的twin()和原始棋盘一定一个可解一个不可解。
   public Board twin() {
       int[][] twinTiles = new int[n][n];
       for (int i = 0; i < n; i++)
           for (int j = 0; j < n; j++)
               twinTiles[i][j] = board[i][j];

       // Find first non-zero tile
       int row = 0;
       while (board[row][0] == 0 || board[row][1] == 0) row++;

       // Swap the first two non-zero tiles in this row
       int temp = twinTiles[row][0];
       twinTiles[row][0] = twinTiles[row][1];
       twinTiles[row][1] = temp;

       return new Board(twinTiles);
   }

        // unit testing (not graded)
    public static void main(String[] args) {
        // 创建一个测试棋盘
        int[][] initialTiles = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };

        // 初始化 Board 对象
        Board board = new Board(initialTiles);

        // 打印棋盘
        System.out.println("Initial board:");
        System.out.println(board);

        // 打印 Hamming 距离
        System.out.println("Hamming distance: " + board.hamming());

        // 打印 Manhattan 距离
        System.out.println("Manhattan distance: " + board.manhattan());

        // 打印邻居棋盘
        System.out.println("Neighbors:");
        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }

        // 打印 twin 棋盘
        System.out.println("Twin board:");
        System.out.println(board.twin());
    }
}
