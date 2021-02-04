package exchangeRates;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpException;

public class SalesDocumentService {
	private static void insert() throws IOException, HttpException {
		ExchangeRates exchangeRates = new ExchangeRates();
		BigDecimal exchangeValue = exchangeRates.getValue("2021-02-05", new BigDecimal("100"), "USD");
		System.out.println(exchangeValue);
		//exchangeRates.printRates();
	}
	
	public static void main(String[] args) throws IOException, HttpException {
		insert();
	}
}
