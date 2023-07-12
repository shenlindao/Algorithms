package Seminar.Seminar_03;

public class LinkedList {
    private Node root;
    private Node last;
    private int size;

    private class Node {
        int value;
        Node next;

        Node() {
        }

        Node(int _value) {
            this.value = _value;
        }

        Node(int _value, Node _next) {
            this.value = _value;
            this.next = _next;
        }
    }

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null)
            currentNode = currentNode.next;
        currentNode.next = new Node(value);
        size++;
    }

    public boolean remove(int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size)
            return;
        if (index == 0) {
            root = root.next;
            size--;
            return;
        }
        Node currentNode = getNode(index - 1);
        currentNode.next = currentNode.next.next;
        size--;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size)
            return null;
        if (index == 0) {
            return root;
        }
        Node currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public int getValue(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void swap(int index1, int index2) {
        if ((index1 < 0 || index1 >= size) || (index2 < 0 || index2 >= size))
            return;
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        int temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    public void quickSort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = getValue((leftMarker + rightMarker) / 2);
        do {
            while (getValue(leftMarker) < pivot) {
                leftMarker++;
            }
            while (getValue(rightMarker) > pivot) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker)
                    swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder)
            quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(leftBorder, rightMarker);
    }

    public void print() {
        Node currentNode = root;
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
        System.out.print("\n");
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void reverse() {
        Node currentNode = root;

        if (!isEmpty() && root.next != null) {
            last = root;
            currentNode = root.next;
            root.next = null;
            while (currentNode != null) {
                Node next = currentNode.next;
                currentNode.next = root;
                root = currentNode;
                currentNode = next;
            }
        }
    }
}
