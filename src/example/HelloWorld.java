package example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebService()
public class HelloWorld {
	public static void main(String[] argv) {
		Object implementor = new HelloWorld();
		String address = "http://localhost:9000/HelloWorld";
		Endpoint.publish(address, implementor);
	}
	
	@WebMethod
	public String makeCaps(@WebParam(name = "text") String text) {
		return text.toUpperCase();
	}
	
	@WebMethod
	public int countOneWord(@WebParam(name = "text") String text, @WebParam(name = "word") String word) {
		Pattern pattern = Pattern.compile(word + " ");
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find()) count++;
		return count;
	}
	
	@WebMethod
	public Pair[] countCharacters(@WebParam(name = "text") String text) {
		HashMap<Character, Integer> collect = new HashMap<Character, Integer>();
		for (Character c : text.toLowerCase().toCharArray())
			//if(!(c == ' '))
				collect.put(c, collect.containsKey(c) ? ( collect.get(c) + 1 ) : 1);
		
		ArrayList<Pair> array = new ArrayList<>();
		Character[] keys = collect.keySet().toArray(new Character[0]);
		for (Character key: keys) array.add(new Pair(key.toString(), collect.get(key)));
		return array.toArray(new Pair[0]);
	}
	
	@WebMethod
	public Pair[] countWords(@WebParam(name = "text") String text) {
		HashMap<String, Integer> collect = new HashMap<String, Integer>();
		for (String c : text.toLowerCase().split("[\\p{P}\\p{N}\\s]+"))
//		for (String c : text.toLowerCase().split("[\\p{P}\\s]+"))
			collect.put(c, collect.containsKey(c) ? ( collect.get(c) + 1 ) : 1);
		
		ArrayList<Pair> array = new ArrayList<>();
		String[] keys = collect.keySet().toArray(new String[0]);
		for (String key: keys) array.add(new Pair(key, collect.get(key)));
		return array.toArray(new Pair[0]);
	}
}
