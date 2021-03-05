import java.io.*;
import java.util.Map;

import java.util.TreeMap;
public class WordCount {

    public static void main(String[] args) throws IOException{
        // write your code here
        Lib.countChar(args[0]);
        Lib.countWord(args[0]);
        Lib.countLine(args[0]);
        Lib.countKeyword();

        FileWriter file=new FileWriter(args[1]);
        BufferedWriter out = new BufferedWriter(file);

        out.write("characters: "+Lib.charnum+"\n");
        out.write("lines: "+Lib.linenum+"\n");
        out.write("words: "+Lib.wordnum+"\n");
        for(int k=0;k<Lib.realnum;k++)
        {
            int max=0;
            String maxs=null;
            for(String key:Lib.keyWord.keySet()){
                if(max<(int) Lib.keyWord.get(key))
                {
                    max=(int) Lib.keyWord.get(key);
                    maxs=key;

                }
                if(max==(int) Lib.keyWord.get(key))
                {
                    if(maxs.compareTo(key)>0)
                    {
                        max=(int) Lib.keyWord.get(key);
                        maxs=key;
                    }
                }

            }
            out.write(maxs+": "+max+"\n");
            Lib.keyWord.remove(maxs);
        }


        out.close();
        System.out.println("文件创建成功！");
    }


}