/**
 * 
 */
package com.yh.apoplexy.common.constants;

/**
 * Mongo使用到的表名常量
 * 
 * @author CC
 *
 */
public class MongoDBTableConstants {

	/**
	 * 卒中助手图片数据表
	 */
	public static final String ZFZS_IMAGE = "zfzsImage";
	
	/**
	 * 卒中助手视频数据表
	 */
	public static final String ZFZS_VIDEO = "zfzsVideo";
	
	/**
	 * 卒中助手视频数据表
	 */
	public static final String ZFZS_PDF = "zfzsPdf";
	
	/**
	 * 中风助手图片表
	 */
	public class ZfzsImageTable {
		
		public static final String COLUMN_PIC_ID = "PIC_ID";
		
		public static final String COLUMN_FILENAME = "fileName";
		
		public static final String COLUMN_FILE = "file";
	}
	
	/**
	 * 中风助手视频表
	 */
	public class ZfzsVideoTable {
		
		public static final String COLUMN_PIC_ID = "PIC_ID";
		
		public static final String COLUMN_FILENAME = "fileName";
		
		public static final String COLUMN_FILE = "file";
	}
	
	/**
	 * 中风助手文件表
	 */
	public class ZfzsPdfTable {
		
		public static final String COLUMN_PIC_ID = "PIC_ID";
		
		public static final String COLUMN_FILENAME = "fileName";
		
		public static final String COLUMN_FILE = "file";
	}
}
