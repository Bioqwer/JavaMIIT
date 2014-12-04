package eco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Antony on 01.12.2014.
 */
public class Lab {

    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( fileName));
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
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static double[][] parse(String text)
    {
        int n=7;
        double[][] result = new double[n][n];
        StringTokenizer lines = new StringTokenizer(text,"\n");
        int i=0;
        while (lines.hasMoreElements())
        {
            String line = lines.nextToken();
            StringTokenizer digit = new StringTokenizer(line,"\t");
            int j=0;
            while (digit.hasMoreElements())
            {
                String stDigit = digit.nextToken();
                result[i][j]=Double.parseDouble(stDigit);
                j++;
            }
            i++;
        }
        return result;
    }

    public static String matrixToString(double[][] matrix)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result.append(matrix[i][j]);
                result.append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        double[][] matrix;

        //Чтение файла
        String textFromFile = read("data.txt");
        matrix = parse(textFromFile);
        int[] exception = {0,6};
        System.out.println("Matrix Before");
        System.out.println(matrixToString(matrix));

        matrix = exceptionFromA(matrix,exception);

        System.out.println("Matrix After");
        System.out.println(matrixToString(matrix));
    }

    private static int change(int a) {
        a=0;
        return a;
    }

    private static double[][] exceptionFromA(double[][] matrix, int[] exception) {
        for (int i = 0; i < exception.length; i++) {
            matrix = swapCols(matrix,i,exception[i]-i);
        }
        return matrix;
    }

    private static double[][] swapCols(double[][] matrix, int step, int except) {
        int last = matrix.length - step - 1;
        for (int i = 0; i < matrix.length; i++) {
            double temp = matrix[except][i];
            matrix[except][i]= matrix[last][i];
            matrix[last][i] = temp;
        }
        return matrix;
    }
}
