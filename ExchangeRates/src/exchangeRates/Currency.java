package exchangeRates;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Currency {
	private String currency;
	private String code;
	private BigDecimal mid;
	
	public Currency() {
		
	}
	
	public Currency(String currency, String code, BigDecimal mid) {
		this.currency = currency;
		this.code = code;
		this.mid = mid;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public BigDecimal getMid() {
		return this.mid;
	}
	
	public BigDecimal findCurrency(String text, ArrayList<Currency> exchangeRates) {
		BigDecimal mid_temp = new BigDecimal("0");
		if(exchangeRates.size() > 0 && text.length() > 0) {
			for (Currency currency : exchangeRates) {
				if(currency.getCurrency().toLowerCase().equals(text.toLowerCase()) || currency.getCode().toLowerCase().equals(text.toLowerCase())) {
					mid_temp = currency.getMid();
					break;
				}
			}
		}
		return mid_temp;
	}
}
