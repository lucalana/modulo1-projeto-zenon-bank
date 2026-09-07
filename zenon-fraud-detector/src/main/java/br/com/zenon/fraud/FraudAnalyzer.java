package br.com.zenon.fraud;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> fraudes;

    public FraudAnalyzer(List<Transaction> transacoes) {
        this.fraudes = transacoes.stream()
                .filter(Transaction::isFraud)
                .toList();
    }

    public List<Transaction> fraudes() {
        return fraudes;
    }

    public long quantidadeFraudes() {
        return fraudes.size();
    }

    public List<Transaction> maioresFraudes(int n) {
        return fraudes.stream()
                .sorted(Comparator.comparingDouble(Transaction::amount).reversed())
                .limit(n)
                .toList();
    }

    public List<String> clientesSuspeitos(int n) {
        return fraudes.stream()
                .sorted(Comparator.comparingDouble(Transaction::amount).reversed())
                .map(t -> t.origin().name())
                .distinct()
                .limit(n)
                .toList();
    }

    public double prejuizoTotal() {
        return fraudes.stream()
                .mapToDouble(Transaction::amount)
                .sum();
    }

    public Map<TransactionType, Long> fraudesPorTipo() {
        return fraudes.stream()
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
