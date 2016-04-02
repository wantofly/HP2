package com.kiwi.stripes;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;


public class JsonTools {
	public static JsonTools instance = new JsonTools();
	public static ObjectMapper jsonmap = new ObjectMapper();
	private static JsonGenerator jsonGenerator =null;
	
	private JsonTools(){
	}
	public static String getRetJson(int bsuc, String msg){
		Map<String,Object> userData = new HashMap<String,Object>();
		userData.put("ret", bsuc);
		userData.put("msg", msg);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
//			jsonmap.writeValue(baos, userData);
			jsonGenerator = jsonmap.getJsonFactory().createJsonGenerator(baos, JsonEncoding.UTF8);
			jsonGenerator.setHighestNonEscapedChar(255);
			jsonGenerator.writeObject(userData);
			return baos.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static String getRetJson(int bsuc, String msg, Map data){
//		Map<String,Object> userData = new HashMap<String,Object>();
//		userData.put("jsonret", bsuc);
//		userData.put("jsonmsg", msg);
//		userData.put("jsondata", data);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
////			jsonmap.writeValue(baos, userData);
//			jsonGenerator = jsonmap.getJsonFactory().createJsonGenerator(baos, JsonEncoding.UTF8);
//			jsonGenerator.setHighestNonEscapedChar(255);
//			jsonGenerator.writeObject(userData);
//			return baos.toString();
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public static String getRetJson(int bsuc, String msg, List data){
//		Map<String,Object> userData = new HashMap<String,Object>();
//		userData.put("jsonret", bsuc);
//		userData.put("jsonmsg", msg);
//		userData.put("jsondata", data);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
////			jsonmap.writeValue(baos, userData);
//			jsonGenerator = jsonmap.getJsonFactory().createJsonGenerator(baos, JsonEncoding.UTF8);
//			jsonGenerator.setHighestNonEscapedChar(255);
//			jsonGenerator.writeObject(userData);
//			return baos.toString();
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	public static String getRetJson(int bsuc, String msg, Object data){
		Map<String,Object> userData = new HashMap<String,Object>();
		userData.put("jsonret", bsuc);
		userData.put("jsonmsg", msg);
		userData.put("jsondata", data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
//			jsonmap.writeValue(baos, userData);
			jsonGenerator = jsonmap.getJsonFactory().createJsonGenerator(baos, JsonEncoding.UTF8);
			jsonGenerator.setHighestNonEscapedChar(255);
			jsonGenerator.writeObject(userData);
			return baos.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getJSONString(Object aobj){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			jsonmap.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
//			jsonmap.writeValue(baos, aobj);
			jsonGenerator = jsonmap.getJsonFactory().createJsonGenerator(baos, JsonEncoding.UTF8);
			jsonGenerator.setHighestNonEscapedChar(255);
			jsonGenerator.writeObject(aobj);
			return baos.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static String getRetGoodsListJson(int bsuc, String msg, List<Goods> alist){
//		 Map<String,Object> userData = new HashMap<String,Object>();
//		 userData.put("ret", bsuc);
//		 userData.put("msg", msg);
//		 Map<String,Object> userData2 = null;
//		 ArrayList<Map<String,Object>> blist = new ArrayList<Map<String,Object>>();
//		 for(Goods agoods: alist){
//			 userData2 = new HashMap<String,Object>();
//			 userData2.put("id", agoods.getIntPk());
//			 userData2.put("goodscode", agoods.getGoodscode());
//			 userData2.put("price", agoods.getPrice());
//			 userData2.put("bothflag", agoods.getBothflag()?2:1);
//			 try {
//				 userData2.put("goodsname", java.net.URLEncoder.encode(agoods.getGoodsname(),"UTF-8"));
//			 } catch (UnsupportedEncodingException e) {
//				 userData2.put("goodsname", "");
//			 }
//			 blist.add(userData2);
//		 }
//		 userData.put("data", blist);
//		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		 alist.get(0).getIntPk();
//			try {
//				jsonmap.writeValue(baos, userData);
//				return baos.toString();
//			} catch (JsonGenerationException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//	}
//	public static void main2(String[] args) {
//		Store ast = new Store(); 
//		ast.setString("storename","alll");
//		System.out.println(getJSONString(ast));
//	}
//	public static void main(String [] args){
//		System.out.println("===================");
//		
//		Entity userData = new Entity();
//		Entity userData2 = null;
//		
//		ArrayList<Map<String,Object>> blist = new ArrayList<Map<String,Object>>();
//		for (int i = 0; i < 5; i++) {
//			 userData2 = new Entity();
//			 userData2.setInteger("id", i+1);
//			 userData2.setString("goodscode", "商品-"+i);
//			 try {
//				 userData2.setString("goodsname", java.net.URLEncoder.encode("品品","UTF-8"));
//			 } catch (UnsupportedEncodingException e) {
//				 userData2.setString("goodsname", "");
//			 }
//			 blist.add(userData2);
//		 }
//		userData.put("jsondata", blist);
//		userData.put("jsonret", 2);
//		userData.put("jsonmsg", "success");
//		userData.put("data", userData2);
//		System.out.println(getJSONString(userData));
////		Client auser = new Client();
////		auser.setString("clientname","asdf");
////		auser.setInteger("age",1);
////		Client buser = new Client();
////		buser.setString("clientname","ccc");
////		buser.setObject("xxx", auser);
////		System.out.println(getJSONString(auser));
////		System.out.println(getJSONString(buser));
////		System.out.println(getRetJson(2, "asdf"));
//	}
}
