package codersliberty.dynamic_pojo;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javassist.ClassPool;
import javassist.NotFoundException;

public class PojoFromJson {
	ClassPool pool=ClassPool.getDefault();
	
	public Object deserializeIntoPOJO(String json, String rootClass) throws NotFoundException {
		Gson gson=new Gson();
		JsonElement obj = gson.fromJson(json, JsonElement.class);
		JsonObject jsonObject=obj.getAsJsonObject();
		return jsonObject;
	}
	
	public Map<String, Object> readJSON(String json) {
		if(json.startsWith("{")) {
			readJSON(json.substring(1));
		}
		return null;
		
	}
	
	public static void main(String[] args) throws NotFoundException {
		//String json="{\"name\":\"English\",\"marks\":55.0}";
		String json="{\"course\":{\"name\":\"English\",\"marks\":55.0},\"institute\":\"SSVN\",\"roll\":0,\"batch\":null,\"name\":\"John\",\"address\":{\"city\":\"Ghy\",\"locality\":\"LastGate\",\"state\":\"Assam\",\"pin\":null},\"email\":null,\"age\":23,\"pid\":null}";
		PojoFromJson fromJson=new PojoFromJson();
		Object object = fromJson.deserializeIntoPOJO(json,"Course");
		System.out.println(object);
		
	}
}
