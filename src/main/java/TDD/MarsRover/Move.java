package TDD.MarsRover;

class ExecCommand{
    private  MarsCar turn(MarsCar marsCar) throws Exception{return null;}
    private MarsCar move(MarsCar marsCar) throws Exception{return null;}
}
class TurnLeft extends ExecCommand{

    MarsCar turn(MarsCar marsCar) throws Exception {
        String curdirection=Direction.leftDirectionCommandMap.get(marsCar.getCurDirection());
        marsCar.setDirection(new Direction(curdirection));
        return marsCar;
    }

}
class TurnRight extends ExecCommand{

    MarsCar turn( MarsCar marsCar) throws Exception {
        String curdirection=Direction.rightDirectionCommandMap.get(marsCar.getCurDirection());
        marsCar.setDirection(new Direction(curdirection));
        return marsCar;
    }
}
class MoveBack extends ExecCommand{

    MarsCar move(MarsCar marsCar) {
        int curx=marsCar.getX();
        int cury=marsCar.getY();
        int desxory;
        switch (marsCar.getCurDirection()){
            case "N":
                marsCar.getPoint().setY(cury-1<0?marsCar.getWidth()-1:cury-1);
                break;
            case "S":
                marsCar.getPoint().setY(cury+1==marsCar.getWidth()?0:cury+1);
                break;
            case "E":
                marsCar.getPoint().setX(curx-1<0?marsCar.getLength()-1:curx-1);
                break;
            case "W":
                marsCar.getPoint().setX(curx+1==marsCar.getLength()?0:curx+1);
                break;
            default:
                break;
        }
        return marsCar;
    }
}
class MoveForward extends ExecCommand{

    MarsCar move(MarsCar marsCar) {
        int curx=marsCar.getX();
        int cury=marsCar.getY();
        int desxory;
        switch (marsCar.getCurDirection()){
            case "N":
                desxory=cury+1;
                marsCar.getPoint().setY(desxory==marsCar.getWidth()?0:desxory);
                break;
            case "S":
                desxory=cury-1;
                marsCar.getPoint().setY(desxory<0?marsCar.getWidth()-1:desxory);
                break;
            case "E":
                desxory=curx+1;
                marsCar.getPoint().setX(desxory==marsCar.getLength()?0:desxory);
                break;
            case "W":
                desxory=curx-1;
                marsCar.getPoint().setX(desxory<0?marsCar.getLength()-1:desxory);
                break;
            default:
                break;
        }

        return marsCar;
    }
}