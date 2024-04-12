import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Pavel\\IdeaProjects\\lab16_moduli\\Inp.txt";
        String outputFile = "C:\\Users\\Pavel\\IdeaProjects\\lab16_moduli\\outp.txt";

        removeComments(inputFile, outputFile);

    }

    private static void removeComments(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            boolean inCommentBlock = false;

            while ((line = reader.readLine()) != null) {
                if (inCommentBlock) {
                    if (line.contains("*/")) {
                        inCommentBlock = false;
                        int endPos = line.indexOf("*/");
                        if (endPos != -1) {
                            line = line.substring(endPos + 2);
                        } else {
                            continue; // конец коммента не найден в этой строке
                        }
                    } else {
                        continue; // пропускаем всю строку если блок комментов не закончился
                    }
                }

                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//"));
                }

                if (line.contains("/*")) {
                    if (line.contains("*/")) {
                        line = line.replace(line.substring(line.indexOf("/*"), line.indexOf("*/") + 2), "");
                    } else {
                        inCommentBlock = true;
                        line = line.substring(0, line.indexOf("/*"));
                    }
                }

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}