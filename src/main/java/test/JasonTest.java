package test;

import org.json.JSONObject;

public class JasonTest {

	public static void main(String[] args) {
		JSONObject jo = new JSONObject("{\"name\": \"\"}");
		System.out.println(jo.get("name"));
		System.out.println(jo.get("name") == null);
		System.out.println(jo.get("name").equals(null));
		System.out.println(jo.get("name").equals(""));

	}

}
