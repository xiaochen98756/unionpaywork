package TDD;

import TDD.MarsRover.*;


public class MarsTest {

    public static void main(String[] args) throws Exception {
        //1.探索区域信息
        AreaInfo areaInfo=new AreaInfo(5,3);


        //2.初始化信息：火星车的降落地点（x, y）和朝向（N, S, E, W）信息
        Point point=new Point(0,0);
        Direction direction=new Direction("N");
        MarsCar marsCar =new MarsCar(areaInfo,point,direction);

        //3.移动指令：火星车可以前进（f）或后退（b）


        //4.转向指令：火星车可以左转90度（l）或右转90度（r）

        MarsCommand command=new MarsCommand("lrlr");
        String strcommand=command.getCommand();
        for(int i=0;i<strcommand.length();i++){
            System.out.println("before: "+marsCar.getDirection().getCurDirection());
            giveMeCommand(marsCar,strcommand.charAt(i));
            System.out.println("now: "+marsCar.getDirection().getCurDirection());
        }

    }
    private static void giveMeCommand(MarsCar marsCar,char command) throws Exception {
        String curdirection;
        switch (command){
            case 'l':
                  curdirection=Direction.leftDirectionCommandMap.get(
                        marsCar.getDirection().getCurDirection());
                marsCar.setDirection(new Direction(curdirection));
                break;
            case 'r':
                 curdirection=Direction.rightDirectionCommandMap.get(
                        marsCar.getDirection().getCurDirection());
                marsCar.setDirection(new Direction(curdirection));
                break;
            default:

                break;
        }

    }


}
