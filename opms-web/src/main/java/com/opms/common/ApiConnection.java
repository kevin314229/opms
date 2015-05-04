package com.opms.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.Validate;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiConnection {

	private static Logger logger = LoggerFactory.getLogger(ApiConnection.class);
	public static String JSON = "application/json;charset=utf-8";

	public static JSONObject request(String address, String contentType, Map<String, Object> paraMap) throws Exception {
		Validate.notNull(contentType, "Content-type must not be null!");
		URL url;
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;
		OutputStream outputStream = null;
		try {
			url = new URL(address);
			urlConnection = (HttpURLConnection) url.openConnection();
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			urlConnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			urlConnection.setDoInput(true);
			// Post 请求不能使用缓存
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			// 设定传送的内容类型是可序列化的java对象
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			urlConnection.setRequestProperty("Content-type", contentType);
			// 设置连接主机超时
			urlConnection.setConnectTimeout(10000);// 10s
			// 从主机读取数据超时
			urlConnection.setReadTimeout(30000000);
			outputStream = urlConnection.getOutputStream();
			if (paraMap != null && !paraMap.isEmpty()) {
				outputStream.write(JSONObject.fromObject(paraMap).toString().getBytes());
			}
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == HttpServletResponse.SC_OK) {
				// 查看http头文件，在游戏中规定失败会把信息放在头里
				String errorMsg = urlConnection.getHeaderField("msg");
				if (errorMsg != null) {
					throw new RuntimeException("游戏服务器error:" + errorMsg);
				}
				inputStream = urlConnection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
				return JSONObject.fromObject(buffer.toString());
			} else {
				logger.error("address:" + address + ";responseCode:" + responseCode);
			}

		} catch (JSONException e) {
			throw new RuntimeException("网络连接失败,请检查目标机器"+address, e);
		} catch (java.net.ConnectException e) {
			throw new RuntimeException("网络连接失败,请检查目标机器"+address, e);
		} catch (java.net.SocketTimeoutException e) {
			throw new RuntimeException("网络连接失败,请检查目标机器"+address, e);
		} catch (Exception e) {
			throw new RuntimeException("网络连接失败,请检查目标机器"+address, e);
		} finally {
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return null;
	}
}
