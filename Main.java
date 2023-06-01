package matrices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static int[][] importarMatriz(String nombreArchivo) {
        int[][] matriz = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int filas = 0;
            int columnas = 0;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                if (matriz == null) {
                    filas = 1;
                    columnas = elementos.length;
                    matriz = new int[filas][columnas];
                } else {
                    filas++;
                    int[][] nuevaMatriz = new int[filas][columnas];
                    System.arraycopy(matriz, 0, nuevaMatriz, 0, filas - 1);
                    matriz = nuevaMatriz;
                }
                for (int i = 0; i < elementos.length; i++) {
                    try {
                        matriz[filas - 1][i] = Integer.parseInt(elementos[i].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Formato de número inválido en el archivo.");
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matriz;
    }

    public static void exportarMatriz(int[][] matriz, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    writer.write(String.valueOf(matriz[i][j]));
                    if (j != matriz[0].length - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String matriz1File = "matriz1.txt";
        String matriz2File = "matriz2.txt";
        String resultadoFile = "resultado.txt";

        // Leer las matrices desde los archivos
        int[][] matriz1 = importarMatriz(matriz1File);
        int[][] matriz2 = importarMatriz(matriz2File);

        // Verificar si las matrices son válidas para la multiplicación
        if (matriz1 != null && matriz2 != null && matriz1[0].length == matriz2.length) {
            // Lugar en donde se almacena el resultado
            int[][] producto = new int[matriz1.length][matriz2[0].length];

            // Necesitamos hacer esto por cada columna de la segunda matriz (B)
            for (int a = 0; a < matriz2[0].length; a++) {
                // Dentro recorremos las filas de la primera (A)
                for (int i = 0; i < matriz1.length; i++) {
                    int suma = 0;
                    // Y cada columna de la primera (A)
                    for (int j = 0; j < matriz1[0].length; j++) {
                        // Multiplicamos y sumamos resultado
                        suma += matriz1[i][j] * matriz2[j][a];
                    }
                    // Lo acomodamos dentro del producto
                    producto[i][a] = suma;
                }
            }

            // Imprimir el resultado
            System.out.println("Resultado:");
            for (int i = 0; i < producto.length; i++) {
                for (int j = 0; j < producto[0].length; j++) {
                    System.out.print(producto[i][j] + " ");
                }
                System.out.println();
            }

            // Exportar el resultado a un archivo de texto
            exportarMatriz(producto, resultadoFile);
            System.out.println("Resultado exportado en " + resultadoFile);
        } else {
            System.out.println("Las matrices no son válidas para la multiplicación.");
        }
    }
}

