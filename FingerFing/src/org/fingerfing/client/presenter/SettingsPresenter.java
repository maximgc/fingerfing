package org.fingerfing.client.presenter;

import java.util.Arrays;
import java.util.List;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEventHandler;
import org.fingerfing.client.resource.ExerciseResource;
import org.fingerfing.client.view.SettingsView;

import com.google.gwt.event.shared.EventBus;

public class SettingsPresenter implements ExerciseDescriptorModifyEventHandler{
	
	private SettingsView settingsView;
	private ExerciseDescriptorLoader exDescLoader;
	private EventBus eventBus;

	private class ExerciseDescriptorLoader {

		private ExerciseResource er = ExerciseResource.INST;
		private DescriptorManager dm = new DescriptorManager();
		private List<String> descriptorNameList = Arrays.asList("ExerciseDescriptor 1",
				"ExerciseDescriptor 2");

		public List<String> getDescriptorNameList() {
			return descriptorNameList;
		}

		public ExerciseDescriptor loadExerciseDescriptor(int index) {
			switch (index) {
			case 0:
				return dm.decodeFromJson(ExerciseDescriptor.class, er
						.getJsonExerciseDescriptor1().getText());
			case 1:
				return dm.decodeFromJson(ExerciseDescriptor.class, er
						.getJsonExerciseDescriptor2().getText());
			default:
				throw new ClientException("Descriptor not found");
			}
		}
	}
	
	public SettingsPresenter(SettingsView settingsView, EventBus eventBus) {
		this.settingsView = settingsView;
		this.settingsView.setPresenter(this);
		this.eventBus = eventBus;

		this.exDescLoader = new ExerciseDescriptorLoader();
		this.settingsView.setExerciseDescriptorNameList(exDescLoader.getDescriptorNameList());
		
		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(0);
	}

	public void onSelectExerciseDescriptorName(int selectedIndex) {
		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(selectedIndex);
		eventBus.fireEventFromSource(new ExerciseDescriptorChangeEvent(), this);
	}

	@Override
	public void onExerciseDescriptorChange(ExerciseDescriptorModifyEvent event) {
		settingsView.resetExerciseDescriptorSelector();
	}

}
