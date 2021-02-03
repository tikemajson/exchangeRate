package exchangeRates;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchangerates")
public class CurrencyDataBase{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true)
	private int id;
	@Column(name = "currency", nullable = false)
	private String currency;
	@Column(name = "code", nullable = false)
	private String code;
	@Column(name = "mid", nullable =  false)
	private BigDecimal mid;
	
	public int getId() {
		return id;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public String getCode() {
		return code;
	}
	
	public BigDecimal getMid() {
		return mid;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}
}
