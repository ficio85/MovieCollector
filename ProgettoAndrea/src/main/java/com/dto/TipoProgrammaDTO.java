package com.dto;

public enum TipoProgrammaDTO {

	TUTTI("TUTTI","TUTTI"),
	FILM("FILM","FILM"),
	SERIE("SERIE","SERIE TV"),
	FILMSERIE("FILMSERIE","FILM E SERIE TV"),
	
	;
	
	
	
	public final String codProgramma;
	public final String desProgramma;
	
	TipoProgrammaDTO(String cod,String des)
	{
		codProgramma=cod;
		desProgramma=des;
	}
		


	/**
	 * @return the codProgramma
	 */
	public String getCodProgramma() {
		return codProgramma;
	}



	/**
	 * @return the desProgramma
	 */
	public String getDesProgramma() {
		return desProgramma;
	}



	public static TipoProgrammaDTO parse(String codice){
		TipoProgrammaDTO result = null;
		
		for(TipoProgrammaDTO tipo : values()){
			if(tipo.equals(codice)){
				result = tipo;
				break;
			}
		}
		return result;
	}
	
	public static TipoProgrammaDTO[] getValues(){
		return values();
	}
}
