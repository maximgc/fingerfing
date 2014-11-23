package org.fingerfing.client.presenter;

import java.util.Arrays;
import java.util.List;

import org.fingerfing.client.domain.ExerciseDescriptor;
import org.fingerfing.client.json.DescriptorManager;
import org.fingerfing.client.resource.ExerciseResource;

public class ExerciseDescriptorLoader {

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