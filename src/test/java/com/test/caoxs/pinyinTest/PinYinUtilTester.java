package com.test.caoxs.pinyinTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import com.test.caoxs.pinyinTest.PinYinUtil.TypeEnum;

public class PinYinUtilTester {
	@Test
	public void testChinesePunctuation() {
		String[] ss = PinYinUtil.getPy('！');
		for (String s : ss) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------");
	}

	@Test
	public void testGetCharPy() {
		String[] ss = PinYinUtil.getPy('都');
		for (String s : ss) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------");
	}

	@Test
	public void testGetCharPinyin() {
		String[] ss = PinYinUtil.getPinyin('都');
		for (String s : ss) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------");
	}

	@Test
	public void testGetStrPy() {
		HashSet<String> pys = PinYinUtil.allPossiblePys("都在中华人民共和国", TypeEnum.py);
		int i = 1;
		for (String py : pys) {
			System.out.println("py格式的第" + i + "条" + py);
			i++;
		}
		System.out.println("--------------------------------------");

	}

	@Test
	public void testGetStrPiny() {
		HashSet<String> pinys = PinYinUtil.allPossiblePys("都在中华人民共和国", TypeEnum.piny);
		int i = 1;
		for (String piny : pinys) {
			System.out.println("piny格式的第" + i + "条" + piny);
			i++;
		}
		System.out.println("--------------------------------------");

	}

	@Test
	public void testGetStrPiny2() {
		HashSet<String> pinys = PinYinUtil.allPossiblePys("!都在中华人民共和国", TypeEnum.piny);
		int i = 1;
		for (String piny : pinys) {
			System.out.println("piny格式的第" + i + "条" + piny);
			i++;
		}
		System.out.println("--------------------------------------");

	}

	@Test
	public void testGetStrPiny3() {
		HashSet<String> pinys = PinYinUtil.allPossiblePys("！<>《》_102都在中华人民共和国", TypeEnum.piny);
		int i = 1;
		for (String piny : pinys) {
			System.out.println("piny格式的第" + i + "条" + piny);
			i++;
		}
		System.out.println("--------------------------------------");

	}

	@Test
	public void testGetStrPinyin() {
		HashSet<String> pinyins = PinYinUtil.allPossiblePys("都在中华人民共和国", TypeEnum.pinyin);
		int i = 1;
		for (String pinyin : pinyins) {
			System.out.println("pinyin格式的第" + i + "条" + pinyin);
			i++;
		}
		System.out.println("--------------------------------------");

	}
	
	@Test
	public void testGetNullStrPinyin() {
		HashSet<String> pinyins = PinYinUtil.allPossiblePys("", TypeEnum.pinyin);
		int i = 1;
		for (String pinyin : pinyins) {
			System.out.println("pinyin格式的第" + i + "条" + pinyin);
			i++;
		}
		System.out.println("--------------------------------------");
	}
	@Test
	public void testGetDataPinyin(){
		System.out.println("testGetDataPinyin--------------------------------------begin");
		Data d= new Data();
		ArrayList<String> dList = d.strs;
		HashMap<String,HashSet<String>> pyMap = new HashMap<String, HashSet<String>>();
		HashMap<String,HashSet<String>> pinyMap = new HashMap<String, HashSet<String>>();
		HashMap<String,HashSet<String>> pinyinMap = new HashMap<String, HashSet<String>>();
		for(String s : dList){
			HashSet<String> pys = PinYinUtil.allPossiblePys(s, TypeEnum.py);
			HashSet<String> pinys = PinYinUtil.allPossiblePys(s, TypeEnum.piny);
			HashSet<String> pinyins = PinYinUtil.allPossiblePys(s, TypeEnum.pinyin);
			pyMap.put(s, pys);
			pinyMap.put(s, pinys);
			pinyinMap.put(s, pinyins);
		}
		System.out.println("----------->py");
		for(String s : dList){
			for(String py : pyMap.get(s)){
				System.out.println(s + "-->" + py);
			}
		}
		System.out.println("----------->piny");
		for(String s : dList){
			for(String piny : pinyMap.get(s)){
				System.out.println(s + "-->" + piny);
			}
		}
		System.out.println("----------->pinyin");
		for(String s : dList){
			for(String pinyin : pinyinMap.get(s)){
				System.out.println(s + "-->" + pinyin);
			}
		}
		
		System.out.println("testGetDataPinyin--------------------------------------end");

	}

}
