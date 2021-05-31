package com.webcoder.vaccine.api.intigration;

import com.fasterxml.jackson.databind.JsonNode;
import com.webcoder.vaccine.api.dto.SlotsResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface CowinApiIntigration {

    SlotsResponse getSlots (String url, String authToken, String date, String districtId, String vaccine, String userAgent) throws Exception;

    JsonNode bookSlot (String url, String payload, String authToken, String userAgent) throws Exception;

    String getOTP () throws JSONException;

    String validateOTP (String otp, String txnId) throws JSONException;
}
