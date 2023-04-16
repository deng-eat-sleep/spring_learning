package J06_lambda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class LambdaDateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime min = now.with(ChronoField.MILLI_OF_DAY,0);
        LocalDateTime min2 = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        System.out.println(min);
        System.out.println(min2);
    }
}
