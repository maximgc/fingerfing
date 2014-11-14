package org.fingerfing.client.core;

import java.util.HashMap;
import java.util.Map;

public enum NativeKey {

	KEY_BACKSPACE(8, "Backspace", Key.KEY_BACKSPACE), KEY_TAB(9, "Tab",
			Key.KEY_TAB), KEY_ENTER(13, "Enter", Key.KEY_ENTER),

	KEY_SHIFT(16, "Shift", Key.KEY_SHIFT_RIGHT, Key.KEY_SHIFT_LEFT), KEY_CTRL(
			17, "Ctrl", Key.KEY_CTRL_RIGHT, Key.KEY_CTRL_LEFT), KEY_ALT(18,
			"Alt", Key.KEY_ALT_RIGHT, Key.KEY_ALT_LEFT),

	KEY_PAUSE(19, "Pause", Key.KEY_PAUSE), KEY_CAPS_LOCK(20, "Caps Lock",
			Key.KEY_CAPS_LOCK), KEY_ESC(27, "Esc", Key.KEY_ESC), KEY_SPACE(32,
			"Space", Key.KEY_SPACE),

	KEY_PAGE_UP(33, "Page Up", Key.KEY_PAGE_UP), KEY_PAGE_DOWN(34, "Page Down",
			Key.KEY_PAGE_DOWN), KEY_END(35, "End", Key.KEY_END), KEY_HOME(36,
			"Home", Key.KEY_HOME), KEY_ARROW_LEFT(37, "Left",
			Key.KEY_ARROW_LEFT), KEY_ARROW_UP(38, "Up", Key.KEY_ARROW_UP), KEY_ARROW_RIGHT(
			39, "Right", Key.KEY_ARROW_RIGHT), KEY_ARROW_DOWN(40, "Down",
			Key.KEY_ARROW_DOWN),

	KEY_PRT_SCR(44, "Prt Scr", Key.KEY_PRT_SCR), KEY_INS(45, "Insert",
			Key.KEY_INS), KEY_DEL(46, "Delete", Key.KEY_DEL),

	KEY_0(48, "0", Key.KEY_0), KEY_1(49, "1", Key.KEY_1), KEY_2(50, "2",
			Key.KEY_2), KEY_3(51, "3", Key.KEY_3), KEY_4(52, "4", Key.KEY_4), KEY_5(
			53, "5", Key.KEY_5), KEY_6(54, "6", Key.KEY_6), KEY_7(55, "7",
			Key.KEY_7), KEY_8(56, "8", Key.KEY_8), KEY_9(57, "9", Key.KEY_9), KEY_A(
			65, "A", Key.KEY_A), KEY_B(66, "B", Key.KEY_B), KEY_C(67, "C",
			Key.KEY_C), KEY_D(68, "D", Key.KEY_D), KEY_E(69, "E", Key.KEY_E), KEY_F(
			70, "F", Key.KEY_F), KEY_G(71, "G", Key.KEY_G), KEY_H(72, "H",
			Key.KEY_H), KEY_I(73, "I", Key.KEY_I), KEY_J(74, "J", Key.KEY_J), KEY_K(
			75, "K", Key.KEY_K), KEY_L(76, "L", Key.KEY_L), KEY_M(77, "M",
			Key.KEY_M), KEY_N(78, "N", Key.KEY_N), KEY_O(79, "O", Key.KEY_O), KEY_P(
			80, "P", Key.KEY_P), KEY_Q(81, "Q", Key.KEY_Q), KEY_R(82, "R",
			Key.KEY_R), KEY_S(83, "S", Key.KEY_S), KEY_T(84, "T", Key.KEY_T), KEY_U(
			85, "U", Key.KEY_U), KEY_V(86, "V", Key.KEY_V), KEY_W(87, "W",
			Key.KEY_W), KEY_X(88, "X", Key.KEY_X), KEY_Y(89, "Y", Key.KEY_Y), KEY_Z(
			90, "Z", Key.KEY_Z),

	KEY_WIN_LEFT(91, "Win", Key.KEY_WIN_LEFT), KEY_WIN_RIGHT(92, "Win",
			Key.KEY_WIN_RIGHT), KEY_MENU(93, "Menu", Key.KEY_MENU),

	KEY_NUM_0(96, "0", Key.KEY_NUM_0), KEY_NUM_1(97, "1", Key.KEY_NUM_1), KEY_NUM_2(
			98, "2", Key.KEY_NUM_2), KEY_NUM_3(99, "3", Key.KEY_NUM_3), KEY_NUM_4(
			100, "4", Key.KEY_NUM_4), KEY_NUM_5(101, "5", Key.KEY_NUM_5), KEY_NUM_6(
			102, "6", Key.KEY_NUM_6), KEY_NUM_7(103, "7", Key.KEY_NUM_7), KEY_NUM_8(
			104, "8", Key.KEY_NUM_8), KEY_NUM_9(105, "9", Key.KEY_NUM_9), KEY_NUM_MULT(
			106, "*", Key.KEY_NUM_MULT), KEY_NUM_PLUS(107, "'+",
			Key.KEY_NUM_PLUS), KEY_NUM_MINUS(109, "-", Key.KEY_NUM_MINUS), KEY_NUM_DOT(
			110, ".", Key.KEY_NUM_DOT), KEY_NUM_DIV(111, "/", Key.KEY_NUM_DIV),

	KEY_F1(112, "F1", Key.KEY_F1), KEY_F2(113, "F2", Key.KEY_F2), KEY_F3(114,
			"F3", Key.KEY_F3), KEY_F4(115, "F4", Key.KEY_F4), KEY_F5(116, "F5",
			Key.KEY_F5), KEY_F6(117, "F6", Key.KEY_F6), KEY_F7(118, "F7",
			Key.KEY_F7), KEY_F8(119, "F8", Key.KEY_F8), KEY_F9(120, "F9",
			Key.KEY_F9), KEY_F10(121, "F10", Key.KEY_F10), KEY_F11(122, "F11",
			Key.KEY_F11), KEY_F12(123, "F12", Key.KEY_F12),

	KEY_NUM_LOCK(144, "Num Lock", Key.KEY_NUM_LOCK), KEY_SCR_LOCK(145,
			"Scroll Lock", Key.KEY_SCR_LOCK),

	KEY_SEMICOLON(186, ";", Key.KEY_SEMICOLON), KEY_EQUAL(187, "=",
			Key.KEY_EQUAL), KEY_COMMA(188, ",", Key.KEY_COMMA), KEY_MINUS(189,
			"-", Key.KEY_MINUS), KEY_FULLSTOP(190, ".", Key.KEY_FULLSTOP), KEY_SLASH(
			191, "/", Key.KEY_SLASH), KEY_GRAVE_ACCENT(192, "`",
			Key.KEY_GRAVE_ACCENT), KEY_SQ_BRACKET_OPEN(219, "[",
			Key.KEY_SQ_BRACKET_OPEN), KEY_BACKSLASH(220, "\\",
			Key.KEY_BACKSLASH), KEY_SQ_BRACKET_CLOSE(221, "]",
			Key.KEY_SQ_BRACKET_CLOSE), KEY_APOSTROPHE(222, "'",
			Key.KEY_APOSTROPHE),

	KEY_BACKSLASH_ADDITIONAL(226, "\\", Key.KEY_BACKSLASH_ADDITIONAL);

	public static NativeKey getByNativeCode(Integer nativeCode) {
		NativeKey ret = nativeCodeMap.get(nativeCode);
		if (ret == null) {
			throw new IllegalNativeCode(String.valueOf(nativeCode));
		}
		return ret;
	}

	private static void putKeyToMap(Integer nativeCode, NativeKey NativeKey2) {
		if (nativeCodeMap == null) {
			nativeCodeMap = new HashMap<Integer, NativeKey>();
		}
		nativeCodeMap.put(nativeCode, NativeKey2);
	}

	public static Map<Integer, NativeKey> nativeCodeMap;
	private int nativeCode;
	private Key[] keys;

	private String textualNotation;

	private NativeKey(int nativeCode, String text, Key... keys) {
		this.nativeCode = nativeCode;
		this.keys = keys;
		this.textualNotation = text;
		putKeyToMap(Integer.valueOf(nativeCode), this);
	}

	public Key[] getKeys() {
		return keys;
	}

	public int getNativeCode() {
		return nativeCode;
	}

	public boolean isModificator() {
		return (this == KEY_SHIFT || this == KEY_CTRL || this == KEY_ALT);
	}

	public String toText() {
		return textualNotation;
	}

}
