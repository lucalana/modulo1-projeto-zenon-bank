package br.com.zenon.fraud;

import java.nio.file.Path;
import java.util.List;

public class Main {

    static void main() {
        TransactionIngestor ingestor = new TransactionIngestor();
        List<Transaction> transacoes = ingestor.lerArquivo(Path.of("data/PS_bad_data.csv"));

        System.out.println(transacoes.size());
        transacoes.forEach(System.out::println);
    }
}
