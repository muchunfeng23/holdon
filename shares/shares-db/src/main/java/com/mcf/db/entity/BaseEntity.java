package com.mcf.db.entity;

import java.util.LinkedHashMap;

public abstract class BaseEntity {
	private LinkedHashMap<String, String> sort;

	public LinkedHashMap<String, String> getSort() {
		return this.sort;
	}

	public void setSort(LinkedHashMap<String, String> sort) {
		this.sort = sort;
	}
}
