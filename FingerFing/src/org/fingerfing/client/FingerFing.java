package org.fingerfing.client;

import org.fingerfing.client.presenter.CourseDesignerPresenter;
import org.fingerfing.client.presenter.MainPresenter;
import org.fingerfing.client.presenter.SettingsPresenter;
import org.fingerfing.client.presenter.TrainPresenter;
import org.fingerfing.client.presenter.event.ActionChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEvent;
import org.fingerfing.client.presenter.event.KeyboardDescriptorChangeEvent;
import org.fingerfing.client.view.KeyboardDescriptor;
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

	public void onModuleLoad() {

		final MainView mv = new MainView();
		RootPanel.get("mainArea").add(mv);
		
		final EventBus eventBus = new SimpleEventBus();
		final FlowController flowController = new FlowController(eventBus);

		final SettingsPresenter settingsPresenter = new SettingsPresenter(mv.getSettingsView(), eventBus);
		final MainPresenter mainPresenter = new MainPresenter(mv, eventBus);
		final TrainPresenter trainPresenter = new TrainPresenter(mv.getTrainView(), eventBus);
		final CourseDesignerPresenter courseDesignerPresenter = new CourseDesignerPresenter(mv.getCourseDesignerView(), eventBus);
		
		eventBus.addHandler(ExerciseDescriptorChangeEvent.TYPE, trainPresenter);
		eventBus.addHandler(ExerciseDescriptorChangeEvent.TYPE, courseDesignerPresenter);
		
		eventBus.addHandler(ExerciseDescriptorModifyEvent.TYPE, settingsPresenter);
		
		eventBus.addHandler(ActionChangeEvent.TYPE, mainPresenter);
		eventBus.addHandler(ActionChangeEvent.TYPE, trainPresenter);
		eventBus.addHandler(ActionChangeEvent.TYPE, courseDesignerPresenter);
		eventBus.addHandler(ActionChangeEvent.TYPE, flowController);
		
		eventBus.addHandler(KeyboardDescriptorChangeEvent.TYPE, trainPresenter);

		History.addValueChangeHandler(flowController);
		History.fireCurrentHistoryState();
	}
}
