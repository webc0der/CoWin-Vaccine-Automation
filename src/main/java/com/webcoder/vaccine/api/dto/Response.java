package com.webcoder.vaccine.api.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("districts")
	private List<DistrictsItem> districts;

	@JsonProperty("ttl")
	private int ttl;
}