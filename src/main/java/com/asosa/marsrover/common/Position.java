package com.asosa.marsrover.common;

/**
 * Represents a position on a grid with X and Y coordinates.
 */
public class Position {
    private int x; // The X-coordinate of the position
    private int y; // The Y-coordinate of the position

    /**
     * Default constructor for Position.
     */
    public Position() {
    }

    /**
     * Constructor to create a position with specified X and Y coordinates.
     * @param x The X-coordinate.
     * @param y The Y-coordinate.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X-coordinate of the position.
     * @return The X-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-coordinate of the position.
     * @param x The new X-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y-coordinate of the position.
     * @return The Y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate of the position.
     * @param y The new Y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the position.
     * @return A string in the format (X, Y).
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Checks if two positions are equal.
     * @param obj The object to compare.
     * @return True if the positions are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    /**
     * Generates a hash code for the position.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }
}
