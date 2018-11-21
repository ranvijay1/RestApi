package com.epam.jacsonutils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.empam.pojomapper.UserDetails;



public class JacsonUtils {
	
	public static ObjectMapper mapper;
	
	static{
		mapper = new ObjectMapper();
	}
	
	public static <T> T[] convertJSONArrayToJavaObject(File file, Class<T[]> class1) {
		T[] javaObject = null;
		try {
			javaObject =  mapper.readValue(file, class1);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return javaObject;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convertJsonToJavaObject(String string, Class<T> class1) {
		T javaObject = null;
		
		try {
			javaObject =  mapper.readValue(string, class1);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return javaObject;
	}
	
	public static <T> T convertPojoToJson(Object obj) {
		T jsonAsString = null;
		
		try {
			jsonAsString =  (T) mapper.writeValueAsString(obj);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonAsString;
	}

}

