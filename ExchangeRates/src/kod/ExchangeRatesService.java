package exchangeRates;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpException;

public interface ExchangeRatesService {
	public void printRates() throws IOException, HttpException;
	public void printRates(String date) throws IOException, HttpException;
	public BigDecimal getValue(BigDecimal quantity, String text) throws IOException, HttpException;
	public BigDecimal getValue(String date, BigDecimal quantity, String text) throws IOException, HttpException;
}
