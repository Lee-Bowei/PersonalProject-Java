import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException{
		 	
		//Test();
		
		//File file=new File("C:\\Users\\31139\\workspace\\WordCount\\src\\input.txt");
		//FileWriter writer=new FileWriter("C:\\Users\\31139\\workspace\\WordCount\\src\\output.txt");
		File file=new File(args[0]);
		FileWriter writer=new FileWriter(args[1]);
		
		run(file,writer);

	}
	
	public static void Test() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter("C:\\Users\\31139\\workspace\\WordCount\\src\\input.txt"));
	    final String testString = "aaaz aaaa aaab aaad aaaf aaae aaae\r\n";
	    final int loopCount = 1000;
	    for (int i = 0; i < loopCount; i++) {
	        w.write(testString);
	    }
	    w.close();
	}

	public static void run(File file,FileWriter writer) {
		//ͳ���ļ����ַ�������Ӧ�����һ�У���ֻ��Ҫͳ��Ascii�룬���ֲ��迼��,�ո�ˮƽ�Ʊ�������з��������ַ�
		try{
			int num=0;
			num=Lib.getCharNum(file);
			writer.write("characters: "+num+'\n');
			System.out.println("characters��"+num);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//ͳ���ļ��ĵ�����������Ӧ����ڶ��У�
		String[] linewords= {};
		try {
			String toLine=Lib.turnToLine(file);
			linewords=Lib.splitLine(toLine);
			writer.write("words��"+Lib.countWords(linewords)+'\n');
			System.out.println("words: "+Lib.countWords(linewords));
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//ͳ���ļ�����Ч��������Ӧ��������У����κΰ����ǿհ��ַ����У�����Ҫͳ�ơ�
		try{
			int line=Lib.getLine(file);
			writer.write("lines��"+line+'\n');
			System.out.println("lines: "+line);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//ͳ���ļ��и����ʵĳ��ִ�������Ӧ���������10�У�������ֻ���Ƶ����ߵ�10����
		Lib.setFrequency(linewords,writer);
	}
	
}
