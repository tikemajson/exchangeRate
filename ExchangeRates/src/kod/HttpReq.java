package exchangeRates;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpReq {
	private final CloseableHttpClient closableHttpClient;
	
	public HttpReq() {
		closableHttpClient = HttpClients.createDefault();
	}
	
	public String getData(String URL) throws IOException,HttpException {
		if(URL.length() > 0) {
			HttpGet httpGet = new HttpGet(URL);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
			try {
				CloseableHttpResponse closableHttpResponse = closableHttpClient.execute(httpGet);
				HttpEntity httpEntity = closableHttpResponse.getEntity();
				if(closableHttpResponse.getStatusLine().getStatusCode() == 200) {
					if(httpEntity != null) {
						return EntityUtils.toString(httpEntity);
					} else {
						return "Nie mo¿na po³¹czyæ siê z podanym adresem!";
					}
				} else {
					//throw new HttpResponseException(closableHttpResponse.getStatusLine().getStatusCode(), closableHttpResponse.getStatusLine().getReasonPhrase());
					System.out.println("Nie mo¿na po³¹czyæ siê z podanym adresem! Kod b³êdu: " + closableHttpResponse.getStatusLine().getStatusCode() + " " + closableHttpResponse.getStatusLine().getReasonPhrase());
					return null;
				}
			} finally {
				closableHttpClient.close();
			}
		} else {
			return "Nie mo¿na po³¹czyæ siê z podanym adresem!";
		}
	}
}
