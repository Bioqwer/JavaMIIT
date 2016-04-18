package parsePdf;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 */
public class PdfParser {

    private static final String COMMA = ";";
    private static final Pattern datePattern = Pattern.compile("Дата(.*)№(.*)");
    private static final Pattern volumePattern = Pattern.compile(": (.*) Ква");
    private static final Pattern roomNumber = Pattern.compile(
            "Космонавтов.*кв[\\. ](.*)");
    private static final Pattern ownerNamePatter = Pattern.compile(
            "2\\.1\\. (.*)");
    private static final Pattern ownerNamePatterWhile = Pattern.compile(
            "3\\.");
    private static final Pattern ownerNumber = Pattern.compile("3\\.1\\..*?(\\d.*)");

    private static void parseAndWrite(File f1,
                                      StringBuilder builder) {
        PDDocument document;
        try {
            document = PDDocument.load(f1);

            PDFTextStripper textStripper = new PDFTextStripper();

            String text = textStripper.getText(document);

            String[] rows = text.split(System.lineSeparator());

            write(builder, f1.getName());

            add(datePattern, builder, rows[5], 1);

            Pattern dateResolvePattern = Pattern.compile("(.*) ,");
            Matcher matcher1 = dateResolvePattern.matcher(rows[7]);
            if (matcher1.find()) {
                write(builder, matcher1.group(1));
            }
            add(datePattern, builder, rows[5], 2);

            String objectNumber = rows[11];
            write(builder, objectNumber);

            String object = rows[12].split(":")[1];
            write(builder, object);

            String target = rows[13].split(":")[1];
            write(builder, target);

            Matcher matcher = volumePattern.matcher(rows[14]);
            if (matcher.find()) {
                write(builder, matcher.group(1).replaceFirst("\\.",","));
            }

            String etaj = rows[16].split(":")[1];
            write(builder, etaj);

            Stack<Pattern> patterns = new Stack<>();
            patterns.push(ownerNumber);
            patterns.push(ownerNamePatter);
            patterns.push(roomNumber);

            for (int i = 19; i < 50; i++) {
                if (!patterns.empty()) {
                    if (patterns.peek().matcher(rows[i]).find())
                        add(patterns.pop(), builder, rows[i], 1);
                } else {
                    String ownerDate = rows[i];
                    builder.append(ownerDate);
                    break;
                }
            }

            /*for (int i = 0; i < rows.length; i++) {
                String s = rows[i];
                System.out.println(i + " " + s);
            }*/
            builder.append(System.lineSeparator());
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void add(Pattern pattern,
                            StringBuilder builder,
                            String row,
                            int groupNumber) {
        Matcher matcher = pattern.matcher(row);
        if (matcher.find()) {
            write(builder, matcher.group(groupNumber));
        }
    }

    private static void write(StringBuilder builder,
                              String data) {
        builder.append(data.trim()).append(COMMA);
    }

    public static void main(String[] args) throws IOException, PrinterException {
        String pathToPdf = args[0];

        File directoryWithFiles = new File(pathToPdf);
        List<File> ls = new ArrayList<>(Arrays.asList(directoryWithFiles.listFiles()));

        File f1 = ls.get(0);
        System.out.println(f1);

        String header = "FileName;Дата Запроса;Дата Рассмотрения;Номер запроса;Номер объекта;Объект;Назначение объекта;Площадь;Этаж;Квартира;Правообладатель;Собственность;Дата Собственность";

        StringBuilder builder = new StringBuilder(header)
                .append(System.lineSeparator());
        Long start = System.currentTimeMillis();
        for (int i = 0; i < ls.size(); i++) {
            File file = ls.get(i);
            //if (file.getName().equals(
              //      "obj_01ee2235-a9b0-4dd5-9f73-68fe65cb6129.pdf")) {
                System.out.println(i + " of " + ls.size());
                parseAndWrite(file, builder);
            //}
        }
        System.out.println("Exec " + (System.currentTimeMillis() - start));

        File output = new File("home7.csv");

        FileUtils.write(output, builder.toString(), Charset.forName("windows-1251"));

    }


}
