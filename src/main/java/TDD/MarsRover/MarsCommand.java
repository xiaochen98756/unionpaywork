package TDD.MarsRover;


import java.util.Arrays;
import java.util.List;

public class MarsCommand {
    private String command;
    private static List<Character> commandList;

    static {
        commandList = Arrays.asList('b', 'f', 'l', 'r');
    }

    public MarsCommand(String command) throws Exception {
        for (int i=0;i<command.length();i++) {
            if(!commandList.contains(command.charAt(i))){
                throw new Exception("无效指令，请重新输入指令集！");
            }
        }
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
