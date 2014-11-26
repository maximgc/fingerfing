package org.fingerfing.client.view;

import java.util.List;

import org.fingerfing.client.presenter.SettingsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class SettingsView extends Composite {
	
	interface SettingsViewUiBinder extends UiBinder<Widget, SettingsView> {
	}

	private static SettingsViewUiBinder uiBinder = GWT
			.create(SettingsViewUiBinder.class);
	@UiField
	ListBox exerciseDescriptorSelector;
	@UiField
	ListBox keyboardDescriptorSelector;
	@UiField
	ListBox keyboardGeneralLabelDescriptorSelector;
	@UiField
	ListBox keyboardAlternativeLabelDescriptorSelector;
	private SettingsPresenter presenter;

	public SettingsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("exerciseDescriptorSelector")
	void onExerciseDescriptorSelectorChange(ChangeEvent event) {
		presenter.onSelectExerciseDescriptorIndex(exerciseDescriptorSelector
				.getSelectedIndex());
	}

	@UiHandler("keyboardAlternativeLabelDescriptorSelector")
	void onKeyboardAlternativeLabelDescriptorSelectorChange(ChangeEvent event) {
		presenter
				.onSelectKeyboardAlternativeLabelDescriptorIndex(keyboardAlternativeLabelDescriptorSelector
						.getSelectedIndex());
	}

	@UiHandler("keyboardDescriptorSelector")
	void onKeyboardDescriptorSelectorChange(ChangeEvent event) {
		presenter.onSelectKeyboardDescriptorIndex(keyboardDescriptorSelector
				.getSelectedIndex());
	}

	@UiHandler("keyboardGeneralLabelDescriptorSelector")
	void onKeyboardGeneralLabelDescriptorSelectorChange(ChangeEvent event) {
		presenter
				.onSelectKeyboardGeneralLabelDescriptorIndex(keyboardGeneralLabelDescriptorSelector
						.getSelectedIndex());
	}

	public void resetExerciseDescriptorSelector() {
		exerciseDescriptorSelector.setSelectedIndex(-1);
	}

	public void resetKeyboardAlternativeLabelDescriptorSelector() {
		keyboardAlternativeLabelDescriptorSelector.setSelectedIndex(-1);
	}

	public void resetKeyboardDescriptorSelector() {
		keyboardDescriptorSelector.setSelectedIndex(-1);
	}

	public void resetKeyboardGeneralLabelDescriptorSelector() {
		keyboardGeneralLabelDescriptorSelector.setSelectedIndex(-1);
	}

	public void setExerciseDescriptorNameList(List<String> nameList) {
		for (String ex : nameList) {
			exerciseDescriptorSelector.addItem(ex);
		}
	}

	public void setKeyboardAlternativeLabelDescriptorNameList(
			List<String> nameList, int selectIndex) {
		for (String ex : nameList) {
			keyboardAlternativeLabelDescriptorSelector.addItem(ex);
		}
		keyboardAlternativeLabelDescriptorSelector.setSelectedIndex(selectIndex);
	}

	public void setKeyboardDescriptorNameList(List<String> nameList) {
		for (String ex : nameList) {
			keyboardDescriptorSelector.addItem(ex);
		}
	}

	public void setKeyboardGeneralLabelDescriptorNameList(List<String> nameList, int selectIndex) {
		for (String ex : nameList) {
			keyboardGeneralLabelDescriptorSelector.addItem(ex);
		}
		keyboardGeneralLabelDescriptorSelector.setSelectedIndex(selectIndex);
	}

	public void setPresenter(SettingsPresenter presenter) {
		this.presenter = presenter;
	}
}
