package br.com.zenon.fraud;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class Main {

    static void main() {
        TransactionIngestor ingestor = new TransactionIngestor();
        List<Transaction> transacoes = ingestor.lerArquivo(Path.of("data/PS_log.csv"));

        FraudAnalyzer analyzer = new FraudAnalyzer(transacoes);

        System.out.println("Transacoes carregadas: " + transacoes.size());
        System.out.println("Fraudes encontradas: " + analyzer.quantidadeFraudes());

        System.out.println("\n-- 3 maiores fraudes --");
        analyzer.maioresFraudes(3).forEach(System.out::println);

        System.out.println("\n-- 5 clientes suspeitos --");
        analyzer.clientesSuspeitos(5).forEach(System.out::println);

        System.out.printf(Locale.US, "%n-- Prejuizo total: %.2f%n", analyzer.prejuizoTotal());

        System.out.println("\n-- Fraudes por tipo --");
        analyzer.fraudesPorTipo().forEach((tipo, qtd) -> System.out.println(tipo + ": " + qtd));
    }
}
