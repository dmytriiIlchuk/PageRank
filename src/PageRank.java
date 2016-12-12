public class PageRank {
    private double[][] references;
    private double[][] gMatrix;
    private int[] rowElements;
    private double dampingFactor;

    public PageRank(int size) {
        init(size, 0.85);
    }

    public PageRank(int[][] edges, int size, double dampingFactor) {
        init(size, dampingFactor);
        for (int i = 0; i < edges.length; i++) {
            references[edges[i][0]][edges[i][1]] = 1;
            rowElements[edges[i][0]]++;
        }
    }

    private void init(int size, double dampingFactor) {
        references = new double[size][size];
        gMatrix = new double[size][size];
        rowElements = new int[size];
        this.dampingFactor = dampingFactor;
    }

    public void addEdge(int source, int destination) {
        references[source][destination] = 1;
        rowElements[source]++;
    }

    private void calcWeight() {
        for (int i = 0; i < references.length; i++) {
            for (int j = 0; j < references.length; j++) {
                references[i][j] = 1 / rowElements[i];
            }
        }
    }

    private void buildGMatrix() {
        int n = references.length;
        double[][] e = new double[1][n];
        double[][] et = new double[n][1];
        for(int i = 0; i < n; i++) {
            e[0][i] = 1;
            e[i][0] = 1;
        }
        double[][] a = new double[1][n];
        for (int i = 0; i < n; i++) {
            a[0][i] = 1 - rowElements[i];
        }
        double[][] s = add(references, multiply(a, multiplyByScalar(et, 1.0 / n)));
        gMatrix = add(multiplyByScalar(s, dampingFactor), multiplyByScalar(multiply(e, et), (1.0 - dampingFactor) / n));

    }

    private double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a.length];
        return result;
    }


    private double[][] add(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a.length];
        return result;
    }

    private double[][] multiplyByScalar(double[][] a, double b) {
        double[][] result = new double[a.length][a.length];
        return result;
    }


}
