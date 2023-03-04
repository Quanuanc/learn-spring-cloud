package cheng;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PredictTest {
    @Test
    void test1() {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<String> result = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
        assertEquals(2, result.size());
    }
}
