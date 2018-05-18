package logickakontrola;

import java.io.IOException;
import java.util.ArrayList;

import	veze.VratiDrzaveSO;
import veze.VratiKursSO;
import veze.SacuvajLogSO;
import veze.URLUtil;
public class Menjacnica {

	public String URLConnection(String url) throws IOException {
		return URLUtil.getContent(url);
	}
	
	public ArrayList<Drzava> getCountries(){
		return VratiDrzaveSO.listOfCountries();
	}
	public double returnExchangeRate(String from, String to) throws Exception{
		return VratiKursSO.convert(from, to);
	}
	public void saveExchange(String from, String to, Double odnos) throws Exception {
		SacuvajLogSO.save(from, to,	odnos);
	}
}
