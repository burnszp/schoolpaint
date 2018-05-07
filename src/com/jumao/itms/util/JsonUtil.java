package com.jumao.itms.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.jumao.itms.data.ColorParam;
import com.jumao.itms.data.ImageParam;
import com.jumao.itms.data.LineParam;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static final ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		ImageParam image = new ImageParam();
		image.setHeight(500);
		image.setWidth(600);
		image.setColor(new ColorParam(0, 0, 0));

		List<LineParam> lines = new ArrayList<LineParam>();
		LineParam line = new LineParam();
		line.setDirection(2);
		line.setSize(20);

		lines.add(line);

		image.setLines(lines);

		System.out.println(getJsonString(image));
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(String json, Class<T> beanClass) {
		JavaType javaType = getCollectionType(ArrayList.class, beanClass);

		List<T> arr = null;
		try {
			arr = (List<T>) objectMapper.readValue(json, javaType);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return arr;
	}

	public static <T> T[] getArray(String json, Class<T[]> beanClass) {
		T[] arr = null;
		try {
			arr = (T[]) objectMapper.readValue(json, beanClass);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return arr;
	}

	public static <T> T getObject(String json, Class<T> beanClass) {
		T obj = null;
		try {
			obj = objectMapper.readValue(json, beanClass);
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return obj;
	}

	@SuppressWarnings("deprecation")
	public static String getJsonString(Object o) {
		JsonGenerator jsonGenerator = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					baos, JsonEncoding.UTF8);
			jsonGenerator.writeObject(o);
		} catch (IOException e) {
			return null;
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
			}
		}

		try {
			return baos.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
}
