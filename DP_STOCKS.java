import java.util.*;
public class DP_STOCKS {

    //can buy and sell stocks any number of times
    public static int stocks_II_1(int[]arr,int n,int index,int buy){
        if(index==n)return 0;
        int opt1=0;
        int opt2=0;
        if(buy==0){
            opt1=0+stocks_II_1(arr, n, index+1, 0);
            opt2=-arr[index]+stocks_II_1(arr, n, index+1, 1);
        }
        if(buy==1){
            opt1=0+stocks_II_1(arr, n, index+1, 1);
            opt2=arr[index]+stocks_II_1(arr, n, index+1, 0);
        }
        return Math.max(opt1,opt2);
    }
    public static int stocks_II_2(int[]arr,int n,int index,int buy,int[][]dp){
        if(index==n)return 0;
        if(dp[index][buy]!=-1)return dp[index][buy];
        int opt1=0;
        int opt2=0;
        if(buy==0){
            opt1=0+stocks_II_2(arr, n, index+1, 0,dp);
            opt2=-arr[index]+stocks_II_2(arr, n, index+1, 1,dp);
        }
        if(buy==1){
            opt1=0+stocks_II_2(arr, n, index+1, 1,dp);
            opt2=arr[index]+stocks_II_2(arr, n, index+1, 0,dp);
        }
        return dp[index][buy]=Math.max(opt1,opt2);
    }
    public static int stocks_II_3(int[]arr,int n,int[][]dp){
        for(int buy=0;buy<=1;buy++)dp[n][buy]=0;
        int opt1=0;
        int opt2=0;
        for(int index=n-1;index>=0;index--){
           for(int buy=0;buy<=1;buy++){
                if(buy==0){
                    opt1=dp[index+1][0];
                    opt2=-arr[index]+dp[index+1][1];
                }
                else{
                    opt1=dp[index+1][1];
                    opt2=arr[index]+dp[index+1][0];
                }
                dp[index][buy]= Math.max(opt1,opt2);
            }
       }
       return dp[0][0];
       
    }
    public static int stocks_II_4(int[]arr,int n){
        int[]next=new int[2];
        int[]curr=new int[2];
        next[0]=next[1]=0;
       int opt1=0;
        int opt2=0;
        for(int index=n-1;index>=0;index--){
           for(int buy=0;buy<=1;buy++){
                if(buy==0){
                    opt1=next[0];
                    opt2=-arr[index]+next[1];
                }
                else{
                    opt1=next[1];
                    opt2=arr[index]+next[0];
                }
               curr[buy]= Math.max(opt1,opt2);
            }
            curr=next;
       }
       return curr[0];
       
    }
    
    // can only have two transaction at most
    public static int stocks_III_1(int[]arr,int n,int index,int buy,int trans,int[][][]dp){
        if(trans==0)return 0;
        if(index==n)return 0;
        if(dp[index][buy][trans]!=-1)return dp[index][buy][trans];
        int opt1=0;
        int opt2=0;
        if(buy==0){
            opt1=0+stocks_III_1(arr, n, index+1, 0,trans,dp);
            opt2=-arr[index]+stocks_III_1(arr, n, index+1, 1,trans,dp);
        }
        if(buy==1){
            opt1=0+stocks_III_1(arr, n, index+1, 1,trans,dp);
            opt2=arr[index]+stocks_III_1(arr, n, index+1, 0,trans-1,dp);
        }
        return dp[index][buy][trans]=Math.max(opt1,opt2);

    }
    public static int stocks_III_2(int[]arr,int n,int[][][]dp){
        for(int index=0;index<=n;index++){
            for(int buy=0;buy<=1;buy++){
                dp[index][buy][0]=0;
            }
        }
        for(int buy=0;buy<=1;buy++){
            for(int trans=0;trans<=2;trans++){
                dp[n][buy][trans]=0;
            }
        }
        
        for(int index=n-1;index>=0;index--){
            for(int buy=0;buy<=1;buy++){
                for(int trans=1;trans<=2;trans++){
                    int opt1=0;
                    int opt2=0;
                    if(buy==0){
                        opt1=0+dp[index+1][0][trans];
                        opt2=-arr[index]+dp[index+1][1][trans];
                    }
                     else{
                        opt1=0+dp[index+1][1][trans];
                        opt2=arr[index]+dp[index+1][0][trans-1];
                    }
                    dp[index][buy][trans]=Math.max(opt1,opt2);
                }
            }
        }
        return dp[0][0][2];
    }
    
    //can have k number of transaction
    public static int stocks_IV_1(int[]arr,int n,int[][][]dp,int k){
        for(int index=0;index<=n;index++){
            for(int buy=0;buy<=1;buy++){
                dp[index][buy][0]=0;
            }
        }
        for(int buy=0;buy<=1;buy++){
            for(int trans=0;trans<=k;trans++){
                dp[n][buy][trans]=0;
            }
        }
        
        for(int index=n-1;index>=0;index--){
            for(int buy=0;buy<=1;buy++){
                for(int trans=1;trans<=k;trans++){
                    int opt1=0;
                    int opt2=0;
                    if(buy==0){
                        opt1=0+dp[index+1][0][trans];
                        opt2=-arr[index]+dp[index+1][1][trans];
                    }
                     else{
                        opt1=0+dp[index+1][1][trans];
                        opt2=arr[index]+dp[index+1][0][trans-1];
                    }
                    dp[index][buy][trans]=Math.max(opt1,opt2);
                }
            }
        }
        return dp[0][0][k];
    }
    
    
    public static void main(String[] args) {
        int[]arr={3,2,6,5,0,3};
        int n=arr.length;
        int k=2;
        int transactions=k;
        int[][][]dp=new int[n+1][2][transactions+1];
        // for(int[][]row:dp){
        //     for(int[]col:row){
        //         Arrays.fill(col,-1);
        //     }
        // }
        System.out.println(stocks_IV_1(arr,n,dp,k));

    }
}
