import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TransactionIngestor {
    private int limiteLinhas = 1000;

    public TransactionIngestor() {
    }

    public List<Transaction> lerArquivo(Path arquivo) {
        try(Stream<String> linha = Files.lines(arquivo)){
            return linha.skip(1)
                    .limit(this.limiteLinhas)
                    .map(l -> l.split(","))
                    .map(campos ->
                        new Transaction(
                                Integer.parseInt(campos[0]), Type.valueOf(campos[1]), Double.parseDouble(campos[2]),
                                campos[3], Double.parseDouble(campos[4]), Double.parseDouble(campos[5]),
                                campos[6], Double.parseDouble(campos[7]), Double.parseDouble(campos[8]),
                                Integer.parseInt(campos[9]), Integer.parseInt(campos[10]))
                        )
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
