package exchangeRates;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.json.*;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;

public class ExchangeRatesHttpRequest implements JSONExchangeUtils{
	private final CloseableHttpClient closableHttpClient = HttpClients.createDefault();
	private final String URL = "http://api.nbp.pl/api/exchangerates/tables/A/";
	
	public ExchangeRatesHttpRequest() {
	}
	
	public ArrayList<Currency> getExchangeRatesData() {
		ArrayList<Currency> exchangeRates = new ArrayList<>();
		HttpGet getRequest = new HttpGet(URL);
		getRequest.setHeader("Accept", "application/json");
		getRequest.setHeader("Content-type", "application/json");
		try (CloseableHttpResponse closableHttpResponse = closableHttpClient.execute(getRequest)){
			HttpEntity httpEntity = closableHttpResponse.getEntity();
			if(httpEntity != null) {
				String httpResult = EntityUtils.toString(httpEntity);
				if(JSONExchangeUtils.isJSON(httpResult) == true) {
					httpResult = httpResult.substring(1, httpResult.length() - 1);
					JSONObject httpObject = new JSONObject(httpResult);
					JSONArray jsonArray = httpObject.getJSONArray("rates");
					for (Object object : jsonArray) {
						JSONObject jsonObject = new JSONObject(object.toString());
						exchangeRates.add(new Currency(jsonObject.getString("currency"), jsonObject.getString("code"), new BigDecimal(jsonObject.getBigDecimal("mid").toString())));
					}
				}
			}
			closableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeRates;
	}
}

