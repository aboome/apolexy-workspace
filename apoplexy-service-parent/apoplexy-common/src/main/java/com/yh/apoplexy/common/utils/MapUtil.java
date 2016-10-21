package com.yh.apoplexy.common.utils;

import java.math.BigDecimal;

/**
 * 地图工具类
 * Created by wunder on 16/9/5 16:26.
 */
public class MapUtil {

    private static double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param lon1 第一点经度
     * @param lat1 第一点纬度
     * @param lon2 第二点经度
     * @param lat2 第二点纬度
     * @return 返回距离 单位：米
     */
    public static double distanceBetween(double lon1, double lat1, double lon2, double lat2) {

        double radLat1 = rad(lat1);

        double radLat2 = rad(lat2);

        double diffLat = radLat1 - radLat2;

        double diffLon = rad(lon1) - rad(lon2);

        double sinDiffLat = Math.sin(diffLat / 2.0);
        double sinDiffLon = Math.sin(diffLon / 2.0);

        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(sinDiffLat * sinDiffLat + Math.cos(radLat1) * Math.cos(radLat2) * sinDiffLon * sinDiffLon));

        BigDecimal bigDecimal = new BigDecimal(distance);

        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
