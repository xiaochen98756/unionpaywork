package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class j121_BestTimetoBuyandSellStock{
    //额外学习，更加巧妙啦
//    public int maxProfit2(int prices[]) {
//        int minprice = Integer.MAX_VALUE;
//        int maxprofit = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < minprice)
//                minprice = prices[i];
//            else if (prices[i] - minprice > maxprofit)
//                maxprofit = prices[i] - minprice;
//        }
//        return maxprofit;
//    }

    public static int maxProfit(int[] prices) {
        if(prices.length==0 || prices.length==1){
            return 0;
        }
        List<Integer> list=new ArrayList<Integer>();
        int max=0;
        for(int i=0;i<prices.length-1;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[j]-prices[i]>max){
                    max=prices[j]-prices[i];
                }
            }
            list.add(max);
            max=0;
        }
        //System.out.println(Arrays.toString(list.toArray()));
        return Collections.max(list);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }
}
