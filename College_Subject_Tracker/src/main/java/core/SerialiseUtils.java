package core;

import com.google.gson.*;

public final class SerialiseUtils {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Public methods
	//
	public static final String serializeObject(Object o) {
		Gson gson = new Gson();
		String serializedObject = gson.toJson(o);
		return serializedObject;
	}
	
	
	public static final Object unserializeObject(String s, Object o){
		Gson gson = new Gson();
		Object object = gson.fromJson(s, o.getClass());
		return object;
	}
	
	
	public static final Object cloneObject(Object o){
		String s = serializeObject(o);
		Object object = unserializeObject(s,o);
		return object;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private SerialiseUtils() {}
}
