package com.company.TowerOfHanoi;

public class TowerOfHanoi {

    public static long count;

    public static void hanoi(int n, char A, char C, char helper) {
        if (n == 1) {
            System.out.println("Take disk 1 from rod " +  A + " to rod " + C);
            count += 1;
        } else {
            hanoi(n - 1, A, helper, C);
            System.out.println("Move disk "+ n + " from rod " +
                    A +" to rod " + C );
            count += 1;
            hanoi(n - 1, helper, C, A);
        }
    }

    public static void main(String[] args) {

        hanoi(4, 'A', 'C', 'B');
        System.out.println("Кол-во операций: " + count);
    }
}
