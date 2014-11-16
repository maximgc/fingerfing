package org.fingerfing.client.core;

//WARN наследование?
public class Attempt extends Element {

	private int eval;
	private NativeKey actualNativeKey;

	public NativeKey getActualNativeKey() {
		return actualNativeKey;
	}

	public Attempt(Element expectElement, NativeKey actualNativeKey) {
		this(expectElement.getPos(), expectElement.getKey(),  actualNativeKey);
	}

	public Attempt(int pos, Key expectKey, NativeKey actualNativeKey) {
		super(pos, expectKey);
		this.actualNativeKey = actualNativeKey;
		this.eval = (expectKey.getNativeCode() == actualNativeKey.getNativeCode()) ? 1
				: 2;
	}

	public int getEval() {
		return eval;
	}

	public boolean isSuccess() {
		return eval == 1;
	}

}
