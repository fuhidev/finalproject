package main.tdt.it.finalproject.portfolio;

import java.util.List;

public interface Algorithm<T> {
	double[][] cov();
	double mean();
	List<T> getFilter();
	
}
