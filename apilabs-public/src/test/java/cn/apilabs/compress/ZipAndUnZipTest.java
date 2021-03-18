package cn.apilabs.compress;

import org.apache.commons.compress.archivers.ArchiveException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/15
 * @className ZipAndUnZipTest
 */
public class ZipAndUnZipTest {


    @Test
    public void test() {
        Assert.assertEquals("zhangkai", "zhangkai");
    }

    @Test
    public void archival() throws IOException {

//        ZipAndUnZip.zip("C:\\Users\\zhangkai\\.config", "C:\\Users\\zhangkai\\ss.zip");

    }

    @Test
    public void unzip() throws IOException, ArchiveException {
//        ZipAndUnZip.unzip("C:\\Users\\zhangkai\\ss.zip", "C:\\Users\\zhangkai\\456");
    }
}