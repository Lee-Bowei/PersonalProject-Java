//import java.io.IOException;
//import java.util.List;
//
//public class Test {
//
//    /**
//     * ���Թ���1
//     */
//    public void testCharnums() throws IOException {
//        Utils utils=new Utils();
//        String message;
//        StringBuilder stringBuilder = new StringBuilder();
//        //��������ascii���Ƿ�����ȷ����
//        for(int i=0;i<128;i++){
//            stringBuilder.append((char)i);
//        }
//        message=stringBuilder.toString();
//        utils.writeTo("1.txt",message);
//        System.out.println("characters: "+utils.charNums("1.txt"));
//    }
//
//    /**
//     * ���Թ���2,��֤������Ч��Ч���ж�
//     */
//    public void testWordnums(){
//        Utils utils=new Utils();
//        StringBuilder stringBuilder = new StringBuilder();
//        //��Ӳ��ԣ������������ܳ��ֵ����
//        stringBuilder.append("cold111").append("\n"); //��
//        stringBuilder.append("col111").append("\n");  //����
//        stringBuilder.append("col]dl11").append("\n"); //����
//        stringBuilder.append("col\nd111").append("\n"); //����
//        stringBuilder.append("cola! sssb. aaaa, abbd\t dsds123").append("\n"); //5������
//
//        System.out.println("words: "+utils.wordNums(stringBuilder));
//    }
//
//    /**
//     * ���Թ���3,�ǿ��ַ�ȫ�����У�
//     * 1.txt����:
//     * ssss444 563ff 11d fase11 windows95 windows98 windows2000
//     *
//     * 1
//     *
//     * 2
//     */
//    public void testLinenums() {
//        try {
//            Utils utils=new Utils();
//            System.out.println("lines��"+utils.lineNums("1.txt"));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * ���Թ���4����֤���Ƿ񰴴�Ƶ�����Ҵ�Ƶ��ͬʱͨ���ֵ������Ƚϣ�����Ч�ʲ������롣
//     */
//    public void testMapnums(){
//        Utils utils=new Utils();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 9; i++) {
//            for (int j = 2000; j < 2010; j++) {
//                stringBuilder.append("windows").append(j).append(",\n");
//            }
//        }
//        for (int i = 0; i < 10; i++) {
//            stringBuilder.append("windows2021,\n");
//            stringBuilder.append("WINDOWS2000.\n").append("windows98!\n").append("windows95?\n").append("windosa,\n");
//            stringBuilder.append("sjy%dsd*,\n");
//     }
//        List list;
//        list = utils.mapNums(stringBuilder);
//        StringBuilder mapStringbuild = new StringBuilder();
//        for (int i = 1; i <= list.size(); i++) {
//            mapStringbuild.append(list.get(i - 1)).append("\n");
//        }
//        String mapWord = mapStringbuild.toString().replace("=", ":");
//        System.out.println(mapWord);
//    }
//
//
//    public static void main(String []args) throws IOException {
//        Test test=new Test();
//        test.testCharnums();
//        test.testWordnums();
//        test.testLinenums();
//        test.testMapnums();
//    }
//}
