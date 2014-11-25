package org.fingerfing.client;

import org.fingerfing.client.presenter.CourseDesignerPresenter;
import org.fingerfing.client.presenter.MainPresenter;
import org.fingerfing.client.presenter.SettingsPresenter;
import org.fingerfing.client.presenter.TrainPresenter;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEvent;
import org.fingerfing.client.view.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 */
public class FingerFing implements EntryPoint {

	int i = 0;

	public void onModuleLoad() {

		final MainView mv = new MainView();
		RootPanel.get("mainArea").add(mv);
		
		final EventBus eventBus = new SimpleEventBus();
		final FlowController flowController = new FlowController(eventBus);

		final MainPresenter mp = new MainPresenter(mv, eventBus);
		final TrainPresenter tp = new TrainPresenter(mv.getTrainView(), eventBus);
		final CourseDesignerPresenter dp = new CourseDesignerPresenter(mv.getCourseDesignerView(), eventBus);
		final SettingsPresenter sp = new SettingsPresenter(mv.getSettingsView(), eventBus);
		
		eventBus.addHandler(ExerciseDescriptorChangeEvent.TYPE, tp);
		eventBus.addHandler(ExerciseDescriptorChangeEvent.TYPE, dp);
		
		eventBus.addHandler(ExerciseDescriptorModifyEvent.TYPE, sp);
		
		eventBus.addHandler(ActionChangeEvent.TYPE, mp);
		eventBus.addHandler(ActionChangeEvent.TYPE, tp);
		eventBus.addHandler(ActionChangeEvent.TYPE, dp);
		eventBus.addHandler(ActionChangeEvent.TYPE, flowController);

		History.addValueChangeHandler(flowController);
		History.fireCurrentHistoryState();
	}
}
