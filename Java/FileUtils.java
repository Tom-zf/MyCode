import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFile(String path){
        return readFile(new File(path));
    }

    public static List<String> readFile(File file){
        BufferedReader br = null;
        List<String> content = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String buf = null;
            content = new ArrayList<String>();
            while((buf = br.readLine()) != null){
                content.add(buf);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
       }
        return content;
    }

    public static boolean writeFileWithAdd(String path, List<String> content){
        return writeFileWithAdd(new File(path), content);
    }

    public static boolean writeFileWithAdd(File file, List<String> content){
        return writeFile0(file, content, true);
    }

    public static boolean writeFile(String path, List<String> content){
        return writeFile0(new File(path), content, false);
    }

    public static boolean writeFile(File file, List<String> content){
        return writeFile0(file, content, false);
    }

    private static boolean writeFile0(File file, List<String> content,boolean isAppend){
        BufferedWriter bw = null;
        boolean flag = false;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,isAppend),"utf-8"));
            for(String line : content){
                bw.write(line+"\r\n");
            }
            flag = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static void writeStream(InputStream input,OutputStream output) throws IOException {
        byte[] buf = new byte[1024];
        int len = 0;
        while(( len = input.read(buf,0,buf.length)) != -1){
            output.write(buf,0,len);
            output.flush();
        }
    }

    public static void main(String[] args){
        String path = "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt";
        List<String> content = readFile(path);
        for(String line : content)
            System.out.println(line);

        List<String> content_to_add = new ArrayList<String>();
        content_to_add.add("hell0");
        content_to_add.add("你好啊 ！@");
        writeFileWithAdd(path, content_to_add);
    }
}
