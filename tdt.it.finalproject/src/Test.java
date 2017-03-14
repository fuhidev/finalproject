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

public class Test {
	private static Document doc;

	public Document getDoc() {
		return doc;
	}

	public static void main(String[] args) throws IOException {
		List<String> link = new GenerateDay().generate("20170311", "20170312");
		for (String item : link) {
			doc = Jsoup.connect(
					"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + item)
					.get();

			Elements aElements = doc
					.select("meta[property='og:url'],#gold_tb #goldtb td.c1 b,#gold_tb #goldtb .c2,#gold_tb #goldtb .c4");
			for (int i = 0; i < aElements.size(); i++) {
				if (i % 100 == 0) {
					String link1 = aElements.attr("content");
					String dateString = link1.substring(link1.length() - 8,
							link1.length());
					String date = dateString.substring(6, 8) + "-"
							+ dateString.substring(4, 6) + "-"
							+ dateString.substring(0, 4);
					System.out.println(date);
				} else {
					System.out.println(aElements.get(i).text());
				}
			}
//			System.out.println(aElements.size());
		}
	}
}
