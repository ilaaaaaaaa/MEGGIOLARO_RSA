import java.math.BigInteger;
import java.util.Random; // per generare numeri primi casuali

public class RSA {
    private BigInteger p;    // primo segreto
    private BigInteger q;    // secondo segreto
    private BigInteger n;    // modulo pubblico
    private BigInteger phi;  // funzione di Eulero
    private BigInteger e;    // esponente pubblico
    private BigInteger d;    // esponente privato
    private Random random;   // generatore di numeri casuali

    // Costruttore
    public RSA(int bitLength) {
        random = new Random();

        // 1. Genera p e q
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);

        // 2. Calcola n e phi
        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // 3. Scelgo e (tipicamente 65537) e verifico che sia coprimo con phi
        e = BigInteger.valueOf(65537);

        if (!e.gcd(phi).equals(BigInteger.ONE)) {

            // Se non è coprimo scelgo un numero casuale coprimo con phi
            e = BigInteger.probablePrime(bitLength / 2, random);

            while (!e.gcd(phi).equals(BigInteger.ONE)) {
                e = BigInteger.probablePrime(bitLength / 2, random);
            }
        }

        // 4. Calcolo d (inverso modulo phi)
        d = e.modInverse(phi);
    }

    // Metodo per cifrare
    public BigInteger cifra(BigInteger messaggio) {
        return messaggio.modPow(e, n);
    }

    // Metodo per decifrare
    public BigInteger decifra(BigInteger cifrato) {
        return cifrato.modPow(d, n);
    }

    // Get chiave pubblica
    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    // Get chiave privata (solo per test)
    public BigInteger getD() {
        return d;
    }
}
