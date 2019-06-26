package com.sharebooks.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.http.enums.ContentType;
import com.sharebooks.http.enums.HttpMethod;
import com.sharebooks.util.HttpUtlity;

public class HttpClient {
	private static final Logger LOGGER = Logger.getLogger(HttpClient.class);

	private String url;
	private HttpMethod method;
	private Map<String, String> parameters;
	private String json;
	private ContentType contenType;
	private Map<String, String> headerMap;
	// private String data;
	private boolean isHttps;
	private int connTimeout;
	private int readTimeout;

	public HttpClient() {

	}

	public HttpClient(HttpClientBuilder b) {
		url = b.url;
		method = b.method;
		parameters = b.parameters;
		json = b.json;
		contenType = b.contenType;
		headerMap = b.headerMap;
		isHttps = b.isHttps;
		connTimeout = b.connTimeout;
		readTimeout = b.readTimeout;
	}

	public static void main(String[] args) {

	}

	public static class HttpClientBuilder {
		private String url = null;
		private HttpMethod method = HttpMethod.GET;
		private Map<String, String> parameters = new HashMap<String, String>();
		private String json = null;
		private ContentType contenType = ContentType.APPLICATION_FORM;
		private Map<String, String> headerMap = new HashMap<String, String>();
		private boolean isHttps = false;
		private int connTimeout = 5000;
		private int readTimeout = 5000;

		public HttpClient build() {
			return new HttpClient(this);
		}

		public HttpClientBuilder url(String url) {
			this.url = url;
			return this;
		}

		public HttpClientBuilder method(HttpMethod method) {
			this.method = method;
			return this;
		}

		public HttpClientBuilder parameters(Map<String, String> parameters) {
			this.parameters = parameters;
			return this;
		}

		public HttpClientBuilder json(String json) {
			this.json = json;
			return this;
		}

		public HttpClientBuilder contentType(ContentType contenType) {
			this.contenType = contenType;
			return this;
		}

		public HttpClientBuilder headerMap(Map<String, String> headerMap) {
			this.headerMap = headerMap;
			return this;
		}

		public HttpClientBuilder isHttps(boolean isHttps) {
			this.isHttps = isHttps;
			return this;
		}

		public HttpClientBuilder connTimeout(int connTimeout) {
			this.connTimeout = connTimeout;
			return this;
		}

		public HttpClientBuilder readTimeout(int readTimeout) {
			this.readTimeout = readTimeout;
			return this;
		}
	}

	public HttpResponse makeRequest() throws Exception {
		URL url = new URL(this.url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(method.desc());

		setRequestHeaders(con);
		setTimeouts(con);

		if (method == HttpMethod.POST) {
			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			String data = getData();
			out.writeBytes(data);
			out.flush();
			out.close();
		}
		// will execute the request
		// con.connect();
		int status = con.getResponseCode();
		String resData = null;
		String resMessage = con.getResponseMessage();
		if (status == 200 || status == 201) {
			resData = readResponse(con);
			LOGGER.info("HTTP Response => " + resData);
		}

		return new HttpResponse(status, resMessage, resData);
	}

	public void setRequestHeaders(HttpURLConnection con) {
		for (String header : headerMap.keySet()) {
			con.setRequestProperty(header, headerMap.get(header));
		}
	}

	public void setTimeouts(HttpURLConnection con) {
		con.setConnectTimeout(connTimeout);
		con.setReadTimeout(readTimeout);
	}

	public String readResponse(HttpURLConnection con) throws Exception {
		try {
			// int status = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			return content.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			con.disconnect();
		}
	}

	private String getData() throws UnsupportedEncodingException {
		String data = null;
		if (contenType.equals(ContentType.APPLICATION_FORM)) {
			data = HttpUtlity.getParamsString(parameters);
		} else if (contenType.equals(ContentType.JSON)) {
			data = this.json;
		}
		return data;
	}

//	public void readReqHeaders() {
//		String contentType = con.getHeaderField("Content-Type");
//	}
//
//	public void handlingCookies() {
//		String cookiesHeader = con.getHeaderField("Set-Cookie");
//		List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
//	}
//
//	public void redirect() {
//		con.setInstanceFollowRedirects(false);
//		HttpUrlConnection.setFollowRedirects(false);
//		if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
//			String location = con.getHeaderField("Location");
//			URL newUrl = new URL(location);
//			con = (HttpURLConnection) newUrl.openConnection();
//		}
//	}
}
