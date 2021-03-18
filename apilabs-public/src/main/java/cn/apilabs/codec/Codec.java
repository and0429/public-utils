package cn.apilabs.codec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.QCodec;
import org.apache.commons.codec.net.URLCodec;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/18
 * @className Codec
 */
public class Codec {


    public static void main(String[] args) throws IOException, EncoderException {

        // sha384
        DigestUtils digestUtils1 = new DigestUtils(DigestUtils.getSha384Digest());
        final String zhangsan1 = digestUtils1.digestAsHex("zhangsan");
        System.out.println(zhangsan1);

        // md5
        DigestUtils digestUtils = new DigestUtils("MD5");
        final String zhangsan = digestUtils.digestAsHex("zhangsan");
        System.out.println(zhangsan);

        //base64
        Base64 base64 = new Base64();
        final InputStream inputStream = new FileInputStream("ka.jpg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int rd = -1;
        byte[] buff = new byte[2048];
        while ((rd = inputStream.read(buff, 0, buff.length)) != -1) {
            outputStream.write(buff, 0, rd);
        }
        final String base64Str = base64.encodeAsString(outputStream.toByteArray());
        System.out.println(base64Str);


        //QCodec
        QCodec codec = new QCodec(StandardCharsets.UTF_8);
        final String lidehua = codec.encode("刘德华");
        System.out.println(lidehua);

        //URLCodec
        URLCodec codec1 = new URLCodec(StandardCharsets.UTF_8.toString());
        final String lidehua1 = codec1.encode("刘德华");
        System.out.println(lidehua1);

    }


}
