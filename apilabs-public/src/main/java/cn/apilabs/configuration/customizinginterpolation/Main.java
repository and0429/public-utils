package cn.apilabs.configuration.customizinginterpolation;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/16
 * @className Main
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws ConfigurationException {

        // 方法一
        Map<String, Lookup> lookups = new HashMap<>(ConfigurationInterpolator.getDefaultPrefixLookups());
        lookups.put("echo", new EchoLookUp());

        Parameters params = new Parameters();

        final PropertiesBuilderParameters propertiesBuilderParameters = params
                .properties()
                .setFileName("test.properties")
                .setPrefixLookups(lookups);

//        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
//                .configure(propertiesBuilderParameters);

        Configurations configurations = new Configurations();
        final FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configurations.propertiesBuilder("test.properties");
        builder.configure(propertiesBuilderParameters);

        PropertiesConfiguration config = builder.getConfiguration();


        final String echo = config.getString("echo");
        System.out.println(echo);




        // 方法二
//        Configurations configurations = new Configurations();
//        final FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configurations.propertiesBuilder("test.properties");
//
//        final PropertiesConfiguration configuration = builder.getConfiguration();
//        configuration.getInterpolator().registerLookup("echo", new EchoLookUp());
//
//        final String echo = configuration.getString("echo");
//        System.out.println(echo);

    }
}
