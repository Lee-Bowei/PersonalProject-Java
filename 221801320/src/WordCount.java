package wordcount;

import java.io.IOException;

public class WordCount {
	public static String output = "1";
	
	public static void main(String[] args) throws IOException {
		
        String inputFile = args[0];
        String outputFile = args[1];
        Lib.charactersCount(inputFile,outputFile);//ͳ���ļ��ַ�����
        Lib.wordsCount(inputFile,outputFile);//ͳ���ļ�������
        Lib.linesCount(inputFile,outputFile);//ͳ���ļ���Ч����
        Lib.wordNum(inputFile,outputFile);//ͳ���ļ��ĵ�����

    }
}
