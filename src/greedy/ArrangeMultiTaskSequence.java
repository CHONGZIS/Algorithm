package greedy;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * 题目：现在一共有N个待执行的任务，每个任务需要Pi的时间完成执行。
 * 同时任务之间可能会有一些依赖关系，比如任务1可能依赖任务2和任务3，那么任务1必须等任务2和任务3执行完成后才能开始执行。为了最小化任务的平均返回时长，请安排所有任务的执行顺序。假设在零时刻，所有N个任务已到达系统。
 * 知识点：贪心（多个可能并列的任务，优化处理符合题目要求的任务） + 拓扑（前后有依赖关系，后者必须在前者执行结束才开始这种题目考虑拓扑结构）
 * 思路：将没有依赖关系的任务存入集合，先处理没有依赖关系的任务，执行任务之后再更新没有依赖关系的任务集合，直至全部执行完成，
 * 依赖利用拓扑结构表示，最小化任务可以通过先处理集合中耗时较小的任务，这样可以更快的释放有依赖关系的任务进入无依赖任务集合，
 */

public class ArrangeMultiTaskSequence {
    static class Task {
        int sequence;
        int time;

        public Task(int seq, int time) {
            this.sequence = seq;
            this.time = time;
        }
    }

    /**
     * 输入样例：
     * 5（任务个数） 6（任务间依赖的个数）
     * 1 2 1 1 1 （各个任务执行的时间）
     * 1 2 （任务2 依赖任务1）
     * 1 3 …
     * 1 4
     * 2 5
     * 3 5
     * 4 5
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int n = in.nextInt();
        Task[] tasks = new Task[num + 1];
        for (int i = 1; i <= num; i++) {
            tasks[i] = new Task(i, in.nextInt());
        }
        // 图用map结构体，(节点,下一节点的集合)
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[num + 1];

        for (int i = 0; i < n; i++) {
            int preSeq = in.nextInt();
            int curSeq = in.nextInt();
            if (graph.containsKey(preSeq)) {
                graph.get(preSeq).add(curSeq);
            } else {
                ArrayList<Integer> nexts = new ArrayList<>();
                nexts.add(curSeq);
                graph.put(preSeq, nexts);
            }
            inDegree[curSeq]++;
        }

        System.out.println(processTask(inDegree, tasks, graph));
    }

    private static List<Integer> processTask(int[] inDegree, Task[] tasks, HashMap<Integer, List<Integer>> graph) {
        // 用堆存储不依赖其他任务的任务（入度==0），按照从耗时小到大的顺序
        List<Integer> taskList = new ArrayList<>();
        // 1.建堆
        PriorityQueue<Task> processDump = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.time - o2.time;
            }
        });

        // 2.先遍历indegree数组，将入度为0的先处理
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                processDump.offer(tasks[i]); //
            }
        }

        // 3.处理堆中的任务，同时更新剩下任务中的依赖关系，处理任务a,a对应的子节点入度减少1，判断子节点入度是否为0，若为0则进入堆等待处理
        while (!processDump.isEmpty()) {
            Task processedTask = processDump.poll();
            int processSquence = processedTask.sequence;
            taskList.add(processSquence);
            if (graph.containsKey(processSquence)) {
                for (int child : graph.get(processSquence)) {
                    if (--inDegree[child] == 0) {
                        processDump.offer(tasks[child]);
                    }
                }
            }
        }
        return taskList;
    }
}
