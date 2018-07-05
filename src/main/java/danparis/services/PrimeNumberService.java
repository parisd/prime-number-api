package danparis.services;

import danparis.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeNumberService {
    /**
     * A function to return all the prime numbers up to and including the provided number.
     *
     * @param maxNum - the maximum number to return primes up to.
     *
     * @return List<Integer> representing all of the prime numbers up to the given number.
     */
    public final List<Integer> getPrimes(final int maxNum) {
        List<Integer> primes = new ArrayList<>();
        if(maxNum < Integer.MAX_VALUE) {
            try {
                final boolean[] nonPrime = new boolean[maxNum + 1];
                for (int i = 2; i <= Math.sqrt(maxNum); ++i) {
                    if (!nonPrime[i]) {
                        for (int j = i * 2; j <= maxNum; j += i) {
                            nonPrime[j] = true;
                        }
                    }
                }

                for (int i = 2; i <= maxNum; ++i) {
                    if (!nonPrime[i]) primes.add(i);
                }
            } catch(final OutOfMemoryError outOfMemoryError) {
                throw new BadRequestException(outOfMemoryError);
            }
        }
        return primes;
    }
}
