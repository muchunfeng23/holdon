package com.mcf.examples.condition;

public class LinuxListService implements ListService{

	@Override
	public String showListCmd() {
		return "ls";
	}

}
