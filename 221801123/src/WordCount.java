import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class WordCount {
    /**
     * @description 文件处理工具类
     */
    static class IOUtils {
        /**
         * @param infile 待读取文件的地址
         * @return 文件字符串
         * @throws IOException
         * @description 读取指定文件，返回对应字符串形式
         */
        static String readFile(String infile) throws IOException {
            BufferedReader reader = null;
            StringBuilder builder = new StringBuilder();
            int ch = 0;

            try {
                reader = new BufferedReader(new FileReader(infile));
                while ((ch = reader.read()) != -1) {
                    builder.append((char) ch);
                }
            } catch (IOException e) {
                System.out.println("文件读取错误");
                throw e;
            } finally {
                if (reader != null)
                    reader.close();
            }

            return builder.toString();
        }

        /**
         * @param result  统计数据字符串
         * @param outfile 待写入文件的地址
         * @throws IOException
         * @description 将统计数据字符串写入文件
         */
        static void writeFile(String result, String outfile) throws IOException {
            BufferedWriter writer = null;

            try {
                writer = Files.newBufferedWriter(Paths.get(outfile), StandardCharsets.UTF_8);
                writer.write(result);
            } catch (IOException e) {
                System.out.println("文件写入错误");
                throw e;
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    /**
     * @param content 文件字符串
     * @return 统计数据字符串
     * @description 统计数据硬编码
     */
    private String getResult(String content) {
        StringBuilder result = new StringBuilder("characters: " + Lib.countCharNum(content) + "\n" + "words: "
            + Lib.countWordNum(content) + "\n" + "lines: " + Lib.countValidLineNum(content) + "\n");
        List<HashMap.Entry<String, Integer>> wordsList = Lib.sortWordMap();
        int count = 0;

        for (HashMap.Entry entry : wordsList) {
            count++;
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            if (count >= 10)
                break;
        }

        return result.toString();
    }

    /**
     * @param infile  待读取文件的地址
     * @param outfile 待写入文件的地址
     * @throws IOException
     * @description 执行统计
     */
    private void process(String infile, String outfile) throws IOException {
        String content = IOUtils.readFile(infile);
        IOUtils.writeFile(getResult(content), outfile);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("2 paths needed");
            return;
        } else if (args.length > 2)
            System.out.println("choose tow font paths");

        try {
            new WordCount().process(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("出错啦");
            e.printStackTrace();
        }
    }
}