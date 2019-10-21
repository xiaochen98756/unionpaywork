package Leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * j771_JewelsandStones class
 *
 * @author wangxiaochen
 * @date 2019/10/21
 */
public class j771_JewelsandStones {

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA","aAAbbbb"));
        System.out.println(numJewelsInStones("z","ZZ"));
    }

    public static int numJewelsInStones(String J, String S) {
        if(S.length()==0){
            return 0;
        }
        int count=0;
        Character character;
        Map<Character,Integer> smap=new HashMap<>();
        for(int i=0;i<S.length();i++){
            character=S.charAt(i);

            if(smap.get(character)==null){
                smap.put(character,1);
            }
            else {
                smap.put(character,smap.get(character)+1);
            }
        }
        for (Character c:J.toCharArray()) {
            count+=smap.get(c)==null?0:smap.get(c);
        }
        return count;
    }
    /*
    You're given strings J representing the types of stones that are jewels,
    and S representing the stones you have.  Each character in S is a type of stone you have. 
     You want to know how many of the stones you have are also jewels.
    The letters in J are guaranteed distinct, and all characters in J and S are letters.
    Letters are case sensitive, so "a" is considered a different type of stone from "A".

    Example 1:

    Input: J = "aA", S = "aAAbbbb"
    Output: 3
    Example 2:

    Input: J = "z", S = "ZZ"
    Output: 0
    Note:

    S and J will consist of letters and have length at most 50.
    The characters in J are distinct.
     */
}
