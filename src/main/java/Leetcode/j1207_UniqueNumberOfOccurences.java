package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class j1207_UniqueNumberOfOccurences {
    public static void main(String[] args) {

        System.out.println(uniqueOccurrences(new int[]{1,2}));




    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int a:arr) {
            if(map.get(a)!=null){
                map.put(a,map.get(a)+1);
            }
            else{
                map.put(a,1);
            }
        }
        return map.values().size()==new HashSet<Integer>(map.values()).size();
    }
}
