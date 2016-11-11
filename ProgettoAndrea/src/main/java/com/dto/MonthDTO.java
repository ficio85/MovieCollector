package com.dto;

public enum MonthDTO {

	January(1,"January"),
	February(2,"February"),
	March(3,"March"),
	April(4,"April"),
	May(5,"May"),
	June(6,"June"),
	July(7,"July"),
	August(8,"August"),
	September(9,"September"),
	October(10,"October"),
	November(11,"November"),
	December(12,"December");

public final int codice;
public final String descrizione;
	
	MonthDTO(int cod,String des)
	{
		codice=cod;
		descrizione=des;
	}

	
	
	/**
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}



	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}



	public static MonthDTO parseFromDesc(String desc){
		MonthDTO result = null;
		for(MonthDTO mese : values()){
			if(mese.getDescrizione().equals(desc)){
				result = mese;
				break;
			}
		}
		return result;
	}

	
}
