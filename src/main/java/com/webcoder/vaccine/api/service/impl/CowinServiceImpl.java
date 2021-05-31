package com.webcoder.vaccine.api.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.webcoder.vaccine.api.commons.Constants;
import com.webcoder.vaccine.api.commons.DateUtil;
import com.webcoder.vaccine.api.commons.StringUtil;
import com.webcoder.vaccine.api.dto.CentersItem;
import com.webcoder.vaccine.api.dto.SessionsItem;
import com.webcoder.vaccine.api.dto.SlotsResponse;
import com.webcoder.vaccine.api.intigration.CowinApiIntigration;
import com.webcoder.vaccine.api.service.AlertService;
import com.webcoder.vaccine.api.service.CowinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class CowinServiceImpl implements CowinService {

    @Autowired
    DateUtil dateUtil;

    @Autowired
    StringUtil stringUtil;

    @Autowired
    CowinApiIntigration cowinApiIntigration;

    @Autowired
    AlertService alertService;

    @Override
    public SlotsResponse getSlots(String authToken, String districtId) throws Exception {
        log.info("getSlots | requesting for slots");
        String requestDate = dateUtil.getDate(1);
        String userAgent = stringUtil.getRandomElementFromStringArray(Constants.USER_AGENTS);
        SlotsResponse slotsResponse = null;
        try {
            slotsResponse = cowinApiIntigration.getSlots(Constants.VACCINE_SLOT_URL, authToken, requestDate, districtId, Constants.COVAXIN, userAgent);
            log.info("getSlots | slots retrieved successfully | data: {}", slotsResponse);
        } catch (Exception e) {
            log.info("getSlots | Exception Occurred while fetching slots | cause: {} | message: {}", e.getCause(), e.getMessage());
            throw e;
        }
        return slotsResponse;
    }

    @Override
    public SessionsItem findSlots(SlotsResponse slotsResponse) {
        log.info("findSlots | started checking slots availability");
        SessionsItem sessionsItem = null;

        if (!CollectionUtils.isEmpty(slotsResponse.getCenters())) {
            log.info("findSlots | center size: {} |centers: {}", slotsResponse.getCenters().size(), slotsResponse.getCenters());

            for (CentersItem centersItem : slotsResponse.getCenters()) {
                log.info("findSlots | center Id: {} ", centersItem.getCenterId());

                if (!CollectionUtils.isEmpty(centersItem.getSessions())) {
                    log.info("findSlots | session size: {} |sessions: {}", centersItem.getSessions().size(), centersItem.getSessions());

                    for (SessionsItem session : centersItem.getSessions()) {
                        log.info("findSlots | session id: {}", session.getSessionId());

                        if (session.getAvailableCapacityDose1() > 0 && session.getMinAgeLimit() == 18) {
                            sessionsItem = session;

                            new Thread(() -> {
                                log.info("findSlots | slot found | center name: {} | region: {} |session id: {} | date: {} | age: {} | capacity dose-1: {}", centersItem.getName(), centersItem.getDistrictName(), session.getSessionId(), session.getDate(), session.getMinAgeLimit(), session.getAvailableCapacityDose1());
                                alertService.mailAlert(Constants.SLOT_FOUND_SUBJECT, getBodyWithCenterDetails(session, centersItem));
                            }).start();

                            break;
                        } else {
                            log.info("findSlots | no 1st dose or slots for 18+ | keep trying");
                        }
                    }
                    if (!ObjectUtils.isEmpty(sessionsItem)) {
                        break;
                    }
                } else {
                    log.info("findSlots | empty sessions | no session available");
                }
            }
        } else {
            log.info("findSlots | received empty centers");
            return null;
        }
        return sessionsItem;
    }

    private String getBodyWithCenterDetails(SessionsItem session, CentersItem centersItem) {
        return Constants.SLOT_FOUND_BODY_WITH_DETAILS.replace("%center%", centersItem.getName())
                .replace("%region%", centersItem.getDistrictName())
                .replace("%session%", session.getSessionId());
    }

    @Override
    public String sendAlertAndBookSlot(SessionsItem sessionsItem, String authToken) {
        log.info("sendAlertAndBookSlot | sending slot available email alert");
        String bookingId = null;

        try {
            String slotBookingPayload = getSlotPayload(sessionsItem.getSessionId(), sessionsItem.getSlots().get(1));
            String userAgent = stringUtil.getRandomElementFromStringArray(Constants.USER_AGENTS);

            log.info("sendAlertAndBookSlot | sending slot booking request");
            JsonNode response = cowinApiIntigration.bookSlot(Constants.VACCINE_SLOT_BOOK_URL, slotBookingPayload, authToken, userAgent);

            if (response.has("appointment_id")) {
                log.info("sendAlertAndBookSlot | Congrats your slot is booked | booking id: {}", response.get("appointment_id").asText());
                bookingId = response.get("appointment_id").asText();
            }
        } catch (Exception e) {
            log.info("sendAlertAndBookSlot | Exception Occurred while booking slot | cause: {} | message: {}", e.getCause(), e.getMessage());
        }
        return bookingId;
    }

    public String getSlotPayload(String session_id, String slot) throws JSONException {
        JSONObject requestObject = new JSONObject();
        requestObject.put("dose", 1);
        requestObject.put("session_id", session_id);
        requestObject.put("slot", slot);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(0, Constants.USER_BENEFICIARY);
        requestObject.put("beneficiaries", jsonArray);

        return requestObject.toString();
    }
}
