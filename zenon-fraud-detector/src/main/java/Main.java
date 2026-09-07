import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try(Stream<String> linha = Files.lines(Path.of("data/PS_log.csv"))){
            linha.skip(1)
                .map(l -> l.split(","))
                .map(campos ->
                            new Transaction(
                                    Integer.parseInt(campos[0]), Type.valueOf(campos[1]), Double.parseDouble(campos[2]),
                                    campos[3], Double.parseDouble(campos[4]), Double.parseDouble(campos[5]),
                                    campos[6], Double.parseDouble(campos[7]), Double.parseDouble(campos[8]),
                                    Integer.parseInt(campos[9]), Integer.parseInt(campos[10]))
                            )
                .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}