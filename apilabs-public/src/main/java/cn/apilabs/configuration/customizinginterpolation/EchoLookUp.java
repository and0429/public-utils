package cn.apilabs.configuration.customizinginterpolation;

import org.apache.commons.configuration2.interpol.Lookup;

/**
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/16
 * @className EchoLookUp
 */
public class EchoLookUp implements Lookup {

    @Override
    public Object lookup(String variable) {
        return "ECHO-" + variable;
    }
}
