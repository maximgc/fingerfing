package org.fingerfing.client;

import org.fingerfing.client.controller.MainController;
import org.fingerfing.client.widget.MainWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * NativeKey
 * Element, Attempt
 * ExerciseDescriptorImpl
 * Exercise
 * 
 */
public class FingerFing implements EntryPoint {

	public void onModuleLoad() {
		
		MainWidget mw = new MainWidget();
		MainController mc = new MainController(mw);

		RootPanel.get("mainArea").add(mw);
		mc.start();

	}
}
