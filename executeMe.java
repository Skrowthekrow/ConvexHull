import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
// Combined Quick Hull and Brute Force solutions into one program.
// 

public class executeMe {
    static ArrayList<Point> points = new ArrayList<Point>();
    static Set<Point> finalsolutions = new HashSet<Point>();

    public static void randompoints(int n) {
        // random number generator includes number <= n
        for (int i = 1; i <= n; i++) {
            int min = 0;
            int max = 10;
            int x = (int) Math.floor(Math.random() * (max - min + 1) + min);
            int y = (int) Math.floor(Math.random() * (max - min + 1) + min);
            Point d = new Point(x, y);
            
                points.add(d);

             
        }
    }


    public static Point Center(Set<Point> solution) {
        ArrayList<Point> ordered = new ArrayList<Point>();
        int xtotal = 0;
        int ytotal = 0;
        for (Point x : solution) {
            ordered.add(x);
        }
        // average all the xy points
        for (Point s : ordered) {
            int temp = s.GetX();
            int temp2 = s.GetY();

            xtotal += temp;
            ytotal += temp2;
        }
        xtotal = xtotal / ordered.size();
        ytotal = xtotal / ordered.size();
        Point center = new Point(xtotal, ytotal);

        return center;
        // average^
    }

    public static void sortPoints(Set<Point> solution) {
        ArrayList<Point> ordered = new ArrayList<Point>();
        for (Point x : solution) {
            ordered.add(x);
        }

        Point center = Center(solution);
        Collections.sort(ordered, (a, b) -> {
            double s1 = (Math.toDegrees(Math.atan2(a.GetX() - center.GetX(), a.GetY() - center.GetY())) + 360) % 360;
            double s2 = (Math.toDegrees(Math.atan2(b.GetX() - center.GetX(), b.GetY() - center.GetY())) + 360) % 360;
            return (int) (s1 - s2);
        });
        System.out.print(ordered);
    }

    public static void PrintList(ArrayList<Point> List) {
        for (int i = 0; i <= List.size() - 1; i++) {
            System.out.print(List.get(i).toString() + "\n");
        }
        System.out.print("\n");
    }

    // O(n)^3 using 3 for loops for brute force approach
    public static void BruteForce(ArrayList<Point> points, Set<Point> solution) {
        // initial for loop running through all points in points ArrayList
        for (int i = 0; i < points.size(); i++) {
            // secondary for loop running through rest of ArrayList elements
            for (int j = 0; j < points.size(); j++) {
                // if i == j pass through and ignore
                if (i == j) {
                    continue;
                }
                // boolean sameSide exists to test whether all points exist on same side of
                // created line
                boolean sameSide = true;

                // funcs to pull values from points ArrayList
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                // A = Y2-Y1, B= X1-X2, C=X1*Y2-Y1*X2
                int A = p2.GetY() - p1.GetY();
                int B = p1.GetX() - p2.GetX();
                int C = p1.GetX() * p2.GetY() - p1.GetY() * p2.GetX();
                // for loop to test all other points to line
                for (int k = 0; k < points.size(); k++) {
                    if (k == i || k == j) {
                        // same as before continue if index is the same
                        continue;
                    }
                    // starts at 0
                    Point p3 = points.get(k);

                    // test equation (A*X)+(B*Y)-C from the coefficients above
                    int side = A * p3.GetX() + B * p3.GetY() - C;
                    // if statement is testing if the result from side is less than 0, if so it will
                    // trigger
                    // sameSide bool to turn false and will break out of loop and go to next value
                    if (side < 0) {
                        sameSide = false;
                        break;
                    }
                }
                // two initial for loops continue until completion and if the sameSide hasnt
                // been triggered then
                // we add both points to our solution
                if (sameSide) {
                    solution.add(p1);
                    solution.add(p2);
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please make your selection to solve the Convex Hull Problem");
        System.out.println("Enter (1) for BruteForce ");
        System.out.println("Enter (2) for QuickHull ");

        int selection = s.nextInt();
        if (selection == 1) {
            int numOfPoints;
            // Provide number of points
            System.out.println("You have selected the BruteForce Solution!");
            System.out.println("Input # of points: ");
            numOfPoints = s.nextInt();

            // create the given amount of points
            randompoints(numOfPoints);

            // close resource link
            s.close();
            PrintList(points);

            // Call Brute Force solution
            System.out.println("The Brute-force solution:");
            BruteForce(points, finalsolutions);

            // Print solution list
            System.out.println(finalsolutions);
            System.out.println("Here is the solution in counter clock-wise order below");
            sortPoints(finalsolutions);
        } else if (selection == 2) {
            int numOfPoints;
            // User provide number of points
            System.out.println("Input # of points: ");
            numOfPoints = s.nextInt();
            // create the given amount of points
            randompoints(numOfPoints);

            // close resource link
            s.close();
            PrintList(points);

            // Call Divide and Conquer solution
            QuickHull.printHull(points, numOfPoints);

        } else {
            System.exit(0);
        }

    }
}