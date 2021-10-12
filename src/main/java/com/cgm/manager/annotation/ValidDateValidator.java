package com.cgm.manager.annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.base.Strings;

/**
* ConstraintValidator implementation. 
* This class implemet a string date validator based on the input format 
*
* @author salvatore.binetti
*/
public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {
	private Boolean isOptional;
	private String format;

    @Override
    public void initialize(ValidDate validDate) {
        this.isOptional = validDate.optional();
        this.format = validDate.format();
    }

    /**
     * Check if the string value is a date in the input format 
     * @param  value  a String value representing a date
     * @param  constraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean validDate = isValidFormat(this.format, value);
        return isOptional ? (validDate || (Strings.isNullOrEmpty(value))) : validDate;
    }

    private static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null){
                date = sdf.parse(value);
                if (!value.equals(sdf.format(date))) {
                    date = null;
                }
            }
        } catch (ParseException ex) {
        }
        return date != null;
    }
}