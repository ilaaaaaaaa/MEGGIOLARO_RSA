import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA(32); // per test, 32 bit vanno bene

        String testo = "Ciao RSA";
        System.out.println("Testo originale: " + testo);

        // 1. Trasformo ogni carattere in BigInteger e lo cifro
        BigInteger[] cifrati = new BigInteger[testo.length()];
        for (int i = 0; i < testo.length(); i++) {
            BigInteger m = BigInteger.valueOf((int) testo.charAt(i));
            cifrati[i] = rsa.cifra(m);
        }

        // Stampa cifrati
        System.out.print("Testo cifrato: ");
        for (BigInteger c : cifrati) {
            System.out.print(c + " ");
        }
        System.out.println();

        // 2. Decifro i numeri e ricostruisco la stringa
        StringBuilder decifrato = new StringBuilder();
        for (BigInteger c : cifrati) {
            decifrato.append((char) rsa.decifra(c).intValue());
        }

        System.out.println("Testo decifrato: " + decifrato.toString());
    }
}