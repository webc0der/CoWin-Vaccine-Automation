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
public class DistrictsItem{

	@JsonProperty("district_name")
	private String districtName;

	@JsonProperty("district_id")
	private int districtId;
}