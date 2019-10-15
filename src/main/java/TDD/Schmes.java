package TDD;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
         switch (type) {
             case "bool":
                 return "true".equalsIgnoreCase(strVal);
             case "int":
                 return Integer.parseInt(strVal);
             case "str":
                 return String.valueOf(strVal);
             case "strlist":
                 List<String> asList;
                 try {
                     asList = Arrays.asList(strVal.split(","));
                 } catch (Exception e) {
                     return String.valueOf(Boolean.FALSE);
                 }
                 return Arrays.toString(asList.toArray());
             case "map":
                 JSONObject object = JSONObject.parseObject(strVal);
                 Map<String ,Object> map=new HashMap<>();
                 for (String key:object.keySet()) {
                     map.put(key,object.get(key));
                 }
                 return map.toString();
             default:
                 return strVal;
         }


    }
}
