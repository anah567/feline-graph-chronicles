package mision4.algoritmo;

import app.EntradaInvalidaException;

public final class UnionFind {

    private final int[] parent;
    private final int[] size;


    /*
     * Estructura Union-Find (Disjoint Set)
     *
     * Complejidad temporal:
     * - find: O(α(V)) amortizado
     * - union: O(α(V)) amortizado
     *
     * Complejidad espacial: O(V)
     *
     * Union-Find se utiliza junto con Kruskal para mantener los componentes
     * conectados del grafo y determinar si agregar una nueva arista produciría
     * un ciclo.
     *
     * La compresión de caminos hace que los nodos apunten progresivamente
     * hacia la raíz de su componente, haciendo más eficientes las búsquedas.
     *
     * La unión por tamaño conecta el componente más pequeño al componente
     * más grande, evitando árboles innecesariamente altos.
     *
     * La combinación de ambas optimizaciones permite que las operaciones
     * find y union sean muy eficientes.
     */

    public UnionFind(int n) {

        if (n < 1) {
            throw new EntradaInvalidaException(
                    "N debe ser mayor o igual a 1"
            );
        }

        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {

        validateIndex(x);

        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    public boolean union(
            int a,
            int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        if (size[rootA] < size[rootB]) {

            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];

        return true;
    }

    private void validateIndex(int x) {

        if (x < 0 || x >= parent.length) {
            throw new EntradaInvalidaException(
                    "Indice fuera de rango"
            );
        }
    }
}