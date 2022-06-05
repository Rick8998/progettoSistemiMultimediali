package main;

/**
 * 
 *	definisce come devono essere implementate le operazioni che permettono di definire una trasformata
 */
public interface Transform {

	/**
	 * setta e definisce i dati di origine:
	 * 
	 * @param src di tipo Object
	 * 
	 */
	void setSourceData(Object src);
	
	/**
	 * Esegue il calcolo
	 */
	void calculate();
	
	/**
	 * Restituisce i risultati
	 * @return Object
	 */
	Object getResult();
	
}
