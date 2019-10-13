package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class j1_twosum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        int[] result =new int[2];
        for(int i=0;i<nums.length;i++){
            int key=target-nums[i];
            if(map.containsKey(key)){
                result[0]=i;
                result[1]=map.get(key);
                Arrays.sort(result);
                return result;
            }
            map.put(nums[i],i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] a={-1,-2,-3,-4,-5};
        System.out.println(Arrays.toString(twoSum(a, -8)));
    }
}
