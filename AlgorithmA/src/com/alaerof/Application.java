package com.alaerof;

import com.alaerof.logic.Logic;
import com.alaerof.model.Node;
import com.alaerof.util.FileHandler;

/**
 * Task:
 * given: matrix MxN with penalties in cells
 * goal: build path from any cell from first row to any cell in the last (N) row with the minimum penalties sum,
 * for the cell(i,j) possible next positions are: cell(i+1,j-1), cell(i+1, j), cell(i+1, j+1)
 *
 * decision:
 * Algorithm A*. f(x) = g(x) + h(x)
 * g(x) = sum path penalties from the beginning to the current cell x (= penalty for the first row)
 * h(x) = count rows from the current to the last * to the min penalty in the matrix (= 0 for the last row)
 *
 * therefore path sum will be = g(x) for the last cell
 */
public class Application {
    public static void main(String[] args) {
        Node[][] nodeArray = FileHandler.readFromFile("source.txt");
        printArray(nodeArray);

        Node node = Logic.runA(nodeArray);
        System.out.println(node.toString());
        while (node.getParent() != null) {
            node = node.getParent();
            System.out.println(node.toString());
        }

    }

    private static void printArray(Node[][] nodeArray) {
        int rowCount = nodeArray.length;
        int colCount = nodeArray[0].length;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                System.out.print(nodeArray[i][j].toStringNode() + " ");
            }
            System.out.println("");
        }
    }
}
