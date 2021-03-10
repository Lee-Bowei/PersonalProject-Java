import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lib {

    /**
     * 获得文件的内容
     *
     * @param filename 输入文件的路径
     * @return content 输入文件的内容
     */
    public static String getFile(String filename) {
        StringBuilder content = new StringBuilder();
        BufferedInputStream bufferedInput = null;
        byte[] buffer = new byte[1024];
        try {
            //创建BufferedInputStream 对象
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));
            int bytesRead;
            //从文件中按字节读取内容，到文件尾部时read方法将返回-1
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                //将读取的字节转为字符串对象
                String temp = new String(buffer, 0, bytesRead);
                content.append(temp);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //关闭 BufferedInputStream
        if (bufferedInput != null) {
            try {
                bufferedInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }

    /**
     * 字符统计
     *
     * @param content 文件内容
     * @return int
     */
    public static int countCharacters(String content) {
        return content.length();
    }

    /**
     * 单词统计
     *
     * @param map 词频关系
     * @return int
     */
    public static int countWords(Map<String, Integer> map) {
        int words = 0;
        for (Integer value : map.values()) {
            words += value;
        }
        return words;
    }

    /**
     * 行数统计
     *
     * @param content 输入文件的内容
     * @return int
     */
    public static int countLines(String content) {
        int lines = 0;
        StringTokenizer st = new StringTokenizer(content, "\n");
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            if (!line.trim().equals("")) {
                lines++;
            }
        }
        return lines;
    }

    /**
     * 构造输出文件的信息
     *
     * @param chars     字符数
     * @param words     单词数
     * @param lines     总行数
     * @param frequency 词频
     * @return String
     */
    public static String answerBuilder(int chars, int words, int lines, String frequency) {
        //拼接输出信息
        return "characters: " + chars +
                "\nwords: " + words +
                "\nlines: " + lines + "\n" +
                frequency;
    }

    /**
     * 获取词频表
     *
     * @param content 文章内容
     * @return Map 词频hashmap集合
     */
    public static Map<String, Integer> countFrequency(String content) {
        Map<String, Integer> map = new HashMap<>();
        content = content.replaceAll("[^A-Za-z0-9]", " ");
        StringTokenizer st = new StringTokenizer(content, " ");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            //将单词全部转为小写
            word = word.toLowerCase();
            //根据作业要求，判断截取字符串是否为单词
            if (IsWord(word)) {
                if (map.get(word) != null) {
                    int value = map.get(word);
                    value++;
                    map.put(word, value);
                } else {
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    /**
     * 对词频表排序
     *
     * @param map hashmap的集合
     * @param num 输出数量
     */
    public static String sortFrequency(Map<String, Integer> map, int num) {
        StringBuilder result = new StringBuilder();
        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        //idea自动转化成lambda表达式
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list.size() < num) {
            num = list.size();
        }
        if (list.size() == 1)
            return list.get(0).getKey() + ": " + list.get(0).getValue();
        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list.get(i).getValue().equals(list.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    String temp = s + ": " + list.get(i).getValue();
                    result.append(temp);
                    outputCount++;
                    if (outputCount != num) {
                        result.append("\n");
                    } else
                        break;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list.get(i).getKey());
        }
        return result.toString();
    }

    /**
     * 单词判定：前四个字符为字母判定为单词
     *
     * @param word 单词
     * @return boolean
     */
    public static boolean IsWord(String word) {
        if (word.length() < 4)
            return false;
        char letter;
        for (int i = 0; i < 4; i++) {
            letter = word.charAt(i);
            if (!(letter >= 'a' && letter <= 'z'))
                return false;
        }
        return true;
    }

    /**
     * 输出内容
     *
     * @param output        输出文件的路径
     * @param answerBuilder 输出内容
     */
    public static void outputInfo(String output, String answerBuilder) {
        //控制台输出
        System.out.println(answerBuilder);
        //文件输出
        File outputFile = new File(output);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), StandardCharsets.UTF_8));
            writer.write(answerBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}