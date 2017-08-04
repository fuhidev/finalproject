package main.tdt.it.finalproject.portfolio.modal;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private List<Point> list;

	public Graph() {
		super();
		list = new LinkedList<>();
	}

	public void add(Point p) {
		list.add(p);
	}
	

	public Point maxSlope() {
		float max = 0;
		Point pMax = null;
		for (Point point : list) {
			float value = point.getExpectedReturn() / point.getStandardDeviation();
			if (value > max) {
				max = value;
				pMax = point;
			}
		}
		return pMax;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < this.list.size(); i++) {
			System.out.println(i + ": " + this.list.get(i).toString());
		}
		return s;
	}

}
