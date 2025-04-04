import java.util.EmptyStackException;
import java.util.Stack;

public class stackMax {
    private Stack<Double> stack;
    private Stack<Double> maxStack;

    public stackMax() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(double x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        }else if(x > maxStack.peek()){
            maxStack.push(x);
        }
    }


    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            maxStack.pop();//如果stack出去的是最大值辅助栈要保持一致
        }
    }

    public double getMax() {
        if (!stack.isEmpty()) {
            return maxStack.peek();
        }
        throw new EmptyStackException();
    }

}
