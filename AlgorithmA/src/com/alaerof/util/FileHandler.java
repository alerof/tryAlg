package com.alaerof.util;

import com.alaerof.model.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    public static Node[][] readFromFile(String file) {
        Node[][] result = new Node[0][0];
        try {
            Path path = Paths.get(file);
            //read all lines
            List<String> lines = Files.readAllLines(path);
            String[] firstLine = lines.get(0).split(" ");
            int rows = Integer.parseInt(firstLine[0]);
            int cols = Integer.parseInt(firstLine[1]);
            result = new Node[rows][cols];
            for (int i = 1; i < rows + 1; i++) {
                String[] line = lines.get(i).split(" ");
                for (int j = 0; j < cols; j++) {
                    int penalty = Integer.parseInt(line[j]);
                    Node node = new Node(i - 1, j, penalty);
                    result[i - 1][j] = node;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
