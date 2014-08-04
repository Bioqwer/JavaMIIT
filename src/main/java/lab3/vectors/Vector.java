package lab3.vectors;

/**
 * Created by Antony on 04.08.2014.
 */
public interface Vector {

    public int getElement(int number);

    public void setElement(int number, int element);

    public int getVectorSize();

    public double euclideanNorm();
}
