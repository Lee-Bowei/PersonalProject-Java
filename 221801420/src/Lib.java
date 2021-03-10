import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lib {
    static Map<String,Integer> wordsMap = new TreeMap<>();//���ʺ�Ƶ�ʵ�ӳ���
    
    /*
     * ���ܣ�����utf-8����Ķ�ָ��
     * ���룺File�ļ�ָ��
     * �����BufferedReaderָ��
     */
    static BufferedReader getReader(File file) throws IOException {
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
          throw e;
        }
        return new BufferedReader(read);
    }
    
    /*
     * ���ܣ����ַ���д��ָ���ļ�
     * ���룺strΪ�ַ�����filenameΪ�ļ���
     * �������
     */
    static void writeFile(String str,String fileName) {
        File file = new File(fileName);
        try {
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            buffWriter.write(str);
            buffWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * ���ܣ�ͳ���ļ��ַ���
     * ���룺File�ļ�ָ��
     * ������ļ��к��е��ַ���
     */
    static int statisticsCharacters(File file) throws FileNotFoundException {
        int characterNum = 0;
        int characterAscill = 0;
        try {
            BufferedReader in = getReader(file);
            while (true) {
                characterAscill=in.read();
                if (characterAscill >= 0 && characterAscill <= 127) {
                    characterNum ++;
                }
                if (characterAscill == -1) break;
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterNum;
    }
	 
    /*
     * ���ܣ�ͨ��������ʽ�Ե��ʽ��в���
     * ���룺File�ļ�ָ��
     * ��������ļ��к��еĵ�����
     */
    static int statisticsWords(File file) throws FileNotFoundException {
	     int wordNum = 0;
	     int num = 0;
	     String s = null;
	     try {
	         String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
             Pattern p = Pattern.compile(ragex);
	         BufferedReader in = getReader(file);
	         String str = null;
	         while ((str = in.readLine()) != null){
				
	             //ͨ��������ʽ��ÿһ�е��ַ�������ƥ����ң������ڷ��������ĵ��������wordsMap
	           /*  String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
	             Pattern p = Pattern.compile(ragex);*/
	             Matcher m = p.matcher(str);
	             while (m.find()) {
	                 s = m.group();
	                 wordNum ++;
	                 s = s.toLowerCase();
	                 if (wordsMap.containsKey(s)) {
	                     num = wordsMap.get(s);
	                     wordsMap.put(s, num + 1);
	                 }else {
	                     wordsMap.put(s, 1);
	                 }
	             }
	         }
	     } catch (FileNotFoundException e) {
	         throw e;
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return wordNum;
    }
	 
    /*
     * ���ܣ�ͨ����ÿһ�е��ַ����ļ����ͳ���ļ�����Ч����
     * ���룺File�ļ�ָ��
     * ��������ļ��к��е���Ч����
     */
    static int statisticsLines(File file) throws FileNotFoundException {
        int lineNum = 0;
        int i = 0;
        try {
            BufferedReader in = getReader(file);
            String str = null;
            while ((str = in.readLine()) != null){
                for (i = 0;i<str.length();i++) {
                    if (str.charAt(i) != ' ' && str.charAt(i) !='\t') {
                        lineNum++;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
           throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNum;
    }
    
    /*
     * ���ܣ�ͨ����ÿ�����ʷ���treemap���ٽ�treemap����value����ɻ��ǰʮ�����ʵ�Ƶ��
     * ���룺�ļ���ָ��
     * ��������ֵ���������ɵ�List
     */
    static  List<Map.Entry<String, Integer>> wordsFrequency(File file) throws FileNotFoundException{
        
       //�˲��ֿ��ڲ�ѯ���ʸ�����ͬʱ���
       /* Map<String,Integer> wordsMap = new TreeMap<>();//���ʺ�Ƶ�ʵ�ӳ���
        try {
            BufferedReader in = getReader(file);
            String str = null;
            while ((str = in.readLine()) != null){
               
                //ͨ��������ʽ��ÿһ�е��ַ�������ƥ����ң������ڷ��������ĵ��������wordsMap
                String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
                Pattern p = Pattern.compile(ragex);
                Matcher m = p.matcher(str);
                while (m.find()) {
                    String s = m.group();
                    s = s.toLowerCase();
                    if (wordsMap.containsKey(s)) {
                        int num = wordsMap.get(s);
                        wordsMap.put(s, num + 1);
                    }else {
                        wordsMap.put(s, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet()); 
        
        //ͨ���Ƚ���ʵ�ֱȽ�
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });    
        return list;
    }
    
}
