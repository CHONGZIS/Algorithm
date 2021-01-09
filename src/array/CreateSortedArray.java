package array;

public class CreateSortedArray {
    Node root;

    public int createSortedArray(int[] instructions) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0 ; i < instructions.length ; i++){
            if(max < instructions[i]){
                max = instructions[i];
            }
            if(min > instructions[i]) {
                min = instructions[i];
            }
        }

        root = new Node(min , max , 0);

        int res = 0;

        for(int i = 0 ; i < instructions.length ; i++){
            if(instructions[i] - 1 >= min && instructions[i] + 1 <= max){
                res += Math.min(query(root , 0 , instructions[i] - 1) ,
                        query(root , instructions[i] + 1 , max));
            }else if(instructions[i] - 1 < min){
                res += Math.min(0 , query(root , instructions[i] + 1 , max));
            }else if(instructions[i] + 1 > max){
                res += Math.min(query(root , 0 , instructions[i] - 1) , 0);
            }else {

            }

            update(root , instructions[i] , 1);

            res %= 1000000007;
        }

        return res;
    }

    public int query(Node root , int l , int r){
        if(l <= root.left && r >= root.right){
            return root.val;
        }else{

            if(root.l == null || root.r == null){
                biludNode(root);
            }

            int cur = 0;

            int mid = (root.left + root.right ) / 2;

            if(l <= mid){
                cur += query(root.l , l , r);
            }

            if(r > mid){
                cur += query(root.r , l , r);
            }

            return cur;
        }
    }

    public void biludNode(Node root){
        if(root.l != null || root.r != null){
            return;
        }else{
            int mid = (root.left + root.right) / 2;
            root.l = new Node(root.left , mid , root.val);
            root.r = new Node(mid + 1 , root.right , root.val);
        }
    }

    public void update(Node root , int index , int val){
        if(root.left == root.right && root.left == index){
            root.val += val;
            return;
        }else{
            int mid = (root.left + root.right) / 2;

            if(root.l == null || root.r == null){
                biludNode(root);
            }

            if(index <= mid){
                update(root.l , index , val);
            }

            if(index > mid){
                update(root.r , index , val);
            }

            root.val = root.l.val + root.r.val;
        }
    }
}

class Node{
    // 左区间
    int left;
    // 右区间
    int right;
    // 权值
    int val;

    // 左子节点
    Node l;
    // 右子节点
    Node r;

    public Node(int left , int right , int val){
        this.left = left;
        this.right = right;
        this.val = val;
    }

}
