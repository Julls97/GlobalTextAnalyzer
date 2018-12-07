package example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
String makeCaps (String text) - возвращает исходный текст, записанный ПРОПИСНЫМИ буквами.

Int countOneWord (String text, String word) - возвращает количество слов word в тексте text.

(uChar, Int) [] countCharacters (String text) - возвращает массив, содержащий набор пар (символ, количество),
отражающий информацию, по количеству использования каждого символа в тексте.

(String, Int) [] countWords (String text) - возвращает массив,
содержащий набор пар (слово, количество), отражающий информацию,
по количеству использования каждого из слов в тексте.


Разместить разработанный веб-сервис на облачной платформе.	* 2 балла
Реализовать метод (String Int) [] countWordsOnWebPage (String URL),
возвращающий информацию по количеству слов на веб-странице с адресом URL	 * 3 балла
*/

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
		Pattern pattern = Pattern.compile(word);
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find()) count++;
		return count;
	}
	
	@WebMethod
	public Pair[] countCharacters(@WebParam(name = "text") String text) {
		HashMap<Character, Integer> collect = new HashMap<Character, Integer>();
		for (Character c : text.toLowerCase().toCharArray())
			collect.put(c, collect.containsKey(c) ? ( collect.get(c) + 1 ) : 1);
		
		ArrayList<Entry<Character, Integer>> array = new ArrayList<>();
		array.addAll(collect.entrySet());
		return array.toArray(new Pair[0]);
	}
	
	@WebMethod
	public Pair[] countWords(@WebParam(name = "text") String text) {
		HashMap<String, Integer> collect = new HashMap<String, Integer>();
		for (String c : text.toLowerCase().split(" "))
			collect.put(c, collect.containsKey(c) ? ( collect.get(c) + 1 ) : 1);
		
		ArrayList<Entry<String, Integer>> array = new ArrayList<>();
		array.addAll(collect.entrySet());
		return array.toArray(new Pair[0]);
	}
}
