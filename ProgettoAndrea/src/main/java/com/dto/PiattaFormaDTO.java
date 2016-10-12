package com.dto;

public enum PiattaFormaDTO {

	SKY("SKY","SKY"),
	DIGITTERR("DIGITTER","DIGITALE TERRESTRE"),
	TUTTO("TUTTO","TUTTO");
	
	
	public final String codPiattaforma;
	public final String desPiattaforma;
	
	PiattaFormaDTO(String cod,String des)
	{
		codPiattaforma=cod;
		desPiattaforma=des;
	}
	
	/**
	 * @return the codPiattaforma
	 */
	public String getCodPiattaforma() {
		return codPiattaforma;
	}

	/**
	 * @return the desPiattaforma
	 */
	public String getDesPiattaforma() {
		return desPiattaforma;
	}

	public static PiattaFormaDTO parse(String codice){
		PiattaFormaDTO result = null;
		
		for(PiattaFormaDTO tipo : values()){
			if(tipo.getCodPiattaforma().equals(codice)){
				result = tipo;
				break;
			}
		}
		return result;
	}
	
	public static PiattaFormaDTO[] getValues(){
		return values();
	}
}
