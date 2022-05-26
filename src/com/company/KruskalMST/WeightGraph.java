package com.company.KruskalMST;

import java.util.LinkedList;
import java.util.List;

public class WeightGraph{

    public record Edge(int first, int second, int weight) {

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return String.format("%d - %d : %d", first, second, weight);
        }
    }

    private int edgeNum;                  // кол-во ребер
    private final int vertexNum;          // кол-во вершин
    private final List<Edge>[] graph;     // взвешенный граф, каждая вершина содержит связанный список с ребрами
    private final boolean directed;

    public WeightGraph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;

        graph = new LinkedList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public List<Edge>[] getGraph() {
        return graph;
    }

    public void addEdge(int first, int second, int weight) {
        assert first >= 0
                && second >= 0
                && first < vertexNum
                && second < vertexNum;

        List<Edge> edges1 = graph[first];
        Edge newEdge1 = new Edge(first, second, weight);
        edges1.add(newEdge1);

        if (!directed) {
            List<Edge> edges2 = graph[second];
            Edge newEdge2 = new Edge(first, second, weight);
            edges2.add(newEdge2);
        }

        edgeNum++;
    }

    public void print() {
        System.out.printf("Количество вершин:% d, Количество ребер:% d%n", vertexNum, edgeNum);
        for (List<Edge> edges : graph) {
            // Вынимаем список, связанный с каждой стороны, чтобы пройти
            for (Edge edge : edges) {
                System.out.printf("%d - %d : %d%n",
                        edge.getFirst(),
                        edge.getSecond(),
                        edge.getWeight());
            }
        }
    }
}
