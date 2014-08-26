package lab6;


import java.util.Iterator;

public interface Vector {

    public int getElement(int number);

    public void setElement(int number, int element);

    public int getVectorSize();

    public double euclideanNorm();

    public Iterator iterator();
}
