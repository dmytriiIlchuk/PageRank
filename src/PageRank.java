public class PageRank {
    private double[][] references;
    private double[][] gMatrix;
    private int[] rowElements;
    private double dampingFactor = 0.85;
    private double eps = 0.00001;

    public PageRank(int[][] edges, int size, double dampingFactor) {
        init(size, dampingFactor);
        for (int i = 0; i < edges.length; i++) {
            references[edges[i][0] - 1][edges[i][1] - 1] = 1;
            rowElements[edges[i][0] - 1]++;
        }
    }

    private void init(int size, double dampingFactor) {
        references = new double[size][size];
        gMatrix = new double[size][size];
        rowElements = new int[size];
        this.dampingFactor = dampingFactor;
    }

    private void calcWeight() {
        for (int i = 0; i < references.length; i++) {
            for (int j = 0; j < references.length; j++) {
                if (rowElements[i] != 0) {
                    references[i][j] /= rowElements[i];
                }
            }
        }
    }

    public void buildGMatrix() {
        calcWeight();
        int n = references.length;
        double[][] e = new double[n][1];
        double[][] et = new double[1][n];
        for(int i = 0; i < n; i++) {
            e[i][0] = 1;
            et[0][i] = 1;
        }
        double[][] a = new double[n][1];
        for (int i = 0; i < n; i++) {
            a[i][0] = (rowElements[i] > 0)? 0 : 1;
        }
        double[][] s = add(references, multiply(a, multiplyByScalar(et, 1.0 / n)));
        gMatrix = add(multiplyByScalar(s, dampingFactor), multiplyByScalar(multiply(e, et), (1.0 - dampingFactor) / n));
    }

    public double[][] eigenvectorOfGMatrix() {
        double[][] y = new double[1][gMatrix.length];
        int a = 1;
        for (int i = 0; i < gMatrix.length; i++) {
            y[0][i] = 1;
        }
        double lambda = 1;
        double prevLambda = -1;
        double[][] yNext = new double[1][gMatrix.length];
        while (Math.abs(lambda-prevLambda) > eps) {
            yNext = multiply(y, gMatrix);
            prevLambda = lambda;
            lambda = yNext[0][0]/y[0][0];
            y = yNext;
        }
        return yNext;
    }
    private double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }


    private double[][] add(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private double[][] multiplyByScalar(double[][] a, double b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] * b;
            }
        }
        return result;
    }


}
