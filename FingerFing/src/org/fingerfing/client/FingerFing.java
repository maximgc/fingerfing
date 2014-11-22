package org.fingerfing.client;

import org.fingerfing.client.presenter.MainPresenter;
import org.fingerfing.client.widget.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * Key
 * Element, Attempt
 * ExerciseDescriptorImpl
 * Exercise
 * 
 */
public class FingerFing implements EntryPoint {

	public void onModuleLoad() {
		
		MainView mw = new MainView();
		MainPresenter mc = new MainPresenter(mw);

		RootPanel.get("mainArea").add(mw);
		mc.start();

	}
}
