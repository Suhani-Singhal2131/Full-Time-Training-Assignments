import java.util.*;
public class DP_2D{
    public static int count_unique_ways_1(int i,int j){
        if(i==0 && j==0)return 1;
        if(i<0 ||j<0)return 0;
        int up=count_unique_ways_1( i-1, j);
        int left=count_unique_ways_1( i, j-1);
        return up+left;
    }
    public static int count_unique_ways_2(int i,int j,int[][]dp){
        if(i==0 && j==0)return 1;
        if(i<0 ||j<0)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int up=count_unique_ways_2( i-1, j,dp);
        int left=count_unique_ways_2( i, j-1,dp);
        return dp[i][j]=up+left;
    }
    public static int count_unique_ways_3(int[][]dp){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0 && j==0)dp[0][0]=1;
                else{
                    int up=0;
                    int left=0;
                    if(i>=1)up=dp[i-1][j];
                    if(j>=1)left=dp[i][j-1];
                    dp[i][j]=up+left;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public static int count_unique_ways_4(int[][]dp,int n,int m){
        int[]prev=new int[m];
        for(int i=0;i<n;i++){
            int[]curr=new int[m];
            for(int j=0;j<m;j++){
                if(i==0 && j==0)curr[0]=1;
                else{
                    int up=0;
                    int left=0;
                    if(i>=1)up=prev[j];
                    if(j>=1)left=curr[j-1];
                    curr[j]=up+left;
                }
            }
            prev=curr;
        }
         
       return prev[m-1];
    }


    public static int count_unique_ways_obstacle_1(int i,int j,int[][]grid){
        if(i>=0 && j>=0 && grid[i][j]==-1)return 0;
        if(i==0 && j==0)return 1;
        if(i<0 ||j<0)return 0;
        int up=count_unique_ways_obstacle_1( i-1, j,grid);
        int left=count_unique_ways_obstacle_1( i, j-1,grid);
        return up+left;
    }
    public static int count_unique_ways_obstacle_2(int i,int j,int[][]dp,int[][]grid){
        if(i>=0 && j>=0 && grid[i][j]==-1)return 0;
        if(i==0 && j==0)return 1;
        if(i<0 ||j<0)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int up=count_unique_ways_obstacle_2( i-1, j,dp,grid);
        int left=count_unique_ways_obstacle_2( i, j-1,dp,grid);
        return dp[i][j]=up+left;
    }
    public static int count_unique_ways_obstacle_3(int[][]dp,int[][]grid){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0 && j==0)dp[0][0]=1;
                else if(i>=0 && j>=0 && grid[i][j]==-1){
                    dp[i][j]=0;
                }
                else{
                    int up=0;
                    int left=0;
                    if(i>=1)up=dp[i-1][j];
                    if(j>=1)left=dp[i][j-1];
                    dp[i][j]=up+left;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    

    public static int min_path_sum_1(int[][]grid,int n,int m,int i,int j){
        if(i==0 && j==0)return grid[0][0];
        if(i<0 ||j<0)return (int)(10e+7);
        int up=grid[i][j]+min_path_sum_1(grid, n, m, i-1, j);
        int left=grid[i][j]+min_path_sum_1(grid, n, m, i, j-1);
        return Math.min(up,left);
    }
    public static int min_path_sum_2(int[][]grid,int n,int m,int i,int j,int[][]dp){
        if(i==0 && j==0)return grid[0][0];
        if(i<0 ||j<0)return (int)(10e+7);
        if(dp[i][j]!=-1)return dp[i][j];
        int up=grid[i][j]+min_path_sum_2(grid, n, m, i-1, j,dp);
        int left=grid[i][j]+min_path_sum_2(grid, n, m, i, j-1,dp);
        return dp[i][j]=Math.min(up,left);
    }
    public static int min_path_sum_3(int[][]grid,int n,int m,int[][]dp){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0)dp[i][j]=grid[i][j];
                else{
                    int up=(int)(10e+7);
                    int left=(int)(10e+7);
                    if(i>=1)up=grid[i][j]+dp[i-1][ j];
                    if(j>=1)left=grid[i][j]+dp[i][j-1];
                    dp[i][j]=Math.min(up,left);
                }
               
            }
        }
        return dp[n-1][m-1];
        
    }
    public static int min_path_sum_4(int[][]grid,int n,int m){
        int[]prev=new int[m];
        for(int i=0;i<n;i++){
            int[]curr=new int[m];
            for(int j=0;j<m;j++){
                if(i==0 && j==0)curr[j]=grid[i][j];
                else{
                    int up=(int)(10e+7);
                    int left=(int)(10e+7);
                    if(i>=1)up=grid[i][j]+prev[ j];
                    if(j>=1)left=grid[i][j]+curr[j-1];
                    curr[j]=Math.min(up,left);
                }   
            }
            prev=curr;
        }
        return prev[m-1];
        
    }
    
    //bottom-up
    public static int triangle_min_path_sum_1(int[][]grid,int i,int j){
        if(i==0 && j==0)return grid[0][0];
        if(i<0||j<0)return (int)(10e+7);
        if(j>i)return (int)(10e+7);

        int up=grid[i][j]+triangle_min_path_sum_1(grid,i-1,j);
        int left_diagonal=grid[i][j]+triangle_min_path_sum_1(grid, i-1, j-1);
        return Math.min(up,left_diagonal);
    }
    //top down
    public static int triangle_min_path_sum_2(int[][]grid,int n,int i,int j){
        if(i==n-1)return grid[i][j];

        int down=grid[i][j]+triangle_min_path_sum_2(grid,n,i+1,j);
        int right_diagonal=grid[i][j]+triangle_min_path_sum_2(grid,n, i+1, j+1);
        return Math.min(down,right_diagonal);
    }
    public static int triangle_min_path_sum_3(int[][]grid,int n,int i,int j,int[][]dp){
        if(i==n-1)return grid[i][j];
        if(dp[i][j]!=-1)return dp[i][j];
        int down=grid[i][j]+triangle_min_path_sum_3(grid,n,i+1,j,dp);
        int right_diagonal=grid[i][j]+triangle_min_path_sum_3(grid,n, i+1, j+1,dp);
        return dp[i][j]=Math.min(down,right_diagonal);
    }
    public static int triangle_min_path_sum_4(int[][]grid,int n,int[][]dp){
        for(int j=0;j<n;j++){
            dp[n-1][j]=grid[n-1][j];
        }
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                int down=grid[i][j]+dp[i+1][j];
                int right_diagonal=grid[i][j]+dp[i+1][j+1];
                dp[i][j]=Math.min(down,right_diagonal);
            }
        }
        return dp[0][0];
        
    }
    public static int triangle_min_path_sum_5(int[][]grid,int n){
        int[]next=new int[n];
        for(int j=0;j<n;j++){
            next[j]=grid[n-1][j];
        }
        for(int i=n-2;i>=0;i--){
            int[]curr=new int[i+1];
            for(int j=i;j>=0;j--){
                int down=grid[i][j]+next[j];
                int right_diagonal=grid[i][j]+next[j+1];
                curr[j]=Math.min(down,right_diagonal);
            }
            next=curr;
        }
        return next[0];
        
    }
    
    public static int falling_path_sum_1(int[][]grid,int n,int i,int j){
        if(j<0 ||j>=n)return (int)(-(10e+7));
        if(i==n-1)return grid[n-1][j];
        

        int down=grid[i][j]+falling_path_sum_1(grid, n, i+1, j);
        int left_diagonal=grid[i][j]+falling_path_sum_1(grid, n, i+1, j-1);
        int right_diagonal=grid[i][j]+falling_path_sum_1(grid, n, i+1, j+1);
        return Math.max(down,Math.max(left_diagonal,right_diagonal));

    }
    public static int falling_path_sum_2(int[][]grid,int n,int i,int j,int[][]dp){
        if(j<0 ||j>=n)return (int)(-(10e+7));
        if(i==n-1)return grid[n-1][j];
        if(dp[i][j]!=-1)return dp[i][j];

        int down=grid[i][j]+falling_path_sum_2(grid, n, i+1, j,dp);
        int left_diagonal=grid[i][j]+falling_path_sum_2(grid, n, i+1, j-1,dp);
        int right_diagonal=grid[i][j]+falling_path_sum_2(grid, n, i+1, j+1,dp);
        return dp[i][j]=Math.max(down,Math.max(left_diagonal,right_diagonal));

    }
    public static int falling_path_sum_3(int[][]grid,int n,int[][]dp,int col){
        for(int j=0;j<n;j++){
            dp[n-1][j]=grid[n-1][j];
        } 
        
        for(int i=n-2;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                int left_diagonal=Integer.MIN_VALUE;
                int right_diagonal=Integer.MIN_VALUE;
                int down=grid[i][j]+dp[i+1][j];
                if(j>0)left_diagonal=grid[i][j]+dp[i+1][j-1];
                if(j<n-1)right_diagonal=grid[i][j]+dp[i+1][j+1];
                dp[i][j]= Math.max(down,Math.max(left_diagonal,right_diagonal));
            }
        }
       return dp[0][col];
    }
    public static int falling_path_sum_4(int[][]grid,int n,int col){
        int[]next=new int[n];
        for(int j=0;j<n;j++){
            next[j]=grid[n-1][j];
        } 
        
        for(int i=n-2;i>=0;i--){
            int[]curr=new int[n];
            for(int j=n-1;j>=0;j--){
                int left_diagonal=Integer.MIN_VALUE;
                int right_diagonal=Integer.MIN_VALUE;
                int down=grid[i][j]+next[j];
                if(j>0)left_diagonal=grid[i][j]+next[j-1];
                if(j<n-1)right_diagonal=grid[i][j]+next[j+1];
                curr[j]= Math.max(down,Math.max(left_diagonal,right_diagonal));
            }
            next=curr;
        }
       return next[col];
    }
    

    public static int cherry_pickup_1(int[][]grid,int n,int m,int row,int col1,int col2){
        if(col1<0||col1>=m||col2<0||col2>=m )return (int)(-10e+7);
        if(row==n-1){
            if(col1==col2)return grid[row][col1];
            return grid[row][col1]+grid[row][col2];
        }
        int maxi=0;
        for(int dcol1=-1;dcol1<=1;dcol1++){
            for(int dcol2=-1;dcol2<=1;dcol2++){
                if(col1==col2){
                    maxi=Math.max(maxi,grid[row][col1]+cherry_pickup_1(grid, n, m, row+1, col1+dcol1, col2+dcol2));
                }
                else{
                    maxi=Math.max(maxi,grid[row][col1]+grid[row][col2]+cherry_pickup_1(grid, n, m, row+1, col1+dcol1, col2+dcol2));
                }
            }
        }
        return maxi;
    }
    public static int cherry_pickup_2(int[][]grid,int n,int m,int row,int col1,int col2,int[][][]dp){
        if(col1<0||col1>=m||col2<0||col2>=m )return (int)(-10e+7);
        if(row==n-1){
            if(col1==col2)return grid[row][col1];
            return grid[row][col1]+grid[row][col2];
        }
        if(dp[row][col1][col2]!=-1)return dp[row][col1][col2];
        int maxi=0;
        for(int dcol1=-1;dcol1<=1;dcol1++){
            for(int dcol2=-1;dcol2<=1;dcol2++){
                if(col1==col2){
                    maxi=Math.max(maxi,grid[row][col1]+cherry_pickup_2(grid, n, m, row+1, col1+dcol1, col2+dcol2,dp));
                }
                else{
                    maxi=Math.max(maxi,grid[row][col1]+grid[row][col2]+cherry_pickup_2(grid, n, m, row+1, col1+dcol1, col2+dcol2,dp));
                }
            }
        }
        return dp[row][col1][col2]=maxi;
    }
    public static void main(String[] args) {
        int[][]grid={{2,3,1,2},{3,4,2,2},{5,6,3,5}};
        int n=grid.length;
        int m=grid[0].length;
        int[][][]dp=new int[n][m][m];
        for(int[][]row:dp){
            for(int[]r:row){
                Arrays.fill(r,-1);
            }
        }
        // int min=(int)(10e+7);
        // for(int j=0;j<n;j++){
        //     min=Math.min(min,triangle_min_path_sum_1(grid,n-1, j));
        // }
        
        // int max=Integer.MIN_VALUE;
        // for(int j=0;j<n;j++){
        //     max=Math.max(max,falling_path_sum_4(grid,n,j));
        // }
        System.out.println(cherry_pickup_2(grid, n, m, 0, 0, m-1,dp));
    }
}
