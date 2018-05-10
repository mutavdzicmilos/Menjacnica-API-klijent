package logickakontrola;

import java.io.IOException;
import java.util.ArrayList;

import	veze.DrzaveVeza;
import veze.UrlVeza;
public class Kontroler {

	public String URLConnection(String url) throws IOException {
		return UrlVeza.getContent(url);
	}
	
	public ArrayList<Drzava> getCountries(){
		return DrzaveVeza.listOfCountries();
	}
}