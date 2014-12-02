package org.fingerfing.client.view.widget.event;

import java.util.ArrayList;
import java.util.List;

public class HandlerManager<H extends Handler>{

	List<H> handlers = new ArrayList<H>();

	public void addHandler(H h) {
		handlers.add(h);
	}

	public void fireEvent(Event<H> e) {
		for (H h : handlers) {
			e.dispatch(h);
		}
	}
}