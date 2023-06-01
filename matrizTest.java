package matrices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class MainTest {

    @Test
    void importarMatriz_ValidFile_ReturnsMatrix() {
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] actual = Main.importarMatriz("matriz1.txt");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void importarMatriz_InvalidFile_ReturnsNull() {
        int[][] actual = Main.importarMatriz("archivo_invalido.txt");

        Assertions.assertNull(actual);
    }

    @Test
    void exportarMatriz_ValidMatrix_ExportSuccessful() {
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String nombreArchivo = "matriz_exportada.txt";

        Main.exportarMatriz(matriz, nombreArchivo);

        int[][] exportedMatrix = Main.importarMatriz(nombreArchivo);

        Assertions.assertArrayEquals(matriz, exportedMatrix);

        // Delete the exported file after the test
        File file = new File(nombreArchivo);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void exportarMatriz_EmptyMatrix_ExportSuccessful() {
        int[][] matriz = {};
        String nombreArchivo = "matriz_exportada.txt";

        Main.exportarMatriz(matriz, nombreArchivo);

        int[][] exportedMatrix = Main.importarMatriz(nombreArchivo);

        Assertions.assertArrayEquals(matriz, exportedMatrix);

        // Delete the exported file after the test
        File file = new File(nombreArchivo);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void exportarMatriz_NullMatrix_ExportSuccessful() {
        int[][] matriz = null;
        String nombreArchivo = "matriz_exportada.txt";

        Main.exportarMatriz(matriz, nombreArchivo);

        int[][] exportedMatrix = Main.importarMatriz(nombreArchivo);

        Assertions.assertNull(exportedMatrix);

        // Delete the exported file after the test
        File file = new File(nombreArchivo);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void exportarMatriz_ExceptionThrown_PrintsStackTrace() {
        int[][] matriz = {
                {1, 2},
                {3, 4, 5}, // Invalid row
                {6, 7, 8}
        };
        String nombreArchivo = "matriz_exportada.txt";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        Main.exportarMatriz(matriz, nombreArchivo);

        String errorOutput = outputStream.toString();

        Assertions.assertTrue(errorOutput.contains("ArrayIndexOutOfBoundsException"));

        // Delete the exported file after the test
        File file = new File(nombreArchivo);
        if (file.exists()) {
            file.delete();
        }
    }

    // TODO: Add more test cases to cover different scenarios

}

