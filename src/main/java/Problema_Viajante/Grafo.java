package Problema_Viajante;
import java.util.*;


/**
 * Clase de tipo Grafo.
 */
public class Grafo {
    private Map<String, List<Map.Entry<String, Integer>>> adjList;


// Constructor
    public Grafo() {
        adjList = new HashMap<>();
    }

    /**
     * Agregar ciudad.
     * Se encarga de agregar al grafo(red) creando un nuevo vertice(Ciudad)
     *
     * @param ciudad nombre de la ciudad
     */
    public void agregarCiudad(String ciudad) {
        adjList.putIfAbsent(ciudad, new LinkedList<>());
    }

    /**
     * Agregar arco.
     *Se encarga de agregar una conexión entre 2 vertices ya existentes dando una distancia entre vertice y vertice(Ciudad y ciudad)
     *
     * @param origen    ciudad(vertice) de origen
     * @param destino   ciudad(vertice) de destino
     * @param distancia distancia entre vertice y vertice(Ciudad y ciudad)
     */
    public void agregarArco(String origen, String destino, int distancia) {
        adjList.putIfAbsent(origen, new LinkedList<>());
        adjList.putIfAbsent(destino, new LinkedList<>());
        adjList.get(origen).add(new AbstractMap.SimpleEntry<>(destino, distancia));
        adjList.get(destino).add(new AbstractMap.SimpleEntry<>(origen, distancia)); // Como es un grafo no dirigido
    }

    /**
     * Es conexo boolean.
     *Se encarga de determinar si un grafo(red) es fuertemente conexa o no
     *
     * @return bool resultado de la conexión entre vertices
     */
    public boolean esConexo() {
        if (adjList.isEmpty()) return true;

        Set<String> visitado = new HashSet<>();
        String ciudadInicial = adjList.keySet().iterator().next();
        dfs(ciudadInicial, visitado);

        // Verificar si todos los nodos han sido visitados
        return visitado.size() == adjList.size();
    }

    /**
     * dfs.
     *Metodo de busqueda en profundidad para encontrar nodos adyacentes
     *
     * @param ciudad    ciudad(vertice) de origen
     * @param visitado   ciudad(vertice) ya visitada
     */
    private void dfs(String ciudad, Set<String> visitado) {
        visitado.add(ciudad);
        for (Map.Entry<String, Integer> adyacente : adjList.get(ciudad)) {
            if (!visitado.contains(adyacente.getKey())) {
                dfs(adyacente.getKey(), visitado);
            }
        }
    }

    /**
     * Imprimir grafo.
     * Metodo encargado de imprimir el grafo(red)
     */
    public void imprimirGrafo() {
        for (String ciudad : adjList.keySet()) {
            System.out.print("Ciudad " + ciudad + ":");
            for (Map.Entry<String, Integer> adyacente : adjList.get(ciudad)) {
                System.out.print(" -> " + adyacente.getKey() + " (distancia: " + adyacente.getValue() + ")");
            }
            System.out.println();
        }
    }
}
