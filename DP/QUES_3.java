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
    // gfg Min insertions to form a palindrome
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
    // LEETCODE CAPACITY TO SHIP PACKAGES WITHEN D DAYS BINARY SEARCH
    public static boolean check(int val , int[] weights , int days)
    {
        int sum = 0;
        int count = 1;
        for(int ele : weights)
        {
            if(ele > val) return false; //means val is small
            sum += ele;
            if(sum > val)
            {
                count++;
                sum = ele;
            }
            if(count > days) return false;// means val is small
        }
        return true;
        
    }
    public int shipWithinDays(int[] weights, int days) 
    {
        int si = 1;
        int ei = (int)1e9;
        while(si < ei)
        {
            int mid = (si + ei)/2;
            if(!check(mid , weights , days)) si = mid+1;
            else ei = mid;
        }
        return si;
    }
    // MINIMUM NO OF DAYS TO MAKE M BOCQUETE
    public boolean check(int val , int k , int m , int[] arr)
    {
        int b = 0;
        int f = 0;
        for(int i = 0;i<arr.length;i++)
        {
            if(arr[i] > val)
            {
                if(f < k) f = 0;
                continue;
            }
            if(arr[i] <= val)
            {
                f++;
                if(f == k)
                {
                    b++;
                    f = 0;
                }
                if(b == m) return true;
            }
        }
        return false;
    }
    public int minDays(int[] arr, int m, int k) 
    {
        if(m*k > arr.length) return -1;
        int si = 0;
        int ei = (int)1e9+7;
        while(si < ei)
        {
            int mid = (si+ei)/2;
            if(check(mid , k , m , arr)) ei = mid;
            else si = mid+1;
        }
        return (ei == (int)1e9+7) ?  -1 : ei ;
    }
    // ALLOCATE BOOKS PAGES
    public static boolean check1(int val , int[] a , int stu)
    {
        int sum = 0;
        int c = 1;
        for(int ele : a)
        {
            if(ele > val) return false;
            sum += ele;
            if(sum > val)
            {
                c++;
                sum = ele;
            }
            if(c > stu) return false;
        }
        return true;
    }
    public static int findPages(int[]a,int n,int m)
    {
        int ei = (int)1e9;
        int si = 1;
        
        while(si < ei)
        {
            int mid = (si+ei)/2;
            if(check1(mid , a , m)) ei = mid;
            else si = mid + 1;
        }
        return si;
    }
    // INTERLEAVINJG STRINGS LEETCODE  
    public boolean isInterleave(String s1, String s2, String s3) 
    {
        if(s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        //return check(s1,s2,s3,0,0,dp);
        return check(s1,s2,s3,dp);
    }
    public boolean check(String s1, String s2, String s3 , int i , int j , boolean[][]dp)
    {
        if(i == s1.length() && j == s2.length()) dp[i][j] = true;
        if(dp[i][j]) return true;
        
        boolean temp = false;
        
        if(i < s1.length() && s1.charAt(i) == s3.charAt(i+j)) temp = temp || check(s1,s2,s3,i+1,j,dp);
        if(j < s2.length() && s2.charAt(j) == s3.charAt(i+j)) temp = temp || check(s1,s2,s3,i,j+1,dp);
        
        return dp[i][j] = temp;
    }
    public boolean check(String s1, String s2, String s3 , boolean[][]dp)
    {
        dp[s1.length()][s2.length()] = true;        
        for(int i = s1.length(); i>=0;i--)
        {
            for(int j = s2.length(); j>=0;j--)
            {
                if(i+1 <= s1.length() && s1.charAt(i) == s3.charAt(i+j)) dp[i][j] = dp[i+1][j];
                if(j+1 <= s2.length() && s2.charAt(j) == s3.charAt(i+j)) dp[i][j] = dp[i][j] || dp[i][j+1];       
            }
        }
        return dp[0][0];
    }
    // PROBABLITY OF KNIGHT TO REMAIN IN THE CHESS BOARD
    public double knightProbability(int n, int k, int row, int column) 
    {
        double[][] curr = new double[n][n];
        double[][] next = new double[n][n];
        curr[row][column] = 1;
        double max = 0.0;
        int[][] dir = new int[][]{{-2,-1},{-1,-2},{-2,1},{-1,2},{1,-2},{2,-1},{1,2},{2,1}};
        for(int l = 0;l<k;l++)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(curr[i][j]!=0)
                    {
                        for(int[] d : dir)
                        {
                            int x = i + d[0];
                            int y = j + d[1];
                            
                            if(x < n && y < n && x>=0 && y>=0)
                            {
                                next[x][y] += (curr[i][j] * 1/8);
                            }
                        }
                    }
                }
            }
            curr = next;
            next = new double[n][n];
        }
        for(int i = 0;i<n;i++)
        {
            for(int j = 0;j<n;j++)
            {
                max += curr[i][j];
            }
        }
        return max;
    }

    // word wrap GFG
    public static int solveWordWrap (int[] nums, int k)
    {
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp,-1);
        solve(nums,0,k,dp);
        return dp[0];
    }
    public static int solve(int []arr , int i , int k , int[] dp)
    {
        if(dp[i] != -1) return dp[i];
        int sum = 0;
        int minCost = (int)1e9;
        for(int j=i;j<arr.length;j++)
        {
            sum += arr[j];
            if((sum + (j-i)) <= k)
            {
                if(j<arr.length-1) minCost = Math.min(minCost,k-(sum + (j-i)) + solve(arr,j+1,k,dp));
                else return dp[i] = 0;
            }
        }
        return dp[i] = minCost;
    }
    // REMOVE IN ELEMENTS EITHER SIDE -> 2MIN > MAX
    public static int minRemoval(int arr[], int n) 
    {
        Arrays.sort(arr);
        int[][] dp = new int[n][n];
        solve(arr , n , dp);
        return dp[0][arr.length-1];
    }
    public static void solve(int []arr , int n, int[][]dp)
    {
        for(int k = 1;k<n;k++)
        {
            for(int i = 0,j = k;i<n && j<n;i++,j++)
            {
                if(j-i == 1)
                {
                    dp[i][j] = 0;
                    continue;
                }
                if(arr[i]*2 >= arr[j])
                {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = Math.min(dp[i+1][j] , dp[i][j-1]) + 1;
            }
        }
    }
    
    // Largest Sum Subarray with atleast k elements
    public long maxSumWithK(long a[], long n, long k)
    {
        long arr[]  = new long[(int)a.length];
        long sum = 0;
        for(int i = 0;i<n;i++)
        {
            if((sum + a[i]) > a[i])
            {
                sum += a[i];
                arr[i] = sum;
            }
            else
            {
                arr[i] = a[i];
                sum = a[i];
            }
        }
        
        long exactK = 0;
        
        for(int i = 0;i<k;i++)
        {
            exactK += a[i];
        }
        
        long lSum = exactK;
        
        for(long i = k;i<n;i++)
        {
            exactK = (exactK + a[(int) i])- a[(int) (i-k)];
            long temp = exactK + arr[(int) (i-k)];
            lSum = Math.max(lSum , Math.max(exactK , temp));
            
        }
        
        return lSum;
    }
    // Geeks For Geeks Perfect Sum
    public static void perfectSum(int arr[],int n, int sum) 
	{
        int[][] dp = new int[n+1][sum+1];
        for(int[] ele : dp) Arrays.fill(ele,-1);
        
        getCount(arr,0,sum,dp);
        print2d(dp);
        //return getCount_dp(arr ,sum ,n, dp);
	}
	public static int getCount(int []arr , int j , int sum , int[][]dp)
	{
	    if(sum == 0) return dp[j][sum] = 1;
	    if(dp[j][sum] != -1) return dp[j][sum];
	    int count = 0;
	    for(int i = j;i<arr.length;i++)
	    {
	        if(i+1 <= arr.length && sum - arr[i] >= 0) count = (count +  getCount(arr , i+1 , sum - arr[i] , dp) % ((int)1e9 + 7)) % ((int)1e9 + 7);
	    }
	    return dp[j][sum] = count % ((int)1e9 + 7);
 	}
    public static int getCount_dp(int arr[] , int sum)
 	{
 	    int[][] dp = new int[arr.length + 1][sum + 1];
 	    
 	    for(int j = 0;j<=sum;j++)
 	    {
 	        for(int i = arr.length;i>=0;i--)    
 	        {
 	            if(j == 0)
 	            {
 	                dp[i][j] = 1;
 	                continue;
 	            }
 	            if(i == arr.length)
 	            {
 	                dp[i][j] = 0;
 	                continue;
 	            }
 	            if(i + 1 <= arr.length && (j - arr[i]) >= 0) dp[i][j] = (dp[i][j] +  dp[i+1][j - arr[i]]) % ((int)1e9 + 7);
 	            if(i + 1 <= arr.length) dp[i][j] = (dp[i][j] + dp[i+1][j]) % ((int)1e9 + 7);
 	        }
 	    }
 	    return dp[0][sum];
 	}
    // Check O(n^3) Solution On Geeks For Geeks 
    // Geeks For geeks Longest Even Length Substring such that Sum of First and Second Half is same O(n^2) Time and O(n^2) Space
     public static void getLength(int []arr)
     {
        int n = arr.length;
        int[][] dp = new int[arr.length][arr.length];
        int length = 0;
        for(int k = 0;k<n;k++)
        {
            for(int i = 0 , j = k; j<n ;j++,i++)
            {
                if(k == 0)
                {
                    dp[i][j] = arr[i];
                    continue;
                }
                dp[i][j] = dp[i][j-1] + arr[j];
            }
        }
        print2d(dp);

        for(int k = 0;k<n;k++)
        {
            for(int i = 0 , j = k; j<n ;j++,i++)
            {
                if((i + (j-i+1)) < n && (j + (j-i+1)) < n && dp[i][j] == dp[i + (j-i+1)][j + (j-i+1)])
                {
                    length = Math.max(length , 2*(j-i+1));
                }
            }
        }
        System.out.println(length);
    }
    // O(n^2) Time and O(1) Space;
    public static void getLength_2(int []arr)
    {
        int n = arr.length;
        int max = 0;
            for(int length = 1;length<=n/2;length++)
            {
                int l = 0;
                int r = 0;
                int lsum = 0;
                int rsum = 0;
                while(l < length)
                {
                    lsum += arr[l];
                    l++;
                }
                r = l;
                while(r < arr.length && r < length + l)
                {
                    rsum += arr[r];
                    r++;
                }
                while(r-l == length && l<=arr.length && r<=arr.length)
                {
                    if(lsum == rsum) max = Math.max(max , r-l);  
                    if(l < arr.length) lsum = (lsum - arr[l - length]) + arr[l];
                    if(r < arr.length) rsum = (rsum - arr[r - length]) + arr[r];
                    l++;
                    r++;
                }
            }
        System.out.println(max * 2);
    }
    // lEETCODE lONGEST COMMON INCREASING SUBSEQUENCE IMPORTANT PROBLEM VIDEO IN VS VS CODE MUST WATCH
    public static int LCIS(int[] arr1, int m, int[] arr2, int n) 
    {
        int table[] = new int[m];

        for (int i = 0; i < n; i++)
        {
            int current = 0;

            for (int j = 0; j < m; j++)
            {
                if (arr1[i] == arr2[j])
                    if (current + 1 > table[j])
                        table[j] = current + 1;
 
                if (arr1[i] > arr2[j])
                    if (table[j] > current)
                        current = table[j];
            }
        }
        int result = 0;
        for (int i=0; i<m; i++)
            if (table[i] > result)
                result = table[i];
 
        return result;
    }
    // LEETCODE 357
    // Exponential Solution
    public static int countNumbersWithUniqueDigits(int n) 
    {
        if(n == 1 || n == 0) 
        {
            return (n == 1) ? 10 : 1; 
        }
        int val = (int)(Math.pow(10, n));
        return countNumbersWithUniqueDigits(val,0) + 1;
    }
    public static boolean canWe(int no , int i)
    {
        if(no == 0 && i == 0) return false;
        while(no!=0)
        {
            if(i == no % 10) return false;
            no = no / 10;
        }
        return true;
    }
    public static int countNumbersWithUniqueDigits(int n , int val) 
    {
        int count = 0;
        for(int i = 0;i<=9;i++)
        {
            if((val * 10) + i < n && canWe(val,i)) count += countNumbersWithUniqueDigits(n, (val * 10) + i) + 1;
        }
        return count;
    }
    //O(1) Solution Easy Explaination just dry run you will get to know
    public static int countNumbersWithUniqueDigits_(int n) 
    {
        if (n == 0) {
            return 1;
        }
        int ans = 10, base = 9;
        for (int i = 2; i <= n; i++) {
            base = base * (9 - i + 2);
            ans += base;
        }
        return ans;
    }
    // Maximize The Cut Segments GEEKS FOR GEEKS
    public static int maximizeCuts_dp(int n, int x, int y, int z)
    {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1;i<=n;i++)
        {
            int countA = (i-x >=0) ? (i-x == 0) ? dp[i-x] : ((dp[i-x] == 0) ? 0 : dp[i-x] + 1) : 0;
            int countB = (i-y >=0) ? (i-y == 0) ? dp[i-y] : ((dp[i-y] == 0) ? 0 : dp[i-y] + 1) : 0;
            int countC = (i-z >=0) ? (i-z == 0) ? dp[i-z] : ((dp[i-z] == 0) ? 0 : dp[i-z] + 1) : 0;

            dp[i] = Math.max(countA , Math.max(countB , countC));
        }
        print1D(dp);
        return dp[n];
    }
    // Maximum Product SubArray
    public static long maxProduct(int[] arr, int n) 
    {
        long min = 1;
        long max = 1;
        long ans = 0;
        for(int i = 0;i<n;i++)
        {
            if(arr[i] == 0)
            {
                min = 1;
                max = 1;
                continue;
            }
            long temp1 = Math.min(arr[i] ,Math.min(arr[i]*min , arr[i]*max));
            long temp2 = Math.max(arr[i] ,Math.max(arr[i]*min , arr[i]*max));
            min = temp1;
            max = temp2;
            System.out.println(max);
            System.out.println(min);
            System.out.println(ans);
            System.out.println("##############################");
            ans = Math.max(ans,max);
        }
        return ans;
    }
    // leetcode 472 Concatenated Words SOLVE THIS QUESTION USING TRIES !!!!!!!!!!!!!!!!!!!!!!!!
    public static List<String> findAllConcatenatedWordsInADict(String[] words) 
    {
        List<String> ans = new ArrayList<>();
        HashSet<String> map = new HashSet<>();
        for(String word : words) map.add(word);

        for(String word : words)
        {
            boolean res = helperFun(word,map,0,0);
            if(res) ans.add(word);
        }
        return ans;
    }
    public static boolean helperFun(String word , HashSet<String> map , int j , int count)
    {
        if(j == word.length() && count > 1) return true;
        boolean res = false;
        for(int i=j+1;i<=word.length();i++)
        {
            if(j-i != word.length() && map.contains(word.substring(j,i)))
            {
                res = res || helperFun(word, map, i, count + 1);
                if(res) return res;
            }
        }
        return res;
    }
    // GEEKS FOR GEEKS COUNT NO OF HOPS
    public static long mod =  (long)1000000007;
    public static long countWays(int n)
    { 
        long[] dp = new long[n+1];
        dp[0] = 1;
        for(int j = 1;j<=n;j++)
        {
            for(int i = 1;i<=3;i++)
            {
                if(j-i >= 0) dp[j] = (dp[j] + dp[j-i]) % mod;
            }   
        }
        return dp[n];
    }
    // geeks for geeks count numbers containing 4 Check Dp Solution On geeks for geeks
    // DO THIS WHEN YOU ARE DONE WITH PERMUTATIONS COMBINATIONS AND COUNTING
    public static int countNumberswith4(int n) 
    {
        int result = 0;
      
        // One by one compute sum of digits
        // in every number from 1 to n
        for (int x=1; x<=n; x++)
            result += has4(x)? 1 : 0;
      
        return result;
    }
    public static boolean has4(int x)
    {
        while (x != 0)
        {
            if (x%10 == 4)
               return true;
            x   = x /10;
        }
        return false;
    }
    // geeks for geeks Count of strings that can be formed using a, b and c under given constraints 
    // dp memoisation solution 3D Dp not passing on geeks for geeks giving stack OverFlow
    // check for the combination solution when you are familier with permutations and combinations
    // screen shot in Dry Run 
    public static int countStrUtil(int[][][] dp, int n,int bCount, int cCount)
    {
 
        if (bCount < 0 || cCount < 0)
        {
            return 0;
        }
        if (n == 0)
        {
            return 1;
        }
        if (bCount == 0 && cCount == 0)
        {
            return 1;
        }
 
        if (dp[n][bCount][cCount] != -1)
        {
            return dp[n][bCount][cCount];
        }

        int res = countStrUtil(dp, n - 1, bCount, cCount);
        res += countStrUtil(dp, n - 1, bCount - 1, cCount);
        res += countStrUtil(dp, n - 1, bCount, cCount - 1);
 
        return (dp[n][bCount][cCount] = res);
    }
 
    public static long countStr(long n)
    {
        int[][][] dp = new int[(int)n + 1][2][3];
        for (int i = 0; i < (int)n + 1; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    dp[i][j][k] = -1;
                }
            }
        }
        return countStrUtil(dp, (int)n,1,2);
    }
    // GEELKS FOR GEEKS COUNT OF SUB ARRAYS , MAX ELEMENT STRICTLY GREATER THAN K
    public long countSubarray(int arr[], int n, int k) 
    {
        long totalSum = 0;
        long val = -1;
        long sum = 0;
        for(int i = 0;i<n;i++)
        {
            totalSum += i+1;
            if(arr[i]<=k) sum += (i-val);
            else val = i;
        }
        return totalSum - sum;
    }
    // dp[0] = 0;

    // dp[1] = dp[0] + 1;

    // dp[2] = dp[0] + 1;

    // dp[3] = dp[1] +1;

    // dp[4] = dp[0] + 1;

    // dp[5] = dp[1] + 1;

    // dp[6] = dp[2] + 1;

    // dp[7] = dp[3] + 1;

    // dp[8] = dp[0] + 1;
    // ...

    // This is the function we get, now we need find the other pattern for the function to get the general function. After we analyze the above function, we can get
    // dp[0] = 0;

    // dp[1] = dp[1-1] + 1;

    // dp[2] = dp[2-2] + 1;

    // dp[3] = dp[3-2] +1;

    // dp[4] = dp[4-4] + 1;

    // dp[5] = dp[5-4] + 1;

    // dp[6] = dp[6-4] + 1;

    // dp[7] = dp[7-4] + 1;

    // dp[8] = dp[8-8] + 1;
    // ..

    // Obviously, we can find the pattern for above example, so now we get the general function

    // dp[index] = dp[index - offset] + 1;
    // LEETCODE 338 COUNTING BITS DP IMPORTANT
    public int[] countBits(int num) 
    {
        int result[] = new int[num + 1];
        int offset = 1;
        for (int index = 1; index < num + 1; ++index){
            if (offset * 2 == index){
                offset *= 2;
            }
            result[index] = result[index - offset] + 1;
        }
        return result;
    }
    // Leetcode Count The Repetetions Leetcode Brute Force Solution 
    public static int getMaxRepetitions(String s1, int n1, String s2, int n2)
    {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = ch1.length, len2=ch2.length;
        int i=0, j=0, k=0, h=0;
        while(k < n1){ 
            if (ch1[i]==ch2[j])
            {
                j++;
                if (j==len2){
                    j=0;
                    h++;
                }
            }
            i++;
            if(i==len1){
                i=0;
                k++;
            }
        }
        return h/n2;
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
        // getCount(2);
        // int[] arr = new int[]{10,20,30,40,60,90,44,56,78,33,21,55,67,88};
        // minTime(arr, arr.length, 4);
        // System.out.println(solveWordWrap(new int[]{3,2,2,5}, 6));
        // int[] arr = new int[]{2,3,5,6,8,10};
        // int[][] dp = new int[7][11];
        // perfectSum(arr,6,10);
        // int[] arr = new int[]{1,5,3,8,0,2,3};
        // int[] arr = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
        // int[] arr = new int[]{0,0,0,0,5,5,5,5,5,5,5,5,5,0,0,0,0};
        // getLength_2(arr);
        // System.out.println(countNumbersWithUniqueDigits(2));
        // System.out.println(maximizeCuts(7, 5, 5, 2));
        // System.out.println(maximizeCuts_dp(7,1,2,3));
        // System.out.println(maxProduct(new int[]{6,-3,-10,0,-3}, 5));
        // List<String> res = findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
        // System.out.println(res.size());
        // for(String word : res) System.out.println(word);
        // String temp = "JAI";
        // System.out.println(temp.substring(0,1));
    }
}