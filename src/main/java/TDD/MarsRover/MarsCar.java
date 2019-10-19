package TDD.MarsRover;

public class MarsCar {

    private AreaInfo areaInfo;
    private Point point;
    private Direction direction;

    public MarsCar(AreaInfo areaInfo, Point point, Direction direction) throws Exception {
        this.areaInfo = areaInfo;
        this.point = point;
        this.direction = direction;
        if(point.getX()<0||point.getY()<0||point.getX()>=areaInfo.getLength()
                ||point.getY()>=areaInfo.getWidth()){
            throw new Exception("火星车的降落地点不在指定区域内！");
        }
    }



    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfo areaInfo) {
        this.areaInfo = areaInfo;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
