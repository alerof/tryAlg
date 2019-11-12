package com.alaerof.model;

public class Node implements Comparable<Node>{
    private  int row;
    private int col;
    private int penalty;
    private Node parent;
    private int gValue = -1;
    private int hValue = -1;
    private boolean closed;

    public Node(int row, int col, int penalty) {
        this.row = row;
        this.col = col;
        this.penalty = penalty;
    }

    @Override
    public int compareTo(Node o) {
        int res = (this.gValue + this.hValue) - (o.gValue + o.hValue);
        if (res == 0) {
            res = -1*(this.row - o.row);
            if (res == 0) {
                res = this.col - o.col;
            }
        }
        return res;
    }

    public String toStringNode() {
        return "" + penalty;
    }

    @Override
    public String toString() {
        return "[" + row + "," + col + "] " + penalty + " {func= " + gValue + " + " + hValue + "}";
    }

    public int getFuncValue() {
        return gValue + hValue;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getGValue() {
        return gValue;
    }

    public void setGValue(int gValue) {
        this.gValue = gValue;
    }

    public int getHValue() {
        return hValue;
    }

    public void setHValue(int hValue) {
        this.hValue = hValue;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
