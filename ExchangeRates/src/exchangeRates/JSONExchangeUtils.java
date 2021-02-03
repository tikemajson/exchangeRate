package exchangeRates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface JSONExchangeUtils {
	public static boolean isJSON(String jsonData) {
		try {
			new JSONObject(jsonData);
		} catch (JSONException obj) {
			try {
				new JSONArray(jsonData);
			} catch (JSONException arr) {
				return false;
			}
		}
		return true;
	}
}
