package cn.apilabs.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Objects;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/15
 * @className ZipAndUnZip
 */
public class ZipAndUnZip {


    /**
     * 解压缩文件
     *
     * @param zipFile 待解压缩的文件
     * @param destDir 解压到此目录下面。目录不存在时候自动创建
     */
    public static void unzip(final String zipFile, final String destDir) throws IOException {
        final File[] files = checkParam(zipFile, destDir);
        final File zip = files[0];
        final File dir = files[1];

        try (final ZipArchiveInputStream ai = new ZipArchiveInputStream(new FileInputStream(zip), "utf-8")) {
            for (ArchiveEntry entry = ai.getNextEntry(); entry != null; entry = ai.getNextEntry()) {
                final String name = entry.getName();
                final File file = new File(dir, name);
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    IOUtils.copy(ai, new FileOutputStream(file));
                }
            }
        }


    }

    //
    private static File[] checkParam(String zipFile, String destDir) {
        final File file = new File(zipFile);
        final File dest = new File(destDir);

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("待解压的文件不存在或不是一个文件");
        }

        if (!dest.exists()) {
            dest.mkdirs();
        } else {
            if (!dest.isDirectory()) {
                throw new IllegalArgumentException("目标参数必须是一个文件夹");
            }
        }

        return new File[]{file, dest};
    }


    /**
     * 归档
     *
     * @param src  待归档的文件或者文件夹
     * @param dest 归档后的输出文件，如果不是.zip结尾，会在后面添加.zip
     */
    public static void zip(final String src, final String dest) throws IOException {
        File srcFile = new File(src);
        final String canonicalPath = srcFile.getCanonicalPath();

        String d = dest;
        if (!dest.toUpperCase().endsWith(".ZIP")) {
            d = d + ".zip";
        }

        final File destFile = new File(d);
        final String destFileCanonicalPath = destFile.getCanonicalPath();
        final ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(destFile);

        archivalZip(srcFile, zipArchiveOutputStream, canonicalPath, destFileCanonicalPath);
        zipArchiveOutputStream.finish();
    }

    //
    private static void archivalZip(final File file, final ZipArchiveOutputStream zipOutputStream,
                                    final String srcCanonicalPath, final String destCanonicalPath) throws IOException {

        if (file.getCanonicalPath().equals(destCanonicalPath))
            return;

        final ArchiveEntry archiveEntry = zipOutputStream.createArchiveEntry(file, getEntryName(file, srcCanonicalPath));
        zipOutputStream.putArchiveEntry(archiveEntry);

        if (file.isFile()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                IOUtils.copy(inputStream, zipOutputStream);
            }
            zipOutputStream.closeArchiveEntry();
        } else {
            zipOutputStream.closeArchiveEntry();
            final File[] files = file.listFiles();
            if (Objects.isNull(files))
                return;
            for (File subFile : files) {
                archivalZip(subFile, zipOutputStream, srcCanonicalPath, destCanonicalPath);
            }
        }
    }

    //
    private static String getEntryName(File file, final String canonicalPath) throws IOException {
        final String canonicalPath1 = file.getCanonicalPath();
        final String s = canonicalPath1.replace(canonicalPath, "")
                .replace("\\", "/");
        return s.startsWith("/") ? s.substring(1) : s;
    }

}
