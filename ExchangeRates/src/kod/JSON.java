package exchangeRates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON implements JSONService{
	public JSON() {
		
	}
	
	public List<Currency> convertJsonToList(String s) {
		if(s.length() > 0) {
			if(isJSON(s) == true) {
				String jsonString = s.substring(1, s.length() - 1);
				JSONObject obj = new JSONObject(jsonString);
				JSONArray jsonArray = obj.getJSONArray("rates");
				List<Currency> exchangeRates = new ArrayList<>();
				String date = obj.getString("effectiveDate");
				for (Object object : jsonArray) {
					JSONObject jsonObject = new JSONObject(object.toString());
					exchangeRates.add(new Currency(jsonObject.getString("currency"), jsonObject.getString("code"), new BigDecimal(jsonObject.getBigDecimal("mid").toString()), date));
				}
				if(exchangeRates.size() > 0) {
					return exchangeRates;
				} else {
					return null;
				}
			} else {
				System.out.println("B³¹d odczytu danych.");
				return null;
			}
		}
		return null;
	}
	
	public List<Currency> convertJsonToList(String s, String date) {
		if(s.length() > 0) {
			if(isJSON(s) == true) {
				String jsonString = s.substring(1, s.length() - 1);
				JSONObject obj = new JSONObject(jsonString);
				JSONArray jsonArray = obj.getJSONArray("rates");
				List<Currency> exchangeRates = new ArrayList<>();
				for (Object object : jsonArray) {
					JSONObject jsonObject = new JSONObject(object.toString());
					exchangeRates.add(new Currency(jsonObject.getString("currency"), jsonObject.getString("code"), new BigDecimal(jsonObject.getBigDecimal("mid").toString()), date));
				}
				if(exchangeRates.size() > 0) {
					return exchangeRates;
				} else {
					return null;
				}
			} else {
				System.out.println("B³¹d odczytu danych.");
				return null;
			}
		}
		return null;
	}
	
	private boolean isJSON(String s) {
		try {
			new JSONObject(s);
		} catch (JSONException obj) {
			try {
				new JSONArray(s);
			} catch (JSONException arr) {
				return false;
			}
		}
		return true;
	}
}
