package Problema_Viajante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        int[] numCiudades = {20, 40, 60, 80, 100}; // Cantidad de ciudades para cada grafo(red)
        String archivoCiudades = "Recursos/Ciudades.txt"; // Nombre del archivo que contiene las ciudades

        for (int n : numCiudades) {
            System.out.println("\nGenerando grafo con " + n + " ciudades:");
            Grafo grafo = generarGrafoConCiudades(archivoCiudades, n);
            grafo.imprimirGrafo();

            if (grafo.esConexo()) {
                System.out.println("El grafo es conexo.");
            } else {
                System.out.println("El grafo no es conexo.");
            }
        }
    }

    /**
     * Generar grafo con ciudades grafo.
     *Metodo encargado de generar un grafo con todas las ciudades dentro del archivo "Recursos/Ciudades.txt"
     *
     * @param archivoCiudades archivo con las ciudades
     * @param numCiudades     cantidad de ciudades(vertices) para el grafo(red)
     * @return grafo completo
     */
    public static Grafo generarGrafoConCiudades(String archivoCiudades, int numCiudades) {
        List<String> nombresCiudades = leerCiudadesDesdeArchivo(archivoCiudades);
        Collections.shuffle(nombresCiudades); // Barajar nombres para evitar repetición

        Grafo grafo = new Grafo();
        Set<String> ciudadesUsadas = new HashSet<>();

        for (int i = 0; i < numCiudades; i++) {
            String nombreCiudad = nombresCiudades.get(i);
            grafo.agregarCiudad(nombreCiudad);
            ciudadesUsadas.add(nombreCiudad);
        }

        Random random = new Random();
        List<String> listaCiudades = new ArrayList<>(ciudadesUsadas);

        for (int i = 0; i < numCiudades; i++) {
            for (int j = i + 1; j < numCiudades; j++) {
                int distancia = random.nextInt(50) + 1; //Genera una distancia aleatoria entre 1 y 50
                grafo.agregarArco(listaCiudades.get(i), listaCiudades.get(j), distancia);
            }
        }

        return grafo;
    }

    /**
     * Leer ciudades desde archivos
     *Metodo encargado de leer las ciudades dentro del archivo "Recursos/Ciudades.txt"
     *
     * @param archivo archivo con las ciudades
     * @return ciudades seleccionadas
     */
    public static List<String> leerCiudadesDesdeArchivo(String archivo) {
        List<String> ciudades = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                ciudades.add(linea.trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return ciudades;
    }
}
