package org.fingerfing.client.view.widget.event;

public abstract class Event<H extends Handler> {
	
	public abstract void dispatch(H handler); 

}