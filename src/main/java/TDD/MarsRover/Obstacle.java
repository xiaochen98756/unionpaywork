package TDD.MarsRover;

import java.util.ArrayList;
import java.util.List;

public class Obstacle {
   private int x;
   private int y;
   private Point point;

    private static List<Point> obstacleList=new ArrayList<>();

   static {
       obstacleList.add(new Point(0,1));
       obstacleList.add(new Point(2,0));
   }
    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
        this.point = new Point(x,y);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
