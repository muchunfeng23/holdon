package com.mcf.shares.web.condition;

public class LinuxListService implements ListService{

	@Override
	public String showListCmd() {
		return "ls";
	}

}
