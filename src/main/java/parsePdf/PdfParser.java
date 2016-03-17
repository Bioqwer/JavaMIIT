package parsePdf;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 */
public class PdfParser {

    private static final String COMMA = ",";

    private static void printRecursive(COSObject object) {


        System.out.println(object.getObject());


    }

    private static void parseAndWrite(File f1,
                                      StringBuilder builder) {
        PDDocument document;
        try {
            document = PDDocument.load(f1);

            PDFTextStripper textStripper = new PDFTextStripper();

            String text = textStripper.getText(document);

            String[] rows = text.split(System.lineSeparator());

            builder.append(f1)
                    .append(COMMA);


            Pattern pattern = Pattern.compile("Дата (.*)№");
            Matcher matcher = pattern.matcher(rows[5]);

            String data = matcher.group(1);


            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, PrinterException {
        String pathToPdf = args[0];

        File directoryWithFiles = new File(pathToPdf);
        List<File> ls = new ArrayList<>(Arrays.asList(directoryWithFiles.listFiles()));

        File f1 = ls.get(0);
        System.out.println(f1);

        String header = "FILE,Дата Запроса,Дата Рассмотрения,Номер запроса,Номер объекта,Объект,Назначение объекта,Площадь,Этаж,Квартира,Правообладатель,Собственность,Дата Собственность,Возможно с ошибкой при парсинге";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            parseAndWrite(ls.get(i), builder);
        }
    }


}
