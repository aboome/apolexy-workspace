package com.yh.apoplexy.common.constants;

/**
 * 字符串校验正则表达式常量
 * Created by wunder on 16/9/12 10:51.
 */
public class RegularConstants {

    public static final String PHONE_REGULAR = "^1\\d{10}$";

    public static final String VALIDATE_CODE_REGULAR = "^\\d{6}$";

    public static final String UUID_REGULAR = "^\\w{32}$";

    public static final String ID_REGULAR = "^\\d+$";

    public static final String DATE_REGULAR = "^\\d{14}$";

    public static final String REAL_NAME_REGULAR = "^.{0,16}$";

    public static final String DOUBLE_REGULAR = "^(\\+|\\-)?[0-9]+(.[0-9]{1,16})?$";

    public static final String TYPE_REGULAR = "^\\d{0,2}$";

    public static final String SHORT_TEXT_REGULAR = "^.{0,128}$";

    public static final String SMALL_TEXT_REGULAR = "^.{0,32}$";

}
