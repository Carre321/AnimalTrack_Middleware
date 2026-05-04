package com.tonin.animaltrack.dao;

import java.util.ArrayList;
import java.util.List;

public class Results<T> {

	private List<T> pageResults;
	private int total;
	
	public Results() {
		pageResults = new ArrayList<T>();
	}

	public List<T> getPageResults() {
		return pageResults;
	}

	public void setPageResults(List<T> pageResults) {
		this.pageResults = pageResults;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
