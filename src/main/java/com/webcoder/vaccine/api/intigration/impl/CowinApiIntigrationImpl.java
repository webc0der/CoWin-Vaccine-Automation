package com.webcoder.vaccine.api.intigration.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.webcoder.vaccine.api.commons.Constants;
import com.webcoder.vaccine.api.commons.StringUtil;
import com.webcoder.vaccine.api.dto.SlotsResponse;
import com.webcoder.vaccine.api.intigration.CowinApiIntigration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Component
public class CowinApiIntigrationImpl implements CowinApiIntigration {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    StringUtil stringUtil;


    @Override
    public SlotsResponse getSlots(String url, String authToken, String date, String districtId, String vaccine, String userAgent) throws Exception {
        String requestURL = url.replace("%DIST-ID%", districtId)
                .replace("%DATE-DD-MM-YYYY%", date)
                .replace("%VACCINE%", vaccine);
        ResponseEntity<SlotsResponse> responseEntity = null;
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(authToken);
            httpHeaders.setOrigin("https://selfregistration.cowin.gov.in");
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("user-agent", userAgent);
            httpHeaders.set("Host", "https://selfregistration.cowin.gov.in");

            HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
            traceRequest(requestURL, HttpMethod.GET, httpHeaders);
            responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, httpEntity, SlotsResponse.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.info("getSlots | Exception Occurred | code: {} | text: {}", e.getStatusCode(), e.getStatusText());
            switch (e.getStatusCode()) {
                case UNAUTHORIZED:

                case FORBIDDEN:
                    throw new Exception(Constants.UPDATE_USER_TOKEN);

                case TOO_MANY_REQUESTS:
                    throw new Exception(Constants.API_LIMIT_REACHED);

                default:
                    throw e;
            }
        } catch (Exception e) {
            log.info("getSlots | Exception Occurred | cause: {} | message: {}", e.getCause(), e.getMessage());
            throw e;
        }

    }

    @Override
    public JsonNode bookSlot(String url, String payload, String authToken, String userAgent) throws Exception {
        ResponseEntity<JsonNode> responseEntity = null;
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(authToken);
            httpHeaders.setOrigin("https://selfregistration.cowin.gov.in");
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("user-agent", userAgent);
            httpHeaders.set("Host", "https://selfregistration.cowin.gov.in");

            HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
            traceRequest(url, HttpMethod.POST, httpHeaders, payload);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JsonNode.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.info("bookSlot | Exception Occurred | cause: {} | message: {}", e.getCause(), e.getMessage());
            throw e;
        }
    }

    @Override
    public String getOTP() throws JSONException {
        ResponseEntity<JsonNode> responseEntity = null;
        JSONObject requestObject = new JSONObject();
        requestObject.put("secret", Constants.OTP_SECRETE);
        requestObject.put("mobile", Constants.USER_MOBILE);
        String payload = requestObject.toString();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setOrigin("https://selfregistration.cowin.gov.in");
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("user-agent", stringUtil.getRandomElementFromStringArray(Constants.USER_AGENTS));
            httpHeaders.set("Host", "https://selfregistration.cowin.gov.in");

            HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
            traceRequest(Constants.SEND_OPT_URL, HttpMethod.POST, httpHeaders, payload);
            responseEntity = restTemplate.exchange(Constants.SEND_OPT_URL, HttpMethod.POST, httpEntity, JsonNode.class);
            return responseEntity.getBody().has("txnId") ? responseEntity.getBody().get("txnId").asText() : null;
        } catch (Exception e) {
            log.info("getOTP | Exception Occurred | cause: {} | message: {}", e.getCause(), e.getMessage());
            return null;
        }
    }

    @Override
    public String validateOTP(String otp, String txnId) throws JSONException {
        ResponseEntity<JsonNode> responseEntity = null;
        JSONObject requestObject = new JSONObject();
        requestObject.put("otp", otp);
        requestObject.put("txnId", txnId);
        String payload = requestObject.toString();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setOrigin("https://selfregistration.cowin.gov.in");
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("user-agent", stringUtil.getRandomElementFromStringArray(Constants.USER_AGENTS));
            httpHeaders.set("Host", "https://selfregistration.cowin.gov.in");

            HttpEntity<String> httpEntity = new HttpEntity<>(payload, httpHeaders);
            traceRequest(Constants.VALIDATE_OPT_URL, HttpMethod.POST, httpHeaders, payload);
            responseEntity = restTemplate.exchange(Constants.VALIDATE_OPT_URL, HttpMethod.POST, httpEntity, JsonNode.class);
            return responseEntity.getBody().has("token") ? responseEntity.getBody().get("token").asText() : null;
        } catch (Exception e) {
            log.info("getOTP | Exception Occurred | cause: {} | message: {}", e.getCause(), e.getMessage());
            return null;
        }
    }

    private void traceRequest(String baseUrl, HttpMethod method, HttpHeaders headers, String body) throws IOException {

        log.info(
                "==========================request begin==============================================");
        log.info("URI                 : {}", baseUrl);
        log.info("Method            : {}", method);
        log.info("Headers         : {}", headers);
        log.info("Request body: {}", body.toString());
        log.info(
                "==========================request end================================================");
    }

    private void traceRequest(String baseUrl, HttpMethod method, HttpHeaders headers) throws IOException {

        log.info(
                "==========================request begin==============================================");
        log.info("URI                 : {}", baseUrl);
        log.info("Method            : {}", method);
        log.info("Headers         : {}", headers);
        log.info(
                "==========================request end================================================");
    }
}
