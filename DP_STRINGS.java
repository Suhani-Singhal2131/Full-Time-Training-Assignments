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
   public static void helper(String s,String t,int n,int m,int[][]dp){
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
    System.out.println(str.toString());
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
 
   //In finding longest palindromic subsequence we take the original string as s1 and 
   //revese of s1 as s2 then just find LCS;

   //In min insertion steps to make string palindrome
   //s1.length()-LCS(s1,rev(s1))

 //SHORTEST COMMON SUPERSEQUENCE
 public static void S_C_S(String s1,String s2){
    int n=s1.length();
    int m=s2.length();
    int[][]dp=new int[n+1][m+1];

    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                dp[i][j]=1+dp[i-1][j-1];
            }
            else{
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
    }

    int i=n;
    int j=m;
    StringBuilder s= new StringBuilder();
    while(i>0 && j>0){
        if(s1.charAt(i-1)==s2.charAt(j-1)){
            s.append(s1.charAt(i-1));
            i--;
            j--;
        }
        else if(dp[i-1][j]>dp[i][j-1]){
            s.append(s1.charAt(i-1));
            i--;
        }
        else{
            s.append(s2.charAt(j-1));
            j--;
        }
    }
    while(i>0){
        s.append(s1.charAt(i-1));
        i--; 
    }
    while(j>0){
        s.append(s2.charAt(j-1));
        j--;
    }
    System.out.println(s.reverse());
 }

 //DISTINCT SUBSEQUENCES
 public static int distinct_subsequences(String s1,String s2,int i,int j){
    if(j==0)return 1;
    if(i==0)return 0;

    if(s1.charAt(i-1)==s2.charAt(j-1)){
        return distinct_subsequences(s1, s2, i-1, j-1)+distinct_subsequences(s1, s2, i-1, j);
    }
    return distinct_subsequences(s1, s2, i-1, j);
 }
 public static int distinct_subsequences_2(String s1,String s2,int i,int j,int[][]dp){
    if(j==0)return 1;
    if(i==0)return 0;
    if(dp[i][j]!=-1)return dp[i][j];
    if(s1.charAt(i-1)==s2.charAt(j-1)){
        return dp[i][j]=distinct_subsequences(s1, s2, i-1, j-1)+distinct_subsequences(s1, s2, i-1, j);
    }
    return dp[i][j]=distinct_subsequences(s1, s2, i-1, j);
 }
 public static int distinct_subsequences_3(String s1,String s2,int n,int m,int[][]dp){
    for(int i=0;i<=n;i++)dp[i][0]=1;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
            }
            else  dp[i][j]=dp[i-1][j];
        }
    }
    return dp[n][m];
 }
 public static int distinct_subsequences_4(String s1,String s2,int n,int m){
    int[]prev=new int[m+1];
    int[]curr=new int[m+1];
    prev[0]=curr[0]=1;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                curr[j]=prev[j-1]+prev[j];
            }
            else  curr[j]=prev[j];
        }
        prev=curr;
    }
    return prev[m];
 }


 public static int edit_distance(String s1,String s2,int i,int j){
    if(i<0)return j+1;
    if(j<0)return i+1;

    if(s1.charAt(i)==s2.charAt(j)){
        return edit_distance(s1, s2, i-1, j-1);
    }
    return 1+Math.min(edit_distance(s1, s2, i-1, j-1),Math.min(edit_distance(s1, s2, i-1, j),edit_distance(s1, s2, i, j-1))) ;
 }
 public static int edit_distance_2(String s1,String s2,int i,int j,int[][]dp){
    if(i<0)return j+1;
    if(j<0)return i+1;
    if(dp[i][j]!=-1)return dp[i][j];
    if(s1.charAt(i)==s2.charAt(j)){
        return dp[i][j]=edit_distance_2(s1, s2, i-1, j-1,dp);
    }
    return dp[i][j]=1+Math.min(edit_distance_2(s1, s2, i-1, j-1,dp),Math.min(edit_distance_2(s1, s2, i-1, j,dp),edit_distance_2(s1, s2, i, j-1,dp))) ;
 }
 public static int edit_distance_3(String s1,String s2,int n,int m,int[][]dp){
    for (int i = 0; i <= n; i++) {
        dp[i][0] = i;
    }
    for (int j = 0; j <= m; j++) {
        dp[0][j] = j;
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                dp[i][j]=dp[i-1][j-1];
            }
            else dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) ;
        }
    }
    return dp[n][m];
 }

 //WILDCARD MATCHING
 public static boolean wildcard_matching_1(String s1,String s2,int n,int m ,int i,int j){
    if(i<0 && j<0)return true;
    if(i<0 && j>=0)return false;
    if(i>=0 && j<0)return isallstars(s1,i);

    if(s1.charAt(i)==s2.charAt(j)||s1.charAt(i)=='?'){
        return wildcard_matching_1(s1, s2, n, m, i-1, j-1);
    }
    else{
        if(s1.charAt(i)=='*'){
            return wildcard_matching_1(s1, s2, n, m, i-1, j)||wildcard_matching_1(s1, s2, n, m, i, j-1);
        }
    }
    return false;
}
 public static int wildcard_matching_2(String s1,String s2,int n,int m ,int i,int j,int[][]dp){
    if(i<0 && j<0)return 1;
    if(i<0 && j>=0)return 0;
    if(i>=0 && j<0)return isallstars(s1,i)?1:0;
    if(dp[i][j]!=-1)return dp[i][j];

    if(s1.charAt(i)==s2.charAt(j)||s1.charAt(i)=='?'){
        return dp[i][j]=wildcard_matching_2(s1, s2, n, m, i-1, j-1,dp);
    }
    else{
        if(s1.charAt(i)=='*'){
            if(wildcard_matching_2(s1, s2, n, m, i-1, j,dp)==1||wildcard_matching_2(s1, s2, n, m, i, j-1,dp)==1){
                return dp[i][j]=1;
            }
            else{
                return dp[i][j]=0;
            }
        }
    }
    return 0;
}


public static boolean isallstars(String s,int i){
    for(int j=i;j>=0;j--){
       if(s.charAt(j)!='*')return false;
    }
    return true;
}
 public static void main(String[] args) {
     String s1="*";
     int n=s1.length();
     String s2="aa";
     int m=s2.length();
     int[][]dp=new int[n][m];

     for(int[]row:dp){
        Arrays.fill(row,-1);
     }

     System.out.println(wildcard_matching_2(s1, s2, n, m,n-1,m-1,dp));
    //  for(int[]row:dp){
    //     for(int val: row){
    //         System.out.print(val+" ");
    //     }
    //     System.out.println();
    //  }
   
    
   


   } 
}
