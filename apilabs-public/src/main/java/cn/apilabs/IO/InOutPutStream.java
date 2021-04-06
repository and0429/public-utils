package cn.apilabs.IO;

import java.io.*;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/18
 * @className InOutPutSteam
 */
public class InOutPutStream {


    public static void main(String[] args) throws IOException {
        copyFile(new File("ka.jpg"), new File("ka1.jpg"));
    }

    /**
     * @return
     */
    public static byte[] inputTobyteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int nRead = -1;
        byte[] buff = new byte[1024];
        while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
            bos.write(buff, 0, nRead);
        }
        return bos.toByteArray();
    }


    /**
     * @param src
     * @param dist
     */
    public static void copyFile(File src, final File dist) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dist);

        byte[] buff = new byte[2048];
        for (int read = in.read(buff, 0, buff.length); read != -1; read = in.read(buff, 0, buff.length)) {
            out.write(buff, 0, read);
        }

        out.close();
        in.close();
    }


}
