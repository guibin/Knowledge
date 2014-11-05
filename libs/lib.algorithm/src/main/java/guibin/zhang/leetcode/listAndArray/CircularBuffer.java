package guibin.zhang.leetcode.listAndArray;

/**
 * http://www.vias.org/javacourse/chap16_04.html
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class CircularBuffer {
    
    int capacity;
    int head;
    int next;//point to the next available position to add the item on tail
    Object[] array;
    
    public CircularBuffer(int capacity) {
        //Use one extra space to distinguish the full and empty status.
        //Full: head + 1 == next; empty: head == next
        this.capacity = capacity + 1;
        array = new Object[this.capacity];
        head = next = 0;
    }
    
    public boolean isEmpty() {
        return head == next;
    }
    
    public boolean isFull() {
        return ((next + 1) % array.length == head);
    }
    
    public void add(Object o) {
        
        //If it is full, move the head ahead to free room.
        if (isFull()) head = (head + 1) % array.length;
        
        array[next] = o;
        next = (next + 1) % array.length;
    }
    
    public Object remove() {
        
        if (isEmpty()) return null;
        
        Object result = array[head]; 
        head = (head + 1) % array.length;
        return result;
    }
    
    public Object peek() {
        if (!isEmpty()) {
            return array[head];
        }
        return null;
    }
    
    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(3);
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        System.out.println(buffer.remove());//2
        buffer.add(5);
        
        while (!buffer.isEmpty()) {
            System.out.print(buffer.remove());//3, 4, 5
        }
        System.out.println(">>");
    }
}
