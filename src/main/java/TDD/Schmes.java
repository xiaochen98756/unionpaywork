package TDD;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Schmes {
    private Map<String,String> schmes=new HashMap<String,String>();
    public Schmes(String schmeConfig) {
        Arrays.asList(schmeConfig.split(","))
                .stream()
                .forEach(flag->{
                    String[] namevalue=flag.split(":");
                    schmes.put(namevalue[0],namevalue[1]);
                });

    }

    public Object getValue(String name,String strVal){
         String type=schmes.get(name);
         switch (type){
             case "bool":
                 return "true".equalsIgnoreCase(strVal);
             case "int":
                 return Integer.parseInt(strVal);

             default:
                 return strVal;
         }


    }
}
