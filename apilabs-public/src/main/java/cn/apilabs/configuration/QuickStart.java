package cn.apilabs.configuration;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.List;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/16
 * @className QuickStart
 */
public class QuickStart {

    /**
     *
     */
    public static void wirteXml() throws ConfigurationException {
        final Configurations configurations = new Configurations();
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = configurations.xmlBuilder("test.xml");
        final XMLConfiguration configuration = builder.getConfiguration();
        configuration.addProperty("processing.paths.path", "lisi");
        configuration.setProperty("processing.paths.path(1)", "第二个被修改了");
        builder.save();
    }


    /**
     * @throws ConfigurationException
     */
    public static void wirteProperties() throws ConfigurationException {

        final Configurations configurations = new Configurations();
        final FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configurations.propertiesBuilder("wirte.properties");
        final PropertiesConfiguration configuration = builder.getConfiguration();
        configuration.addProperty("name", "zhangkai");
        configuration.addProperty("name.nam", "lisi");
        configuration.setProperty("age", 12);

        builder.save();
    }


    /**
     * 读取 properties
     */
    public static void readProperties() throws ConfigurationException {

        final Configurations configurations = new Configurations();
        final Configuration properties = configurations.properties("test.properties");

        final String host = properties.getString("database.host");
        final String user = properties.getString("database.user");
        final String password = properties.getString("database.password");
        final String echo = properties.getString("echo");

        final int port = properties.getInt("database.port");
        final long timeout = properties.getLong("database.timeout");
        System.out.println(host + "==" + user + "==" + password + "==" + port + "==" + timeout + "==" + echo);
    }

    /**
     *
     */
    public static void readXml() throws ConfigurationException {
        Configurations configurations = new Configurations();
        final XMLConfiguration xml = configurations.xml("test.xml");

        final String stage = xml.getString("processing[@stage]");
        final List<String> list = xml.getList(String.class, "processing.paths.path");

        final String path1 = xml.getString("processing.paths.path(0)");

        System.out.println(path1);
        System.out.println(stage);
        list.forEach(System.out::println);
    }

}
