import edu.princeton.cs.algs4.MinPQ;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    private Board board;
    private List<Board> visited; // 改为使用 List 而不是 Set


    private class NewNode implements Comparable<NewNode> {
        private NewNode parent;
        private Board board;
        private final int manhattanDistance;
        private final int sunkCost;
        private final int priority;

        public NewNode(Board board, NewNode parent, int manhattanDistance, int sunkCost) {
            this.board = board;
            this.parent = parent;
            this.manhattanDistance = manhattanDistance;
            this.sunkCost = sunkCost;
            this.priority = manhattanDistance + sunkCost;
            if (sunkCost == 0) this.parent = null;
        }

        @Override
        public int compareTo(NewNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial0) {
        this.board = initial0;
        this.visited = new ArrayList<>();
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return (board.manhattan() / 2) % 2 == 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        NewNode solvedNode = solve();
        if (solvedNode == null) return -1;
        int moves = 0;
        while (solvedNode.parent != null) {
            solvedNode = solvedNode.parent;
            moves++;
        }
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        NewNode solvedNode = solve();
        if (solvedNode == null) return null;
        List<Board> solutionPath = new ArrayList<>();
        while (solvedNode != null) {
            solutionPath.add(solvedNode.board);
            solvedNode = solvedNode.parent;
        }
        Collections.reverse(solutionPath);
        return solutionPath;
    }

    private NewNode solve() {
        MinPQ<NewNode> queue = new MinPQ<>();  // 局部变量
        queue.insert(new NewNode(board, null, board.manhattan(), 0)); // 初始化队列
        while (!queue.isEmpty()) {
            NewNode currentNode = queue.delMin(); // 使用 delMin() 代替 poll()
            if (currentNode.board.isGoal()) return currentNode;
            for (Board neighbor : currentNode.board.neighbors()) {
                if (currentNode.parent == null || !neighbor.equals(currentNode.parent.board)) {
                    queue.insert(new NewNode(neighbor, currentNode,
                            neighbor.manhattan(), currentNode.sunkCost + 1));
                } //neighbors不等于current的特例是根节点
            }
        }
        return null;
    }
}


/**
 * 在 8-puzzle 这个问题中，你面临的是一个 搜索问题。你需要从一个起始状态出发，经过若干合法的变换，最终达到目标状态。
 * 你必须追踪整个棋盘的状态，而不仅仅是零星的坐标。这就是为什么neighbor和path以及其他地方我们都选择用board而不是单个点
 * 而且如果以后你的问题不再只是解决一个目标状态，而是多个目标状态，用全局状态更好。
 */


/**
 * 复盘。最开始的我想的是贪心算法，但是局部的贪心并不能代替整体最佳。当决策之间存在依赖关系、涉及到组合。必须考虑到全局的约束时。通常都不能通过局部实现
 *  A*通常都是这个衡量局部和全局的最佳解决办法，考虑沉没成本和机会成本实现全局最佳。同时还需要结合优先队列来选择最佳的f(n)+g(n)
 *  因为需要起码两个元素，所以我们需要自己设立一个新的class。弄清我们的需求：沉没成本、机会成本（m距离）、board来展示、parent（为了回溯和推出路径）。
 *  这里重要的一个点是理解为什么是board而不是一些坐标点。因为我们整个8-puzzle问题大多数应用是为了整体服务的，我们不是要求具体的哪条线，而是在这个面里的那条线、在这个面里的线的变化。参考应用拼图，只有拼的路径没有整体的图没有用。
 */