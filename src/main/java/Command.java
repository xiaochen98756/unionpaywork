import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class Command {
    Map<String,String> values;
    public Command(String commandline) {
        values=new HashMap<>();
        ListIterator<String> commadIterator=Arrays.asList(commandline.split("\\s+")).listIterator();
        while (commadIterator.hasNext()){
            String name=commadIterator.next().substring(1);
            if(commadIterator.hasNext()){
                String value=commadIterator.next();
                if(isValue(value)){
                    values.put(name,value);
                }
                else{
                    commadIterator.previous();
                }
            }

        }
    }
    private boolean isValue(String value){
        if(value.charAt(0)=='-'){
            if(value.length()>2){
                return true;
            }
            if(value.charAt(1)>='0'&&value.charAt(1)<='9'){
                return true;
            }
            return false;
        }
        return true;
    }

    public String getValue(String name) {
        return values.get(name);
    }
}
