package delivery;

/*
  문제 이름: 배달
  링크: https://school.programmers.co.kr/learn/courses/30/lessons/12978
  알고리즘: 다익스트라
  자료구조: 배열, 배열 리스트
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

public class Solution {
    static final int INF = 10000000;
    static List<Node>[] list;

    public static void main(String[] args) {
        Solution solution=new Solution();
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        int result = solution.solution(N, road, K);
        System.out.println(result);
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new ArrayList[N + 1];
        int[] dist = new int[N + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int start = road[i][0];
            int end = road[i][1];
            int weight = road[i][2];

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        dijkstra(N, dist);
        for (int i = 2; i < N + 1; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }

    public static void dijkstra(int N, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];
        pq.add(new Node(1, 0));
        dist[1] = 0;
        isVisited[1] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int current = node.end;

            for (Node next : list[current]) {
                if (isVisited[next.end]) {
                    continue;
                }
                if (dist[next.end] > dist[current] + next.weight) {
                    dist[next.end] = dist[current] + next.weight;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }
}
