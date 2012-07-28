package me.com.consurme.util;

import java.text.DecimalFormat;




public final class FormatarCampos {


	private FormatarCampos() {}
	
	
	public static String formatarFloat(float valor){
		
		DecimalFormat df  = new DecimalFormat();
		df.applyPattern("00.00;(00.00)");
		return df.format(valor);  
		
	}
	
	
	
	
}
