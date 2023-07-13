package Homework.Homework_04;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

    private Node root;

    public boolean add(T value) {
        if (root == null) {
            root = new Node(value, Color.black);
            return true;
        }
        return addNode(root, value);
    }

    public boolean addNode(Node currentNode, T value) {
        if (currentNode.value.compareTo(value) == 0)
            return false;
        if (currentNode.value.compareTo(value) > 0) // currentNode.value > value
        {
            if (currentNode.left != null) {
                boolean result = addNode(currentNode.left, value);
                currentNode.left = rebalanced(currentNode.left);
                return result;
            } else {
                currentNode.left = new Node(value, Color.red);
                return true;
            }
        } else {
            if (currentNode.right != null) {
                boolean result = addNode(currentNode.right, value);
                currentNode.right = rebalanced(currentNode.right);
                return result;
            } else {
                currentNode.right = new Node(value, Color.red);
                return true;
            }
        }
    }

    public boolean contains(T value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.compareTo(value) == 0)
                return true;
            if (currentNode.value.compareTo(value) > 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    public boolean remove(T value) {
        if (!contains(value))
            return false;

        Node deleteNode = root;
        while (deleteNode != null) {
            if (deleteNode.value.compareTo(value) == 0) {
                if (deleteNode.right == null && deleteNode.left == null)
                    deleteNode = null;
                else if (deleteNode.right == null) {
                    deleteNode.left.color = deleteNode.color;
                    deleteNode.value = deleteNode.left.value;
                    deleteNode.left = deleteNode.left.left;
                    deleteNode.right = deleteNode.left.right;
                    deleteNode = rebalanced(deleteNode);
                } else {
                    Node replaceNode = deleteNode.right;
                    Node preReplace = replaceNode;
                    while (replaceNode.left != null) {
                        preReplace = replaceNode;
                        replaceNode = replaceNode.left;
                    }
                    deleteNode.value = replaceNode.value;
                    if (replaceNode == preReplace)
                        deleteNode.right = null;
                    else
                        preReplace.left = null;
                    deleteNode = rebalanced(deleteNode);
                    return true;
                }
                return true;
            }
            if (deleteNode.value.compareTo(value) > 0)
                deleteNode = deleteNode.left;
            else
                deleteNode = deleteNode.right;
        }
        return false;
    }

    public void print() {
        int maxDepth = maxDepth() + 3;
        int nodeCount = nodeCount(root, 0);
        int width = 50; // maxDepth * 4 + 2;
        int height = nodeCount * 5;
        List<List<PrintNode>> list = new ArrayList<>();
        for (int i = 0; i < height; i++) /* Создание ячеек массива */ {
            ArrayList<PrintNode> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new PrintNode());
            }
            list.add(row);
        }

        list.get(height / 2).set(0, new PrintNode(root));
        list.get(height / 2).get(0).depth = 0;
        for (int j = 0; j < width; j++) /* Принцип заполнения */ {
            for (int i = 0; i < height; i++) {
                PrintNode currentNode = list.get(i).get(j);
                if (currentNode.node != null) {
                    currentNode.str = currentNode.node.value.toString();
                    if (currentNode.node.left != null) {
                        int in = i + (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.left;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;
                    }
                    if (currentNode.node.right != null) {
                        int in = i - (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.right;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) /* Чистка пустых строк */ {
            boolean flag = true;
            for (int j = 0; j < width; j++) {
                if (list.get(i).get(j).str != " ") {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.remove(i);
                i--;
                height--;
            }
        }
        for (var row : list) {
            for (var item : row) {
                if (item.node != null && item.node.color == Color.red)
                    System.out.print("\u001B[31m" + item.str + " " + "\u001B[0m");
                else if (item.node != null && item.node.color == Color.black) {
                    System.out.print("\u001B[30m" + item.str + " " + "\u001B[0m");
                } else
                    System.out.print(item.str + " ");
            }
            System.out.println();
        }
    }

    private void printLines(List<List<PrintNode>> list, int i, int j, int i2, int j2) {
        if (i2 > i) // Идём вниз
        {
            while (i < i2) {
                i++;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "\\";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        } else {
            while (i > i2) {
                i--;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "/";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        }
    }

    private int nodeCount(Node node, int count) {
        if (node != null) {
            count++;
            return count + nodeCount(node.left, 0) + nodeCount(node.right, 0);
        }
        return count;
    }

    public int maxDepth() {
        return maxDepth2(0, root);
    }

    private int maxDepth2(int depth, Node node) {
        depth++;
        int left = depth;
        int right = depth;
        if (node.left != null)
            left = maxDepth2(depth, node.left);
        if (node.right != null)
            right = maxDepth2(depth, node.right);
        return left > right ? left : right;
    }

    private Node rebalanced(Node node) {
        Node result = node;
        boolean needRebalanced;
        do {
            needRebalanced = false;
            if (result.right != null && result.right.color == Color.red &&
                    (result.left == null || result.left.color == Color.black)) {
                needRebalanced = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.red &&
                    result.left.left != null && result.left.left.color == Color.red) {
                needRebalanced = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.red &&
                    result.right != null && result.right.color == Color.red) {
                needRebalanced = true;
                colorSwap(result);
            }
        } while (needRebalanced);
        return result;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.black;
        node.left.color = Color.black;
        node.color = Color.red;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.red;
        return left;
    }

    private Node rightSwap(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.red;
        return right;
    }

    private class PrintNode {
        Node node;
        String str;
        int depth;

        public PrintNode() {
            node = null;
            str = " ";
            depth = 0;
        }

        public PrintNode(BinaryTree.Node node) {
            depth = 0;
            this.node = node;
            this.str = node.value.toString();
        }
    }

    private class Node {
        private T value;
        private Color color;
        private Node left;
        private Node right;

        public Node() {
            value = null;
            left = null;
            right = null;
            color = null;
        }

        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
            color = null;
        }

        public Node(T value, Color color) {
            this.value = value;
            left = null;
            right = null;
            this.color = color;
        }
    }

    enum Color {
        red, black
    }
}
