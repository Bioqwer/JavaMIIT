package JavaSE.toByte;

import junit.framework.TestCase;
import JavaSE.toByte.impl.ArrayVector;
import JavaSE.toByte.impl.LinkedListVector;
import JavaSE.toByte.impl.Vectors;

import java.io.*;

public class ToByteTests extends TestCase {

    public static void main(String[] args) {
        Vector vector = new ArrayVector(3);
        Vector linkVector = new LinkedListVector(3);
        for (int i = 0; i < 3; i++) {
            vector.setElement(i,i);
            linkVector.setElement(i, i);
        }

        try {
            //to file and Back Array
            FileOutputStream out = new FileOutputStream("byteSerialization.txt");
            Vectors.writeVectorByte(vector,out);
            out.close();

           Vector result = null;
            FileInputStream inputStream =new FileInputStream("byteSerialization.txt");
            result = Vectors.readVectorByte(inputStream);
            System.out.println("result = " + result);
            inputStream.close();

            //to file and Back Link
            out = new FileOutputStream("byteSerialization.txt");
            Vectors.writeVectorByte(linkVector,out);
            out.close();

            result = null;
            inputStream = new FileInputStream("byteSerialization.txt");
            result = Vectors.readVectorByte(inputStream);
            System.out.println("result = " + result);
            inputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}