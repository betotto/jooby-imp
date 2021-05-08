package mx.javamexico.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonParser {	

	public static Gson parser = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz").create();
}
