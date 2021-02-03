package exchangeRates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class ExchangeRatesMain {
	public static void main(String[] args) {
		ExchangeRatesHttpRequest exchangeRatesHttpRequest = new ExchangeRatesHttpRequest();
		ArrayList<Currency> exchangeRates = exchangeRatesHttpRequest.getExchangeRatesData();
		//Rates rates = new Rates();
		Scanner in = new Scanner(System.in);
		String key = "";
		while(key != "3") {
			System.out.println("1. Aktualny kurs walut");
			System.out.println("2. Przelicznik");
			System.out.println("3. Koniec");
			System.out.println("Opcja: ");
			key = in.next();
			switch(key) {
			case "1": {
					if(exchangeRates.size() > 0) {
						exchangeRates.forEach( currency -> System.out.println("WALUTA: " + currency.getCurrency() + ", KOD: " + currency.getCode() + ", KURS: " + currency.getMid()));
					} else {
						System.out.println("Brak danych");
					}
					break;
				}
			case "2": {
					Currency currency = new Currency();
					System.out.println("Podaj nazwe lub kod waluty: ");
					String currencyName = in.next();
					BigDecimal mid = currency.findCurrency(currencyName, exchangeRates);
					if(mid.compareTo(new BigDecimal("0")) > 0) {
						System.out.println("Podaj iloœæ: ");
						String quantity = in.next();
						if(NumberUtils.isCreatable(quantity) == true) {
							BigDecimal bigDecimalQuantity = new BigDecimal(quantity);
							System.out.println("Przeliczona wartoœæ: " + (mid.multiply(bigDecimalQuantity)));
							break;
						}
						break;
					}
					break;
				}
			case "3":
				in.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
}
