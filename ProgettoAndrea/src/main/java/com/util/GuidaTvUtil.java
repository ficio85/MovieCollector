package com.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.dto.FasciaOrariaDTO;

public class GuidaTvUtil {

	
	
 public static	Timestamp[] fromFasciaToTimeStamp(String fascia)
 {
	 Timestamp [] orariFascia = new Timestamp[2];
	 Timestamp oggi = new Timestamp(new Date().getTime());

	 if(fascia.equals(FasciaOrariaDTO.POMERIGGIO.codFascia))
	 {
		 orariFascia[0]=new Timestamp(DateUtil.getCalendarHour(15,30).getTimeInMillis());
		 orariFascia[1]=new Timestamp(DateUtil.getCalendarHour(20,30).getTimeInMillis());

	 }
	 else if(fascia.equals(FasciaOrariaDTO.SERALE.codFascia))
	 {
		 orariFascia[0]=new Timestamp(DateUtil.getCalendarHour(20,30).getTimeInMillis());
		 orariFascia[1]=new Timestamp(DateUtil.getCalendarHour(24,00).getTimeInMillis());

	 }
	 return orariFascia;


 }

	
	}
