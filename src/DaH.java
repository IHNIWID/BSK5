import java.util.Random;

/**
 * Created by Jakub Tomczuk on 28.05.2016.
 */
public class DaH /*Deffie and Hellman*/{
    int q = 19;
    int a = 2; //tutaj albo z palca albo tablica albo metoda?
    Random generator = new Random();
    private int alice = generator.nextInt(q-1) + 1;//Mi
    private int bob = generator.nextInt(q-1) + 1;//Mj
    /* int alice,bob = 4 */


    public static void main(String[] args) {
        DaH program = new DaH();
        program.run();
    }

    private void run(){
        System.out.println("Alice secret number: " + alice + "\nBob secret number: " + bob);

        int alice_calculate = calculateC(alice,a,q);
        int bob_calculate = calculateC(bob,a,q);
        System.out.println("Public file numbers: " + alice_calculate +" " + bob_calculate);

        int alice_public = commonKey(bob_calculate,alice,q);
        int bob_public = commonKey(alice_calculate,bob,q);

        System.out.println("Public keys (should be same)\nAlice: " + alice_public + "\nBob: " + bob_public);
    }

    private int calculateC(int M, int a, int q){

        int c = fastpow(a,M,q);
        return c;
    }

    private int commonKey(int C, int M, int q){

        int k = fastpow(C,M,q);
        return k;
    }

    public static int fastpow(int base, int exponent, int modulo) {
        int i;
        int result = 1;
        long x = base % modulo;

        for (i = 1; i <= exponent; i <<= 1) {
            x %= modulo;
            if ((exponent & i) != 0) {
                result *= x;
                result %= modulo;
            }
            x *= x;
        }

        return result;
    }
}
