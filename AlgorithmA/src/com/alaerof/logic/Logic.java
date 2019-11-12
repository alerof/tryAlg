package com.alaerof.logic;

import com.alaerof.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Logic {
    public static Node runA(Node[][] nodeArray) {
        Node currentNode = null;
        int rowCount = nodeArray.length;
        int colCount = nodeArray[0].length;
        if (rowCount > 0 && colCount > 0) {
            int minPenalty = findMinPenalty(nodeArray);
            currentNode = nodeArray[0][0];
            SortedSet<Node> openNodeSet = initOpenNodeSet(nodeArray, minPenalty);

            while (openNodeSet.size() > 0) {
                currentNode = openNodeSet.first();
                int currentRow = currentNode.getRow();
                if (currentRow == rowCount - 1) {
                    break;
                }
                List<Node> childNodes = getChildNodes(currentNode, nodeArray);
                for (Node child : childNodes) {
                    if (!child.isClosed()) {
                        int gFunc = currentNode.getGValue() + child.getPenalty();
                        int hFunc = minPenalty * (rowCount - child.getRow() - 1);
                        // we should check if the func value for child have already been calculated
                        if (child.getFuncValue() == -2 || child.getFuncValue() > gFunc + hFunc) {
                            child.setGValue(gFunc);
                            child.setHValue(hFunc);
                            child.setParent(currentNode);
                        }
                        openNodeSet.add(child);
                    }
                }
                currentNode.setClosed(true);
                openNodeSet.remove(currentNode);
            }
        }
        return currentNode;
    }

    private static List<Node> getChildNodes(Node currentNode, Node[][] nodeArray) {
        List<Node> nodes = new ArrayList<>();
        int currRow = currentNode.getRow();
        int currCol = currentNode.getCol();
        int rowCount = nodeArray.length;
        int colCount = nodeArray[0].length;
        if ((currRow + 1) < rowCount) {
            Node child = nodeArray[currRow + 1][currCol];
            nodes.add(child);
            if ((currCol + 1) < colCount) {
                child = nodeArray[currRow + 1][currCol + 1];
                nodes.add(child);
            }
            if ((currCol - 1) >= 0) {
                child = nodeArray[currRow + 1][currCol - 1];
                nodes.add(child);
            }
        }
        return nodes;
    }

    private static int findMinPenalty(Node[][] nodeArray) {
        int minPenalty = nodeArray[0][0].getPenalty();
        for (Node[] nodeRow : nodeArray) {
            for (Node node : nodeRow) {
                if (node.getPenalty() < minPenalty) {
                    minPenalty = node.getPenalty();
                }
            }
        }
        return minPenalty;
    }

    private static SortedSet<Node> initOpenNodeSet(Node[][] nodeArray, int minPenalty) {
        SortedSet<Node> openNodeSet = new TreeSet<Node>();
        int rowCount = nodeArray.length;
        for (Node node : nodeArray[0]) {
            int gFunc = node.getPenalty(); // as it's first row we don't have parents here
            int hFunc = minPenalty * (rowCount - node.getRow() - 1);
            node.setGValue(gFunc);
            node.setHValue(hFunc);
            openNodeSet.add(node);
        }
        return openNodeSet;
    }

}
