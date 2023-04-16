package J06_lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1);
        stream.collect(Collectors.toList());
    }
}
