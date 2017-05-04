package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactory ef;
	
	private EntityManagerFactorySingleton(){}
	
	public static EntityManagerFactory getInst(){
		return ef = (ef == null) ? Persistence.createEntityManagerFactory("CLIENTE_ORACLE") : ef;
	}

}
