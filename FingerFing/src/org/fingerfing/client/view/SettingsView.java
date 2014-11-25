package org.fingerfing.client.view;

import java.util.List;

import org.fingerfing.client.presenter.SettingsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class SettingsView extends Composite {

	private static SettingsViewUiBinder uiBinder = GWT
			.create(SettingsViewUiBinder.class);

	@UiField
	ListBox exerciseDescriptorSelector;
	
	private SettingsPresenter presenter;

	public void setPresenter(SettingsPresenter presenter) {
		this.presenter = presenter;
	}

	interface SettingsViewUiBinder extends UiBinder<Widget, SettingsView> {
	}

	public SettingsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setExerciseDescriptorNameList(List<String> exerciseDescriptorNameList) {
		for (String ex : exerciseDescriptorNameList) {
			exerciseDescriptorSelector.addItem(ex);
		}
	}

	@UiHandler("exerciseDescriptorSelector")
	void onExerciseDescriptorSelectorChange(ChangeEvent event) {
		assert(presenter!=null) : "SettingsView.onExerciseDescriptorSelectorChange: presenter not set";
		presenter.onSelectExerciseDescriptorName(exerciseDescriptorSelector.getSelectedIndex());
	}

	public void resetExerciseDescriptorSelector() {
		exerciseDescriptorSelector.setSelectedIndex(-1);
	}
}
