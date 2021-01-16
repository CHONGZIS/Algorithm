package array;

import java.util.*;

public class RemoveStones {
    public int removeStones(int[][] stones) {
        // 又是图又是并查集，学不会都不行了
        // 图法：根据同行或者同列存在石头存在，就可以移除的规则
        // 可以将所有石头，分成几堆石头（连通分量）；一堆石头移除之后只会剩下一个，那么结果出来了，移除的石数 = 总石头数 - 石头堆数
        // 1.构建图，同行或者同列的可以理解为两个石头之间有边
        List<List<Integer>> edges = new ArrayList<>(); // edge.get(i) ： 表示 顶点i相邻的所有顶点集合
        int stoneNum = stones.length;
        for (int i = 0; i < stoneNum; i++) {
            edges.add(new ArrayList<>());
            for (int j = 0; j < stoneNum; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) { // 不懂
                    edges.get(i).add(j);
                }
            }
        }
        boolean[] visited = new boolean[stoneNum];
        int num = 0;
        for (int i = 0; i < stoneNum; i++) {
            if (!visited[i]) {
                dfs(edges, i, visited);
                num++;
            }
        }
        return stoneNum - num;
    }

    private void dfs(List<List<Integer>>edges, int cur, boolean[] visited) {
        visited[cur] = true;
        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                dfs(edges, next, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,2},{1,1},{2,0},{2,2}};
        new RemoveStones().removeStones(stones);
    }
}
