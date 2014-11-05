package org.fingerfing.client.core;

//WARN наследование?
public class Attempt extends Element{

	private int eval;
	private int number;
	private NativeKey obtained;
	
	public Attempt(Element expectElement, int number, NativeKey obtained, int eval) {
		this(expectElement.getPos(), expectElement.getNativeKey(), number, obtained, eval);
	}
	
	public Attempt(int pos, NativeKey expect, int number, NativeKey obtained, int eval) {
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

	public NativeKey getObtained() {
		return obtained;
	}

}
