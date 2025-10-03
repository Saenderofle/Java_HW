package HW_1;

import java.util.Scanner;

public class HW_1_5 {
    public static void main(String[] args) {
        int N, M;

        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введіть N: ");
            N = sc.nextInt();
            System.out.print("Введіть M: ");
            M = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int rand = (int)(Math.random() * M);
            System.out.println(rand);
        }
    }
}