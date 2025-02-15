public class DP_1D {
    //frog allowed to jump either 1 or 2 step
    public static int frog_jump1(int index,int[]arr){
        if(index==0)return 0;
        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        left=frog_jump1(index-1, arr)+Math.abs(arr[index]-arr[index-1]);
        if(index>1){
            right=frog_jump1(index-2, arr)+Math.abs(arr[index]-arr[index-2]);
        }
        return Math.min(left,right);
    }
    public static int frog_jump2(int index,int[]arr,int[]dp){
        if(index==0)return 0;
        if(dp[index]!=-1)return dp[index];
        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        left=frog_jump2(index-1,arr,dp)+Math.abs(arr[index]-arr[index-1]);
        if(index>1){
            right=frog_jump2(index-2,arr,dp)+Math.abs(arr[index]-arr[index-2]);
        }
        return dp[index]=Math.min(left,right);
    }
    public static int frog_jump3(int[]arr,int[]dp){
        dp[0]=0;
        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        for(int index=1;index<arr.length;index++){
            left=dp[index-1]+Math.abs(arr[index]-arr[index-1]);
            if(index>1){
                right=dp[index-2]+Math.abs(arr[index]-arr[index-2]);
            }
            dp[index]=Math.min(left,right);
        }
        return dp[arr.length-1];
    }
    public static int frog_jump4(int[]arr){
        int prev1=0;
        int prev2=0;
        int left=Integer.MAX_VALUE;
        int right=Integer.MAX_VALUE;
        for(int i=1;i<arr.length;i++){
            left=prev1+Math.abs(arr[i]-arr[i-1]);
            if(i>1){
              right=prev2+Math.abs(arr[i]-arr[i-2]);
            }
            int curr=Math.min(left,right);
            prev2=prev1;
            prev1=curr;
       } 
       return prev1;
    }
   
    //frog allowed to jump either 1 ,2,....upto k steps
   public static int frog_jump1_1(int index,int k,int[]arr){
    if(index==0)return 0;
    int left=Integer.MAX_VALUE;
    int min=Integer.MAX_VALUE;
    for (int moves=1;moves<=k;moves++){
        if(index>=moves){
          left=frog_jump1_1(index-moves,k, arr)+Math.abs(arr[index]-arr[index-moves]);
        }
        min=Math.min(min,left);
      }
      return min;
    }
    public static int frog_jump1_2(int index,int k,int[]arr,int[]dp){
        if(index==0)return 0;
        if(dp[index]!=-1)return dp[index];
        int left=Integer.MAX_VALUE;
        int min=Integer.MAX_VALUE;
        for (int moves=1;moves<=k;moves++){
          if(index>=moves){
            left=frog_jump1_2(index-moves,k,arr,dp)+Math.abs(arr[index]-arr[index-moves]);
          }
          min=Math.min(min,left);
        }
       return dp[index]=min;
    }
    public static int frog_jump1_3(int k,int[]arr,int[]dp){
        dp[0]=0;
        int left=Integer.MAX_VALUE;
        for(int index=1;index<arr.length;index++){
            int min=Integer.MAX_VALUE;
            for (int moves=1;moves<=k;moves++){
                if(index>=moves){
                    left=dp[index-moves]+Math.abs(arr[index]-arr[index-moves]);
                }
                min=Math.min(min,left);
            }
            dp[index]=min;
        }
        return dp[arr.length-1];
    }
    
    //can pick adjacent elements and maximize sum
    public static int max_sum_subsequence_1(int index,int[]arr){
        if(index==0)return arr[0];
        int take=Integer.MIN_VALUE;
        if(index>1)take=max_sum_subsequence_1(index-2, arr)+arr[index];
        int nottake=max_sum_subsequence_1(index-1, arr);
        return Math.max(take,nottake);

    }
    public static int max_sum_subsequence_2(int index,int[]arr,int[]dp){
        if(index==0)return arr[0];
        if(dp[index]!=-1)return dp[index];
        int take=Integer.MIN_VALUE;
        if(index>1)take=max_sum_subsequence_2(index-2, arr,dp)+arr[index];
        int nottake=max_sum_subsequence_2(index-1, arr,dp);
        return dp[index]=Math.max(take,nottake);

    }
    public static int max_sum_subsequence_3(int[]arr,int[]dp){
        dp[0]=arr[0];
        int take=Integer.MIN_VALUE;
        for(int index=1;index<arr.length;index++){
            if(index>1)take=dp[index-2]+arr[index];
            int nottake=dp[index-1];
            dp[index]=Math.max(take,nottake);
        }
       return dp[arr.length-1];

    }
    public static int max_sum_subsequence_4(int[]arr){
        int prev1=arr[0];
        int prev2=0;
        int take=Integer.MIN_VALUE;
        for(int index=1;index<arr.length;index++){
            if(index>1)take=prev2+arr[index];
            int nottake=prev1;
            int curr=Math.max(take,nottake);
            prev2=prev1;
            prev1=curr;
        }
       return prev1;

    }
    
    //house robber 2 where thief cannot do theft in adjacent houses 
    //here 0th index and last index are adjacent
    //we will first find the answer by removing last element
    //and then find the answer by removing first element
    //and then return max of both

    //ninja moves
    public static int ninja_2(int day,int last_activity,int[][]task,int[][]dp){
        if(day==0){
            int maxi=0;
            for(int activity=0;activity<3;activity++){
                if(activity!=last_activity){
                    maxi=Math.max(maxi,task[0][activity]);
                }
            }
            return maxi;
        }
        if(dp[day][last_activity]!=-1)return dp[day][last_activity];
        int maxi=0;
        for(int activity=0;activity<3;activity++){
            if(activity!=last_activity){
                int points=task[day][activity]+ninja_2(day-1, activity, task,dp);
                maxi=Math.max(maxi,points);
            }
        }
        return dp[day][last_activity]=maxi;

    }
    public static int ninja_1(int day,int last_activity,int[][]task){
        if(day==0){
            int maxi=0;
            for(int activity=0;activity<3;activity++){
                if(activity!=last_activity){
                    maxi=Math.max(maxi,task[0][activity]);
                }
            }
            return maxi;
        }
        int maxi=0;
        for(int activity=0;activity<3;activity++){
            if(activity!=last_activity){
                int points=task[day][activity]+ninja_1(day-1, activity, task);
                maxi=Math.max(maxi,points);
            }
        }
        return maxi;

    }
    public static int ninja_3(int[][]task,int[][]dp){
        dp[0][0]=Math.max(task[0][1],task[0][2]);
        dp[0][1]=Math.max(task[0][0],task[0][2]);
        dp[0][2]=Math.max(task[0][1],task[0][0]);
        dp[0][3]=Math.max(task[0][1],Math.max(task[0][2],task[0][0]));
        for(int day=1;day<task.length;day++){
            for(int last_activity=0;last_activity<4;last_activity++){
                dp[day][last_activity]=0;
                for(int activity=0;activity<3;activity++){
                    if(activity!=last_activity){
                        int points=task[day][activity]+dp[day-1][activity];
                        dp[day][last_activity]=Math.max(dp[day][last_activity],points);
                    }
                }
            }
        }
        return dp[task.length-1][3];

    }
    public static int ninja_4(int[][]task){
        int[]prev=new int[4];
        prev[0]=Math.max(task[0][1],task[0][2]);
        prev[1]=Math.max(task[0][0],task[0][2]);
        prev[2]=Math.max(task[0][1],task[0][0]);
        prev[3]=Math.max(task[0][1],Math.max(task[0][2],task[0][0]));
        for(int day=1;day<task.length;day++){
            int[]curr=new int[4];
            for(int last_activity=0;last_activity<4;last_activity++){
                curr[last_activity]=0;
                for(int activity=0;activity<3;activity++){
                    if(activity!=last_activity){
                        int points=task[day][activity]+prev[activity];
                        curr[last_activity]=Math.max(curr[last_activity],points);
                    }
                }
            }
            prev=curr;
        }
        return prev[3];

    }
    
    
    public static void main(String[] args) {
        int[]arr={30,10,60,10,60,50};
        int[]dp=new int[arr.length];
        //Arrays.fill(dp,-1);
        int[][]task={{1,2,5},{3,1,1},{3,3,3}};
        int[][]dp1=new int[task.length][4];
        System.out.println(ninja_4(task));


    }
}