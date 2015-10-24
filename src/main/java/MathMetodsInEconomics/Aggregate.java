package MathMetodsInEconomics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * The type Aggregate.
 */
public class Aggregate {

    /**
     * Read string from file.
     *
     * @param fileName the file name
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        try {
            //Объект для чтения файла в буфер
            try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append(System.lineSeparator());
                }
            }
            //Также не забываем закрыть файл

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    /**
     * Parse double [ ] [ ].
     *
     * @param text the text
     * @return the double [ ] [ ]
     */
    public static double[][] parse(String text) {
        int n;
        int m = 0;
        {
            StringTokenizer lines = new StringTokenizer(text, System.lineSeparator());
            int i = 0;
            while (lines.hasMoreElements()) {
                String line = lines.nextToken();
                StringTokenizer digit = new StringTokenizer(line, "\t");
                int j = 0;
                while (digit.hasMoreElements()) {
                    String stDigit = digit.nextToken();
                    j++;
                }
                m=j;
                i++;
            }
            n=i;
        }
        double[][] result = new double[n][m];
        StringTokenizer lines = new StringTokenizer(text, System.lineSeparator());
        int i = 0;
        while (lines.hasMoreElements()) {
            String line = lines.nextToken();
            StringTokenizer digit = new StringTokenizer(line, "\t");
            int j = 0;
            while (digit.hasMoreElements()) {
                String stDigit = digit.nextToken();
                result[i][j] = Double.parseDouble(stDigit);
                j++;
            }
            i++;
        }
        return result;
    }

    /**
     * Matrix to string.
     *
     * @param matrix the matrix
     */
    public static void matrixToString(double[][] matrix) {
        for (double[] aMatrix : matrix) {
            for (double anAMatrix : aMatrix) {
                System.out.format("%.5f ", anAMatrix);
            }
            System.out.format("%n");
        }

    }

    /**
     * Aggregate double [ ] [ ].
     *
     * @param matrix    Матрица прямых затрат
     * @param aggregate Вектор агрегирования
     * @param x         Vector x
     * @return Result of aggregation matrix
     */
    public static double[][] aggregate(double[][] matrix, int[][] aggregate, double[] x) {
        int n = aggregate.length;
        System.out.println("aggregate.length = " + aggregate.length);
        double[][] b = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j]=getB(matrix, aggregate,x,i,j);
            }
        }
        return b;
    }

    /**
     * Возваращает вектор B по заданой формуле.
     *
     * @param a       Матрица прямых затрат
     * @param I       Вектор агрегирования
     * @param x       Vector x
     * @param i_index Position element in cols
     * @param j_index Position element in rows
     * @return Return result of function
     */
    public static double getB(double[][] a, int[][] I, double[] x, int i_index, int j_index) {
        double result=0;
        double tempX = 0;
        for (int i = 0; i < I[i_index].length; i++) {
            for (int j = 0; j < I[j_index].length; j++) {
                result += a[I[i_index][i]][I[j_index][j]]*x[I[j_index][j]];
                //System.out.println("a["+I[i_index][i]+"]["+I[j_index][j]+"]*x["+I[j_index][j]+"] = " + result);
            }
        }
        for (int j = 0; j < I[j_index].length; j++) {
            tempX+=x[I[j_index][j]];
        }
        return result/tempX;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws FileNotFoundException the file not found exception
     */
    public static void main(String[] args) throws FileNotFoundException {
        double[][] matrix;
        //Чтение файла
        String textFromFile = read("data.txt");
        matrix = parse(textFromFile);
        int[][] aggregate = {{0,1},{3,5},{2},{4},{6}};
        double[] x = {70,20,25,35,50,75,10};
        System.out.println("Matrix Before");
        matrixToString(matrix);

        matrix = aggregate(matrix, aggregate, x);

        System.out.println("Matrix After");
        matrixToString(matrix);
    }
}
