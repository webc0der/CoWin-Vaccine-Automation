package com.webcoder.vaccine.api.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionsItem{

	@JsonProperty("date")
	private String date;

	@JsonProperty("vaccine")
	private String vaccine;

	@JsonProperty("slots")
	private List<String> slots;

	@JsonProperty("min_age_limit")
	private int minAgeLimit;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("available_capacity")
	private int availableCapacity;

	@JsonProperty("available_capacity_dose2")
	private int availableCapacityDose2;

	@JsonProperty("available_capacity_dose1")
	private int availableCapacityDose1;
}