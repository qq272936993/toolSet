package cn.com.yws.toolset.base.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * 数据压缩与解压缩
 * */
public class CompressHelper {

	/**
	 * 压缩数据
	 * @param val
	 * */
	public static final byte [] compress(byte[] val) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream( val.length );
		GZIPOutputStream gos = new GZIPOutputStream( bos );
		gos.write( val, 0, val.length );
		gos.finish();
		gos.close();
		
		// store it and set compression flag
		return  bos.toByteArray();		
	}
	
	/**
	 * 解压数据
	 * @param buf
	 * */
	public static final byte [] unCompress(byte[] buf) throws IOException {
		GZIPInputStream gzi = new GZIPInputStream( new ByteArrayInputStream( buf ) );
		ByteArrayOutputStream bos = new ByteArrayOutputStream( buf.length );
		
		int count;
		byte[] tmp = new byte[2048];
		while ( (count = gzi.read(tmp)) != -1 ) {
			bos.write( tmp, 0, count );
		}

		// store uncompressed back to buffer		
		gzi.close();
		return bos.toByteArray();
	}
	
	
}
