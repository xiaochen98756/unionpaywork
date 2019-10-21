package Leetcode;


/**
 * LCP1_GuessNumber class
 *
 * @author wangxiaochen
 * @date 2019/10/21
 */
public class LCP1_GuessNumber {

    public static void main(String[] args) {
        System.out.println(game(new int[]{1,2,3},new int[]{1,2,3}));
    }

    public static int game(int[] guess, int[] answer) {
        int count=0;
        for(int i=0;i<3;i++){
            if(guess[i]==answer[i]){
                count++;
            }
        }
        return count;
    }

}
