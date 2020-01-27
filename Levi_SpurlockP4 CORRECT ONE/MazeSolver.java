/* Name: Levi Spurlock
# Date: 11/11/2019
# Class: CSC 1120
# Pledge: I have neither given nor received unauthorized aid on this
program.
# Description: The program takes a user's input and prints out a mouse going through a maze to get some cheese.
# Input: The user types what maze they want to show off.
# Output:The program will print out the maze.
*/

import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {
    public static final String Path_Blocked = "#";
    public static final String Place_Gone = ".";
    public static final String Bread_Crumb = "o";
    public static final String Rat_Place = "R";
    public static final String Cheese_Place = "C";
    public static Point startPlace = null;
    public static Point endPlace = null;
    public static String[][] maze = null;
    public static int solvecount = 0;
    public static int breadcrumbs =0;
    Stack<String> stack = new Stack<String>();
    int i =0;
    public static void main(String[] args) {
        new MazeSolver();
    }

    public String printpoints(Point p){
       return "The Rat is at: " +p.x + ", " + p.y + ".";
    }

    public MazeSolver() {
        System.out.println("Please enter a maze:");
        Scanner scan=new Scanner(System.in);
        String p_choice  = scan.next();

        if (p_choice.contains("maze1")){
            maze = createMaze(1);

            startPlace = getXAndYOf(maze, Rat_Place);
            endPlace = getXAndYOf(maze, Cheese_Place);

            printpoints(startPlace);
            if (startPlace == null || endPlace == null) {
                System.out.println("No start or end point found");
            } else {
                boolean status = tryRoute(MazeSolver.startPlace);
                if (status) {

                    while(i < stack.size()){
                        System.out.println(stack.pop());
                    }

                    System.out.println("   ");
                    System.out.println("Route Found!\n");
                    System.out.println("Solve maze was called " + solvecount + " times.");
                    System.out.println("There are " + breadcrumbs + " breadcrumbs.");
                    printMaze();
                } else {
                    System.out.println("Route Not Found.");
                }
            }
        }
        else if(p_choice.contains("maze2")){
            maze = createMaze(2);
            startPlace = getXAndYOf(maze, Rat_Place);
            endPlace = getXAndYOf(maze, Cheese_Place);
            if (startPlace == null || endPlace == null) {
                System.out.println("No start or end point found");
            } else {
                boolean status = tryRoute(MazeSolver.startPlace);
                if (status) {

                    while(i < stack.size()){
                        System.out.println(stack.pop());
                    }
                    System.out.println("   ");
                    System.out.println("Route Found!\n");
                    System.out.println("Solve maze was called " + solvecount + " times.");
                    System.out.println("There are " + breadcrumbs + " breadcrumbs.");
                    printMaze();
                } else {
                    System.out.println("Route Not Found.");
                }
            }
        }
        else if (p_choice.contains("maze3")){
            maze = createMaze(3);
            startPlace = getXAndYOf(maze, Rat_Place);
            endPlace = getXAndYOf(maze, Cheese_Place);

            if (startPlace == null || endPlace == null) {
                System.out.println("No start or end point found");
            } else {
                boolean status = tryRoute(MazeSolver.startPlace);
                if (status) {

                    while(i < stack.size()){
                        System.out.println(stack.pop());
                    }
                    System.out.println("   ");
                    System.out.println("Route Found!\n");
                    System.out.println("Solve maze was called " + solvecount + " times.");
                    System.out.println("There are " + breadcrumbs + " breadcrumbs.");
                    printMaze();
                } else {
                    System.out.println("Route Not Found.");
                }
            }
        }
        else if(p_choice.contains("maze4")){
            maze = createMaze(4);
            startPlace = getXAndYOf(maze, Rat_Place);
            endPlace = getXAndYOf(maze, Cheese_Place);

            if (startPlace == null || endPlace == null) {
                System.out.println("No start or end point found");
            } else {
                boolean status = tryRoute(MazeSolver.startPlace);
                if (status) {
                    while(i < stack.size()){
                        System.out.println(stack.pop());
                    }
                    System.out.println("   ");
                    System.out.println("Route Found!\n");
                    System.out.println("Solve maze was called " + solvecount + " times.");
                    System.out.println("There are " + breadcrumbs + " breadcrumbs.");
                    printMaze();
                } else {
                    System.out.println("Route Not Found.");
                }
            }
        }
    }

    private void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    private boolean tryRoute(Point currentpoint) {
        if (currentpoint.x < 0 || currentpoint.x >= maze.length || currentpoint.y < 0 || currentpoint.y >= maze[currentpoint.x].length) {
            return false;
        }

//Check if current place is a wall or already visited
        if (maze[currentpoint.x][currentpoint.y] == Path_Blocked || maze[currentpoint.x][currentpoint.y] == Bread_Crumb) {
            return false;
        }

//Check if we reached the end place
        if (maze[currentpoint.x][currentpoint.y] == Cheese_Place) {
            return true;
        }

//Mark the place of the mouse and puts down a crumb.
        maze[currentpoint.x][currentpoint.y] = Bread_Crumb;
        breadcrumbs++;

//South Direction
        if (tryRoute(new Point(currentpoint.x + 1, currentpoint.y))) {
            stack.push(printpoints(currentpoint));
            solvecount++;
            return true;
        }
//West Direction
        if (tryRoute(new Point(currentpoint.x, currentpoint.y + 1))) {
           stack.push(printpoints(currentpoint));
            solvecount++;
            return true;
        }

//East Direction
        if (tryRoute(new Point(currentpoint.x, currentpoint.y - 1))) {
            stack.push(printpoints(currentpoint));
            solvecount++;
            return true;
        }

//North Direction
        if (tryRoute(new Point(currentpoint.x - 1, currentpoint.y))) {
            stack.push(printpoints(currentpoint));
            solvecount++;
            return true;
        }
        return false;
    }

    //Return the mouse's position.
    private Point getXAndYOf(String[][] maze, String choice) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == choice) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    //Creates Mazes for program.
    public String[][] createMaze(int number) {
        String maze1[][] = {
                {"#", "R", "#"},
                {"#", ".", "."},
                {"C", ".","#"}};

        String maze2[][] = {
                {".", "#", ".", "#"},
                {"R", ".", ".", "#"},
                {".", "#",".", "C"}};

        String maze3[][] = {
                {"#", "#", ".",".",".","#",".",".",".","#","#"},
                {".", ".", ".","#",".",".",".","#",".",".","#"},
                {"#", "#", ".","#","#","#",".","#","#","#","#"},
                {"#", ".", ".","#","#",".",".","#","#","R","#"},
                {"#", "#", ".",".","C","#",".",".",".",".","#"}, };

        String maze4[][] = {
                {"#","R", "#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"},
                {"#",".","#",".",".",".",".",".",".",".","#",".","#",".",".",".",".",".",".",".",".",".","#"},
                {"#",".","#",".","#",".","#","#","#",".","#",".","#",".","#","#","#","#","#","#","#",".","#"},
                {"#",".",".",".","#",".",".",".","#",".",".",".","#",".","#",".",".",".","#",".",".",".","#"},
                {"#",".","#","#","#","#","#",".","#","#","#","#","#",".","#",".","#",".","#",".","#","#","#"},
                {"#",".",".",".",".",".","#",".",".",".",".",".","#","C", "#",".","#",".","#",".","#",".","#"},
                {"#",".","#","#","#","#","#","#","#","#","#",".","#","#","#",".","#",".","#",".","#",".","#"},
                {"#",".","#",".",".",".",".",".","#",".",".",".","#",".",".",".","#",".",".",".","#",".","#"},
                {"#",".","#",".","#","#","#",".","#",".","#",".","#",".","#","#","#","#","#","#","#",".","#"},
                {"#",".","#",".","#",".",".",".",".",".","#",".","#",".",".",".","#",".",".",".",".",".","#"},
                {"#",".","#",".","#","#","#","#","#","#","#","#","#","#","#",".","#","#","#",".","#",".","#"},
                {"#",".","#",".","#",".",".",".",".",".",".",".",".",".","#",".",".",".",".",".","#",".","#"},
                {"#","#","#",".","#",".","#","#","#","#","#","#","#",".","#","#","#","#","#","#","#",".","#"},
                {"#",".",".",".","#",".","#",".",".",".",".",".","#",".",".",".",".",".",".",".",".",".","#"},
                {"#",".","#","#","#",".","#","#","#","#","#",".","#","#","#","#","#","#","#","#","#","#","#"},
                {"#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","#"},
                {"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"},};

        if(number==1){
            return maze1;
        }
        else if(number ==2){
            return maze2;
            }
        else if(number ==3){
            return maze3;
        }
        else if(number == 4){
            return maze4;
        }
        return maze;
    }
}
/*
 * Stores where the mouse is.
 */
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}