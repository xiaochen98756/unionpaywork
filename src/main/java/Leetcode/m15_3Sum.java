package Leetcode;

import java.util.*;

public class m15_3Sum {

    public static void main(String[] args) {
        int[] nums=new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists=threeSum(nums);
        System.out.println(lists.toArray().toString());
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(nums.length<=2||nums==null){return res;}
        int length=nums.length;
        int base;
        int left;
        int right;
        int i=0;
        Arrays.sort(nums);
        //[-1,-1,0,1,2,3,5]
        while (i<length-2){
            base=nums[i];
            left=i+1;
            right=length-1;
            while (left<right){
                if(base+nums[left]+nums[right]==0){
                    List<Integer> list=new ArrayList<>();
                    list.add(base);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left=moveright(nums,left+1);
                    right=moveleft(nums,right-1);
                }else if(base+nums[left]+nums[right]>0){
                    right=moveleft(nums,right-1);
                }else {
                    left=moveright(nums,left+1);
                }
            }
            //很关键啊，跳过重复的base
            i=moveright(nums,i+1);
        }

        return  res;
    }

    public static int moveleft(int[] nums,int right){
        while (right==nums.length-1 || (right>=0 && nums[right]==nums[right+1])){
            right--;
        }
        return right;
    }

    public static  int moveright(int[] nums,int left){
        while (left==0 || (left<nums.length) && nums[left]==nums[left-1]){
            left++;
        }
        return left;
    }
}
