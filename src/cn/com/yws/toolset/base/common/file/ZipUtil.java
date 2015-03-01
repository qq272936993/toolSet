package cn.com.yws.toolset.base.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	/**
	 * Creates the new file.
	 * 
	 * @param fFile
	 *            the f file
	 * @throws Exception
	 *             the exception
	 */
	private static void createNewFile(File fFile) throws Exception {
		if (fFile.isDirectory()) {
			createNewFolder(fFile);
		} else {
			File fParent = fFile.getParentFile();
			createNewFolder(fParent);
			fFile.createNewFile();
		}
	}

	/**
	 * Creates the new folder.
	 * 
	 * @param fObj
	 *            the f obj
	 * @throws Exception
	 *             the exception
	 */
	private static void createNewFolder(File fObj) throws Exception {
		Stack<File> fList = new Stack<File>();
		if (fObj.exists()) {
			return;
		}
		searchNewFolder(fObj, fList);
		while (fList.size() > 0) {
			File fTemp = fList.pop();
			fTemp.mkdir();
		}
	}

	/**
	 * Search new folder.
	 * 
	 * @param fObj
	 *            the f obj
	 * @param allFolder
	 *            the all folder
	 * @throws Exception
	 *             the exception
	 */
	private static void searchNewFolder(File fObj, Stack<File> allFolder)
			throws Exception {
		if (fObj.exists()) {
			return;
		} else {
			allFolder.push(fObj);
			File fParent = fObj.getParentFile();
			searchNewFolder(fParent, allFolder);
		}
	}

	/**
	 * Unzip.
	 * 
	 * @param zipFileName
	 *            the zip file name
	 * @param outputDirectory
	 *            the output directory
	 * @throws Exception
	 *             the exception
	 */
	public static void unzip(String zipFileName, String outputDirectory)
			throws Exception {
		ZipInputStream in = null;
		FileOutputStream out = null;
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(zipFileName);
			in = new ZipInputStream(fileIn);
			ZipEntry z;
			while ((z = in.getNextEntry()) != null) {
				if (z.isDirectory()) {
					String name = z.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();

				} else {
					File f = new File(outputDirectory + File.separator
							+ z.getName());
					// f.createNewFile();
					createNewFile(f);
					out = new FileOutputStream(f);
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
				}
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			in.close();
		}
	}

	/**
	 * Zip.
	 * 
	 * @param zipFileName
	 *            the zip file name
	 * @param inputFile
	 *            the input file
	 * @throws Exception
	 *             the exception
	 */
	public static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFileName));
			zip(out, inputFile, "");
		} catch (Exception ex) {
			throw ex;
		} finally {
			out.close();
		}
	}

	/**
	 * Zip.
	 * 
	 * @param zipFileName
	 *            the zip file name
	 * @param inputFile
	 *            the input file
	 * @throws Exception
	 *             the exception
	 */
	public static void zip(String zipFileName, String inputFile) throws Exception {
		zip(zipFileName, new File(inputFile));
	}

	/**
	 * Zip.
	 * 
	 * @param out
	 *            the out
	 * @param f
	 *            the f
	 * @param base
	 *            the base
	 * @throws Exception
	 *             the exception
	 */
	public static void zip(ZipOutputStream out, File f, String base) throws Exception {
		FileInputStream in = null;
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				File fff = fl[i];
				zip(out, fff, base + fff.getName());
			}
		} else {
			// if (base == null || base != null && base.equals("")) {
			if ((base == null) || "".equals(base)) {
				base = f.getName();
			}
		}
		out.putNextEntry(new ZipEntry(base));

		try {
			in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		} catch (FileNotFoundException exTemp) {
			throw exTemp;
		} finally {
			in.close();
		}
	}

	public static void main(String args[]) throws Exception {
//		zip("D:\\TDDownload\\2013-01-23.zip" , "D:\\TDDownload\\2013-01-23.classify");
//		unzip(
//				"D:\\tender1.zip",
//				"D:\\");
	}

}
