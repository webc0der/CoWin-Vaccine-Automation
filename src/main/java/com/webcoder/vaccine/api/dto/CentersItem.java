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
public class CentersItem{

	@JsonProperty("pincode")
	private int pincode;

	@JsonProperty("sessions")
	private List<SessionsItem> sessions;

	@JsonProperty("address")
	private String address;

	@JsonProperty("fee_type")
	private String feeType;

	@JsonProperty("long")
	private int jsonMemberLong;

	@JsonProperty("district_name")
	private String districtName;

	@JsonProperty("block_name")
	private String blockName;

	@JsonProperty("center_id")
	private int centerId;

	@JsonProperty("state_name")
	private String stateName;

	@JsonProperty("name")
	private String name;

	@JsonProperty("from")
	private String from;

	@JsonProperty("to")
	private String to;

	@JsonProperty("lat")
	private int lat;

	@JsonProperty("vaccine_fees")
	private List<VaccineFeesItem> vaccineFees;
}