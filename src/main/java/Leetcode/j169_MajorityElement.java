package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * j169_MajorityElement class
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * @author wangxiaochen
 * @date 2019/10/21
 */
public class j169_MajorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map=new HashMap<>();
//        for (int num : nums) {
//            map.merge(num, 1, (a, b) -> a + b);
//        }
        for (int num : nums) {
            if (map.get(num) == null) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        TreeMap<Object, Object> treemap=new TreeMap<>();
        for (Map.Entry c:map.entrySet()) {
            treemap.put(c.getValue(),c.getKey());
        }
        return Integer.valueOf(String.valueOf(treemap.lastEntry().getValue()));

    }
}
