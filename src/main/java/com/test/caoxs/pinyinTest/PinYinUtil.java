package com.test.caoxs.pinyinTest;

import java.util.ArrayList;
import java.util.HashSet;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转换汉语拼音工具类 支持多音字、保留其他字符
 * 
 * @author 曹雪松
 *
 */
public class PinYinUtil {
	public enum TypeEnum {
		py, piny, pinyin;
	}
	/**
	 * 按照传入的格式，获取传入字符串的所有可能性。
	 * @param chineseStr
	 * @param type
	 * @return
	 */
	public static HashSet<String> allPossiblePys(String chineseStr, TypeEnum type) {
		HashSet<String> allPossiblePys = new HashSet<String>();
		allPossiblePys.add("");
		if(chineseStr == null || "".equals(chineseStr)){
			return allPossiblePys;
		}
		ArrayList<String[]> list = getStringPys(chineseStr, type);
		HashSet<String> tmp = new HashSet<String>();
		for (String[] strs : list) {
			HashSet<String> tmpSet = new HashSet<String>();
			for (String s : strs) {
				for (String nowResult : allPossiblePys) {
					nowResult = nowResult + s;
					tmpSet.add(nowResult);
				}
			}
			allPossiblePys = tmpSet;
		}
		return allPossiblePys;
	}

	private static ArrayList<String[]> getStringPys(String chineseStr, TypeEnum type) {
		char[] chars = chineseStr.toCharArray();
		ArrayList<String[]> pinyinList = new ArrayList<String[]>(chars.length);
		for (int i = 0; i < chars.length; i++) {
			String[] strs = null;
			char c = chars[i];
			strs = getCharPYs(c, i, type);
			pinyinList.add(strs);
		}
		return pinyinList;
	}

	private static String[] getCharPYs(char c, int index, TypeEnum type) {
		String[] strs = null;
		switch (type) {
		case py:
			strs = getPy(c);
			break;
		case piny:
			strs = index == 0 ? getPinyin(c) : getPy(c);
			break;
		case pinyin:
			strs = getPinyin(c);
			break;
		default:
			strs = getPinyin(c);
			break;
		}

		return strs;
	}
	
	public static String[] getPy(char chineseChar) {
		String[] strs = turnProcess(chineseChar);
		for (int i = 0; i < strs.length; i++) {
			strs[i] = strs[i].substring(0, 1);
		}
		return strs;
	}

	public static String[] getPinyin(char chineseChar) {
		String[] strs = turnProcess(chineseChar);
		return strs;

	}

	private static String[] turnProcess(char chineseChar) {
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] strs = null;
		try {
			strs = PinyinHelper.toHanyuPinyinStringArray(chineseChar, defaultFormat);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		//对不可以进行拼音转换的字符串进行，保留原字符的处理
		if (strs == null || strs.length == 0) {
			strs = new String[1];
			strs[0] = chineseChar + "";
		}
		return strs;
	}
}
