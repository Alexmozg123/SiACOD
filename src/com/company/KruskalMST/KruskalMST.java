package com.company.KruskalMST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST {
    private final List<WeightGraph.Edge> mst; // Все ребра включены в минимальное остовное дерево

    public KruskalMST(WeightGraph graph) {
        // Строим минимальную кучу Edge
        // минимальная куча
        PriorityQueue<WeightGraph.Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(WeightGraph.Edge::getWeight));
        mst = new ArrayList<>();

        // Kruskal
        // 1. Добавляем все ребра в самую маленькую кучу
        for (int i = 0; i < graph.getGraph().length; i++) {
            List<WeightGraph.Edge> edges = graph.getGraph()[i];
            for (WeightGraph.Edge edge : edges) {
                // Предотвращаем повторное соединение ребер
                if (edge.getFirst() < edge.getSecond()) {
                    minHeap.add(edge);
                }
            }
        }
        // 2. Создаем и проверяем множество, сохраняем связную связь между вершинами
        UnionFind unionFind = new UnionFind(graph.getVertexNum());
        // 3. Количество ребер, собранных в mst = количество вершин-1, когда собрано количество ребер минимального остовного дерева
        while (!minHeap.isEmpty() && mst.size() < graph.getVertexNum() - 1) {
            WeightGraph.Edge minEdge = minHeap.poll();
            // Определяем, соединены ли вершины в кольцо, если нет, добавляем mst
            assert minEdge != null;
            if (unionFind.findParent(minEdge.getFirst(), minEdge.getSecond())) {
                continue;
            }
            // присоединяемся к mst и проверяем объединение
            mst.add(minEdge);
            unionFind.union(minEdge.getFirst(), minEdge.getSecond());
        }
    }

    public List<WeightGraph.Edge> getMst() {
        return mst;
    }

    public int getWeight() {
        // Суммируем веса в mst и возвращаем
        int mstWeight = 0;
        for (WeightGraph.Edge edge : mst) {
            mstWeight += edge.getWeight();
        }
        return mstWeight;
    }

    public static void main(String[] args) {
        WeightGraph graph = new WeightGraph(4, false);
        graph.addEdge(0, 1, 8);
        graph.addEdge(0, 2, 9);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(2, 3, 6);

        graph.print();

        // Получаем mst
        KruskalMST kruskalMST = new KruskalMST(graph);
        System.out.println(kruskalMST.getMst());
        System.out.println(kruskalMST.getWeight());
    }
}