package org.fingerfing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FingerFing implements EntryPoint {

	public void onModuleLoad() {

		RootPanel.get("mainArea").add(new Button("Click Me!"));

	}
}
