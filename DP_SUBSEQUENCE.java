import java.util.*;

public class DP_SUBSEQUENCE {
    public static boolean subset_sum_1(int[]arr,int index,int target){
        if(target==0)return true;
        if(index==0)return(arr[0]==target);

        boolean nottake=subset_sum_1(arr, index-1, target);
        boolean take=false;
        if(arr[index]<=target){
            take=subset_sum_1(arr, index-1, target-arr[index]);
        }
        return take||nottake;

    }
    public static int subset_sum_2(int[]arr,int index,int target,int[][]dp){
        if(target==0)return 1;
        if(index==0)return((arr[0]==target)?1:0);

        int nottake=subset_sum_2(arr, index-1, target,dp);
        int  take=0;
        if(arr[index]<=target){
            take=subset_sum_2(arr, index-1, target-arr[index],dp);
        }
        if(take==1||nottake==1)return 1;
        return 0;

    }
    public static int subset_sum_3(int[]arr,int[][]dp,int k){
        for(int index=0;index<dp.length;index++){
            dp[index][0]=1;
        }
        dp[0][arr[0]]=1;
        for(int index=1;index<dp.length;index++){
            for(int target=1;target<=k;target++){
                int nottake=dp[index-1][target];
                int take=0;
                if(arr[index]<=target){
                    take=dp[index-1][target-arr[index]];
                }
                if(take==1||nottake==1)dp[index][target]= 1;
                else dp[index][target]=0;
            }
        }
        
      return dp[dp.length-1][k];
    }
    public static int subset_sum_4(int[]arr,int k){
       int[]prev=new int[k+1];
       int[]curr=new int[k+1];
       prev[0]=curr[0]=1;
       prev[arr[0]]=1;
       
        for(int index=1;index<arr.length;index++){
            for(int target=1;target<=k;target++){
                int nottake=prev[target];
                int take=0;
                if(arr[index]<=target){
                    take=prev[target-arr[index]];
                }
                if(take==1||nottake==1)curr[target]= 1;
            }
            prev=curr;
        }
        
      return prev[k];
    }
    
    //Partion equal sunset sum->check if we can partition an arr into two subsets such that both the partion have equal sum
    //this question is similar to above problem,catch here is target is S/2 where S is the total sum

    //count subsets having the target sum
    public static int count_subsets_1(int[]arr,int target,int index){
        if(target==0)return 1;
        if(index==0){
            if(target==arr[0])return 1;
            return 0;
        }
        int notpick=count_subsets_1(arr, target, index-1);
        int pick=0;
        if(target>=arr[index]){
            pick=count_subsets_1(arr, target-arr[index], index-1);
        }
        return pick+notpick;
    }
    public static int count_subsets_2(int[]arr,int target,int index,int[][]dp){
        if(target==0)return 1;
        if(index==0){
            if(target==arr[0])return 1;
            return 0;
        }
        if(dp[index][target]!=-1)return dp[index][target];
        int notpick=count_subsets_2(arr, target, index-1,dp);
        int pick=0;
        if(target>=arr[index]){
            pick=count_subsets_2(arr, target-arr[index], index-1,dp);
        }
        return dp[index][target]=pick+notpick;
    }
    public static int count_subsets_3(int[]arr,int[][]dp,int k){
        for(int index=0;index<dp.length;index++){
            dp[index][0]=1;
        }
        dp[0][arr[0]]=1;
        for(int index=1;index<dp.length;index++){
            for(int target=1;target<=k;target++){
                int notpick=dp[index-1][target];
                int pick=0;
                if(target>=arr[index]){
                    pick=dp[index-1][target-arr[index]];
                }
                dp[index][target]=pick+notpick;
            }
        }
        return dp[dp.length-1][k];
        
    }
    public static int count_subsets_4(int[]arr,int k){
        int[]prev=new int[k+1];
        int[]curr=new int[k+1];
        prev[0]=curr[0]=1;
        prev[arr[0]]=1;
        for(int index=1;index<arr.length;index++){
            for(int target=1;target<=k;target++){
                int notpick=prev[target];
                int pick=0;
                if(target>=arr[index]){
                    pick=prev[target-arr[index]];
                }
                curr[target]=pick+notpick;
            }
            prev=curr;
        }
        return prev[k];
        
    }
    
    
    // if we have the problem of counting subsets with target sum and it also contains 0 then we have to slightly change the code
    /*the base condition will be{
      if(index==0){
         if(sum==0 && arr[0]==0)return 2;
         if(sum==0 || sum==arr[0])return 1;
         return 0;
      }

    //}*/

    //COUNT PARTITIONS WITH GIVEN DIFFERENCE
    //It is similar to count  subsets having target sum .the change is that
    // here the target is target=(Totalsum-difference)/2;

    //0/1 knapsack problem
    public static int bounded_knapsack_1(int[]weight,int[]profit,int capacity,int index){
        if(index==0){
            if(weight[0]<=capacity)return profit[0];
            return 0;
        }

        int nottake=bounded_knapsack_1(weight, profit, capacity, index-1);
        int take=0;
        if(weight[index]<=capacity){
            take=profit[index]+bounded_knapsack_1(weight, profit, capacity-weight[index], index-1);
        }
        return Math.max(take,nottake);
    }
    public static int bounded_knapsack_2(int[]weight,int[]profit,int capacity,int index,int[][]dp){
        if(index==0){
            if(weight[0]<=capacity)return profit[0];
            return 0;
        }
        if(dp[index][capacity]!=-1)return dp[index][capacity];
        int nottake=bounded_knapsack_2(weight, profit, capacity, index-1,dp);
        int take=0;
        if(weight[index]<=capacity){
            take=profit[index]+bounded_knapsack_2(weight, profit, capacity-weight[index], index-1,dp);
        }
        return dp[index][capacity]=Math.max(take,nottake);
    }
    public static int bounded_knapsack_3(int[]weight,int[]profit,int[][]dp,int k){
        for(int capacity=0;capacity<=weight[0];capacity++){
            dp[0][capacity]=profit[0];
        }
        
        for(int index=1;index<weight.length;index++){
            for(int capacity=1;capacity<=k;capacity++){
                int nottake=dp[index-1][capacity];
                int take=0;
                if(weight[index]<=capacity){
                    take=profit[index]+dp[index-1][capacity-weight[index]];
                }
                dp[index][capacity]=Math.max(take,nottake);
            }
        }
        return dp[weight.length-1][k];
        
    }
    public static int bounded_knapsack_4(int[]weight,int[]profit,int k){
        int[]prev=new int[k+1];
        int[]curr=new int[k+1];
        for(int capacity=0;capacity<=weight[0];capacity++){
            prev[capacity]=profit[0];
        }
        
        for(int index=1;index<weight.length;index++){
            for(int capacity=1;capacity<=k;capacity++){
                int nottake=prev[capacity];
                int take=0;
                if(weight[index]<=capacity){
                    take=profit[index]+prev[capacity-weight[index]];
                }
                curr[capacity]=Math.max(take,nottake);
            }
            prev=curr;
        }
        return prev[k];
        
    }
    
    //bounded coin change problem
    //take take a coin any number of times

    public static int unbounded_coin_change(int[]coins,int target,int index){
        if(index==0){
            if(target%coins[0]==0)return target/coins[0];
            return (int)(10e+9);
        }

        int nottake=0+unbounded_coin_change(coins, target, index-1);
        int take=(int)(10e+9);
        if(target>=coins[index]){
            take=1+unbounded_coin_change(coins, target-coins[index], index);
        }
        return Math.min(take,nottake);
    }
    public static int unbounded_coin_change_2(int[]coins,int target,int index,int[][]dp){
        if(index==0){
            if(target%coins[0]==0)return target/coins[0];
            return (int)(10e+9);
        }
        if(dp[index][target]!=-1)return dp[index][target];
        int nottake=0+unbounded_coin_change_2(coins, target, index-1,dp);
        int take=(int)(10e+9);
        if(target>=coins[index]){
            take=1+unbounded_coin_change_2(coins,target-coins[index],index,dp);
        }
        return dp[index][target]=Math.min(take,nottake);
    }
    public static int unbounded_coin_change_3(int[]coins,int k,int[][]dp){
        for(int target=0;target<=k;target++){
            if(target%coins[0]==0)dp[0][target]=target/coins[0];
        }

        for(int index=1;index<coins.length;index++){
            for(int target=1;target<=k;target++){
                int nottake=dp[index-1][target];
                int take=(int)(10e+9);
                if(target>=coins[index]){
                    take=1+dp[index][target-coins[index]];
                }
                dp[index][target]= Math.min(take,nottake);
            }
        }

       return dp[coins.length-1][k];
    }
    public static int unbounded_coin_change_4(int[]coins,int k){
        int[]prev=new int[k+1];
        int[]curr=new int[k+1];
        for(int target=0;target<=k;target++){
            if(target%coins[0]==0)prev[target]=target/coins[0];
        }

        for(int index=1;index<coins.length;index++){
            for(int target=1;target<=k;target++){
                int nottake=prev[target];
                int take=(int)(10e+9);
                if(target>=coins[index]){
                    take=1+curr[target-coins[index]];
                }
                curr[target]= Math.min(take,nottake);
            }
            prev=curr;
        }

       return prev[k];
    }
    
   //TARGET SUM- here you are given an array where you have to arrange +/- sign before each integer to get the target sum
   //this question is boiled down to the COUNT PARTITION WITH GIVEN DIFF
   //the change is that here diff==target that is given in ques
    
   //COIN CHANGE 2
   public static int coin_change_1(int[]coins,int target,int index){
    if(index==0){
        if(target%coins[0]==0)return 1;
        return 0;
    }

    int nottake=coin_change_1(coins, target, index-1);
    int take=0;
    if(target>=coins[index]){
        take=coin_change_1(coins, target-coins[index], index);
    }
    return take+nottake;
   }
   public static int coin_change_2(int[]coins,int target,int index,int[][]dp){
    if(index==0){
        if(target%coins[0]==0)return 1;
        return 0;
    }
    if(dp[index][target]!=-1)return dp[index][target];
    int nottake=coin_change_2(coins, target, index-1,dp);
    int take=0;
    if(target>=coins[index]){
        take=coin_change_2(coins, target-coins[index], index,dp);
    }
    return take+nottake;
   }
   public static int coin_change_3(int[]coins,int k,int[][]dp){
    for(int target=0;target<=k;target++){
        if(target%coins[0]==0)dp[0][target]=1;
    }
    for(int index=1;index<coins.length;index++){
        for(int target=0;target<=k;target++){
            int nottake=dp[index-1][target];
            int take=0;
            if(target>=coins[index]){
                take=dp[index][target-coins[index]];
            }
            dp[index][target]= take+nottake;
        }
    }
    return dp[coins.length-1][k];
   
   }
   public static int coin_change_4(int[]coins,int k){
    int[]prev=new int[k+1];
    int[]curr=new int[k+1];
    for(int target=0;target<=k;target++){
        if(target%coins[0]==0)prev[target]=1;
    }
    for(int index=1;index<coins.length;index++){
        for(int target=0;target<=k;target++){
            int nottake=prev[target];
            int take=0;
            if(target>=coins[index]){
                take=curr[target-coins[index]];
            }
            curr[target]= take+nottake;
        }
        prev=curr;
    }
    return prev[k];
   
   }
   
   public static int unbounded_knapsack_1(int[]weight,int[]profit,int capacity,int index){
    if(index==0){
        return (capacity/weight[0])*profit[0];
    }
    int nottake=unbounded_knapsack_1(weight, profit, capacity, index-1);
    int take=Integer.MIN_VALUE;
    if(capacity>=weight[index]){
        take=profit[index]+unbounded_knapsack_1(weight,profit,capacity-weight[index],index);
    }
    return Math.max(take,nottake);
   }
   public static int unbounded_knapsack_2(int[]weight,int[]profit,int k,int[][]dp){
    for(int capacity=0;capacity<=k;capacity++){
        dp[0][capacity]=capacity/weight[0]*profit[0];
    }
    for(int index=1;index<weight.length;index++){
        for(int capacity=0;capacity<=k;capacity++){
            int nottake=dp[index-1][capacity];
            int take=Integer.MIN_VALUE;
            if(capacity>=weight[index]){
                take=profit[index]+dp[index][capacity-weight[index]];
            }
            dp[index][capacity]= Math.max(take,nottake);
        }
    }
    return dp[weight.length-1][k];
   
   }
   public static int unbounded_knapsack_3(int[]weight,int[]profit,int k){
    int[]prev=new int[k+1];
    int[]curr=new int[k+1];
    for(int capacity=0;capacity<=k;capacity++){
        prev[capacity]=capacity/weight[0]*profit[0];
    }
    for(int index=1;index<weight.length;index++){
        for(int capacity=0;capacity<=k;capacity++){
            int nottake=prev[capacity];
            int take=Integer.MIN_VALUE;
            if(capacity>=weight[index]){
                take=profit[index]+curr[capacity-weight[index]];
            }
            curr[capacity]= Math.max(take,nottake);
        }
        prev=curr;
    }
    return prev[k];
   
   }
   
   
   public static void main(String[] args) {
        int[]arr={1,2,3};
        int n=arr.length;
        int target=4;
        int[]weight={2,4,6};
        int[]profit={5,11,13};
        int capacity=10;
        int[][]dp=new int[n][capacity+1];
        // for(int[]row:dp){
        //     Arrays.fill(row,-1);
        // }
        System.out.println(unbounded_knapsack_3(weight,profit,capacity));
        
        // for(int[]row:dp){
        //     for(int num:row){
        //         System.out.print(num+" ");
        //     }
        //     System.out.println();
        // }
       

    }
}
