package exchangeRates;

import java.math.BigDecimal;
import java.util.List;

public class Currency {
	private String currency;
	private String code;
	private BigDecimal mid;
	private String date;
	
	public Currency() {
		
	}
	
	public Currency(String currency, String code, BigDecimal mid, String date) {
		this.currency = currency;
		this.code = code;
		this.mid = mid;
		this.date = date;
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
	
	public String getDate() {
		return this.date;
	}
	
	public BigDecimal getValue(List<Currency> exchangeRates, String code, BigDecimal quantity) {
		for (Currency currency : exchangeRates) {
			if(currency.getCurrency().toLowerCase().equals(code.toLowerCase()) || currency.getCode().toLowerCase().equals(code.toLowerCase())) {
				if(quantity != null && quantity.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal multiplyValue = new BigDecimal(currency.getMid().multiply(quantity).toString());
					System.out.println("Wartoœæ po przeliczeniu(" + currency.getCurrency() + "(" + currency.getCode() + ") -> PLN): " + multiplyValue + ", data: " + currency.getDate());
					return multiplyValue;
				} else {
					return null;
				}
			}
		}
		return null;		
	}
}
