package exchangeRates;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpException;

public class ExchangeRates implements ExchangeRatesService{
	private String URL= "http://api.nbp.pl/api/exchangerates/tables/A/";
	
	public void printRates() throws IOException, HttpException {
		HttpReq httpReq = new HttpReq();
		String jsonString = httpReq.getData(URL);
		if(jsonString != null) {
			JSON json = new JSON();
			List<Currency> exchangeRates = json.convertJsonToList(jsonString);
			if(exchangeRates != null) {
				exchangeRates.forEach(ex -> System.out.println("WALUTA: " + ex.getCurrency() + ", KOD: " + ex.getCode() + ", KURS: " + ex.getMid() + ", DATA: " + ex.getDate()));
			} else {
				System.out.println("B³¹d odczytu");
			}
		}
	}
	
	public void printRates(String date) throws IOException, HttpException {
		HttpReq httpReq = new HttpReq();
		if(date.length() < 9) {
			Date newDate = new Date();
			date = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
		}
		String jsonString = httpReq.getData(URL + date);
		if(jsonString != null) {
			JSON json = new JSON();
			List<Currency> exchangeRates = json.convertJsonToList(jsonString);
			if(exchangeRates != null) {
				exchangeRates.forEach(ex -> System.out.println("WALUTA: " + ex.getCurrency() + ", KOD: " + ex.getCode() + ", KURS: " + ex.getMid() + ", DATA: " + ex.getDate()));
			} else {
				System.out.println("B³¹d odczytu");
			}
		}
	}
	
	public BigDecimal getValue(BigDecimal quantity, String code) throws IOException, HttpException {
		HttpReq httpReq = new HttpReq();
		String jsonString = httpReq.getData(URL);
		if(jsonString != null) {
			JSON json = new JSON();
			List<Currency> exchangeRates = json.convertJsonToList(jsonString);
			if(exchangeRates != null) {
				Currency currency = new Currency();
				BigDecimal multiplyValue = currency.getValue(exchangeRates, code, quantity);
				if(multiplyValue != null) {
					return multiplyValue;
				} else {
					System.out.println("Nie znaleziono waluty o podanym kodzie lub podana wartoœæ jest nieprawid³owa!");
				}
			} else {
				System.out.println("B³¹d odczytu danych.");
				return null;
			}
		}
		return null;
	}
	
	public BigDecimal getValue(String date, BigDecimal quantity, String code) throws IOException, HttpException {
		HttpReq httpReq = new HttpReq();
		if(date.length() < 9) {
			Date newDate = new Date();
			date = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
		}
		String jsonString = httpReq.getData(URL + date);;
		if(jsonString != null) {
			JSON json = new JSON();
			List<Currency> exchangeRates = json.convertJsonToList(jsonString, date);
			if(exchangeRates != null) {
				Currency currency = new Currency();
				BigDecimal multiplyValue = currency.getValue(exchangeRates, code, quantity);
				if(multiplyValue != null) {
					return multiplyValue;
				} else {
					System.out.println("Nie znaleziono waluty o podanym kodzie lub podana wartoœæ jest nieprawid³owa!");
				}
			} else {
				System.out.println("B³¹d odczytu danych.");
				return null;
			}
		}
		return null;
	}
}