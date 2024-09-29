// Código de testes unitários para o metodo CalculaNucleotideos.java
//Autores: Filipe Santos Lima e Luiz Augusto Mendes Barbosa
package org.testesUnitarios;

import org.junit.jupiter.api.*;
import java.nio.file.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.testesUnitarios.Main.CalculaNucleotideos;

public class CalculaNucleotideosTest {

    private static final String BASE_DIR = "test_files/";

    @BeforeAll
    static void setup() throws IOException {
        Path baseDirPath = Paths.get(BASE_DIR);
        if (!Files.exists(baseDirPath)) {
            Files.createDirectories(baseDirPath);
        }

        // Criação de arquivos de teste
        Files.write(baseDirPath.resolve("sequencia_valida.txt"), "AAAGTCTGAC".getBytes());
        Files.write(baseDirPath.resolve("sequencia_com_erro.txt"), "AACTGTCGBA".getBytes());
        Files.write(baseDirPath.resolve("sequencia_invalida.txt"), "ABC TEM FALHA".getBytes());
        Files.write(baseDirPath.resolve("sequencia_vazia.txt"), "".getBytes());
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Remover arquivos de teste
        Files.deleteIfExists(Paths.get(BASE_DIR + "sequencia_valida.txt"));
        Files.deleteIfExists(Paths.get(BASE_DIR + "sequencia_com_erro.txt"));
        Files.deleteIfExists(Paths.get(BASE_DIR + "sequencia_invalida.txt"));
        Files.deleteIfExists(Paths.get(BASE_DIR + "sequencia_vazia.txt"));

    }

    @Test
    @DisplayName("Testa sequência válida sem erros")
    void testaSequenciaValida() throws IOException {
        int[] esperado = {4, 2, 2, 2, 0};
        assertArrayEquals(esperado, CalculaNucleotideos.calculaNucleotideos(BASE_DIR + "sequencia_valida.txt"));
    }

    @Test
    @DisplayName("Testa sequência com um caractere inválido")
    void testaSequenciaComErro() throws IOException {
        int[] esperado = {3, 2, 2, 2, 1};
        assertArrayEquals(esperado, CalculaNucleotideos.calculaNucleotideos(BASE_DIR + "sequencia_com_erro.txt"));
    }

    @Test
    @DisplayName("Testa sequência com mais de 10% de caracteres inválidos")
    void testaSequenciaInvalida() throws IOException {
        assertNull(CalculaNucleotideos.calculaNucleotideos(BASE_DIR + "sequencia_invalida.txt"));
    }

    @Test
    @DisplayName("Testa arquivo inexistente")
    void testaArquivoInexistente() {
        Exception exception = assertThrows(IOException.class, () -> {
            CalculaNucleotideos.calculaNucleotideos(BASE_DIR + "arquivo_inexistente.txt");
        });
        assertTrue(exception.getMessage().contains("arquivo_inexistente.txt"));
    }
    @Test
    @DisplayName("Testa sequência vazia")
    void testaSequenciaVazia() throws IOException {
        int[] esperado = {0, 0, 0, 0, 0};
        assertArrayEquals(esperado, CalculaNucleotideos.calculaNucleotideos(BASE_DIR + "sequencia_vazia.txt"));
    }
}