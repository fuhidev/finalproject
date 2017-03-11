package tdt.it.finalproject.JavaScraping.ComplicatedScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tdt.it.finalproject.generateDay.generate.GenerateDay;
public class WikiScraper {
	private static Random generator;
	private static Document doc;
	
	public Document getDoc() {
		return doc;
	}


	public void setDoc(Document doc) {
		this.doc = doc;
	}


	public static void main(String[] args) throws IOException {
		List<String> link = new GenerateDay().generate("20170301", "20170311");
		for(String item: link){
			doc = Jsoup.connect("https://www.tygia.com/?nganhang=VIETCOM&ngay=" + item).get();
		 
	        Elements aElements = doc.select("#gold_tb #goldtb .rmore");
	        for (Element aElement : aElements) {
	            String href = aElement.attr("href");
	            String text = aElement.text();
	            System.out.println(text);
	            System.out.println("\t" + href);
	            System.out.println("https://www.tygia.com/?nganhang=VIETCOM&ngay=" + item);
	            System.out.println("----Ngay" + item + "-----");
	        }
		}
	    }
	

	public static void scrapeTopic(String url) {
		String html = getUrl("https://www.wikipedia.org/" + url);
		Document doc = Jsoup.parse(html);
		Elements links = doc
				.select("#mw-content-text [href~=^/wiki/((?!:).)*$]");
		if (links.size() == 0) {
			System.out.println("No links found at " + url
					+ ". Going back to Java!");
			scrapeTopic("wiki/Java");
		}
		int r = generator.nextInt(links.size());
		System.out.println("Random link is: " + links.get(r).text()
				+ " at url: " + links.get(r).attr("href"));
		scrapeTopic(links.get(r).attr("href"));
	}

	public static String getUrl(String url) {
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
			return "";
		}
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText = "";
		try {
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				outputText += line;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("There was an error connecting to the URL");
			return "";
		}
		return outputText;
	}
}
