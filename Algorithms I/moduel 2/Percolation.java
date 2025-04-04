import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int virtualTop;
    private int virtualBottom;
    private final WeightedQuickUnionUF uf;
    private int size;
    private final int n;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.grid = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n*n + 2);
        this.size = 0;
        this.n = n;
        this.virtualTop = n*n;
        this.virtualBottom = n*n + 1;
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

    private int to1D(int row, int col){
        return (row-1) * n + (col-1);
    }

    public void open(int row, int col) {
        validate(row, col);
        if (grid[row-1][col-1]) return;
        grid[row-1][col-1] = true;
        size++;
        int p = to1D(row,col);
        
        if (row == 1) {
            uf.union(p, virtualTop);
        }
        if (row == n) {
            uf.union(p, virtualBottom);
        }
        
        // 连接四个方向
        if (row > 1 && isOpen(row-1, col)) {
            uf.union(p, to1D(row-1, col));
        }
        if (row < n && isOpen(row+1, col)) {
            uf.union(p, to1D(row+1, col));
        }
        if (col > 1 && isOpen(row, col-1)) {
            uf.union(p, to1D(row, col-1));
        }
        if (col < n && isOpen(row, col+1)) {
            uf.union(p, to1D(row, col+1));
        }
    }

    public boolean isOpen(int row, int col){
        validate(row,col);
        return grid[row-1][col-1];
    }

    public boolean isFull(int row, int col){
        validate(row,col);
        int p = to1D(row,col);
        return isOpen(row, col) && uf.find(p) == uf.find(virtualTop);
    }

    public int numberOfOpenSites(){
        return size;
    }

    public boolean percolates(){
        if (n == 1) return isOpen(1, 1);
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }
}
