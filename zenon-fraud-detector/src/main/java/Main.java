void main() {
    TransactionIngestor leitorTransacao = new TransactionIngestor();
    List<Transaction> transacoes = leitorTransacao.lerArquivo(Path.of("data/PS_log.csv"));
    transacoes.forEach(System.out::println);
}