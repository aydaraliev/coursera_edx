import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
	
	public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start with prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int n = getNumPoints(s);
        double av_length = getAverageLength(s);
        double lar_side = getLargestSide(s);
        double lar_x = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Num of points = " + n);
        System.out.println("Average length = " + av_length);
        System.out.println("Largest Side = " + lar_side);
        System.out.println("Largest x = " + lar_x);
    }

	
    public int getNumPoints (Shape s) {
        // Put code here
        int i = 0;
        for (Point p : s.getPoints()){
 
            i++;
        }
        return i;
    }

    public double getAverageLength(Shape s) {
        // Put code here
    	double perim = getPerimeter(s);
    	double num_points = getNumPoints(s);
    	double average = perim/num_points;
        return average;
    }

    public double getLargestSide(Shape s) {
    	// Start with totalPerim = 0
        double sidePerim = 0.0;
        // Start with prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > sidePerim) {
            	sidePerim = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return sidePerim;
    }

    public double getLargestX(Shape s) {
        // Put code here
    	double x = 0.0;
    	for (Point p : s.getPoints()) {
    		if (p.getX() > x) {
    			x = p.getX();
    		}
    	}
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
    	double perim = 0.0;
    	DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > perim) {
            	perim = getPerimeter(s);
            }
        }
        return perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        double perim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) > perim) {
            	perim = getPerimeter(s);
            	temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
    	
    	double perim = getLargestPerimeterMultipleFiles();
    	
        System.out.println(perim);
    }

    public void testFileWithLargestPerimeter() {
    	
    	String s = getFileWithLargestPerimeter();
    	
    	System.out.println(s);
    	
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        int z = getNumPoints(triangle);
        System.out.println("perimeter = "+peri);
        System.out.println(z);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
