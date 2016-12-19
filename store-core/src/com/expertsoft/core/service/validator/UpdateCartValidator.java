package com.expertsoft.core.service.validator;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.expertsoft.core.service.component.UpdateCartForm;

@Component
public class UpdateCartValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateCartForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors paramErrors) {
		UpdateCartForm form = (UpdateCartForm) target;
		for (Map.Entry<String, String> entry : form.getItems().entrySet()) {
			String path = "items[" + entry.getKey() + "]";
			try {
				Integer quantity = Integer.valueOf(entry.getValue());
				if (quantity < 1) {
					paramErrors.rejectValue(path, "cart.quantity.notPositive");
				}
			} catch (NumberFormatException e) {
				paramErrors.rejectValue(path, "cart.quantity.invalid");
			}
		}
	}

}
