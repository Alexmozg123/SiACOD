package com.company.KruskalMST;

// Данный класс необходим для объединения непересикающихся множеств
public class UnionFind {

    private final int[] parent;

    // Изначально каждая вершина представляет собой отдельное можество
    public UnionFind(int capacity) {
        parent = new int[capacity];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    /*
    * Позволяет объединить x и y в отдельное множество
    * Путем присваивания всех ключей множества x, множеству y
    * Тем самым обьединяя их в одно множество
    */
    public void union(int x, int y) {
        if (findParent(x) == findParent(y)) {
            return;
        }
        parent[findParent(x)] = findParent(y);
    }

    /*
    * Проверяет, относиться ли данные вершины к одному множеству
    */
    public int findParent(int desired) {
        if (desired == parent[desired]) {
            return desired;
        }
        if (desired != parent[desired]) {
            parent[desired] = findParent(parent[desired]);
        }
        return parent[desired];
    }

    public boolean findParent(int first, int second) {
        return findParent(first) == findParent(second);
    }
}
