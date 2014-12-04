package MathMetodsInEconomics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Antony on 01.12.2014.
 */
public class Aggregate {

    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static double[][] parse(String text) {
        int n;
        int m = 0;
        {
            StringTokenizer lines = new StringTokenizer(text, "\n");
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
        StringTokenizer lines = new StringTokenizer(text, "\n");
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

    public static void matrixToString(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.format("%.5f ", matrix[i][j]);
            }
            System.out.format("%n");
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        double[][] matrix;

        //Чтение файла
        String textFromFile = read("data.txt");
        matrix = parse(textFromFile);
        int[][] agregate = {{0,3},{1},{2},{4},{5},{6}};
        double[] x = {70,20,25,35,50,75,10};
        System.out.println("Matrix Before");
        matrixToString(matrix);
        System.out.println("agregate[0].length = " + agregate[0].length);
        System.out.println("agregate[1].length = " + agregate[1].length);

        matrix = agregate(matrix, agregate,x);

        System.out.println("Matrix After");
        matrixToString(matrix);
    }

    private static double[][] agregate(double[][] matrix, int[][] agregate, double[] x) {
        int n = agregate.length;
        double[][] b = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j]=getB(matrix,agregate,x,i,j);
            }
        }
        return b;
    }

    private static double getB(double[][] a, int[][] I, double[] x, int i_index, int j_index) {
        double result=0;
        double tempX = 0;
        for (int i = 0; i < I[i_index].length; i++) {
            for (int j = 0; j < I[j_index].length; j++) {
                result = a[I[i_index][i]][I[j_index][j]]*x[I[j_index][j]];
                //System.out.println("a[I[i_index][i]][I[j_index][j]]*x[I[j_index][j]] = " + result);
            }
        }
        for (int j = 0; j < I[j_index].length; j++) {
            tempX+=x[I[j_index][j]];
        }
        return result/tempX;
    }


}