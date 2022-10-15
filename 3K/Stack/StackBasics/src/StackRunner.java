import java.util.*;


public class StackRunner {
    public static void main(String[] args) {
        Queue queue = new LinkedList<String>();
        queue.add("First");
        queue.add("Second");
        queue.add("third");
        System.out.println("Created Queue: " + queue);
        String value = (String) queue.remove();
        System.out.println("The element deleted from the head is: " + value);
        System.out.println("The queue after deletion: " + queue);
        String head = (String) queue.peek();
        System.out.println("The head of the present queue is: " + head);
        int size = queue.size();
        System.out.println("The size of the queue is: " + size);
    }
}
