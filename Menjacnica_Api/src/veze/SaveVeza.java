package veze;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logickakontrola.Konverzija;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SaveVeza {

	public static void save(String from, String to, Double rate) {
		Konverzija konverzija = new Konverzija();
		konverzija.setIzValute(from);
		konverzija.setuValutu(to);
		konverzija.setKurs(rate);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
		konverzija.setDatumVreme(format.format(date));

		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		String s = gson.toJson(konverzija);
		JsonObject objJSON = gson.fromJson(s, JsonObject.class);
		JsonArray log = null;

		try {
			FileReader reader = new FileReader("Data/log.json");
			log = gson.fromJson(reader, JsonArray.class);
			reader.close();
		} catch (Exception e) {
			try {
				FileWriter writer = new FileWriter("Data/log.json");
				writer.close();
			} catch (IOException e1) {
			}
		} 
		
		try {
			FileWriter writer = new FileWriter("Data/log.json");
			if(log == null) 
				log = new JsonArray();

			log.add(objJSON);
			writer.append(gson.toJson(log));
			writer.close();
		} catch (Exception e) {
		}
	}
}