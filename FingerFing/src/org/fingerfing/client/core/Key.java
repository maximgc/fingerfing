package org.fingerfing.client.core;

public enum Key{

	KEY_BACKSPACE(8, "Backspace"), KEY_TAB(9, "Tab"), KEY_ENTER(13, "Enter"),

	KEY_SHIFT_RIGHT(16, "RShift"), KEY_CTRL_RIGHT(17, "RCtrl"), KEY_ALT_RIGHT(18, "RAlt"),
	KEY_SHIFT_LEFT(16, "LShift"), KEY_CTRL_LEFT(17, "LCtrl"), KEY_ALT_LEFT(18, "LAlt"),

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

	KEY_WIN_LEFT(91, "Win"), KEY_WIN_RIGHT(92, "Win"), KEY_MENU(93, "Menu"),

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


	private int nativeCode;
	private String textualNotation;

	private Key(int nativeCode, String text) {
		this.nativeCode = nativeCode;
		this.textualNotation = text;
	}

	public String toText() {
		return textualNotation;
	}
	
	public int getNativeCode() {
		return nativeCode;
	}

	public boolean isModificator() {
		return (this == KEY_SHIFT_RIGHT || this == KEY_CTRL_RIGHT || this == KEY_ALT_RIGHT ||
				this == KEY_SHIFT_LEFT || this == KEY_CTRL_LEFT || this == KEY_ALT_LEFT);
	}

}
