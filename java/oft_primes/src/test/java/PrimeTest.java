import com.nealford.functionalthinking.primes.Prime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.nealford.functionalthinking.primes.Prime.*;
import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimeTest {
  private ArrayList<Integer> PRIMES_BELOW_50 = new ArrayList<Integer>() {{
              add(2);  add(3);  add(5);  add(7);  add(11);  add(13);
              add(17); add(19); add(23); add(29); add(31);  add(37);
              add(41); add(43); add(47);
          }};

  private Set<Integer> setOf(int... numbers) {
    Set<Integer> result = new HashSet<>();
    for (int i : numbers)
      result.add(i);
    return result;
  }

  @Test
  public void factors() {
    assertEquals(setOf(1, 2, 3, 6), getFactors(6));
    assertEquals(setOf(1, 2, 4, 8, 16), getFactors(16));
    assertEquals(setOf(1, 2, 3, 4, 6, 12), getFactors(12));
    assertEquals(setOf(1, 2, 4, 5, 10, 20, 25, 50, 100), getFactors(100));
  }


  @Test
  public void lot_of_primes() {
      for (int i = 2; i < 50; i++)
          if (PRIMES_BELOW_50.contains(i))
              assertTrue(isPrime(i));
          else
              assertFalse(isPrime(i));
      for (Integer i : PRIMES_BELOW_50)
          if (i != 2)
              assertFalse(isPrime(i + 1));
          else
            assertTrue(isPrime(i + 1));
  }

  @Test
  public void next_prime() {
    for (int i = 2; i < 48; i++)
      if (! isPrime(i))
        assertTrue(isPrime(PRIMES_BELOW_50.get(binarySearch(PRIMES_BELOW_50, nextPrimeFrom(i)) - 1)));
  }

}
