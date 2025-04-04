import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private final Point2D point;
        private final RectHV rect;
        private final boolean vertical;
        private Node lb;  // left/bottom
        private Node rt;  // right/top

        public Node(Point2D p, RectHV rect, boolean vertical) {
            this.point = p;
            this.rect = rect;
            this.vertical = vertical;
        }
    }

    public KdTree() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        validateNull(p);
        root = insert(root, p, new RectHV(0, 0, 1, 1), true);
    }

    private Node insert(Node node, Point2D p, RectHV rect, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, rect, vertical);
        }

        if (node.point.equals(p)) return node;

        int cmp = compare(p, node.point, vertical);
        if (cmp < 0) {
            RectHV newRect = vertical ?
                    new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax()) :
                    new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            node.lb = insert(node.lb, p, newRect, !vertical);
        } else {
            RectHV newRect = vertical ?
                    new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax()) :
                    new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
            node.rt = insert(node.rt, p, newRect, !vertical);
        }
        return node;
    }

    private int compare(Point2D p, Point2D q, boolean vertical) {
        return vertical ?
                Double.compare(p.x(), q.x()) :
                Double.compare(p.y(), q.y());
    }

    public boolean contains(Point2D p) {
        validateNull(p);
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean vertical) {
        if (node == null) return false;
        if (node.point.equals(p)) return true;

        int cmp = compare(p, node.point, vertical);
        return cmp < 0 ?
                contains(node.lb, p, !vertical) :
                contains(node.rt, p, !vertical);
    }

    public Iterable<Point2D> range(RectHV rect) {
        validateNull(rect);
        List<Point2D> result = new ArrayList<>();
        range(root, rect, result);
        return result;
    }

    private void range(Node node, RectHV queryRect, List<Point2D> result) {
        if (node == null) return;

        if (queryRect.contains(node.point)) {
            result.add(node.point);
        }

        if (node.lb != null && queryRect.intersects(node.lb.rect)) {
            range(node.lb, queryRect, result);
        }
        if (node.rt != null && queryRect.intersects(node.rt.rect)) {
            range(node.rt, queryRect, result);
        }
    }

    public Point2D nearest(Point2D p) {
        validateNull(p);
        return isEmpty() ? null : nearest(root, p, root.point);
    }

    private Point2D nearest(Node node, Point2D target, Point2D best) {
        if (node == null) return best;

        double bestDist = best.distanceSquaredTo(target);
        double nodeDist = node.point.distanceSquaredTo(target);
        if (nodeDist < bestDist) {
            best = node.point;
            bestDist = nodeDist;
        }

        Node first = shouldGoLeft(target, node) ? node.lb : node.rt;
        Node second = shouldGoLeft(target, node) ? node.rt : node.lb;

        best = nearest(first, target, best);
        if (second != null && second.rect.distanceSquaredTo(target) < bestDist) {
            best = nearest(second, target, best);
        }
        return best;
    }

    private boolean shouldGoLeft(Point2D p, Node node) {
        return node.vertical ? p.x() < node.point.x() : p.y() < node.point.y();
    }

    private void validateNull(Object obj) {
        if (obj == null) throw new IllegalArgumentException();
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) return;

        // 绘制点
        StdDraw.setPenColor(StdDraw.BLACK);
        node.point.draw();

        // 绘制分割线
        StdDraw.setPenColor(node.vertical ? StdDraw.RED : StdDraw.BLUE);
        if (node.vertical) {
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }

        draw(node.lb);
        draw(node.rt);
    }
}