package main.tdt.it.finalproject;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.InterestRateDatabase;
import main.tdt.it.finalproject.modal.DollarPrices;
import main.tdt.it.finalproject.modal.GoldPrices;
import main.tdt.it.finalproject.modal.InterestRatePrices;
import main.tdt.it.finalproject.scraper.ContextDocument;
import main.tdt.it.finalproject.scraper.DollarScraper;
import main.tdt.it.finalproject.scraper.GoldScraper;
import main.tdt.it.finalproject.scraper.InterestRateScraper;
import main.tdt.it.finalproject.scraper.modal.LinkScraper;

/**
 * Lay du lieu trong ngay hien tai cua he thong
 * 
 * @author fuhi
 *
 */
public class DataRuntime {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		// lay du lieu vang
		GoldDatabase goldDatabase = new GoldDatabase();
		List<LinkScraper> goldLink = new LinkedList<>();
		goldLink.add(new LinkScraper(LinkScraper.GOLD_TYPE, "https://www.tygia.com/?nganhang=VIETCOM", "#gold_tb #goldtb #SJCH_Ch_Minh td.c1 b,#gold_tb #goldtb #SJCH_Ch_Minh span.c2,#gold_tb #goldtb #SJCH_Ch_Minh span.c4", null));
		goldLink.add(new LinkScraper(LinkScraper.GOLD_TYPE, "http://banggia.giavang.net/","#tbl tr:nth-child(7) td:first-child,#tbl tr:nth-child(7) td:nth-child(2),#tbl tr:nth-child(7) td:last-child",null));
		for (LinkScraper link : goldLink) {
			try {
				// kiem tra xem co parameters hay khong
				if (link.getparameters() != null) {
					// xu ly pattern
				}
				ContextDocument contextDocument = new ContextDocument(link.getLink());
				System.out.println(link);

				contextDocument.setCssQuery(link.getCss());
				GoldScraper scarper = new GoldScraper();
				scarper.setDate(date);
				scarper.setElements(contextDocument.getElements());
				
				//lay du lieu
				GoldPrices datas = scarper.getData();
				
				//dua vao csdl
				goldDatabase.adds(datas.iterator());
				// neu thanh cong thi thoat
				break;
			} catch (Exception e) {
				// neu khong thanh cong thi tu chuyen sang linkscraper tiep theo
			}
		}

		// lay du lieu dola
		DollarDatabase dollarDatabase = new DollarDatabase();
		List<LinkScraper> dollarLink = new LinkedList<>();
		dollarLink.add(new LinkScraper(LinkScraper.DOLLAR_TYPE, "https://www.tygia.com/?nganhang=VIETCOM", "#ratetb tr:first-child td:first-child b,#ratetb tr:first-child td:last-child span.c4", null));
		dollarLink.add(new LinkScraper(LinkScraper.DOLLAR_TYPE, "https://vietcombank.com.vn/", "#exchangerate .tbl-exch tr:last-child td:first-child,#exchangerate .tbl-exch tr:last-child td:last-child",null));
		for (LinkScraper link : dollarLink) {
			try {
				// kiem tra xem co parameters hay khong
				if (link.getparameters() != null) {
					// xu ly pattern
				}
				ContextDocument contextDocument = new ContextDocument(link.getLink());
				System.out.println(link);

				contextDocument.setCssQuery(link.getCss());
				DollarScraper dollarScaper = new DollarScraper();
				dollarScaper.setDate(date);
				dollarScaper.setElements(contextDocument.getElements());
				
				//lay du lieu
				DollarPrices datas = dollarScaper.getData();
				
				//dua vao csdl
				dollarDatabase.adds(datas.iterator());
				// neu thanh cong thi thoat
				break;
			} catch (Exception e) {
				// neu khong thanh cong thi tu chuyen sang linkscraper tiep theo
			}
		}

		// lay ty gia ngan hang
		InterestRateDatabase bankDatabase = new InterestRateDatabase();
		List<LinkScraper> bankLink = new LinkedList<>();
		bankLink.add(new LinkScraper(LinkScraper.BANK_TYPE, "http://vietbao.vn/vn/lai-suat-tiet-kiem/", ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)", null));
		for (LinkScraper link : bankLink) {
			try {
				// kiem tra xem co parameters hay khong
				if (link.getparameters() != null) {
					// xu ly pattern
				}
				ContextDocument contextDocument = new ContextDocument(link.getLink());
				System.out.println(link);

				contextDocument.setCssQuery(link.getCss());
				InterestRateScraper scarper = new InterestRateScraper();
				scarper.setDate(date);
				scarper.setElements(contextDocument.getElements());
				
				//lay du lieu
				InterestRatePrices datas = scarper.getData();
				
				//dua vao csdl
				bankDatabase.adds(datas.iterator());
				// neu thanh cong thi thoat
				break;
			} catch (Exception e) {
				// neu khong thanh cong thi tu chuyen sang linkscraper tiep theo
			}
		}
	}
}
