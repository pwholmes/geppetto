package org.geppetto.domain;

public enum Phrase {
	PRGM,
	INPUT,
	LINE,
	NL,
	EXP,
	NUM,
	PLUS,
	MINUS,
	MULT,
	DIV,
	NEG,
	POW,
	LPAREN,
	RPAREN;
	
	
	String name;
	
	Phrase()
	{
		
	}
	
	Phrase(String name){
		this.name= name;
	}
	
	public String getName()
	{
		return name;
	}
}
