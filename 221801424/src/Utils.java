import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһ��������
 */
public class Utils {

    /**
     * ���巵�ز���
     * 1.������
     * 2.�ַ���
     * 3.����
     * 4.key���浥�ʣ�value�洢�õ��ʳ��ֵĴ���
     * 5.�洢�ļ�������Ϣ
     */
    private int word_num = 0;
    private int char_num = 0;
    private int line_num = 0;
    private HashMap <String, Integer> map = new HashMap<>();
    private StringBuilder stringBuilder = new StringBuilder();





    /**
     * 1.�������ж��Ƿ�Ϊasc����ͳ���ַ���
     *
     * @param filepath
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public int charNums(String filepath) throws IOException {
        // ͨ��������ʽ��ƥ��
//        String regexs = "\\p{ASCII}";
//        Pattern pattern = Pattern.compile(regexs);
//        Matcher matcher = pattern.matcher(characters);
//        while (matcher.find()) {
//            // �ҵ�һ����ĸ���ۼ�
//            char_num++;
//        }
        BufferedReader bufferedReader = null;
        try{
            String pre_path=new File("").getAbsolutePath();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream( pre_path+ "\\" + filepath),"utf-8"));
            while((bufferedReader.read())!=-1)
            {
                char_num+=1;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(bufferedReader!=null){
            bufferedReader.close();
        }
        return char_num;
    }


    /**
     * 2.����������ʽ��ͳ�Ƶ�����
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public int wordNums(StringBuilder words) {
        // ����������ʽ��ȥ���ݿո�����ƪ����
        // temp�ַ������齫�������еĵ���
        //���������ַ������ⱻ����Ϊ�ǵ��ʵ�һ����
        String[] temps = words.toString() .split("[^a-zA-Z0-9]");
        // �������⣺����ĸ��ͷ�ҳ��ȴ���4�ĵ���
        String regexs = "^[a-zA-Z]{4,}.*";
        // ѭ������������飬����������ʽȥƥ��
        for (int i = 0; i < temps.length; i++) {
            if (temps[i].matches(regexs)) {
                // ƥ��ɹ���������1
                word_num++;
                //System.out.println(temps[i]);
            }
        }
        return word_num;
    }


    /**
     * 3.�������ж�ȡʵ�ֻ�ȡ����
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public int lineNums(String path) throws IOException {
        BufferedReader bufferedReader = null;
        String pre_path=new File("").getAbsolutePath();
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream( pre_path+ "\\" + path),"utf-8"));
            String line;
            // ͨ��ѭ���������ж�ȡ�ļ�
            // ͬʱ��¼��ȡ��������
            while ((line = bufferedReader.readLine()) != null) {
                //ƥ������ǿհ��ַ�
                if (line.length() != 0 && !line.matches("\\s+")) {
                    line_num++;
                }
            }
        } catch (IOException e) {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            e.printStackTrace();
        }
        return line_num;
    }


    /**
     * 4.ͨ��map��ͳ�Ƶ��ʵĳ��ִ���
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public List mapNums(StringBuilder words) {
        //���ÿո����ָ����ʣ�������������ʽ��ƥ����ȷ�ĵ���
        String[] temps = words.toString() .split("[^a-zA-Z0-9]");
        String regexs = "^[a-zA-Z]{4,}.*";
        for (int i = 0; i < temps.length; i++) {
            if (temps[i].matches(regexs)) {
                // map�в��������¼�¼
                if (!map.containsKey(temps[i].toLowerCase())) {
                    map.put(temps[i].toLowerCase(), 1);
                } else {
                    // ���������num+1
                    int num = map.get(temps[i].toLowerCase());
                    map.put(temps[i].toLowerCase(), num + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        // ���������ڲ���
        // ���ȸ���Ƶ�ʱȽϣ����Ƶ����ͬ���Ƚ��ֵ���
        list.sort((Comparator<Map.Entry>) (o1, o2)
                -> ((Integer) o1.getValue()).compareTo((Integer) o2.getValue()) != 0
                ? ((Integer) o2.getValue()).compareTo((Integer) o1.getValue()) : getCharNums((String)o1.getKey(),((String)o2.getKey())));
        // ����list ǰʮ�� ���ݣ�Ҳ�����ֵ�ǰʮ�ĸ�Ƶ��
        return list.size() < 10 ? list.subList(0, list.size()) : list.subList(0, 10);
    }

    /**
     * ʵ���ֵ��������
     * @param op1
     * @param op2
     * @return
     */
    public static int getCharNums(String op1,String op2){
        char []chars1=op1.toCharArray();
        char []chars2=op2.toCharArray();
        int len=0;
        if(chars1.length==chars2.length){
            len=chars1.length;
            for(int i=0;i<len;i++){
                if ((int)chars1[i]>(int)chars2[i]){
                    return 1;
                }
                else if((int)chars1[i]<(int)chars2[i]){
                    return -1;
                }
            }
            return 0;
        }
        else if(chars1.length<chars2.length){
            return -1;
        }
        else {
            return 1;
        }
    }


    /**
     * 5.���ڶ�ȡ�ļ������ַ�
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public StringBuilder readIn(String path) {
        //�����ļ����ڵ�ǰ��Ŀ��
        String pre_path=new File("").getAbsolutePath();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream( pre_path+ "\\" + path),"utf-8"));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("�ļ�������");
            e.printStackTrace();
        }
        return stringBuilder;
    }


    /**
     * 6.д����Ϣ
     *
     * @param path
     * @param message
     */
    @SuppressWarnings("JavaDoc")
    public void writeTo(String path, String message) {
        try {
            //���ɵ��ļ����ڵ�ǰ��Ŀ��
            String pre_path=new File("").getAbsolutePath().toString();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream( pre_path+ "\\" + path),"utf-8"));
            bufferedWriter.write(message);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getTime() {
        Date newDate = new Date();
        return newDate.getTime();
    }



//    /**
//     * ����
//     *
//     * @param args
//     */
// @SuppressWarnings("JavaDoc")
// public static void main(String[] args) throws IOException {
//        //System.out.println(args[0]);
//        Utils utils=new Utils();
//        System.out.println(utils.wordNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(utils.charNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(utils.mapNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(utils.lineNums("1.txt"));
//        System.out.println(new File("").getAbsolutePath());
//        utils.writeTo("2.txt","hello world");
//        }
}
