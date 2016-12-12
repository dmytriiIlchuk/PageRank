import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String... args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int size = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] edges = new int[n][2];
        double dampingFactor = 0.85;
        for (int i = 0; i < n; i++) {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }
        PageRank pagerank = new PageRank(edges, size, dampingFactor);
        pagerank.buildGMatrix();
        double[][] eigenvector = pagerank.eigenvectorOfGMatrix();
        System.out.print("Eigenvector of G matrix: [");
        for (int i = 0; i < size; i++) {
            System.out.print(eigenvector[0][i] + " ");
        }
        System.out.println("]");
        int maxI = 0;
        for (int i = 0; i < size; i++) {
            if (eigenvector[0][i] > eigenvector[0][maxI]) {
                maxI = i;
            }
        }
        System.out.println("The page with number " + (maxI + 1) + " has the highest chance of being visited");
    }
}
