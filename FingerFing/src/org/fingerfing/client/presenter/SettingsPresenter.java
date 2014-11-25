package org.fingerfing.client.presenter;

import java.util.Arrays;
import java.util.List;

import org.fingerfing.client.Settings;
import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.presenter.event.ExerciseDescriptorChangeEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyEvent;
import org.fingerfing.client.presenter.event.ExerciseDescriptorModifyHandler;
import org.fingerfing.client.presenter.event.KeyboardDescriptorChangeEvent;
import org.fingerfing.client.resource.ExerciseResource;
import org.fingerfing.client.resource.KeyboardResource;
import org.fingerfing.client.view.KeyboardDescriptor;
import org.fingerfing.client.view.SettingsView;

import com.google.gwt.event.shared.EventBus;

public class SettingsPresenter implements ExerciseDescriptorModifyHandler {

	private SettingsView settingsView;
	private ExerciseDescriptorLoader exDescLoader;
	private EventBus eventBus;
	private DescriptorManager dm = new DescriptorManager();

	private class ExerciseDescriptorLoader {

		private ExerciseResource er = ExerciseResource.INST;
		private List<String> descriptorNameList = Arrays.asList(
				"ExerciseDescriptor 1", "ExerciseDescriptor 2");

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
				throw new PresenterLevelException("Descriptor not found");
			}
		}
	}

	public SettingsPresenter(SettingsView settingsView, EventBus eventBus) {
		this.settingsView = settingsView;
		this.settingsView.setPresenter(this);
		this.eventBus = eventBus;

		this.exDescLoader = new ExerciseDescriptorLoader();
		this.settingsView.setExerciseDescriptorNameList(exDescLoader
				.getDescriptorNameList());
		this.settingsView.setKeyboardDescriptorNameList(Arrays.asList("Keyboard 1","Keyboard 2"));
		this.settingsView.setKeyboardGeneralLabelDescriptorNameList(Arrays.asList("Label RU","Label EN"));
		this.settingsView.setKeyboardAlternativeLabelDescriptorNameList(Arrays.asList("Label RU","Label EN"));

		Settings.exerciseDescriptor = exDescLoader.loadExerciseDescriptor(0);
		Settings.keyboardDescriptor = loadKeyboardDescriptor(0);
	}

	public void onSelectExerciseDescriptorIndex(int selectedIndex) {
		Settings.exerciseDescriptor = exDescLoader
				.loadExerciseDescriptor(selectedIndex);
		eventBus.fireEventFromSource(new ExerciseDescriptorChangeEvent(), this);
	}

	@Override
	public void onExerciseDescriptorModify(ExerciseDescriptorModifyEvent event) {
		settingsView.resetExerciseDescriptorSelector();
	}

	public void onSelectKeyboardDescriptorIndex(int selectedIndex) {
		Settings.keyboardDescriptor = loadKeyboardDescriptor(selectedIndex);
		eventBus.fireEventFromSource(new KeyboardDescriptorChangeEvent(), this);
	}

	private KeyboardDescriptor loadKeyboardDescriptor(int selectedIndex) {
		switch (selectedIndex) {
		case 0:
			 return dm.decodeFromJson(
					KeyboardDescriptor.class, KeyboardResource.INST
							.getKeyboardDescriptor1().getText());
		case 1:
			return dm.decodeFromJson(
					KeyboardDescriptor.class, KeyboardResource.INST
							.getKeyboardDescriptor2().getText());
		}
		return null;
	}

	public void onSelectKeyboardGeneralLabelDescriptorIndex(int selectedIndex) {
		// TODO Auto-generated method stub

	}

	public void onSelectKeyboardAlternativeLabelDescriptorIndex(
			int selectedIndex) {
		// TODO Auto-generated method stub

	}

}
