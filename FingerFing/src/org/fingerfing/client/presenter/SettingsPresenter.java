package org.fingerfing.client.presenter;

import org.fingerfing.client.Settings;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.view.SettingsView;

import com.google.gwt.event.shared.EventBus;

public class SettingsPresenter {
	
	private SettingsView settingsView;
	private ExerciseDescriptorLoader exDescLoader;
	private EventBus eventBus;

	public SettingsPresenter(SettingsView settingsView, EventBus eventBus) {
		this.settingsView = settingsView;
		settingsView.setPresenter(this);
		this.eventBus = eventBus;
		this.exDescLoader = new ExerciseDescriptorLoader();
		this.settingsView.setExerciseDescriptorNameList(exDescLoader.getDescriptorNameList());
		
		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(0);
	}

	public void onSelectExerciseDescriptorName(int selectedIndex) {
		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(selectedIndex);
		eventBus.fireEvent(new ExerciseDescriptorChangeEvent());
	}

}
