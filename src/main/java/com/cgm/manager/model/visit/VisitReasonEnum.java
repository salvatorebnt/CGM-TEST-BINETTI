package com.cgm.manager.model.visit;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum containing all possible input values for the visit reason field:
 * First visit
 * Recurring visit
 * Urgent
 * @author salvatore.binetti
 *
 */
public enum VisitReasonEnum {
	FIRST_VISIT("First visit"),
	RECURRING_VISIT("Recurring visit"), 
	URGENT("Urgent");
	
	private String value;
	
	VisitReasonEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Stream<VisitReasonEnum> stream() {
        return Stream.of(VisitReasonEnum.values()); 
    }
	
	public static Set<String> getValues(){
		return stream()
				.map(x -> x.getValue())
				.collect(Collectors.toSet());
	}
}
