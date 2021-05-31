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
public class VaccineFeesItem{

	@JsonProperty("vaccine")
	private String vaccine;

	@JsonProperty("fee")
	private String fee;
}