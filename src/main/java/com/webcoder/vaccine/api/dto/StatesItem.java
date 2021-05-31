package com.webcoder.vaccine.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatesItem{

	@JsonProperty("state_name")
	private String stateName;

	@JsonProperty("state_id")
	private int stateId;
}