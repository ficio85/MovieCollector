package com.dto;

public enum FasciaOrariaDTO {

	ORA("ORA","ORA"),
	POMERIGGIO("POME","POMERIGGIO"),
	SERALE("SER","SERALE");
	
	
	public final String codFascia;
	public final String desFascia;
	
	FasciaOrariaDTO(String cod,String des)
	{
		codFascia=cod;
		desFascia=des;
	}
	
	/**
	 * @return the codFascia
	 */
	public String getCodFascia() {
		return codFascia;
	}




	/**
	 * @return the desFascia
	 */
	public String getDesFascia() {
		return desFascia;
	}




	public static FasciaOrariaDTO parse(String codice){
		FasciaOrariaDTO result = null;
		
		for(FasciaOrariaDTO tipo : values()){
			if(tipo.equals(codice)){
				result = tipo;
				break;
			}
		}
		return result;
	}
	
	public static FasciaOrariaDTO[] getValues(){
		return values();
	}
}
