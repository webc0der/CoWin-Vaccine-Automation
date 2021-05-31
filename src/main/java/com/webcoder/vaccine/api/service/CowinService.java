package com.webcoder.vaccine.api.service;

import com.webcoder.vaccine.api.dto.SessionsItem;
import com.webcoder.vaccine.api.dto.SlotsResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface CowinService {

    SlotsResponse getSlots(String authToken, String districtId) throws Exception;

    SessionsItem findSlots(SlotsResponse slotsResponse);

    String sendAlertAndBookSlot(SessionsItem sessionsItem, String authToken);
}
