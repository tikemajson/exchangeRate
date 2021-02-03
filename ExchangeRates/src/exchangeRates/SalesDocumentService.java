package exchangeRates;

import java.util.ArrayList;

public class SalesDocumentService {
	private static void insert() {
		ExchangeRatesHttpRequest request = new ExchangeRatesHttpRequest();
		ArrayList<Currency> currency = request.getExchangeRatesData();
		currency.forEach(cur -> System.out.println("WALUTA: " + cur.getCurrency() + ", KOD: " + cur.getCode() + ", KURS: " + cur.getMid()));
	}
	
	public static void main(String[] args) {
		insert();
	}
}
