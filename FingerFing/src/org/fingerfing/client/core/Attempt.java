package org.fingerfing.client.core;

//WARN наследование?
public class Attempt extends Element{

	private int eval;
	private int number;
	private Key obtained;
	
	public Attempt(Element expectElement, int number, Key obtained, int eval) {
		this(expectElement.getPos(), expectElement.getNativeKey(), number, obtained, eval);
	}
	
	public Attempt(int pos, Key expect, int number, Key obtained, int eval) {
		super(pos, expect);
		this.number = number;
		this.obtained = obtained;
		this.eval = eval;
	}

	public int getEval() {
		return eval;
	}

	public int getNumber() {
		return number;
	}

	public Key getObtained() {
		return obtained;
	}

}
