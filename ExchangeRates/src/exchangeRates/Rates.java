package exchangeRates;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Rates {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExchangeRates");
	
	public void addCurrency(String currency, String code, BigDecimal mid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			CurrencyDataBase currencyDataBase = new CurrencyDataBase();
			currencyDataBase.setCurrency(currency);
			currencyDataBase.setCode(code);
			currencyDataBase.setMid(mid);
			entityManager.persist(currencyDataBase);
			entityTransaction.commit();
		} catch (Exception e){
			if(entityTransaction != null) {
				entityTransaction.rollback();
			}
			
			e.printStackTrace();
		} finally {
			entityManager.clear();
		}
	}
}
