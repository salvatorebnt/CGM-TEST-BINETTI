package com.cgm.manager.model.visit;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum containing all possible input values for the visit type field:
 * Home
 * Office
 * @author salvatore.binetti
 *
 */
public enum VisitTypeEnum {
	HOME("Home"),
	OFFICE("Office");
	
	private String value;
	
	VisitTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public static Stream<VisitTypeEnum> stream() {
        return Stream.of(VisitTypeEnum.values()); 
    }
	
	public static Set<String> getValues(){
		return stream()
				.map(x -> x.getValue())
				.collect(Collectors.toSet());
	}
}
