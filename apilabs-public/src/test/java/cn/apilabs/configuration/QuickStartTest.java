package cn.apilabs.configuration;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class QuickStartTest {

    @Test
    public void readProperties() throws ConfigurationException {
        QuickStart.readProperties();
    }

    @Test
    public void readXml() throws ConfigurationException {
        QuickStart.readXml();
    }


    @Test
    public void wirteProperties() throws ConfigurationException {
        QuickStart.wirteProperties();
    }

    @Test
    public void wirteXml() throws ConfigurationException {
        QuickStart.wirteXml();
    }
}