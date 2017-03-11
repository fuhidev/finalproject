package com.packtpub.JavaScraping.SimpleScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.*;
import java.io.*;

public class WikiScraper {
	public static void main(String[] args) {
		scrapeTopic("/price");
	}

	public static void scrapeTopic(String url) {
		String html = getUrl("http://sjc.com.vn/" + url);
		Document doc = Jsoup.parse(html);
		String context = doc.select(".text_tgvang_mota").text();
		System.out.println("Content: " +context);
	}

	private static String getUrl(String url) {
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (Exception e) {
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
		} catch (Exception e) {
			System.out.println("There was an error connecting to the URL");
			return "";
		}
		return outputText;
	}
}
