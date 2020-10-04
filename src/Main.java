public class Main {

    public static void main(String[] args) throws InterruptedException {
        
        matrixBlockSum( new int[][] {{1,2,3},{4,5,6},{7,8,9}},2);

        while (true) {
            Thread.sleep(Long.MAX_VALUE);
        }

    }
    
    public static int[][] matrixBlockSum(int[][] mat, int K) {

        int m = mat.length, n = mat[0].length;
        int[][] rangeSum = new int[m + 1][n + 1];

        //rangesum i+1,j+1 addresses mat[i][j], since rangesum has one row and one column extra compared to matrix
        //in fact rangesum extra layers are first row and first column both initiated to 0's -> see image
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)

                //note: rangesum has one row and column extra at the start of it, therefore rangeSum[i + 1][j] addresses LEFT of mat[i][j] :
                //same row, one column to the left.

                //SUM of left of mat[i][j] which can be found from rangeSum[i + 1][j]
                //and above of mat[i][j] which can be found by rangeSum[i][j + 1] -> rangesum has one more row
                //MINUS rangesum[i][j] [one row above, and one column left] which is repeated twice
                //covers everything except item itelsef which is covered by + mat[i][j]

                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {

                //max of r2 is m, but of not reached m, we need to access i+k+1 from rangesum, since rangesum
                //is one size bigger than original matrix -> see image, first row of matrix is in fact second row of rangesum
                //second cilumn of matrix is third column of rangesum
                int r1 = Math.max(0, i - K), c1 = Math.max(0, j - K), r2 = Math.min(m, i + K + 1), c2 = Math.min(n, j + K + 1);
                ans[i][j] = rangeSum[r2][c2] - rangeSum[r2][c1] - rangeSum[r1][c2] + rangeSum[r1][c1];
            }
        return ans;

    }
    
}

//solution from:https://leetcode.com/problems/matrix-block-sum/discuss/477036/JavaPython-3-PrefixRange-sum-w-analysis-similar-to-LC-30478

//see image 1: https://drive.google.com/file/d/1_0qJaidkx-xTWnUXnXEFWOMrVaYK11ew/view?usp=sharing
//see image 2: https://drive.google.com/file/d/1jzaO459rbYMZjY81tHcd8wn6Muj0P7Wj/view?usp=sharing

