package Leetcode;


import java.util.List;

/**
 * j709_ToLowerCase class
 *
 * @author wangxiaochen
 * @date 2019/10/21
 */
public class j709_ToLowerCase {
    public static void main(String[] args) {
        System.out.println(toLowerCase("aBV"));
    }

    public static String toLowerCase(String str) {
        if(str.equals("")||str.length()==0){
            return "";
        }
        char[] characters=str.toCharArray();
        for (int i=0;i<characters.length;i++) {
            if(characters[i]>='A' &&characters[i]<='Z'){
                characters[i]+=32;
            }
        }
        return String.valueOf(characters);
    }
}
