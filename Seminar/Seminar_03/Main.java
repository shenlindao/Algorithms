package Seminar.Seminar_03;

public class Main {
    class Node
{
    int data;
    Node next;
 
    Node(int data, Node next)
    {
        this.data = data;
        this.next = next;
    }
}
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add(5);
        list.add(2);
        list.add(9);
        list.add(0);
        list.add(-4);
        
        list.print();
        list.reverse();
        list.print();
    }
}
