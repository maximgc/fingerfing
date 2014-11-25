package org.fingerfing.client.presenter;

import java.util.Arrays;

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
import org.fingerfing.client.view.KeyboardLabelDescriptor;
import org.fingerfing.client.view.SettingsView;

import com.google.gwt.event.shared.EventBus;

public class SettingsPresenter implements ExerciseDescriptorModifyHandler {

	private SettingsView settingsView;
	private EventBus eventBus;
	private DescriptorManager dm = new DescriptorManager();

	public SettingsPresenter(SettingsView settingsView, EventBus eventBus) {
		this.settingsView = settingsView;
		this.settingsView.setPresenter(this);
		this.eventBus = eventBus;

		this.settingsView.setExerciseDescriptorNameList(Arrays.asList(
				"ExerciseDescriptor 1", "ExerciseDescriptor 2"));
		this.settingsView.setKeyboardDescriptorNameList(Arrays.asList(
				"Keyboard 1", "Keyboard 2"));
		this.settingsView.setKeyboardGeneralLabelDescriptorNameList(Arrays
				.asList("Label RU", "Label EN"));
		this.settingsView.setKeyboardAlternativeLabelDescriptorNameList(Arrays
				.asList("Label RU", "Label EN"));

		Settings.exerciseDescriptor = loadExerciseDescriptor(0);
		Settings.keyboardDescriptor = loadKeyboardDescriptor(0);
		Settings.keyboardGeneralLabelDescriptor = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorEN().getText());
		Settings.keyboardAlternativeLabelDescriptor = dm.decodeFromJson(
				KeyboardLabelDescriptor.class, KeyboardResource.INST
						.getKeyboardLabelDescriptorRU().getText());
	}

	@Override
	public void onExerciseDescriptorModify(ExerciseDescriptorModifyEvent event) {
		settingsView.resetExerciseDescriptorSelector();
	}

	public void onSelectExerciseDescriptorIndex(int selectedIndex) {
		Settings.exerciseDescriptor = loadExerciseDescriptor(selectedIndex);
		eventBus.fireEventFromSource(new ExerciseDescriptorChangeEvent(), this);
	}

	public void onSelectKeyboardAlternativeLabelDescriptorIndex(
			int selectedIndex) {
		switch (selectedIndex) {
		case 0:
			Settings.keyboardAlternativeLabelDescriptor = dm.decodeFromJson(
					KeyboardLabelDescriptor.class, KeyboardResource.INST
							.getKeyboardLabelDescriptorRU().getText());
			break;
		case 1:
			Settings.keyboardAlternativeLabelDescriptor = dm.decodeFromJson(
					KeyboardLabelDescriptor.class, KeyboardResource.INST
							.getKeyboardLabelDescriptorEN().getText());
			break;
		}
		eventBus.fireEventFromSource(new KeyboardDescriptorChangeEvent(), this);
	}

	public void onSelectKeyboardDescriptorIndex(int selectedIndex) {
		Settings.keyboardDescriptor = loadKeyboardDescriptor(selectedIndex);
		eventBus.fireEventFromSource(new KeyboardDescriptorChangeEvent(), this);
	}

	public void onSelectKeyboardGeneralLabelDescriptorIndex(int selectedIndex) {
		switch (selectedIndex) {
		case 0:
			Settings.keyboardGeneralLabelDescriptor = dm.decodeFromJson(
					KeyboardLabelDescriptor.class, KeyboardResource.INST
							.getKeyboardLabelDescriptorRU().getText());
			break;
		case 1:
			Settings.keyboardGeneralLabelDescriptor = dm.decodeFromJson(
					KeyboardLabelDescriptor.class, KeyboardResource.INST
							.getKeyboardLabelDescriptorEN().getText());
			break;
		}
		eventBus.fireEventFromSource(new KeyboardDescriptorChangeEvent(), this);
	}

	private ExerciseDescriptor loadExerciseDescriptor(int index) {
		switch (index) {
		case 0:
			return dm.decodeFromJson(ExerciseDescriptor.class,
					ExerciseResource.INST.getJsonExerciseDescriptor1()
							.getText());
		case 1:
			return dm.decodeFromJson(ExerciseDescriptor.class,
					ExerciseResource.INST.getJsonExerciseDescriptor2()
							.getText());
		default:
			throw new PresenterLevelException("Descriptor not found");
		}
	}

	private KeyboardDescriptor loadKeyboardDescriptor(int index) {
		switch (index) {
		case 0:
			return dm.decodeFromJson(KeyboardDescriptor.class,
					KeyboardResource.INST.getKeyboardDescriptor1().getText());
		case 1:
			return dm.decodeFromJson(KeyboardDescriptor.class,
					KeyboardResource.INST.getKeyboardDescriptor2().getText());
		default:
			throw new PresenterLevelException("Descriptor not found");
		}
	}

}
