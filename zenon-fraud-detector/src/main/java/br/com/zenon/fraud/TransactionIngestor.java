package br.com.zenon.fraud;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TransactionIngestor {

    private int limiteLinha = 1000;

    public List<Transaction> lerArquivo(Path arquivo) {
        List<Transaction> transacoes = new ArrayList<>();

        try (Stream<String> linhas = Files.lines(arquivo)) {
            linhas.skip(1).limit(this.limiteLinha).forEach(linha -> {
                try {
                    transacoes.add(parse(linha));
                } catch (RuntimeException e) {
                    System.err.println("Erro: " + linha + " | " + e);
                }
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return transacoes;
    }

    private Transaction parse(String linha) {
        String[] c = linha.split(",", -1);

        TransactionCustomer origin = new TransactionCustomer(
                c[3], Double.parseDouble(c[4]), Double.parseDouble(c[5]));
        TransactionCustomer recipient = new TransactionCustomer(
                c[6], Double.parseDouble(c[7]), Double.parseDouble(c[8]));

        return new Transaction(
                Integer.parseInt(c[0]),
                TransactionType.valueOf(c[1]),
                Double.parseDouble(c[2]),
                origin,
                recipient,
                "1".equals(c[9]),
                "1".equals(c[10]));
    }
}
