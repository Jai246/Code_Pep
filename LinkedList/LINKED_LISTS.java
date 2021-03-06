import java.util.HashMap;
class ll
{
      public static class ListNode
      {
          int val = 0;
          ListNode next = null;
          ListNode(int val)
          {
            val = this.val;
          }
      }

    // WHEN WE WANT TO CHOOSE THE SECOND MIDDLE NODE OF THE TWO MIDDLE NODES
    public ListNode middleNode(ListNode head) 
    {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // WHEN WE WANT TO CHOOSE THE FIRST MIDDLE NODE OF THE TWO MIDDLE NODES
    public ListNode middleNode_(ListNode head) 
    {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // REVERSE A LINKED LIST
    public static ListNode reverseList(ListNode head) 
    {
        if(head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr!=null)
        {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // CHECK IF PALINDROME
    // WITH SAME STRATEGY WE CAN DO THE DATA REVERSE CODE
    public boolean isPalindrome(ListNode head) 
    {
        if(head == null && head.next == null) return true;
        ListNode mid = middleNode_(head);
        ListNode head2 = reverseList(mid.next);
        mid.next = null;
        while(head2!=null)
        {
            if(head.val!=head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    // REORDER LINKEDLIST
    public void reorderList(ListNode head) 
    {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode c1 = head;
        ListNode temp = middleNode_(head); // First Middle
        ListNode c2 = reverseList(temp.next);
        temp.next = null;
        ListNode f1 = null;
        ListNode f2 = null;

        while(c2!=null && c1!=null)
        {
            f1 = c1.next;
            f2 = c2.next;
            c1.next = c2;
            c1 = c1.next;
            c2 = f1;
        }
    }

    // MERGE K SORTED LIST
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null)
        {
            return (l1 == null) ? l2 : l1;
        }
        ListNode ans = null;
        ListNode c1 = l1;
        ListNode c2 = l2;
        
        while(c1!=null && c2!= null)
        {
            ListNode f1 = c1.next;
            ListNode f2 = c2.next;
            if(c1.val <= c2.val)
            {
                if(ans == null) ans = c1;
                while(f1!=null && f1.val < c2.val)
                {
                    c1 = f1;
                    f1 = c1.next;
                }
                c1.next = c2;
                c1 = c1.next;
                c2 = f1;
            }
            else if(c2.val < c1.val)
            {
                ListNode temp = c1;
                c1 = c2;
                c2 = temp;
            }
        }
        return ans;
    }

    // MERGE K SORTED LIST WITH DUMMYNODE
    public ListNode mergeTwoLists_(ListNode l1, ListNode l2) 
    {
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null)
        {
            return (l1 == null) ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while(c1!=null && c2!=null)
        {
            if(c1.val <= c2.val)
            {
                prev.next = c1;
                c1 = c1.next;
            }
            else
            {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        
        if(c1 == null) prev.next = c2;
        else prev.next = c1;
        return dummy.next;
    }

    // SORT LIST USING MERGESORT
    public ListNode sortList(ListNode head) 
    {
        if(head == null || head.next == null) return head;
        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        mid.next = null;
        return mergeTwoLists(sortList(head),sortList(nHead));               
    }

    // MERGE K LISTS
    public ListNode mergeKLists(ListNode[] lists) 
    {
        if(lists.length == 0) return null;
        
        ListNode refList = null;
        for(int i = 0;i<lists.length;i++)
        {
            refList = mergeTwoLists_(refList,lists[i]);
        }
        
        return refList;
    }

    // DETECT CYCLE IN JAVA
    public boolean hasCycle(ListNode head) 
    {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        
        return false;
    }

    // LINKEDLIST CYCLE 2 FIND THE REFRENCE NODE
    public ListNode detectCycle(ListNode head) 
    {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean res = false;
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                res = true;
                break;
            }
        }
        if(res == false) return null;
        slow = head;
        while(slow!=fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }

    // LEETCODE GET INTERSECTION NODE
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) 
    {
        if(headA==null || headB == null) return null;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        int l1 = length(headA);
        int l2 = length(headB);
        
        int l = l1>=l2?l1-l2 : l2-l1;
        while(l>0)
        {
            if(l1>l2) temp1 = temp1.next;
            else temp2 = temp2.next;
            l--;
        }
        while(temp1!=temp2)
        {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        
        return temp1;
        
    }
    public static int length(ListNode head)
    {
        int len = 0;
        ListNode temp = head;
        while(temp!=null)
        {
            len++;
            temp = temp.next;
        }
        return len;
    }

    // GET INTERSECTION NODE METHODE 2 USING FLOYDS CYCLE FINDING ALGORITHM (FINDING THE REFRENCE POINT)
    public ListNode getIntersectionNode_(ListNode headA, ListNode headB) 
    {
        ListNode temp = headA;
        while(temp.next!=null)
        {
            temp = temp.next;
        }
        temp.next = headB;
        ListNode ans = detectCycle(headA);
        temp.next = null; // Undoing the effect we added to linked list
        return ans;
    }

    // REVERSE IN GROUP OF K IMPORTANT QUESTION
    public ListNode[] reverse(ListNode node , int k)
    {
        ListNode curr = node;
        ListNode prev = null;
        if(length(node)<k) return new ListNode[]{node,node,node};
        ListNode temp = null;
        while(k-- > 0)
        {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            
        }
        return new ListNode[]{node,temp,prev};
    }
    public ListNode reverseKGroup(ListNode head, int k) 
    {
        if(k == 1 || head == null) return head;
        ListNode ret = null;
        ListNode c1 = new ListNode(-1);
        c1.next = head;
        while(c1.next!=null)
        {
            ListNode[] ans = reverse(c1.next,k);
            if(ret == null) ret = ans[2];
            if(ans[0] == ans[1] && ans[1] == ans[2])
            {
                c1.next = ans[1];
                break;
            }
            c1.next = ans[2];
            c1 = ans[0];
            c1.next = ans[1];
        }
        return ret;
    }

    public static ListNode tHead = null;
    public static ListNode tTail = null;
    
    //REVERSE IN GROUP OF K APPROACH 2 REVOLUTIONARY APPROACH CHECK ITS VIDEO
    
    public static void addFirst(ListNode head)
    {
        if(tHead == null){
            tHead = head;
            tTail = head;
        }
        else{
            head.next = tHead;
            tHead = head;
        }
    }
    public ListNode reverseKGroup2(ListNode head, int k)
    {
        ListNode oHead = new ListNode(-1);
        ListNode oTail = oHead;
        int tempk = k;
        ListNode curr = head;
        int length = length(head);
        while(length>0)
        {
            if(length < k)
            {
                oTail.next = curr;
                length = 0;
                break;
            }
            while(length>0 && tempk-- > 0)
            {
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                length--;
            }
            tempk = k;
            oTail.next = tHead;
            oTail = tTail;
            tTail = null;
            tHead = null;
        }
        return oHead.next;
    }

    // REVERSE LINKEDLIST 2

    public static ListNode tHead = null;
    public static ListNode tTail = null;
    
    
    public static void addFirst(ListNode head)
    {
        if(tHead == null){
            tHead = head;
            tTail = head;
        }
        else{
            head.next = tHead;
            tHead = head;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right)
    {
        if(head == null || left == right || head.next == null) return head;
        ListNode curr = head;
        ListNode prev = new ListNode(-1);
        prev.next = head;
        head = prev;
        ListNode forw = null;
        int k = 1;
        while(curr!=null)
        {
            while(k>= left && k<= right)
            {
                if(prev.next!=null) prev.next = null;
                forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
                k++;
            }
            if(k > right)
            {
                prev.next = tHead;
                tTail.next = forw;
                break;
            }
            prev = prev.next;
            curr = curr.next;
            k++;
        }
        return head.next;
    }

    // LEETCODE 138 HASHMAP APPROACH FOR COPY LIST WITH RANDOM POINTER

    public static class Node
    {
        int val = 0;
        Node next = null;
        Node random = null;
        Node(int val)
        {
            this.val = val;
        }
    }
    public Node copyRandomList(Node head) 
    {
        if(head == null) return null;
        Node curr = head;
        HashMap<Node,Node> map = new HashMap<>();
        while(curr!=null)
        {
            if(!map.containsKey(curr))
            {
                map.put(curr,new Node(curr.val));
            }

            if(curr.next!=null)
            {
                if(!map.containsKey(curr.next))
                {
                    map.put(curr.next , new Node(curr.next.val));
                }
                map.get(curr).next = map.get(curr.next);
            }

            if(curr.random!=null)
            {
                if(!map.containsKey(curr.random))
                {
                    map.put(curr.random , new Node(curr.random.val));
                }
                map.get(curr).random = map.get(curr.random);
            }
            curr = curr.next;
        }
        return map.get(head);
    }

    
    // IMPLEMENTATION OF LRU CACHE
    // MAKE VIDEO FOR REMEMBERING THE IMPORTANT POINTS FOR LRU CACHE
    // VERY SIMPLE IMPLEMENTATION USING TWO DUMMY NODES
    public class LRUNode
    {
        int val = 0;
        int key = 0;
        LRUNode next = null;
        LRUNode prev = null;
        LRUNode(int key , int val)
        {
            this.val = val;
            this.key = key;
        }
    }
    public HashMap<Integer , LRUNode> map = null;
    public LRUNode head = null;
    public LRUNode tail = null;
    public int maxCapacity = 0;
    
    public LRUCache(int capacity) 
    {
        maxCapacity = capacity;
        map = new HashMap<>();
        head = new LRUNode(-1,-1);
        tail = new LRUNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) 
    {
        if(!map.containsKey(key)) return -1;
        
        LRUNode temp = map.get(key);
        LRUNode prev = temp.prev;
        LRUNode next = temp.next;
        temp.next = null;
        temp.prev = null;
        prev.next = next;
        next.prev = prev;
        
        relocate(temp);
        
        return temp.val;
    }
    
    public void put(int key, int value)
    {
        LRUNode temp = null;
        if(!map.containsKey(key))
        {
            temp = new LRUNode(key,value);
            map.put(key,temp);
            
            if(maxCapacity == 0)
            {
                LRUNode prev = head;
                LRUNode next = head.next.next;
                LRUNode rem = head.next;
                prev.next = next;
                next.prev = prev;
                map.remove(rem.key);
                maxCapacity++;
            }
            maxCapacity--;
        }
        else
        {
            temp = map.get(key);
            temp.val = value;
            map.put(key,temp);
            LRUNode prev = temp.prev;
            LRUNode next = temp.next;
            temp.next = null;
            temp.prev = null;
            prev.next = next;
            next.prev = prev;
        }
        relocate(temp);
    }
    
    public void relocate(LRUNode temp)
    {
        LRUNode prev = tail.prev;
        LRUNode next = tail;
        
        prev.next = temp;
        temp.prev = prev;
        temp.next = next;
        next.prev = temp;
    }

    // COPY LIST WITH RANDOM POINTER APPROACH 2 WITHOUT HASHMAP
    public static Node headP1 = null;
    public static Node headP2 = null;
    public static void  createAttachedCopy(Node head)
    {
        while(head!=null)
        {
            Node forw = head.next;
            head.next = new Node(head.val);
            head.next.next = forw;
            head = forw;
        }
    }
    public static void extractCopyNode()
    {
        Node curr1 = headP1;
        Node curr2 = headP2;
        while(curr1!=null && curr2!=null)
        {
            curr1.next = curr2.next;
            curr1 = curr1.next;
            if(curr1!=null && curr2!=null) curr2.next = curr1.next;
            curr2 = curr2.next;
        }
    }
    public Node copyRandomList_2(Node head)
    {
        if(head == null) return head;
        headP2 = head;
        headP1 = head;
        createAttachedCopy(head);
        while(head!=null)
        {
            Node random = head.random;
            if(random!=null) head.next.random = random.next;
            head = head.next.next; 
        }
        headP2 = headP2.next;
        extractCopyNode();
        Node e = headP1;
        while(e!=null)
        {
            System.out.print(e.val + " ");
            e = e.next;
        }
        return headP2;
    }



    // 328. Odd Even Linked List
    public static ListNode divide(int N,ListNode head)
    {
        ListNode dummyOdd = new ListNode(-1);
        ListNode dummyEven = new ListNode(-1);
        ListNode odd = dummyOdd;
        ListNode even = dummyEven;
        ListNode curr = head;
        while (curr != null) 
        {
            if (curr.val % 2 != 0) 
            {
                odd.next = curr;
                odd = odd.next;
            } 
            else 
            {
                even.next = curr;
                even = even.next;
            }
            curr = curr.next;
        }
        even.next = dummyOdd.next;
        odd.next = null;
        return dummyEven.next;
    }

    // UNFOLD A LINKEDLIST
    public static ListNode UnfoldLL(ListNode head)
    {
        ListNode nHead = new ListNode(-1);
        ListNode temp = head.next;
        ListNode prev = head;
        ListNode Nprev = nHead;
        while(temp!=null)
        {
            Nprev.next = temp;
            Nprev = Nprev.next;
            prev.next = temp.next;
            if(temp.next!=null) prev = temp.next;
            temp.next = null;
            temp = prev.next;
        }
        nHead = nHead.next;
        nHead = reverseList(nHead);
        prev.next = nHead;
        return head;
    }

    // SEGREGATE ODD EVEN NODES LEETCODE 328
    public ListNode oddEvenList(ListNode head) 
    {
        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);

        ListNode e = even;
        ListNode o = odd;

        while(head != null)
        {
            if(head.val%2 == 0)
            {
                ListNode forw = head.next;
                e.next = head;
                head.next = null;
                e = e.next;
                head = forw;
            }
            else
            {
                ListNode forw = head.next;
                o.next = head;
                head.next = null;
                o = o.next;
                head = forw;
            }
        }

        odd = odd.next;
        even = even.next;
        o.next = even;
        return odd;
    }

    // ADDITION OF TWO LINKEDLISTS WITHOUT EXTRA SPACE LEETCODE 
    public static ListNode  addTwoNumbers(ListNode l1 , ListNode l2)
    {
        ListNode ans = new ListNode(-1);
        ListNode a = ans;
        ListNode head1 = reverseList(l1);
        ListNode head2 = reverseList(l2);
        ListNode c1 = head1;
        ListNode c2 = head2;
        int carry = 0;
        while(c1!=null||c2!=null)
        {
            if(c1!=null && c2!=null)
            {
                int sum = carry + c1.val + c2.val;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c1 = c1.next;
                c2 = c2.next;
            }
            else if(c1 != null && c2 == null)
            {
                int sum = c1.val + carry;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c1 = c1.next;
            }
            else if(c1 == null && c2!=null)
            {
                int sum = c2.val + carry;
                int val = sum % 10;
                carry = sum / 10;
                a.next = new ListNode(val);
                a = a.next;
                c2 = c2.next;
            }
        }
        if(carry == 1) a.next = new ListNode(carry);
        return reverseList(ans.next);
    }

    // ADDITION OF TWO LINKED LISTS WITH EXTRA SPACE (RECURSION STACK) WITHOUT REVERSING APPROACH
    public ListNode ans = new ListNode(-1);
    public ListNode  addTwoNumbers_2(ListNode l1 , ListNode l2)
    {
        int carry = addRec(l1,length(l1),l2,length(l2));
        if(carry == 1)
        {
            ListNode temp = new ListNode(1);
            temp.next = ans;
            ans = temp;
        }
        return ans;
    }
    
    
    public int addRec(ListNode l1 ,int pos1 ,  ListNode l2 ,int pos2)
    {
        if(l1 == null && l2 == null) return 0;
        if(pos1 > pos2)
        {
            int carry = addRec(l1.next, pos1-1, l2, pos2);
            int sum = carry + l1.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        else if(pos2 > pos1)
        {
            int carry = addRec(l1, pos1, l2.next, pos2-1);
            int sum = carry + l2.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        else if(pos1 == pos2)
        {
            int carry = addRec(l1.next, pos1-1, l2.next, pos2-1);
            int sum = carry + l1.val + l2.val;
            int val = sum%10;
            System.out.println(val);
            if(ans.val == -1)
            {
                ans = new ListNode(val);
                return sum/10;
            }
            else
            {
                ListNode temp = new ListNode(val);
                temp.next = ans;
                ans = temp;
                return sum/10;
            }
        }
        return 0;
    }

    // SUBTRACT TWO LINKED LISTS NO EXTRA SPACE SUBMITTED ON PEPCODING PORTAL
    // IMPORTANT
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2)
    {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode ans = new ListNode(-1);
        ListNode itr = ans;
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode c1 = l1 , c2 = l2;
        int borrow = 0;

        while(c1!=null)
        {
            int diff = borrow + c1.val - (c2!=null ? c2.val : 0);
            if(diff < 0)
            {
                borrow = -1;
                diff+=10;
            }
            else borrow = 0;
            itr.next = new ListNode(diff);
            itr = itr.next;
            c1 = c1.next;
            if(c2!=null) c2 = c2.next;
        }
        ans =  reverseList(ans.next);
        while(ans!=null && ans.val == 0) ans = ans.next;
        return (ans == null) ? new ListNode(0) : ans;
    }
    
    // MULTIPLY TWO LINKEDLISTS SUBMITTED ON PEPCODING PORTAL
    // IMPORTANT
    public static ListNode addTwoLL(ListNode l1 , ListNode l2)
    {
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        int carry = 0;
        while(c1!=null || c2!=null || carry!=0)
        {
            int sum = carry + ((c1!=null) ? c1.val : 0) + ((c2!=null) ? c2.val : 0);
            carry = sum/10;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            if(c1!=null) c1 = c1.next;
            if(c2!=null) c2 = c2.next;
        }
        return dummy.next;
    }
    
    
    public static ListNode multiplyWithDigit(ListNode head , int digit)
    {
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        int carry = 0;
        while(head != null || carry!=0) // here we are handeling the last carry case as well
        {
            int sum = carry + ((head!=null) ?  head.val : 0) * digit;
            carry = sum/10;
            ans.next = new ListNode(sum%10);
            ans = ans.next;
            if(head!=null) head = head.next;
        }
        return dummy.next;
    }
    
    
    public static ListNode multiplyHelperListNode(ListNode l1 , ListNode l2)
    {
        int count = 1;
        ListNode ans = multiplyWithDigit(l1, l2.val);
        l2 = l2.next;
        while(l2!=null)
        {
            ListNode multiply = multiplyWithDigit(l1, l2.val);
            l2 = l2.next;
            int val = 0;
            while(val < count)
            {
                ListNode temp = new ListNode(0);
                temp.next = multiply;
                multiply = temp;
                val++;
            }
            count++;
            ans = addTwoLL(multiply,ans);
        }
        return reverseList(ans);
    }
    
    
    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) 
    {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        return multiplyHelperListNode(l1, l2);
    }

    // LEETCODE 83 REMOVE DUPLICATES FORM SORTED LIST
    public ListNode deleteDuplicates(ListNode head) 
    {
        if(head == null || head.next == null) return head;
        ListNode ptr = head.next;
        ListNode prev = head;
        while(ptr!=null)
        {
            if(prev.val!=ptr.val)
            {
                prev = ptr;
                ptr = ptr.next;
            }
            else
            {
                ListNode forw = ptr.next;
                prev.next = forw;
                ptr.next = null;
                ptr = forw;
            }
        }
        return head;
    }

    // LEETCODE 82 REMOVE DUPLICATES 
    // || VERY IMPORTANT Using Sentential(Dummy Node Approach)
    // Very Easy to handel edge cases

    
    public ListNode deleteDuplicates(ListNode head) 
    {
        ListNode sentinel = new ListNode(0, head);

        ListNode pred = sentinel;
        
        while (head != null) 
        {

            if (head.next != null && head.val == head.next.val) 
            {
                while (head.next != null && head.val == head.next.val) 
                {
                    head = head.next;    
                }
                pred.next = head.next;     
            } 
            else 
            {
                pred = pred.next;    
            }
                
            head = head.next;    
        }  
        return sentinel.next;
    }

    // LEETCODE 86 PARTITION LIST
    public ListNode partition(ListNode head, int x) 
    {
        if(head == null || head.next == null) return head;
        ListNode dummy1 = new ListNode(-1);
        ListNode a1 = dummy1;
        ListNode dummy2 = new ListNode(-1);
        ListNode a2 = dummy2;
        ListNode ptr = head;
        while(ptr!=null)
        {
            if(ptr.val < x)
            {
                ListNode forw = ptr.next;
                a1.next = ptr;
                a1 = a1.next;
                a1.next = null;
                ptr = forw;
            }
            else
            {
                ListNode forw = ptr.next;
                a2.next = ptr;
                a2 = a2.next;
                a2.next = null;
                ptr = forw;
            }
        }
        a1.next = dummy2.next;
        return dummy1.next;
    }

    // LEETCODE 1721 SWAPPING TWO NODES IN A LINKED LIST
    // IMPORTANT QUESTION
    // IMPORTANT TESTCASES
    // [100,90]
    // 2
    
    // [100,90,100]
    // 3
    public int length(ListNode head)
    {
        int count = 0;
        while(head!=null)
        {
            count++;
            head = head.next;
        }
        return count;
    }
    public ListNode swapNodes(ListNode head, int k) 
    {
        ListNode ret = head;
        k = k - 1;
        int length = length(head);
        
        int start = k;
        int end = length-k-1;
        
        if(end < start)
        {
            int temp = end;
            end = start;
            start = temp;
        }
        
        ListNode c1 = null;
        ListNode c2 = null;
        
        int c = 0;
        
        while(head!=null)
        {
            if(start == c) c1 = head;
            if(end == c) c2 = head;
            head = head.next;
            c++;
        }
        
        int val = c1.val;
        c1.val = c2.val;
        c2.val = val;
        
        return ret;
    }

    //LEETCODE 725 SPLIT LINKEDLISTS IN PARTS
    // IMPORTANT QUESTION
    public ListNode[] splitListToParts(ListNode head, int k) 
    {
        int size = 0;
        ListNode cur = head;
        ListNode[] result = new ListNode[k];
        
        while(cur != null)
        {
            cur = cur.next;
            size++;
        }
        
        int partSize = size/k;
        int remainder = size%k;
        cur = head;
        int i = 0;
        while(cur != null && i < k)
        {
            result[i] = cur;
            i++;
            if(remainder > 0 && partSize > 0)
            {
                remainder--;
                cur = cur.next;
            }
            int curPartSize = 0;
            
            while(curPartSize < partSize-1 && cur != null)
            {
                curPartSize++;
                cur = cur.next;
            }
            
            if(cur != null)
            {
                ListNode next = cur.next;
                cur.next = null;
                cur = next; 
            }
        }
        return result;
    }

    // GFG LINKED LIST THAT IS SORTED ALTERNATINGLY
    public ListNode sort(ListNode head)
   {
       if(head == null || head.next == null) return head;
        ListNode h2 = new ListNode(-1);
        ListNode b = h2;
        ListNode h1 = new ListNode(-1);
        ListNode a = h1;
        int count = 0;
        while(head!=null)
        {
            ListNode forw = head.next;
            if(count%2 == 0)
            {
                a.next = head;
                head.next = null;
                head = forw;
                a = a.next;
            }
            else
            {
                b.next = head;
                head.next = null;
                head = forw;
                b = b.next;
            }
            count++;
        }
        h1 = h1.next;
        h2 = h2.next;
        
        if(h1.val >= a.val) // reverse
        {
            ListNode temp1 = h1;
            h1 = reverseList(h1);
            a = temp1;
        }
        if(h2.val >= b.val) // reverse
        {
            ListNode temp1 = h2;
            h2 = reverseList(h2);
            b = temp1;
        }
        
        return mergeTwoLists(h1, h2);
   }

   // MERGE LISTS ALTERNATINGLY GFG
   // PROBLEM WITH THE QUESTION ON THE SITE
   public void mergeAlt(Node head1, Node head2)
    {
        Node dummy = new Node(-1);
        Node a = dummy;
        int count = 0;
        while(head1!=null && head2!=null)
        {
            if(count%2==0)
            {
                a.next = head1;
                head1 = head1.next;
            }
            else
            {
                a.next = head2;
                head2 = head2.next;
            }
            a.next.next = null;
            a = a.next;
            count++;
        }
        a.next = head1; // one of them will be null at the end
        a.next = head2;
        dummy = dummy.next;
        while(dummy!=null)
        {
            System.out.print(dummy.val + " ");
            dummy = dummy.next;
        }
    }
    // Split Singly Linked List Alternatingly 
    // ERROR ON GFG
    public void alternatingSplitList(Node head)
    {
        Node dummy1 = new Node(-1);
        Node a = dummy1;
        Node dummy2 = new Node(-1);
        Node b = dummy2;
        int count = 0;
        
        while(head!=null)
        {
            if(count%2 == 0)
            {
                a.next = head;
                head = head.next;
                a = a.next;
                a.next = null;
            }
            else
            {
                b.next = head;
                head = head.next;
                b = b.next;
                b.next = null;
            }
            count++;
        }
        dummy1 = dummy1.next;
        dummy2 = dummy2.next;
        while(dummy1!=null)
        {
            System.out.print(dummy1.val + " ");
            dummy1 = dummy1.next;
        }
        System.out.println();
        while(dummy2!=null)
        {
            System.out.print(dummy2.val + " ");
            dummy2 = dummy2.next;
        }
    }

    // split a circular linked list
    public void splitList(circular_LinkedList list)
    {
        Node head = list.head;
        list.head1 = head;
        Node slow = head;
        Node fast = head.next;
        
        while ( fast != head && fast.next != head ){
        fast = fast.next.next;
        slow = slow.next;
        }
        list.head2 = slow.next;
        slow.next = list.head1;
        Node curr = list.head2;
        
        while(curr.next != head) 
        {
            curr = curr.next;
        }
        curr.next = list.head2;
    }

    // DELETE MID GEEKS FOR GEEKS FOR GEEKS
    public static Node deleteMid(Node head) 
    {
        if(head == null || head.next == null) return null;
        Node slow = head;
        Node fast = head;
        Node temp = null;

        while(fast!=null && fast.next!=null)
        {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = slow.next;
        slow.next = null;
        return head;
    }

    // Delete nodes having greater value on right
    // Important questions on geeks for geeksforgeeks
    // simply reverse the linkedlist and every time find greater elements;
    // plz note that last element is always getting included
    public Node compute(Node head)
    {
        if(head == null) return head;
        head = reverseList(head);
        int min = -1;
        Node temp = new Node(-1);
        Node a = temp;
        while(head!=null)
        {
            if(head.data >= min)
            {
                Node forw = head.next;
                min = head.data;
                a.next = head;
                head.next = null;
                head = forw;
                a = a.next;
            }
            else
            {
                Node forw = head.next;
                head.next = null;
                head = forw;
            }
        }
        return reverseList(temp.next);
    }
    
    // DELETION AND REVERSE IN A CIRCULAR LINKED LIST
    public static Node deleteNode(Node head,int d)
    {
        if(head == null || (head.next == head && head.data == d)) return null;
        Node check = head;
        Node prev = null;
        while(head.next!=check)// we will never be deleting first and last element
        {// IF SO THEN WE CAN USE THE BELOW LISTED METHODE
            if(head.data == d)
            {
                prev.next = head.next;
                head.next = null;
                head = prev.next;
                break;
            }
            prev = head;
            head = head.next;
        }
        //return reverse(check);
        return check;
    }

    public static Node reverse(Node head)
    {
        if(head == null || head.next == head) return head;
        Node check = head;
        Node prev = null;
        Node forw = null;
        while(forw!=check)
        {
            forw = head.next;
            head.next = prev;
            prev = head;
            if(forw!=check) head = forw;
        }
        check.next = head;
        return prev;
    }


    // geeksforgeeks Delete N nodes after M nodes of a linked list 
    static void linkdelete(Node head, int M, int N)
    {
        Node temp = head;
        while(temp!=null)
        {
            temp = deleteNM(temp,M,N);
        }
    }
    static Node deleteNM(Node head,int M,int N)
    {
       if(head == null) return head;
        Node temp = head;
        Node prev = null;
        while(temp!=null && M-- > 0)
        {
            prev = temp;
            temp = temp.next;
        }
        Node prev2 = null;
        while(temp!=null && N > 0)
        {
            prev2 = temp;
            temp = temp.next;
            N--;
        }
        prev.next = temp;
        if(prev2!=null) prev2.next = null;
        // doing this because second while loop will not work in the below testcase
        // 6 1
        // 1 2 3 4 5 6
        return temp;
    }

    // Geeks For Geeks 
    // Delete without head pointer 
    public void deleteNode(Node del)
    {
        if(del == null) return;
        del.data = del.next.data;
        if(del.next.next!=null)
        {
            Node forw = del.next;
            del.next = del.next.next;
            forw.next = null;
        }
        else del.next = null;
    }

    // DELETE NODE IN A DOUBLY LINKED LIST FOR THE GIVEN POSITIONS
    Node deleteNodeDoubly(Node head,int x)
    {
        if(head == null || (head.next == null && head.data == x)) return null;
        if(x == 1) // We CAn handle this case with dummy node
        {
            Node forw = head.next;
            head.next = null;
            forw.prev = null;
            head = forw;
            return head;
        }
        Node temp = head;
        int pos = 1;
        while(temp!=null)
        {
            if(pos++ == x)
            {
                Node prev = temp.prev;
                Node forw = temp.next;
                
                prev.next = null;
                if(forw!=null) forw.prev = null;
                temp.prev = null;
                temp.next = null;
                prev.next = forw;
                if(forw!=null) forw.prev = prev;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    // GEEKS FOR GEEKS Doubly linked list Insertion at given position
    public void addNode(Node head, int pos, int data)
	{
	    Node temp = head;
	    while(pos-- > 0)
	    {
	        temp = temp.next;
	    }
	    if(temp.next == null)
	    {
	        Node n = new Node(data);
	        temp.next = n;
	        n.prev = temp;
	    }
	    else
	    {
	        Node n = new Node(data);
	        Node next = temp.next;
	        n.next = next;
	        next.prev = n;
	        temp.next = n;
	        n.prev = temp;
	    }
	}

    // MERGE SORT ON DOUBLY LINKED LIST
    public static Node mergeTwoSorted(Node head1 , Node head2)
    {
        if(head1 == null && head2 == null) return null;
        Node dummy = new Node(-1);
        Node a = dummy;
        while(head1!=null && head2!=null)
        {
            if(head1.data < head2.data)
            {
                a.next = head1;
                head2.prev = null;
                head1.prev = a;
                head1 = head1.next;
                a = a.next;
            }
            else
            {
                a.next = head2;
                head1.prev = null;
                head2.prev = a;
                head2 = head2.next;
                a = a.next;
            }
        }
        if(head1!=null)
        {
            a.next = head1;
            head1.prev = a;
        }
        else if(head2!=null)
        {
            a.next = head2;
            head2.prev = a;
        }
        dummy.next.prev = null;
        return dummy.next;
    }
    public static Node sort(Node head)
    {
        if(head.next == null) return head;
        Node mid = mid(head);
        Node head2 = mid.next;
        head2.prev = null;
        mid.next = null;
        head = sort(head);
        head2 = sort(head2);
        return mergeTwoSorted(head,head2);
    }
    public static Node sortDoubly(Node head)
    {
        if(head == null) return null;
        return sort(head);
    }

    // Insert in Middle of Linked List 
    public Node insertInMid(Node head, int data)
    {
        if(head == null) return new Node(data);
        if(head.next == null)
        {
            head.next = new Node(data);
            return head;
        }
        Node mid = mid(head);
        Node temp = new Node(data);
        temp.next = mid.next;
        mid.next = temp;
        return head;
    }

    // Sorted insert for circular linked list
    // IMPORTANT
    public static Node sortedInsert(Node head,int data)
    {
        Node dummy = new Node(-1);
        dummy.next = head;
        Node prev = head;
        
        while(prev.next != dummy.next) prev = prev.next;
        
        if(data <= head.data)
        {
            Node d = new Node(data);
            d.next = head;
            prev.next = d;
            return d;
        }
        
        Node t = new Node(-1);
        prev.next = t;
        t.next = head;
        prev = t;
        
        while(data > head.data && head.data!=-1)
        {
            prev = head;
            head = head.next;
        }
        
        Node val = new Node(data);
        prev.next = val;
        val.next = head;
        
        while(prev.next.data!=-1) prev = prev.next;
        
        prev.next = prev.next.next;
        return dummy.next;
    }


    // Remove duplicate element from sorted Linked List 
    public Node removeDuplicates(Node head)
    {
        if(head == null || head.next == null) return head;
        Node prev = head;
        Node temp = head.next;
        while(temp!=null)
        {
            if(prev.data == temp.data)
            {
                prev.next = temp.next;
                temp.next = null;
                temp = prev.next;
            }
            else
            {
                prev = temp;
                temp = temp.next;
            }
        }
        return head;
    }
    

    // LFU CACHE
    public class node
    {
        int data;
        int val;
        int freq;
        node next;
        node prev;
        node(){
        }
        node(int data , int val , int freq){
            this.data = data;
            this.freq = freq;
            this.val = val;
        }
    }
    
    public HashMap<Integer,node[]> freq = new HashMap<>();
    public HashMap<Integer,node> map = new HashMap<>();
    public int minFreq;
    public int capacity;
    public int maxCapacity;
    public LFUCache(int capacity) 
    {
        freq = new HashMap<>();
        map = new HashMap<>();
        this.capacity = capacity;
        maxCapacity = capacity;
        minFreq = 0;
    }
    
    public int get(int key) 
    {
        
        // if(key == 4) System.out.println(map.toString());
        if(!map.containsKey(key) || minFreq == 0 || maxCapacity == 0) return -1;
        
        node rem  = map.get(key);

        node prev = rem.prev;
        node next = rem.next;

        if(prev.val == -1 && next.val == -1 && rem.freq == minFreq) minFreq++;

        prev.next = next;
        next.prev = prev;

        if(!freq.containsKey(rem.freq+1))
        {
            node first = new node(-1,-1,0);
            node last = new node(-1,-1,0);
            first.next = last;
            last.prev = first;
            freq.put(rem.freq+1,new node[]{first,last});
        }

        next = freq.get(rem.freq+1)[1];
        prev = next.prev;

        prev.next = rem;
        rem.prev = prev;
        rem.next = next;
        next.prev = rem;
        rem.freq++;
        return rem.val;
    }
    
    public void put(int key, int value)
    {
        if(maxCapacity == 0) return;
        
        if(!map.containsKey(key))
        {
            capacity--;
            
            if(capacity < 0)
            {
                node first = freq.get(minFreq)[0];
                node last = first.next.next;
                int remData = first.next.data;
                map.remove(remData);                
                first.next = last;
                last.prev = first;
                capacity++;
            }
            
            if(!freq.containsKey(1)) 
            {
                node first = new node(-1,-1,0);
                node last = new node(-1,-1,0);
                first.next = last;
                last.prev = first;
                freq.put(1,new node[]{first,last});
            }
            
            node next = freq.get(1)[1];
            node prev = next.prev;

            node add = new node(key,value,1);
            prev.next = add;
            add.prev = prev;
            add.next = next;
            next.prev = add;
            map.put(key,add);
            minFreq = 1;
        }
        else
        {
            node rem  = map.get(key);
            rem.val = value;
            
            node prev = rem.prev;
            node next = rem.next;
            
            if(prev.val == -1 && next.val == -1 && rem.freq == minFreq) minFreq++;
            
            prev.next = next;
            next.prev = prev;
            
            if(!freq.containsKey(rem.freq+1)) 
            {
                node first = new node(-1,-1,0);
                node last = new node(-1,-1,0);
                first.next = last;
                last.prev = first;
                freq.put(rem.freq+1,new node[]{first,last});
            }
            
            rem.freq++;
            
            next = freq.get(rem.freq)[1];
            prev = next.prev;

            prev.next = rem;
            rem.prev = prev;
            rem.next = next;
            next.prev = rem;
        }
    }
}