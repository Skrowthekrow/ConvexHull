
import java.util.*;
import java.util.Random;
import java.lang.Math;

public class QuickHull {//Main class start

  //arraylist to hold Point class objects called points
  static ArrayList<Point> points = new ArrayList<Point>();

  //set to hold Point class objects called finalsolutions
  static Set<Point> finalsolutions = new HashSet<Point>();

  public static void randompoints(int n) {
    // random number generator includes number <=
    for (int i = 1; i <= n; i++) {
      Random random = new Random();
      int x = random.nextInt(20) - 10;
      int y = random.nextInt(20) - 10;
      Point d = new Point(x, y);
      points.add(d);
    }
  }


  public static void PrintList(ArrayList<Point> List) {
    for (int i = 0; i <= List.size() - 1; i++) {
      System.out.print(List.get(i).toString() + "\n");
    }
    System.out.print("\n");
  }

  

  public static void printHull(ArrayList<Point> p, int numPoints){

    if (numPoints < 3) {
      System.out.print("Convex hull not possible\n");
      return;
    }

    // Finding the point with minimum and
    // maximum x-coordinate
    int min_x = 0;
    int max_x = 0;
    for (int i = 1; i < numPoints; i++) {
      if (p.get(i).GetX() < p.get(min_x).GetX()) {
        min_x = i;
      }
      if (p.get(i).GetX() > p.get(max_x).GetX()) {
        max_x = i;
      }

    }

    // Recursively find convex hull points on
        // one side of line joining a[min_x] and
        // a[max_x]
        quickHull(p, numPoints, p.get(min_x), p.get(max_x), 1);
        quickHull(p, numPoints, p.get(min_x), p.get(max_x), -1);

    System.out.print("The points in Convex Hull are:\n");
    System.out.print(finalsolutions);
    
  
  }


  public static void quickHull(ArrayList<Point> p, int npoints, Point p1, Point p2, int side){

    int ind = -1;
    int max_dist = 0;

    for(int i = 0; i < npoints; i++){

      int temp = lineDist(p1, p2, p.get(i));
      if(findSide(p1, p2, p.get(i)) == side  && temp > max_dist){

        ind = i;
        max_dist = temp;
        
      }
      
    }

    if (ind == -1) {
      
      finalsolutions.add(p1);
      finalsolutions.add(p2);
            
      return;
    }

    // Recur for the two parts divided by a[ind]
    quickHull(p, npoints, p.get(ind), p1, -findSide(p.get(ind), p1, p2) );
    quickHull(p, npoints, p.get(ind), p2, -findSide(p.get(ind), p2, p1));
    
  }



  public static int lineDist(Point p1, Point p2, Point p){

    return Math.abs((p.GetY() - p1.GetY()) * (p2.GetX() - p1.GetX())
    - (p2.GetY() - p1.GetY()) * (p.GetX() - p1.GetX()));
    
  }

 

  public static int findSide(Point p1, Point p2, Point p){

    int val = (p.GetY() - p1.GetY()) * (p2.GetX() - p1.GetX())
    - (p2.GetY() - p1.GetY()) * (p.GetX() - p1.GetX());

    if (val > 0) {
            return 1;
        }
        if (val < 0) {
            return -1;
        }
        return 0;
    
  }
}



  
   