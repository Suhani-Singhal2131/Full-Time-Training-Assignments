import java.util.*;
public class DP_STRINGS {
   public static int LCS_1(String s1,String s2,int ind1,int ind2){
    if(ind1==0 ||ind2==0){
        return 0;
    }
    if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
        return 1+LCS_1(s1, s2, ind1-1, ind2-1);
    }
    return 0+Math.max(LCS_1(s1, s2, ind1, ind2-1),LCS_1(s1, s2, ind1-1, ind2));
   }
   public static int LCS_2(String s1,String s2,int ind1,int ind2,int[][]dp){
    if(ind1==0 ||ind2==0){
        return 0;
    }
    if(dp[ind1][ind2]!=-1)return dp[ind1][ind2];
    if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
        return dp[ind1][ind2]=1+LCS_2(s1, s2, ind1-1, ind2-1,dp);
    }
    return dp[ind1][ind2]=0+Math.max(LCS_2(s1, s2, ind1, ind2-1,dp),LCS_2(s1, s2, ind1-1, ind2,dp));
   }
   public static int LCS_3(String s1,String s2,int[][]dp){
    for(int ind2=0;ind2<=s2.length();ind2++)dp[0][ind2]=0;
    for(int ind1=0;ind1<=s1.length();ind1++)dp[ind1][0]=0;
    
    for(int ind1=1;ind1<=s1.length();ind1++){
        for(int ind2=1;ind2<=s2.length();ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
                dp[ind1][ind2]=1+dp[ind1-1][ind2-1];
            }
            else dp[ind1][ind2]=0+Math.max(dp[ind1][ind2-1],dp[ind1-1][ind2]);
        }
    }
    return dp[s1.length()][s2.length()];
  }
   public static int LCS_4(String s1,String s2,int n,int m){
    int[]prev=new int[m+1];
    int[]curr=new int[m+1];
    for(int ind2=0;ind2<=s2.length();ind2++)prev[ind2]=0;
    
    for(int ind1=1;ind1<=s1.length();ind1++){
        for(int ind2=1;ind2<=s2.length();ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
                curr[ind2]=1+prev[ind2-1];
            }
            else curr[ind2]=0+Math.max(curr[ind2-1],prev[ind2]);
        }
        prev=curr;
    }
    return prev[s2.length()];

   
   }
   
   //printing longest common subsequence
   public static void helper(String s,String t,ArrayList<String>ans,int n,int m,int[][]dp){
    int len=dp[n][m];
    int i=n;
    int j=m;
    int index = len-1;
    StringBuilder str=new StringBuilder();
    for(int k=1; k<=len;k++){
       str.append("$");
    }
    while(i>0 && j>0){
        if(s.charAt(i-1) == t.charAt(j-1)){
            str.setCharAt(index,s.charAt(i-1)); 
            index--;
            i--;
            j--;
        }
        else if(dp[i-1][j]>dp[i][j-1]){
            i--;
        }
        else j--;
    }
    ans.add(str.toString());
}
   
   public static void L_C_substring_1(String s1,String s2,int[][]dp){
    
        for(int ind1=1;ind1<=s1.length();ind1++){
            for(int ind2=1;ind2<=s2.length();ind2++){
                if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
                    dp[ind1][ind2]=1+dp[ind1-1][ind2-1];
                }
                else dp[ind1][ind2]=0;
            }
        }
        int ans=0;
        for(int j=0;j<=s2.length();j++){
            ans=Math.max(ans,dp[s1.length()][j]);
        }
        System.out.println(ans);
   }
   public static int L_C_substring_2(String s1,String s2,int n,int m){
    int[]prev=new int[m+1];
    int[]curr=new int[m+1];
    int ans=0;
    for(int ind1=1;ind1<=n;ind1++){
        for(int ind2=1;ind2<=m;ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1)){
                int val=1+prev[ind2-1];
                curr[ind2]=val;
                ans=Math.max(ans,val);
            }
            else curr[ind2]=0;
        }
        prev=curr.clone();
    }
    return ans;
}
   
 public static int longestPalindromeSubseq(String s) {
    int n=s.length();
    StringBuilder str=new StringBuilder(s);
    str.reverse();
    String s2=str.toString();
    int[][]dp=new int[n+1][n+1];
    for(int j=0;j<=n;j++)dp[0][j]=0;
    for(int i=0;i<=n;i++)dp[i][0]=0;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
             if(s.charAt(i-1)==s2.charAt(j-1)){
                dp[i][j]=1+dp[i-1][j-1];
             }
             else {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
             }
        }
    }
    return dp[n][n];
    
}
public static void main(String[] args) {
     String s1="abcdgh";
     int n=s1.length();
     String s2="acdghr";
     int m=s2.length();
     int[][]dp=new int[n+1][m+1];

    //  for(int[]row:dp){
    //     Arrays.fill(row,-1);
    //  }

    // LCS_3(s1, s2,dp);
    //  for(int[]row:dp){
    //     for(int val: row){
    //         System.out.print(val+" ");
    //     }
    //     System.out.println();
    //  }

    // helper(s1,s2,ans,n,m,dp);
    System.out.println(L_C_substring_2(s1,s2,n,m));
    
   


   } 
}
