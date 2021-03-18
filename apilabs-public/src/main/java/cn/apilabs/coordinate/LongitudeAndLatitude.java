package cn.apilabs.coordinate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 经纬度相关
 *
 * @author zhangkai
 * @version 1.0
 * @date 2021/3/17
 * @className LongitudeAndLatitude
 */
public class LongitudeAndLatitude {


    /**
     * 度分秒转小数 （109.3°19.3'23.4'' 转小数）
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return double[longitude, latitude]
     */
    public static double[] toDecimal(final String longitude, final String latitude) {
        return new double[]{toDecimal(longitude), toDecimal(latitude)};
    }


    // 109.3°19.3'23.4'' 转小数
    private static double toDecimal(final String src) {

        if (Objects.isNull(src)) {
            return 0d;
        }

        Pattern pattern = Pattern.compile("([1-9]+\\d*(\\.\\d+)?)");
        final Matcher matcher = pattern.matcher(src);
        BigDecimal[] temp = new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};

        for (int i = 0; matcher.find(); i++) {
            if (i > 2) {
                break;
            }
            temp[i] = BigDecimal.valueOf(Double.parseDouble(matcher.group()));
        }

        final BigDecimal add = temp[0]
                .add(temp[1].divide(new BigDecimal("60"), 9, BigDecimal.ROUND_HALF_UP))
                .add(temp[2].divide(new BigDecimal("3600"), 9, BigDecimal.ROUND_HALF_UP));

        return add.doubleValue();
    }
}
