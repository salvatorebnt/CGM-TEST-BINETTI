package com.cgm.manager.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cgm.manager.model.visit.VisitReasonEnum;
import com.cgm.manager.model.visit.VisitTypeEnum;

public class ValidEnumValidator implements ConstraintValidator<ValidValueOfEnum, CharSequence> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(ValidValueOfEnum annotation) {
    	Class<? extends Enum<?>> e = annotation.enumClass();
    	Set<String> values = new HashSet<>();
    	if(e.getName().equals(VisitReasonEnum.class.getName())) {
    		values = VisitReasonEnum.getValues();
    	}else if(e.getName().equals(VisitTypeEnum.class.getName())) {
    		values = VisitTypeEnum.getValues();
    	}
        acceptedValues = values;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}