package com.stqa.astar.controllers;

public enum Keys {
	S_KEY("Start Point"),
	Enter_KEY("Enter/Run"),
	ESC_KEY("Escape/Reset"),
	R_KEY("Random walls"),
	E_KEY("END Point");
	
	private final String value;
	
	Keys (String s) {
		this.value = s;
	}
	
	@Override
	public String toString () {
		return value;
	}
}
