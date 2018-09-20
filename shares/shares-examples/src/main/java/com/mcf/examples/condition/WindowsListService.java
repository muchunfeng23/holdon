package com.mcf.examples.condition;

public class WindowsListService implements ListService{

	@Override
	public String showListCmd() {
		return "dir";
	}

}
