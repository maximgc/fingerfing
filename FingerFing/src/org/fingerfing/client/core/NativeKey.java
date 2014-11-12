package org.fingerfing.client.core;

import java.util.HashMap;
import java.util.Map;

public enum NativeKey{

	/*
	 * KEY_BACKSPACE(8), KEY_SHIFT(16), KEY_CTRL(17), KEY_ALT(18),
	 * KEY_PAUSE(19), KEY_CAPS_LOCK( 20), KEY_PAGE_UP(33), KEY_PAGE_DOWN(34),
	 * KEY_END(35), KEY_HOME(36), KEY_ARROW_LEFT( 37), KEY_ARROW_UP(38),
	 * KEY_ARROW_RIGHT(39), KEY_ARROW_DOWN(40), KEY_PRT_SCR( 44), KEY_INS(45),
	 * KEY_DEL(46), KEY_WIN(91), KEY_MENU(93), KEY_F1( 112), KEY_F2(113),
	 * KEY_F3(114), KEY_F4(115), KEY_F5(116), KEY_F6( 117), KEY_F7(118),
	 * KEY_F8(119), KEY_F9(120), KEY_F10(121), KEY_F11( 122), KEY_F12(123),
	 * KEY_SCR_LOCK(145), KEY_TAB(9), KEY_ENTER(13),
	 * 
	 * KEY_ESC(27), KEY_SPACE(32),
	 * 
	 * KEY_0(48), KEY_1(49), KEY_2(50), KEY_3(51), KEY_4(52), KEY_5(53),
	 * KEY_6(54), KEY_7( 55), KEY_8(56), KEY_9(57),
	 * 
	 * KEY_A(65), KEY_B(66), KEY_C(67), KEY_D(68), KEY_E(69), KEY_F(70),
	 * KEY_G(71), KEY_H( 72), KEY_I(73), KEY_J(74), KEY_K(75), KEY_L(76),
	 * KEY_M(77), KEY_N( 78), KEY_O(79), KEY_P(80), KEY_Q(81), KEY_R(82),
	 * KEY_S(83), KEY_T( 84), KEY_U(85), KEY_V(86), KEY_W(87), KEY_X(88),
	 * KEY_Y(89), KEY_Z( 90),
	 * 
	 * KEY_SEMICOLON(186), KEY_EQUAL(187), KEY_COMMA(188), KEY_MINUS(189),
	 * KEY_FULLSTOP( 190), KEY_SLASH(191), KEY_GRAVE_ACCENT(192),
	 * KEY_SQ_BRACKET_OPEN( 219), KEY_BACKSLASH(220), KEY_SQ_BRACKET_CLOSE(221),
	 * KEY_APOSTROPHE( 222),
	 * 
	 * KEY_NUM_LOCK(144),
	 * 
	 * KEY_NUM_0(96), KEY_NUM_1(97), KEY_NUM_2(98), KEY_NUM_3(99),
	 * KEY_NUM_4(100), KEY_NUM_5( 101), KEY_NUM_6(102), KEY_NUM_7(103),
	 * KEY_NUM_8(104), KEY_NUM_9(105), KEY_NUM_MULT( 106), KEY_NUM_PLUS(107),
	 * KEY_NUM_MINUS(109), KEY_NUM_DOT(110), KEY_NUM_DIV( 111);
	 */
	KEY_BACKSPACE(8, "Backspace"), KEY_TAB(9, "Tab"), KEY_ENTER(13, "Enter"),

	KEY_SHIFT(16, "Shift"), KEY_CTRL(17, "Ctrl"), KEY_ALT(18, "Alt"),

	KEY_PAUSE(19, "Pause"), KEY_CAPS_LOCK(20, "Caps Lock"), KEY_ESC(27, "Esc"), KEY_SPACE(
			32, "Space"),

	KEY_PAGE_UP(33, "Page Up"), KEY_PAGE_DOWN(34, "Page Down"), KEY_END(35,
			"End"), KEY_HOME(36, "Home"), KEY_ARROW_LEFT(37, "Left"), KEY_ARROW_UP(
			38, "Up"), KEY_ARROW_RIGHT(39, "Right"), KEY_ARROW_DOWN(40, "Down"),

	KEY_PRT_SCR(44, "Prt Scr"), KEY_INS(45, "Insert"), KEY_DEL(46, "Delete"),

	KEY_0(48, "0"), KEY_1(49, "1"), KEY_2(50, "2"), KEY_3(51, "3"), KEY_4(52,
			"4"), KEY_5(53, "5"), KEY_6(54, "6"), KEY_7(55, "7"), KEY_8(56, "8"), KEY_9(
			57, "9"), KEY_A(65, "A"), KEY_B(66, "B"), KEY_C(67, "C"), KEY_D(68,
			"D"), KEY_E(69, "E"), KEY_F(70, "F"), KEY_G(71, "G"), KEY_H(72, "H"), KEY_I(
			73, "I"), KEY_J(74, "J"), KEY_K(75, "K"), KEY_L(76, "L"), KEY_M(77,
			"M"), KEY_N(78, "N"), KEY_O(79, "O"), KEY_P(80, "P"), KEY_Q(81, "Q"), KEY_R(
			82, "R"), KEY_S(83, "S"), KEY_T(84, "T"), KEY_U(85, "U"), KEY_V(86,
			"V"), KEY_W(87, "W"), KEY_X(88, "X"), KEY_Y(89, "Y"), KEY_Z(90, "Z"),

	KEY_WIN(91, "Win"), KEY_MENU(93, "Menu"),

	KEY_NUM_0(96, "0"), KEY_NUM_1(97, "1"), KEY_NUM_2(98, "2"), KEY_NUM_3(99,
			"3"), KEY_NUM_4(100, "4"), KEY_NUM_5(101, "5"), KEY_NUM_6(102, "6"), KEY_NUM_7(
			103, "7"), KEY_NUM_8(104, "8"), KEY_NUM_9(105, "9"), KEY_NUM_MULT(
			106, "*"), KEY_NUM_PLUS(107, "'+"), KEY_NUM_MINUS(109, "-"), KEY_NUM_DOT(
			110, "."), KEY_NUM_DIV(111, "/"),

	KEY_F1(112, "F1"), KEY_F2(113, "F2"), KEY_F3(114, "F3"), KEY_F4(115, "F4"), KEY_F5(
			116, "F5"), KEY_F6(117, "F6"), KEY_F7(118, "F7"), KEY_F8(119, "F8"), KEY_F9(
			120, "F9"), KEY_F10(121, "F10"), KEY_F11(122, "F11"), KEY_F12(123,
			"F12"),

	KEY_NUM_LOCK(144, "Num Lock"), KEY_SCR_LOCK(145, "Scroll Lock"),

	KEY_SEMICOLON(186, ";"), KEY_EQUAL(187, "="), KEY_COMMA(188, ","), KEY_MINUS(
			189, "-"), KEY_FULLSTOP(190, "."), KEY_SLASH(191, "/"), KEY_GRAVE_ACCENT(
			192, "`"), KEY_SQ_BRACKET_OPEN(219, "["), KEY_BACKSLASH(220, "\\"), KEY_SQ_BRACKET_CLOSE(
			221, "]"), KEY_APOSTROPHE(222, "'"),
	
	KEY_BACKSLASH_ADDITIONAL(226, "\\");

	public static Map<Integer, NativeKey> nativeCodeMap;

	private static void putKeyCode(Integer nativeCode, NativeKey nativeKey) {
		if (nativeCodeMap == null) {
			nativeCodeMap = new HashMap<Integer, NativeKey>();
		}
		nativeCodeMap.put(nativeCode, nativeKey);
	}

	private String textualNotation;
	private int nativeCode;

	private NativeKey(int nativeCode) {
		this(nativeCode, "");
	}

	private NativeKey(int nativeCode, String labelText) {
		this.textualNotation = labelText;
		this.nativeCode = nativeCode;
		putKeyCode(Integer.valueOf(nativeCode), this);
	}

	public String toText() {
		return textualNotation;
	}
	
	public int getNativeCode() {
		return nativeCode;
	}

	public boolean isModificator() {
		return (this == KEY_SHIFT || this == KEY_CTRL || this == KEY_ALT);
	}

	public static NativeKey getByNativeCode(Integer nativeCode) {
		NativeKey ret = nativeCodeMap.get(nativeCode);
		if (ret == null) {
			throw new IllegalNativeCode(String.valueOf(nativeCode));
		}
		return ret;
	}

}
