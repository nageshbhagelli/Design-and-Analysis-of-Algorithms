/*
 * 
 * Initially all m∈M and w∈W are free
 * While there is a man m who is free and hasn’t proposed to every woman
 * Choose such a man m
 * Let w be the highest-ranked woman in m's preference list
 * to which m has not yet proposed
 * If w is free then
 * (m,w) become engaged
 * Else w is currently engaged to m′
 * If w prefers m′ to m then
 * m remains free
 * Else w prefers m to m′
 * (m,w) become engaged
 * m′ becomes free
 * Endif
 * Endif
 * Endwhile
 * Return the set S of engaged pairs
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class GaleShapeleyAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of men/women
        System.out.print("Enter number of men/women: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] men = new String[n];
        String[] women = new String[n];

        // Input names of men
        System.out.println("Enter names of men:");
        for (int i = 0; i < n; i++) {
            men[i] = sc.nextLine().trim();
        }

        // Input names of women
        System.out.println("Enter names of women:");
        for (int i = 0; i < n; i++) {
            women[i] = sc.nextLine().trim();
        }

        // Create name -> index mapping (for lookup)
        int[][] menPreferences = new int[n][n];
        int[][] womenPreferences = new int[n][n];

        // Input men's preference lists (by name)
        System.out.println("Enter men's preference lists (space-separated names):");
        for (int i = 0; i < n; i++) {
            String[] prefs = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                menPreferences[i][j] = indexOf(women, prefs[j]);
            }
        }

        // Input women's preference lists (space-separated names):
        System.out.println("Enter women's preference lists (space-separated names):");
        for (int i = 0; i < n; i++) {
            String[] prefs = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                womenPreferences[i][j] = indexOf(men, prefs[j]);
            }
        }
        // Gale-Shapley implementation
        int[] womanPartner = new int[n];
        boolean[] womanEngaged = new boolean[n];
        boolean[] manFree = new boolean[n];
        int[] nextProposalIndex = new int[n];

        for (int i = 0; i < n; i++) {
            womanPartner[i] = -1;
            manFree[i] = true;
            nextProposalIndex[i] = 0;
        }

        int freeMen = n;

        while (freeMen > 0) {
            for (int m = 0; m < n; m++) {
                if (manFree[m]) {
                    int w = menPreferences[m][nextProposalIndex[m]];
                    nextProposalIndex[m]++;

                    if (!womanEngaged[w]) {
                        womanPartner[w] = m;
                        womanEngaged[w] = true;
                        manFree[m] = false;
                        freeMen--;
                    } else {
                        int current = womanPartner[w];
                        if (prefersNewMan(womenPreferences[w], m, current)) {
                            womanPartner[w] = m;
                            manFree[m] = false;
                            manFree[current] = true;
                        }
                    }
                }
            }
        }

        // Print result
        System.out.println("\nStable marriages:");
        for (int w = 0; w < n; w++) {
            System.out.println(men[womanPartner[w]] + " is married to " + women[w]);
        }
    }

    // Get index of name in array
    private static int indexOf(String[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(name))
                return i;
        }
        return -1;
    }

    // Check if woman prefers new man m1 over m2
    private static boolean prefersNewMan(int[] prefs, int m1, int m2) {
        for (int p : prefs) {
            if (p == m1)
                return true;
            if (p == m2)
                return false;
        }
        return false;
    }
}