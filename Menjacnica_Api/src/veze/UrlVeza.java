package veze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlVeza {

	public static String getContent(String url) throws IOException {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = "";
		boolean proceed = true;

		while (proceed) {
			String s = in.readLine();
			if (s != null)
				response += s;
			else
				proceed = false;
		}

		in.close();
		
		return response.toString();
	}
}