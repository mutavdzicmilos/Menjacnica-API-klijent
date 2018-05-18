package veze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import logickakontrola.Drzava;;

public class VratiDrzaveSO {

	public static final String SERVICE = "/countries";
	public static final String CURRENCY_LAYER_API_URL = "http://free.currencyconverterapi.com/api/v5";

	public static ArrayList<Drzava> listOfCountries() {

		String url = CURRENCY_LAYER_API_URL + SERVICE;

		try {
			String content = URLUtil.getContent(url);
			Gson gson = new GsonBuilder().create();

			JsonObject contentJSON = gson.fromJson(content, JsonObject.class);
			JsonObject resultsJSON = contentJSON.get("results").getAsJsonObject();

			ArrayList<Drzava> list = new ArrayList<Drzava>();

			for (Entry<String, JsonElement> entry : resultsJSON.entrySet()) {
				Drzava item = gson.fromJson(entry.getValue().getAsJsonObject(), Drzava.class);
				list.add(item);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}