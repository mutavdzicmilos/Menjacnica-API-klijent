package veze;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class VratiKursSO {

	public static final String SERVICE = "/convert";
	public static final String CURRENCY_LAYER_API_URL = "http://free.currencyconverterapi.com/api/v5";
	
	public static double convert(String from, String to) throws Exception {
	
		String url = CURRENCY_LAYER_API_URL + SERVICE + "?q=" + from + '_' + to;
		
		try {
			String content = URLUtil.getContent(url);
			Gson gson = new GsonBuilder().create();
			JsonObject contentJSON = gson.fromJson(content, JsonObject.class);
			int count = contentJSON.get("query").getAsJsonObject().get("count").getAsInt();
			
			if(count > 0) {
				JsonObject obj = contentJSON.get("results").getAsJsonObject().get(from + "_" + to).getAsJsonObject();
				return obj.get("val").getAsDouble();
			}
			else 
				throw new Exception("ERROR: No available data!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}