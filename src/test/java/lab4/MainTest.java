package lab4;

import java.io.*;

import static lab4.Vectors.inputVector;
import static lab4.Vectors.readVector;
import static lab4.Vectors.writeVector;
import static org.junit.Assert.*;

public class MainTest {

    public static void main(String[] args) throws IOException {
        //Создание объектов
        Vector vector = new ArrayVector(2);
        vector.setElement(0, 10);
        vector.setElement(1, 20);

        LinkedListVector listVector = new LinkedListVector();
        listVector.addElement(1);
        listVector.addElement(2);
        //Файлы
        File fbyte = new File("array_byte.txt");
        File fsymb = new File("array_symbol.txt");
        File fbyteVector = new File("link_byte.txt");
        File fsymbVector = new File("link_symbol.txt");
        //Запись байтового потока
        try {
            FileOutputStream fos = new FileOutputStream(fbyteVector);
            Vectors.outputVector(vector, fos);

            FileOutputStream outputStream = new FileOutputStream(fbyte);
            Vectors.outputVector(listVector, outputStream);

            outputStream.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("ошибка файла: " + e);
        }
        System.out.println("Запись Успешна");
        //Вывод результата в Консоль  ! Переопределен метод ToString() у векторов
        InputStream in = null;
        InputStream inputStream = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fbyte));
            System.out.println(inputVector(in));
            inputStream = new BufferedInputStream(new FileInputStream(fbyteVector));
            System.out.println(inputVector(inputStream));
        } finally {
            if (in != null)
                in.close();
            if (inputStream != null)
                inputStream.close();
        }
        //Запись символьного потока
        FileWriter fileWriter = null;
        FileWriter fileWriterLink = null;
        try {
            fileWriter = new FileWriter(fsymbVector);
            writeVector(vector, fileWriter);

            fileWriterLink = new FileWriter(fsymb);
            writeVector(listVector, fileWriterLink);

            fileWriter.close();
            fileWriterLink.close();
        } catch (IOException e) {
            System.err.println("ошибка файла: " + e);
        }
        System.out.println("Запись Успешна");
        //Вывод символьного потока
        FileReader fileReader = null;
        FileReader fileReaderVector = null;
        try {
            fileReader = new FileReader(fsymbVector);
            System.out.println(readVector(fileReader));
            fileReaderVector = new FileReader(fsymb);
            System.out.println(readVector(fileReaderVector));
        } finally {
            if (fileReader != null)
                fileReader.close();
            if (fileReaderVector != null)
                fileReaderVector.close();
        }
        //Сериализация
        //Запись
        System.out.println("MainTest.Сериализация.Запись");
        try {
            FileOutputStream outputStream = new FileOutputStream("SerializableVector.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(vector);
            objectOutputStream.close();
            outputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream("SerializableLinkVector.txt");
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream);

            stream.writeObject(listVector);
            stream.close();
            fileOutputStream.close();

        }
        catch (IOException e)
        { e.printStackTrace();}
        //Чтение
        System.out.println("MainTest.Сериализация.Чтение");
        try {
            FileInputStream inputStream1 = new FileInputStream("SerializableVector.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream1);

            System.out.println(objectInputStream.readObject());
            objectInputStream.close();
            inputStream1.close();

            FileInputStream fileInputStream = new FileInputStream("SerializableLinkVector.txt");
            ObjectInputStream inputStream2 = new ObjectInputStream(fileInputStream);
            LinkedListVector result =(LinkedListVector) inputStream2.readObject();
            System.out.println(result);
            inputStream2.close();
            fileInputStream.close();
        }
        catch (IOException|ClassNotFoundException e)
        { e.printStackTrace();}
    }
}