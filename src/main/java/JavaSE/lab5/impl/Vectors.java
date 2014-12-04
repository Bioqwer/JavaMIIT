package JavaSE.lab5.impl;

import JavaSE.lab3.IncompatibleVectorSizesException;
import JavaSE.lab5.Vector;
import JavaSE.lab5.VectorFactory;

import java.io.*;

/**
 * Created by Antony on 02.08.2014.
 */
public class Vectors {

    private static VectorFactory vectorFactory = new JavaSE.lab5.impl.VectorFactory();

    public static Vector createInstance(int size) {
        return vectorFactory.createVector(size);
    }

    public static void multiplicationToNumber(ArrayVector arrayVector, int number) {
        for (int i = 0; i < arrayVector.getVectorSize(); i++)
            arrayVector.setElement(i, arrayVector.getElement(i) * number);
    }

    public static ArrayVector additionVector(ArrayVector vectorA, ArrayVector vectorB) throws IncompatibleVectorSizesException {
        if (vectorA.getVectorSize() != vectorB.getVectorSize())
            throw new IncompatibleVectorSizesException();
        ArrayVector result = (ArrayVector) createInstance(vectorA.getVectorSize());
        for (int i = 0; i < vectorA.getVectorSize(); i++)
            result.setElement(i, vectorA.getElement(i) + vectorB.getElement(i));
        return result;
    }

    public static int getScalar(ArrayVector vectorA, ArrayVector vectorB) throws IncompatibleVectorSizesException {
        if (vectorA.getVectorSize() != vectorB.getVectorSize())
            throw new IncompatibleVectorSizesException();
        int result = 0;
        for (int i = 0; i < vectorA.getVectorSize(); i++)
            result += vectorA.getElement(i) * vectorB.getElement(i);
        return result;
    }

    public static void outputVector(Vector v, OutputStream out) {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(v.getClass());
            oos.writeInt(v.getVectorSize());
            for (int i = 0; i < v.getVectorSize(); i++)
                oos.writeInt(v.getElement(i));
        } catch (IOException e) {
        }
    }

    public static void writeVector(Vector v, Writer out) {
        try {
            out.write(v.getClass().getName() + " " + v.getVectorSize());
            for (int i = 0; i < v.getVectorSize(); i++) {
                out.write(" " + v.getElement(i));
            }
            out.flush();
        } catch (IOException e) {
        }
    }

    public static Vector inputVector(InputStream in) {
        Vector v = null;
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            Class arrayClass = (Class) ois.readObject();
            int size = ois.readInt();
            if (arrayClass == Class.forName("JavaSE.lab4.ArrayVector"))
                v = createInstance(size);
            else if (arrayClass == Class.forName("JavaSE.lab4.LinkedListVector"))
                v = new LinkedListVector(size);
            for (int i = 0; i < v.getVectorSize(); i++)
                v.setElement(i, ois.readInt());
        } catch (IOException | ClassNotFoundException e) {
        }
        return v;
    }

    public static Vector readVector(Reader in) {
        Vector v = null;
        try {
            StreamTokenizer st = new StreamTokenizer(in);
            if (st.nextToken() != StreamTokenizer.TT_EOF) {
                String type = st.sval;
                if (st.nextToken() != StreamTokenizer.TT_EOF) {
                    int size = (int) st.nval;
                    if (type.equals("JavaSE.lab4.ArrayVector")) {
                        v = createInstance(size);
                    } else if (type.equals("JavaSE.lab4.LinkedListVector")) {
                        v = new LinkedListVector(size);
                    }
                    for (int i = 0; st.nextToken() != StreamTokenizer.TT_EOF; i++) {
                        v.setElement(i, (int) st.nval);
                    }
                    return v;
                }
            }
        } catch (IOException e) {
        }
        return null;
    }

    public void setVectorFactory(VectorFactory vectorFactory) {
        this.vectorFactory = vectorFactory;
    }


}
