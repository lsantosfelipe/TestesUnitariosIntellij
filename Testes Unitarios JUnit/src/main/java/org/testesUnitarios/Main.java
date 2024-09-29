// Classe Main com metodo Calcula Nucleotideos
// Esse metodo recebe o caminho de um arquivo contendo uma sequência de nucleotídeos e retorna um array de inteiros com
// a quantidade de cada nucleotídeo (A, C, G, T) e a quantidade de caracteres inválidos. Se mais de 10% dos caracteres
// forem inválidos, o método retorna null.
// Foi utilizado o Intellij como IDE e a versão 5.8.1 do JUnit.
// Autores: Filipe Santos Lima e Luiz Augusto Mendes Barbosa

package org.testesUnitarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            int[] nucleotideos = CalculaNucleotideos.calculaNucleotideos("src/main/resources/sequencia_valida.txt");
            if (nucleotideos != null) {
                System.out.printf("A: %d\nC: %d\nG: %d\nT: %d\nErros: %d\n", nucleotideos[0], nucleotideos[1], nucleotideos[2], nucleotideos[3], nucleotideos[4]);
            } else {
                System.out.println("Mais de 10% dos caracteres são inválidos");
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
    public static class CalculaNucleotideos {
        public static int[] calculaNucleotideos(String filePath) throws IOException {
            // Lê o arquivo e obtém a sequência
            String sequence = Files.readString(Paths.get(filePath)).trim();

            // Inicializa o contador de nucleotídeos
            int[] nucleotideos = {0, 0, 0, 0, 0}; // A, C, G, T, Erros
            int length = sequence.length();

            // Verifica cada caractere na sequência
            for (char c : sequence.toCharArray()) {
                switch (c) {
                    case 'A':
                        nucleotideos[0]++;
                        break;
                    case 'C':
                        nucleotideos[1]++;
                        break;
                    case 'G':
                        nucleotideos[2]++;
                        break;
                    case 'T':
                        nucleotideos[3]++;
                        break;
                    default:
                        nucleotideos[4]++;
                        break;
                }
            }
            // Se mais de 10% dos caracteres forem inválidos, retorna null
            if (nucleotideos[4] > length * 0.1) {
                return null;
            }
            return nucleotideos;
        }
    }
}