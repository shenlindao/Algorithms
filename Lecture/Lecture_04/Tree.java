package Lecture.Lecture_04;

import java.util.ArrayList;

public class Tree {
    Node root;

    public boolean exist(int value) {
        if (root != null) {
            Node node = find(value);
            if (node != null) {
                return true;
            }
        }
        return false;
    }

    // private Node find(Node node, int value) {
    // if (node.value == value) {
    // return node;
    // } else {
    // for (Node child : node.children) {
    // Node result = find(child, value);
    // if (result != null) {
    // return result;
    // }
    // }
    // }
    // return null;
    // }

    private Node find(int value) {
        ArrayList<Node> line = new ArrayList<>();
        line.add(root);
        while (line.size() > 0) {
            ArrayList<Node> currentLine = new ArrayList<>();
            for (Node node : line) {
                if (node.value == value) {
                    return node;
                }
                currentLine.addAll(node.children);
            }
            // line = nextLine;
        }
        return null;
    }

    public class Node {
        int value;
        ArrayList<Node> children;
    }
}
