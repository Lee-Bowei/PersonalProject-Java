import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
public class WordCount {
    public static void main(String[] args) {
        Lib lib = new Lib();
        if (args.length == 2) {
            try {
                File inputFile = new File(args[0]);
                File outputFile = new File(args[1]);
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
				
                //��ȡ�ַ����������
                int characterNum = lib.statisticsCharacters(inputFile);
                buffWriter.write("characters: " + characterNum);
                buffWriter.newLine();
				
                //��ȡ���ʸ��������
                int wordNum = lib.statisticsWords(inputFile);
                buffWriter.write("words: " + wordNum);
                buffWriter.newLine();
				
                //��ȡ��Ч���������
                int lineNum = lib.statisticsLines(inputFile);
                buffWriter.write("lines: " + lineNum);
                buffWriter.newLine();
                
                //�Ե���Ƶ��ӳ�����������������Ƶ����ߵ�ǰʮ������
                List<Map.Entry<String, Integer>> list=lib.wordsFrequency(inputFile);
                int num = 1;
                for(Map.Entry<String, Integer> map : list) {  
                    if(num <= 10) {  
                        buffWriter.write(map.getKey() + ": " + map.getValue());
                        buffWriter.newLine();
                        num++;  
                    }else break;  
                }
                buffWriter.close();
            }catch (FileNotFoundException e) {
                System.out.print("�޷��ҵ��ļ�");
            }catch(IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("�ļ������������");
        }
    }
}

