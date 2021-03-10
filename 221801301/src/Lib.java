import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
	//ʵ��ͳ���ļ����ַ����Ĺ���
	public static int getCharNum(File file) throws FileNotFoundException {
		InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
		int num=0;
		try {
			while(inputStreamReader.read()!=-1)
			{
				num++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//ʵ��ͳ���ļ������Ĺ���
	public static int getLine(File file) throws IOException{
		int linenum=0;
		BufferedReader input = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = input.readLine()) != null) {
            if (line.trim().equals("")) //����Ϊ���е����
            	continue;
            else
            	linenum++;
        }
		return linenum;
	}
	
	//���ļ�ת��Ϊһ���ַ��� ÿ��֮���ÿո�ֿ�
	public static String turnToLine(File file) {
		BufferedReader bufferedReader=null;
		try {
			bufferedReader=new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null) {
				stringBuffer = stringBuffer.append(line+' ');//���Ͽո��Էָ�����
			}
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} 
		catch(IOException e1) {
			e1.printStackTrace();
		}
		String sb=stringBuffer.toString().toLowerCase();//������ȫ��ͳһתΪСд
		return sb;
	}
		
	//�ָ�ÿ������
	public static String[] splitLine(String str){
		String[] linewords=str.split("\\W+");//ƥ��ǵ����ַ���ָ�
		return linewords;
	}
		
	//�ж��Ƿ�Ϊ�Ϸ����ʣ�ͳ�ƺϷ����ʸ���
	public static int countWords(String[] linewords) {
		int cnt=0;
		Pattern pattern = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");//�ж��Ƿ�Ϊ�Ϸ�����
		for(int i=0;i<linewords.length;i++) {
			Matcher matcher = pattern.matcher(linewords[i]);
			if(matcher.find()) {
				//System.out.println(matcher.group());
			 	cnt++;
			}
		}
		return cnt;
	}
	
	//��hashmapͳ�ƴ�Ƶ
	public static void setFrequency(String[] linewords,FileWriter writer) {
		Map<String,Integer> hashMap=new HashMap<String,Integer>();
		Set<String> wordSet=hashMap.keySet();
		Pattern pattern = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");
		for(int i=0;i<linewords.length;i++) {
			Matcher matcher = pattern.matcher(linewords[i]);
			if(matcher.find()) {
				String word=matcher.group();
				if(wordSet.contains(word)) {//����Ѿ������������
					Integer number=hashMap.get(word);//��map���ҵ��õ���value++
					number++;
					hashMap.put(word, number);
				}
				else {
					hashMap.put(word, 1);//�ŵ�map�У�value��Ϊ1
				}
			}
		}
		//����
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());//value��ͬ����key�ֵ�����������
                }
                else
                    return o2.getValue().compareTo(o1.getValue());//value��ͬ��������
            }
        });
		//���ǰʮ������
		if(list.size()<=10) {
			for (int i = 0; i < list.size(); i++) {
				try {
					writer.write(list.get(i).getKey()+": "+list.get(i).getValue()+'\n');
					System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
				}
				catch(IOException exc){
					System.out.println("File error!");
				}
			}   
		}
		else {
			for (int i=0;i<10;i++) {
				try {
					writer.write(list.get(i).getKey()+": "+list.get(i).getValue()+'\n');
					System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
				}
				catch(IOException exc){
					System.out.println("File error!");
					}
				}
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
