import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
class dp_faang
{
    public static void print1D(int[] arr)
    {
        for(int ele : arr) System.out.print(ele +  " ");
    }
    public static void print2d(int[][] arr)
    {
        for(int[]a : arr) {
            print1D(a);
            System.out.println();
        }
    }
    public static void print1D(long[] arr)
    {
        for(long ele : arr) System.out.print(ele +  " ");
    }
    public static void print2d(long[][] arr)
    {
        for(long[]a : arr) {
            print1D(a);
            System.out.println();
        }
    }
    public static void print1Dboolean(boolean[] arr)
    {
        for(boolean ele : arr) System.out.print((ele ? "True" : "False") +  " ");
    }
    public static void print2dboolean(boolean[][] arr)
    {
        for(boolean[]a : arr) {
            print1Dboolean(a);
            System.out.println();
        }
    }
    // leetcode 264 ugly numbers
    public static int nthUglyNumber(int n) 
    {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for(int i = 2; i < n+1 ;i++)
        {
            dp[i] = Math.min(2 * dp[p2] , Math.min(3 * dp[p3] , 5 * dp[p5]));
            if(dp[p2] * 2 == dp[i]) p2++;
            if(dp[p3] * 3 == dp[i]) p3++;
            if(dp[p5] * 5 == dp[i]) p5++;
        }
        return dp[n];
    }
    // leetcode 313
    public static int nthSuperUglyNumber(int n, int[] primes) 
    {
        int [] dp = new int[n+1];
        int [] ptrs  = new int[primes.length];
        dp[1] = 1;
        PriorityQueue<Integer> val = new PriorityQueue<>();
        HashSet<Integer> dup = new HashSet<>();
        Arrays.fill(ptrs , 1);
        for(int ele : primes)
        {
            val.add(ele);
            dup.add(ele);
        }
        for(int i=2;i<n+1;i++)
        {
            dp[i] = val.remove();
            for(int j = 0;j<ptrs.length;j++)
            {
                if(dp[i] == primes[j] * dp[ptrs[j]])
                {
                    ptrs[j]++;
                    if(!dup.contains(primes[j] * dp[ptrs[j]]))
                    {
                        dup.add(primes[j] * dp[ptrs[j]]);
                        val.add(primes[j] * dp[ptrs[j]]);
                    }
                }
            }
        }
        return dp[n];
    }
    // gfg max sum bitonic subsequence
    public static int maxSumBS(int arr[], int n)
    {
        int[] lisLr = new int[n];
        int[] lisRl = new int[n];
        lisRl[n-1] = arr[n-1];
        lisLr[0] = arr[0];
        int max = -(int)1e9;
        for(int i = 1;i<n;i++)
        {
            lisLr[i] = arr[i];
            for(int j = i-1;j>=0;j--)
            {
                if(arr[j] < arr[i]) lisLr[i] = Math.max(lisLr[i] , arr[i] + lisLr[j]);
            }
        }
        for(int i = n-2;i>=0;i--)
        {
            lisRl[i] = arr[i];
            for(int j = i+1;j<n;j++)
            {
                if(arr[j] < arr[i]) lisRl[i] = Math.max(lisRl[i] , arr[i] + lisRl[j]);
            }
        }
        print1D(lisLr);
        System.out.println();
        print1D(lisRl);
        for(int i = 0;i<n;i++) max = Math.max(max , lisLr[i] + lisRl[i] - arr[i]);
        return max;
    }
    // LCS OF 3 STRINGS GFG 
    public static int lcs(String A , String B , String C)
    {
        int count = 0;
        int[][][] dp = new int[A.length()+1][B.length()+1][C.length()+1];
        for(int [][]ele  : dp)
        {
            for(int []ele1 : ele) Arrays.fill(ele1,-1);
        }
        return count = lcs_memo(A, B , C , A.length(), B.length(),C.length(), dp);
        
    }
    public static int lcs_memo(String A , String B , String C , int n , int m , int o , int[][][]dp)
    {
        if(n == 0||m == 0 || o == 0) return dp[n][m][o] = 0;
        if(dp[n][m][o]!=-1) return dp[n][m][o];
        int count = 0;
        if(A.charAt(n-1) == B.charAt(m-1) && B.charAt(m-1) == C.charAt(o-1)) count += lcs_memo(A,B,C,n-1,m-1,o-1,dp) + 1;
        else count = Math.max(count,Math.max(lcs_memo(A, B,C, n-1, m,o,dp) , Math.max(lcs_memo(A, B, C,n, m-1,o, dp) , lcs_memo(A, B,C, n, m,o-1 ,dp))));
        return dp[n][m][o] = count;
    }
    public static int LCSof3(String A, String B, String C, int n1, int n2, int n3) 
    { 
        return lcs(A,B,C);
    }
    // Geeks For Geeks Partiton Equal SubSet
    public static void subSetSum(int[] val , int target)
    {
        boolean[][] dp = new boolean[val.length+1][target+1];
        System.out.println(memoSubSet(0, target, val, dp) ? "True" : "False");
        print2dboolean(dp);
    }
    public static boolean memoSubSet(int i , int tar ,int[] val , boolean[][]dp)
    {
        if(tar == 0) return dp[i][tar] =  true;
        if(i == val.length) return dp[i][tar] = false;
        if(dp[i][tar]) return true;
        boolean res = false;
        for(int j = i;j<val.length;j++)
        {
            if(tar - val[j]>=0) res = res || memoSubSet(j+1,tar - val[j],val,dp);
            res = res || memoSubSet(j+1, tar, val, dp); 
        }
        return dp[i][tar] = res;
    }
    // box stacking problem
    public static class pair
    {
        int l = 0;
        int b = 0;
        int h = 0;
        pair(int l , int b , int h)
        {
            this.l = l;
            this.b = b;
            this.h = h;
        }
    }
    public static void maxHeight(int[] height, int[] width, int[] length, int n)
    {
        pair[] lis = new pair[3*n];
        int j = 0;
        for(int i = 0;i<n;i++)
        {
            lis[j++] = new pair(height[i] , width[i] , length[i]);
            lis[j++] = new pair(height[i] , length[i] , width[i]);
            lis[j++] = new pair(width[i] , length[i] , height[i]);
        }
        Arrays.sort(lis ,Collections.reverseOrder((pair a , pair b) -> 
        {
            return a.l*a.b - b.l*b.b;
        }));
        LisForBoxStacking(lis);
    }
    public static void LisForBoxStacking(pair[] dim)
    {
        int[]dp = new int[dim.length];
        int max = -(int)1e9;
        for(int i = 0;i<dim.length;i++)
        {
            dp[i] = dim[i].h;
            for(int j = i-1;j>=0;j--)
            {
                if(((dim[i].l < dim[j].l && dim[i].b < dim[j].b)||(dim[i].l < dim[j].b && dim[i].b < dim[j].l)) && dim[i].h+dp[j] > dp[i]) dp[i] = dim[i].h + dp[j];
                max  = Math.max(max , dp[i]);
            }
        }
        System.out.println(max);
        for(int k = 0;k<dim.length;k++)
        {
            System.out.println(dim[k].l + " " + dim[k].b + " " + dim[k].h );
        }
        print1D(dp);
    }
    // gfg form a palindrome
    public static int countMin(String str)
    {
        int[][] dp = new int[str.length()][str.length()];
        for(int[] ele : dp) Arrays.fill(ele , -1);
        System.out.println(countMin_memo(str, 0, str.length()-1, dp));
        return countMin_memo(str, 0, str.length()-1, dp);
    }
    public static int countMin_memo(String s ,int i , int j ,  int[][] dp)
    {
        if(i > j) return 0;
        if(i==j) return dp[i][j] = 0;
        if(dp[i][j] != -1) return dp[i][j];
        int count = (int)1e9;
        if(s.charAt(i) == s.charAt(j)) count = countMin_memo(s , i+1 , j-1 , dp);
        else
        {
            count = Math.min(countMin_memo(s,i+1,j-1,dp)+2,Math.min(countMin_memo(s,i+1,j , dp)+1,countMin_memo(s, i, j-1 , dp)+1));
        }
        return dp[i][j] = count;
    }
    // maximum rod cutting
    public static int maxProd(int n)
    {
        if (n == 0 || n == 1) return 0;
        int max_val = 0;
        for (int i = 1; i < n; i++)
        max_val = Math.max(max_val,Math.max(i * (n - i),maxProd(n - i) * i));
        return max_val;
    }
    // question Geeks For Geeks optimal game startegy
    public static void countMaximum(int arr[], int n) // Apply gap strategy for tabluation
    {
        long[][] dp = new long[n][n];
        for(long[] ele : dp) Arrays.fill(ele , -(int)1e9);
        System.out.println(countMaximumMain(arr,0,n-1, dp));
        //return countMaximum(arr,0,n-1, dp);
    }
    public static long countMaximumMain(int arr[],int i , int j ,  long[][] dp)
    {
        if(i == j) return dp[i][j] = arr[i];
        if(i == j-1) return dp[i][j] = Math.max(arr[i],arr[j]);
        if(dp[i][j] != -(int)1e9) return dp[i][j];
        long max = -(int)1e9;
        if(j > i){ 
        long val1 = Math.min(arr[i] + countMaximumMain(arr, i+2, j, dp) ,arr[i] + countMaximumMain(arr, i+1, j-1, dp));
        long val2 = Math.min(arr[j] + countMaximumMain(arr, i+1, j-1, dp) ,arr[j] + countMaximumMain(arr, i, j-2, dp));
        max = Math.max(val1 , val2);
        }
        return dp[i][j] = max;
    }
    // leetcode 139 word break
    public boolean wordBreak(String s, List<String> wordDict) 
    {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];
        
        for (int end = start + 1; end <= s.length(); end++) 
        {
            if (wordDict.contains(s.substring(start, end))) 
            {
                boolean res = wordBreakMemo(s, wordDict, end, memo);
                if(res) return memo[start] = true;
                // matlab agar true aaya tho return kardo aage kuch karne ki zaroorat nahi hei agar false aaya toh loop ko aage badhao
            }
        }
        return memo[start] = false;
    }
    // Mobile Numeric KeyPad
    public static long getCount(int N)
	{
        if(N == 1) return 10;
        int[][] map = new int[11][];
        map[0] = new int[]{};
        map[1] = new int[]{4,2};
        map[2] = new int[]{1,3,5};
        map[3] = new int[]{2,6};
        map[4] = new int[]{1,7,5};
        map[5] = new int[]{2,4,6,8};
        map[6] = new int[]{3,5,9};
        map[7] = new int[]{4,8};
        map[8] = new int[]{5,7,10,9};
        map[9] = new int[]{6,8};
        map[10] = new int[]{8};

        long[] dp1 = new long[11];
        long[] dp2 = new long[11];
        Arrays.fill(dp1,1);
        Arrays.fill(dp2,1);
        dp1[0] = 0;
        dp2[0] = 0;
        for(int i = 1;i<N;i++)
        {
            for(int j = 1;j<11;j++)
            {
                for(int ele : map[j])
                {
                    dp2[j] += dp1[ele]; 
                }
            }
            for(int k = 0;k<11;k++) dp1[k] = dp2[k];
        }
        long sum = 0;
        for(long ele : dp1) sum += ele;
        System.out.println(sum);
        return sum;
	}
    // Geeks For Geeks painters partitian problem
    // Memoisation solution is giving TLE on GFG
    // Tabulation solution also giving TLE on GFG
    public static void minTime(int[] arr,int n,int k)
    {
        long [][] dp = new long[k+1][n+1];
        System.out.println(paintersPartitian(arr, n, k, dp));
        print2d(dp);
    }
    public static long paintersPartitian(int[] arr,int n,int k , long[][]dp)
    {
        if(k == 1) return dp[k][n] = sum(0, n, arr);
        if(n == 1) return dp[k][n] = arr[0];
        long best = (long)1e10;
        for(int i = 1 ;i<=n;i++)
        {
            best = Math.min(best , Math.max(paintersPartitian(arr , i , k-1 , dp) , sum(i , n , arr)));
        }
        return dp[k][n] = best;
    }
    public static long minTime_dp(int arr[], int n, int k)
    {
        long dp[][] = new long[k+1][n+1];
        for (int i = 1; i <= n; i++)
            dp[1][i] = sum(0, i - 1 , arr);
      
        for (int i = 1; i <= k; i++)
            dp[i][1] = arr[0];
      
        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
      
                long best = Integer.MAX_VALUE;
      for (int p = 1; p <= j; p++) best = Math.min(best, Math.max(dp[i - 1][p],sum(p, j - 1 , arr)));      
      
                dp[i][j] = best;
            }
        }
        return dp[k][n];
    }
    public static long sum(int i , int j , int[] arr)
    {
        long total = 0;
        for(int k = i;k<j;k++) total+=arr[k];
        return total;
    }
    public static void main(String[] args) 
    {
        // int[] arr1 = new int[]{1, 15, 51, 45, 33,100, 12, 18, 9};
        // maxSumBS(arr1, 9);
        // LCSof3("geeks","geeksfor", "geeksforgeeks", 5, 8, 13);
        // LCSof3("abcd","efgh","ijkl", 4, 4, 4);
        // int[]val = new int[]{2,3,5,7};
        // subSetSum(val, 20);
        // int[] height = new int[]{5,3};
        // int[] width = new int[]{2,5};
        // int[] length = new int[]{6,3};
        // maxHeight(height, width, length, height.length);
        // countMin("abababababa");
        // int[] arr = new int[]{20,30,2,10};
        // countMaximum(arr, 4);
        //getCount(2);
        // int[] arr = new int[]{10,20,30,40,60,90,44,56,78,33,21,55,67,88};
        // minTime(arr, arr.length, 4);
    }
}