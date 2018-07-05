package danparis.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PrimesResponse {
    private int initial;
    private List<Integer> primes;

    public PrimesResponse() {

    }

    public PrimesResponse(final int maxNum, final List<Integer> primes) {
        this.initial = maxNum;
        this.primes = primes;
    }

    public int getInitial() {
        return initial;
    }

    @XmlAttribute
    public void setInitial(int initial) {
        this.initial = initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    @XmlElement
    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }
}
