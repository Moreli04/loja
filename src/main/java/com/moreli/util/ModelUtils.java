package com.moreli.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public final class ModelUtils {
	private static final ModelMapper modelMapper = new ModelMapper();

	private ModelUtils() {
	}

	public static <T> List<T> convertModelList(List<? extends Object> modelList, Class<T> destinationType) {
		validarModelListAndDestinationType(modelList, destinationType);

		if (modelList.isEmpty()) {
			return new ArrayList<>();
		}

		return modelList.stream().map(model -> modelMapper.map(model, destinationType)).collect(Collectors.toList());
	}

	public static <T> T convertModel(Object model, Class<T> destinationType) {
		if (model == null || destinationType == null) {
			return null;
		}
		return modelMapper.map(model, destinationType);
	}

	private static <T> void validarModelListAndDestinationType(List<? extends Object> modelList,
			Class<T> destinationType) {

		if (modelList == null || destinationType == null) {
			throw new IllegalArgumentException();
		}
	}

}
