package com.SeromSb.dajuva.app.demodajuva.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static Date convertiFecha(String fch) {
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy H:mn:ss");
		Date date=null;
		try {
			date=formato.parse(fch);
		} catch(ParseException e) {
			// TODO Auto - generated catch block
			
			e.printStackTrace();
		}
		return date;
	}
}
