package exchangeRates;

import java.util.List;

public interface JSONService {
	public List<Currency> convertJsonToList(String s);
	public List<Currency> convertJsonToList(String s, String date);
}
