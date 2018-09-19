package com.mcf.shares.web.condition;

public class WindowsListService implements ListService{

	@Override
	public String showListCmd() {
		return "dir";
	}

}
