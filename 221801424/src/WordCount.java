import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.List;

class WordCount {
    public static void main(String[] args) {
        Utils utils = new Utils();
        /**
         * ֱ������,���и����ʲ���
         */
        if (args.length != 2) {
            try {
                System.out.println("��ʽ����,ʹ��Ĭ���ļ�(input.txt),����(output.txt)");
                String readPath = "input.txt";
                String writePath = "output.txt";
//           long begintime = Utils.getTime();
                //����һ��input.txt�ļ�
                Filetest File = new Filetest();
                File.fileTest();

                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder tempStringbuild = utils.readIn(readPath);

                String characters = tempStringbuild.toString();
                long begintime = Utils.getTime();
                stringBuilder.append("characters: ").append(utils.charNums(readPath)).append("\n");
                stringBuilder.append("words: ").append(utils.wordNums(tempStringbuild)).append("\n");
                stringBuilder.append("lines: ").append(utils.lineNums(readPath)).append("\n");

                List list;
                list = utils.mapNums(tempStringbuild);
                StringBuilder mapStringbuild = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    mapStringbuild.append(list.get(i - 1)).append("\n");
                }
                String mapWord = mapStringbuild.toString().replace("=", ": ");

                System.out.println(stringBuilder + mapWord);

                utils.writeTo(writePath, (stringBuilder + mapWord));
                long endTime = Utils.getTime();
                long time = endTime - begintime;
                System.out.println("���гɹ�,��ʱ" + time + "����");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * ��������������
         */
        else {
            try {
                String readPath = args[0];
                String writePath = args[1];
//            long begintime = Utils.getTime();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder tempStringbuild = utils.readIn(readPath);
                //���������ַ������ⱻ����Ϊ�ǵ��ʵ�һ����
                String characters = tempStringbuild.toString();
                stringBuilder.append("characters: ").append(utils.charNums(readPath)).append("\n");
                stringBuilder.append("words: ").append(utils.wordNums(tempStringbuild)).append("\n");
                stringBuilder.append("lines: ").append(utils.lineNums(readPath)).append("\n");
                List list;
                list = utils.mapNums(tempStringbuild);
                StringBuilder mapStringbuild = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    mapStringbuild.append(list.get(i - 1)).append("\n");
                }
                String mapWord = mapStringbuild.toString().replace("=", ": ");
                System.out.println(stringBuilder + mapWord);
                utils.writeTo(writePath, (stringBuilder + mapWord));
//            long endTime = Utils.getTime();
//            long time = endTime - begintime;
//            System.out.println("���гɹ�,��ʱ" + time + "����");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}