package J06_lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

    public void test(){
        List<Integer> list = Arrays.asList(1,2,3,4);

        list.stream()
                .filter(s -> s>0)

                .collect(Collectors.toList());

        list.sort(Comparator.reverseOrder());
        list.stream().filter(s -> s >0).

//        int sum = list.stream().reduce();
    }
}
