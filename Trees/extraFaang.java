import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
class extraFaang
{
    public static class Node
    {
        int data = 0;
        Node left = null;
        Node right = null;
        Node(int val)
        {
            this.data = val;
        }
    }
    public static class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int val)
        {
            this.val = val;
        }
    }
    public static class Tree
    {
        int d = 0;
        Tree left , right , random;
        Tree(int d)
        {
            this.d = d;
        }
    }
    public static void preorder(Tree root)
    {
        System.out.print(root.d + " ");

    }
    // GEEKS FOR GEEKS IMAGE MULTIPLICATION
    public static void addAllRight(Node root , LinkedList<Node> stack)
    {
        while(root!=null)
        {
            stack.addLast(root);
            root = root.right;
        }
    }
    public static void addAllLeft(Node root , LinkedList<Node> stack)
    {
        while(root!=null)
        {
            stack.addLast(root);
            root = root.left;
        }
    }
    public static long imgMultiply(Node root)
    {
        long sum = 0;
        LinkedList<Node> left = new LinkedList<>();
        LinkedList<Node> right = new LinkedList<>();

        addAllLeft(root, left);
        addAllRight(root, right);

        while(left.size()!=0 && right.size()!=0)
        {
            if(left.size() == right.size())
            {
                sum  = (sum + (left.get(left.size()-1).data * right.get(right.size()-1).data))%((int)1e9+7);
                if(left.size() == 1 && right.size() == 1)
                {
                    break;
                }
                Node Right = right.removeLast();
                Node Left = left.removeLast();
                if(Right.left!=null) addAllLeft(Right.left, left);
                if(Left.right!=null) addAllRight(Left.right, right);
            }
            else if(left.size() > right.size())
            {
                int temp = left.size() - right.size();
                while(temp > 0)
                {
                    left.removeLast();
                    temp--;
                }
            }
            else if(right.size() > left.size())
            {
                int temp = right.size() - left.size();
                while(temp > 0)
                {
                    right.removeLast();
                    temp--;
                }
            }
        }
        return sum;
    }

    public static Tree cloneTree(Tree root)
    {
        HashMap<Tree,Tree> map = new HashMap<>();  
        clone(root, map); 
        return map.get(root); 
    }

    public static void clone(Tree root , HashMap<Tree,Tree> map)
    {
        if(root == null) return;
        if(!map.containsKey(root))
        {
            Tree t = new Tree(root.d);
            map.put(root, t);
        }

        if(root.left!=null && !map.containsKey(root.left))
        {
            Tree t = new Tree(root.left.d);
            map.put(root.left, t);
            map.get(root).left = map.get(root.left);
        }
        else map.get(root).left = map.get(root.left);

        if(root.right!=null && !map.containsKey(root.right))
        {
            Tree t = new Tree(root.right.d);
            map.put(root.right,t);
            map.get(root).right = map.get(root.right);
        }
        else map.get(root).right = map.get(root.right);

        if(root.random!=null && !map.containsKey(root.random))
        {
            Tree t = new Tree(root.random.d);
            map.put(root.random,t);
            map.get(root).random = map.get(root.random);
        }
        else map.get(root).random = map.get(root.random);

        clone(root.left,map);
        clone(root.right,map);

        return;
    }
    // invert tree leetcode 226. Invert Binary Tree

    public TreeNode invert(TreeNode root)
    {
        if(root == null) return null;
        TreeNode left = invert(root.left);
        TreeNode right = invert(root.right);
        TreeNode temp = left;
        root.left = right;
        root.right = temp;
        return root;
    }
    public TreeNode invertTree(TreeNode root) 
    {
        return invert(root);
    }
    
    // CONVERT TREE TO A DOUBLY LINKED LIST
    public Node bTreeToClist(Node root)
    {
        if(root == null) return null;
        Node dummy = new Node(-1);
        Node tail = convert(root,dummy);
        dummy = dummy.right;
        dummy.left = tail;
        tail.right = dummy;
        return dummy;
    }
    public Node convert(Node root , Node dummy)
    {
        if(root == null) return null;
        Node left = convert(root.left , dummy);
        if(left == null)
        {
            dummy.right = root;
            root.left = dummy;
        }
        if(left!=null)
        {
            left.right = root;
            root.left = left;
        }
        Node right = convert(root.right,root);
        if(right!=null) return right;
        return root;
    }

    // DOUBLY LINKED LIST TO BINARY SEARCH TREE (INORDER TRAVERSAL TO A BINARY SEARCH TREE)
    // VERY NICE SOLUTION READ FROM GFG 
    // WATCH VIDEO FOR MORE CLEARITY
    // O(n) SOLUTION
    // SUBMITTED ON LEETCODE
    
    public static int length(TreeNode root)
    {
        int count = 0;
        while(root!=null)
        {
            count++;
            root = root.right;
        }
        return count;
    }
    public static TreeNode head;
    public static TreeNode DLLtoBST()
    {
        int len = length(head);
        return convert(len);
    }
    public static TreeNode convert(int n)
    {
        if(n<=0) return null;
        TreeNode left = convert(n/2);
        TreeNode root = head;
        root.left = left;
        head = head.right;
        root.right = convert(n - (n/2) - 1);
        return root;
    }

    // MERGE TWO BST GEEKS FOR GEEKS
    // FIRST CREATE SINGLY LINIKEDLIST FROM BOTH THE BINARY SEARCH TREES
    // MERGE BOTH THE SINGLY LINKED LIST
    // CONVERT THIS CONVERTED LIST TO A BINARY SEARCH TREE
    public static TreeNode convertToSingleLL(TreeNode root , TreeNode dummy)
    {
        if(root == null) return null;
        TreeNode left = convertToSingleLL(root.left , dummy);
        if(left == null) dummy.right = root;
        if(left!=null)
        {
            left.right = root;
            root.left = null;
        }
        TreeNode right = convertToSingleLL(root.right,root);
        if(right!=null) return right;
        return root;
    }
    public static TreeNode mergeTwoLists(TreeNode l1, TreeNode l2) 
    {
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null)
        {
            return (l1 == null) ? l2 : l1;
        }
        TreeNode ans = null;
        TreeNode c1 = l1;
        TreeNode c2 = l2;
        
        while(c1!=null && c2!= null)
        {
            TreeNode f1 = c1.right;
            TreeNode f2 = c2.right;
            if(c1.val <= c2.val)
            {
                if(ans == null) ans = c1;
                while(f1!=null && f1.val < c2.val)
                {
                    c1 = f1;
                    f1 = c1.right;
                }
                c1.right = c2;
                c1 = c1.right;
                c2 = f1;
            }
            else if(c2.val < c1.val)
            {
                TreeNode temp = c1;
                c1 = c2;
                c2 = temp;
            }
        }
        return ans;
    }
    public static TreeNode mergeTwoBST(TreeNode root1 , TreeNode root2)
    {
        TreeNode dummy1 = new TreeNode(-1);
        TreeNode dummy2 = new TreeNode(-1);

        convertToSingleLL(root1,dummy1);
        convertToSingleLL(root2,dummy2);

        dummy1 = dummy1.right;
        dummy2 = dummy2.right;

        TreeNode ans = mergeTwoLists(dummy1, dummy2);

        int n = length(ans);
        ans = convert(n);

        return ans;
    }

    // BINARY SEARCH TREE TO GREATER SUM TREE
    public TreeNode bstToGst(TreeNode root) 
    {
        int[] sum = new int[1];
        bstToGst(root,sum);
        return root;
    }
    public void bstToGst(TreeNode root,int []sum) 
    {
        if(root == null) return;
        bstToGst(root.right,sum);
        root.val = sum[0]+root.val;
        sum[0] = root.val;
        bstToGst(root.left,sum);
        return;
    }

    // REVERSE LEVEL ORDER TRAVERSAL
    public ArrayList<Integer> reverseLevelOrder(Node root) 
    {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0)
        {
            int size = queue.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while(size-- > 0)
            {
                Node temp = queue.removeFirst();
                smallAns.add(temp.data);
                if(temp.left!=null) queue.addLast(temp.left);
                if(temp.right!=null) queue.addLast(temp.right);
            }
            list.add(smallAns);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = list.size()-1;i>=0;i--) for(int ele : list.get(i)) ans.add(ele);
        return ans;
    }

    // DELETE LEAVES WITH A GIVEN VAL LEETCODE 1325
    public TreeNode removeLeafNodes(TreeNode root, int target) 
    {
        if(root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if(root.left == null && root.right == null && root.val == target) return null;
        return root;
    }

    // LEETCODE 116 POPULATING NEXT RIGHT POINTER IN EACH NODE 
    // APPROACH 1 O(N) SPACE USING LEVEL ORDER TRAVERSAL
    public Node connect(Node root) 
    {
        if(root == null) return null;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0)
        {
            int size = queue.size();
            Node attach = new Node(-1);
            while(size-- > 0)
            {
                Node temp = queue.removeFirst();
                //attach.next = temp; IMPORTANT LINK UNCOMMENT THIS
                attach = temp;
                if(temp.left!=null) queue.addLast(temp.left);
                if(temp.right!=null) queue.addLast(temp.right);
            }
        }
        return root;
    }

    // O(1) SPACE SOLUTION VERY INGENIOUS AND NICE SOLUTION
    // THIS SOLUTION CAN BE A POSSIBLE ITERATIVE LEVEL ORDER WITH CONSTANT SPACE
    public Node connect2(Node root)
    {
        Node head = root;
        while(head!=null && head.left!=null)
        {
            connectNext(head);
            head = head.left;
        }
        return root;
    }
    public void connectNext(Node root)
    {
        Node itr = root.left;
        while(root!=null)
        {
            if(root.left!=null)
            {
                //itr.next = root.left;
                //itr = itr.next;
            }
            if(root.right!=null)
            {
                //itr.next = root.right;
                //itr = itr.next;
            }
           // root = root.next;
        }
    }

    // MAXIMUM PRODUCT OF A SPLITTED BINARY TREE LEETCODE 1339
    public void calculateSum(TreeNode root , int[]sum)
    {
        if(root == null) return;
        sum[0] += root.val;
        calculateSum(root.left,sum);
        calculateSum(root.right,sum);
    }
    public int maxProduct(TreeNode root) 
    {
        int m = 1000000007;
        int[]sum = new int[]{0};
        calculateSum(root,sum);
        int Tsum = sum[0]; 
        long[] max = new long[]{-(int)1e9};
        calculateMaxPro(root,Tsum,max);
        return (int)(max[0]%m);
    }
    public int calculateMaxPro(TreeNode root ,int sum, long[]max)
    {   
        if(root == null) return 0; 
        int left = calculateMaxPro(root.left,sum,max);
        int right = calculateMaxPro(root.right,sum,max);
        max[0] = Math.max(max[0],Math.max((sum-left)*(long)left,(sum-right)*(long)right));
        return root.val+left+right;
    }

    // LEETCODE 129 SUM ROOT TO LEAF NUMBERS
    public int sumNumbers(TreeNode root) 
    {
        int[]sum = new int[]{0};
        sumNumbers(root,0,sum);
        return sum[0];
    }
    public void sumNumbers(TreeNode root , int ans , int[]sum)
    {
        if(root == null) return;
        if(root.left == null && root.right == null)
        {
            int val = ((ans*10) + root.val);
            sum[0] += val;
            return;
        }
        sumNumbers(root.left,((ans*10) + root.val),sum);
        sumNumbers(root.right,((ans*10) + root.val),sum);
    }

    // SUM OF DISTANCES LEETCODE NOT PASSING GIVING TLE SOLVE THIS AFTER LEARNING DP ON TREES
    // 64 / 73 test cases passed.
    public void calculateSum(ArrayList<Integer>[] graph , int n ,int s, int[]sum , boolean[] vis)
    {
        for(Integer ele : graph[n])
        {
            if(!vis[ele])
            {
                sum[ele] += s;
                vis[ele] = true;
                calculateSum(graph,ele,s+1,sum,vis);
            }
        }
    }
    public int[] sumOfDistancesInTree(int n, int[][] edges) 
    {
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        for(int[] e : edges)
        {
            int u = e[0];
            int v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] ans = new int[n];
        int[] sum = new int[n];
        boolean[] vis = new boolean[n];
        for(int i = 0;i<n;i++)
        {
            vis[i] = true;
            calculateSum(graph,i,1,sum,vis);
            int temp = 0;
            for(int h = 0;h<n;h++)
            {
                temp += sum[h];
                sum[h] = 0;
                vis[h] = false;
            }
            ans[i] = temp;
        }
        return ans;
    }
    // PRINT ALL K SUM PATHS VERY IMPORTANT 
    // IN PATH SUM 3 LEETCODE PRINT ALL PATHS WHICH HAS K SUM
    public void printPath(ArrayList<Integer> path , int i)
    {
        for(int j = i;j<path.size();j++)
        {
            System.out.print(path.get(j) + " ");
        }
        System.out.println();
    }
    public void printKPath(TreeNode root , int target , ArrayList<Integer> path)
    {
        if(root == null) return;
        path.add(root.val);
        printKPath(root.left, target, path);
        printKPath(root.right, target, path);
        int f = 0;
        for(int i = path.size()-1;i>=0;i--) // traversing from the last
        {
            f += path.get(i);
            if(f == target) printPath(path,i);
        }
        path.remove(path.size()-1);
    }

    // DISTRIBUTE COINS IN  A BINARY TREEE
    // VERY IMPORTANT QUESTION
    int ans;
    public int distributeCoins(TreeNode root) 
    {
        ans = 0;
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode node) 
    {
        if (node == null) return 0;
        int L = dfs(node.left);
        int R = dfs(node.right);
        ans += Math.abs(L) + Math.abs(R);
        return node.val + L + R - 1;
    }

    // MAXIMUM DIFFERENCE BW NODE AND ITS ANCESTOR
    // IMPORTANT QUESTION TO REMEMBER
    // TOP DOWN APPROACH OF RECURSION IS APPLICABLE
    // ON LEETCODE WE NEED TO TAKE THE ABSOLUTE DIFFERENCE VALUE
    // IMPORTANT TESTCASE

    public static int maxAncestorDiff(TreeNode root) 
    {
        if (root == null) 
        {
            return 0;
        }
        int[]result = new int[1];
        helper(root, root.val, root.val , result);
        return result[0];
    }

    public static void helper(TreeNode node, int curMax, int curMin , int[]result) 
    {
        if (node == null) 
        {
            return;
        }
        int possibleResult = Math.max(Math.abs(curMax - node.val), Math.abs(curMin - node.val));
        result[0] = Math.max(result[0], possibleResult);
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);
        helper(node.left, curMax, curMin , result);
        helper(node.right, curMax, curMin , result);
        return;
    }

    //GEEKS FOR GEEKS BOTTOM UP APPROACH COZ WE DO NOT NEED TO DEAL WITH ABSOLUTE VALUE
    public int maxDiff(Node root) 
    {
        int[] ans = new int[1];
        ans[0] = -(int)1e9;
        calculate(root,ans);
        return ans[0];
    }
    public int calculate(Node root , int[]ans)
    {
        if(root == null) return -(int)1e9;
        int left = calculate(root.left,ans);
        int right = calculate(root.right,ans);
        if(left == -(int)1e9 && right == -(int)1e9) return root.data;
        else if(left == -(int)1e9 || right == -(int)1e9)
        {
            ans[0]=(left== -(int)1e9)?Math.max(ans[0],root.data-right):Math.max(ans[0],root.data-left);
            return (left == -(int)1e9) ? Math.min(root.data , right) : Math.min(root.data , left);
        }
        else ans[0] = Math.max(ans[0] , Math.max(root.data-left,root.data-right));
        
        return Math.min(left,Math.min(right,root.data));
    }
    
    // SUM DIFFERENCES ACCEPTED SOLUTION
    
}