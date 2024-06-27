// Código baseado em: https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

import java.util.*;

public class TopologicalSort {
  public static List<Integer> topologicalSort(List<List<Integer>> adj, int V, int minVertex) {
    // Criação de uma lista para armazenar o grau de entrada de cada vértice
    int[] indegree = new int[V];
    // Preenchendo o grau de entrada de cada vértice
    for (List<Integer> adjList : adj) {
      for (int vertex : adjList) {
        indegree[vertex - minVertex]++;
      }
    }

    // Criação de uma fila para armazenar os vértices com grau de entrada 0
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (indegree[i] == 0) {
        q.add(i + minVertex);
      }
    }

    List<Integer> result = new ArrayList<>();
    // Enquanto a fila não estiver vazia, remove o vértice da fila e adiciona na
    // lista,
    // decrementando o grau de entrada dos vértices adjacentes
    while (!q.isEmpty()) {
      // Remove o vértice da fila
      int node = q.poll();
      // Adiciona o vértice na lista
      result.add(node);

      // Decrementa o grau de entrada dos vértices adjacentes
      for (int adjacent : adj.get(node - minVertex)) {
        indegree[adjacent - minVertex]--;

        if (indegree[adjacent - minVertex] == 0)
          q.add(adjacent);
      }
    }

    if (result.size() != V) {
      System.out.println("Graph contains cycle!");
      return new ArrayList<>();
    }
    return result;
  }

  public static void main(String[] args) {
    // Número de vértices, pegar do teclado
    Scanner sc = new Scanner(System.in);
    int verticeNumber = sc.nextInt();
    int edgeNumber = sc.nextInt();
    List<List<Integer>> edges = new ArrayList<>();

    // Preenchendo a lista de arestas
    for (int i = 0; i < edgeNumber; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      edges.add(Arrays.asList(a, b));
    }

    // Encontrando o menor vértice para ajustar a indexação
    int minVertex = Integer.MAX_VALUE;
    for (List<Integer> edge : edges) {
      minVertex = Math.min(minVertex, edge.get(0));
      minVertex = Math.min(minVertex, edge.get(1));
    }

    // Criando a lista de adjacência
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < verticeNumber; i++) {
      adj.add(new ArrayList<>());
    }

    // Preenchendo a lista de adjacência analisando as arestas
    for (List<Integer> edge : edges) {
      adj.get(edge.get(0) - minVertex).add(edge.get(1));
    }

    System.out.print("Topological sorting of the graph: ");
    List<Integer> result = topologicalSort(adj, verticeNumber, minVertex);

    for (int vertex : result) {
      System.out.print(vertex + " ");
    }

    sc.close();
  }
}
