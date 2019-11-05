package Leetcode;

/*
*Input: "Let's take LeetCode contest"
 Output: "s'teL ekat edoCteeL tsetnoc"
* */
public class j557_ReverseWordinaStringIII {


    private static String reverseSingleWords(String s) {
        if(s.length()==1){
            return s;
        }
        StringBuilder tmp =new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            tmp.append(s.charAt(i));
        }
        return tmp.toString();
    }

    private static String reverseWords(String s) {
        if(s.length()==0){
            return s;
        }
        String[] strings=s.split(" ");
        StringBuilder result=new StringBuilder();

        for (String s1:strings) {
          result.append(reverseSingleWords(s1)).append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        String result=reverseWords("Let's take LeetCode contest");
        System.out.println(result);
    }
}
