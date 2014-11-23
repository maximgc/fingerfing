package org.fingerfing.client;

import org.fingerfing.client.presenter.CourseDesignerPresenter;
import org.fingerfing.client.presenter.ExerciseDescriptorLoader;
import org.fingerfing.client.presenter.MainPresenter;
import org.fingerfing.client.presenter.TrainPresenter;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.view.MainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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

	private ExerciseDescriptorLoader exDescLoader;

	public void onModuleLoad() {


		EventBus eventBus = new SimpleEventBus();
		
		final MainView mv = new MainView();
		final MainPresenter mp = new MainPresenter(mv);
		final TrainPresenter tp;
		final CourseDesignerPresenter dp;

		tp = new TrainPresenter(mv.getTrainView());
		dp = new CourseDesignerPresenter(mv.getCourseDesignerView());
		RootPanel.get("mainArea").add(mv);
		
		
		eventBus.addHandler(ExerciseDescriptorChangeEvent.TYPE, tp);



		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				switch (event.getValue()) {
				case "train":
					mp.switchTab(0);
					tp.start();
					break;
				case "courseDesign":
					mp.switchTab(1);
					dp.start();
					break;
				case "keyboardDesign":
					mp.switchTab(2);
					break;
				default:
					History.newItem("train");
					break;
				}
			}
		});
		
		
		// TEMP
		this.exDescLoader = new ExerciseDescriptorLoader();
		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(0);
		eventBus.fireEvent(new ExerciseDescriptorChangeEvent());
		History.fireCurrentHistoryState();
	}
}
