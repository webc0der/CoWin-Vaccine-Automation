package com.webcoder.vaccine.api.controller;

import com.webcoder.vaccine.api.commons.Constants;
import com.webcoder.vaccine.api.commons.EncoderDecoderUtil;
import com.webcoder.vaccine.api.intigration.CowinApiIntigration;
import com.webcoder.vaccine.api.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/cowin")
public class HardcodedController {

    @Autowired
    ProcessService processService;

    @Autowired
    CowinApiIntigration cowinApiIntigration;

    @Autowired
    EncoderDecoderUtil encoderDecoderUtil;

    @PostMapping(value = "/process")
    public void startProcess() throws Exception {
        log.info("REQUEST RECEIVED");
        processService.searchAndBookSlot();
        log.info("REQUEST COMPLETED");
    }

    @PostMapping(value = "/getAndValidateOTP")
    public void getAndValidateOTP() throws JSONException {
        log.info("REQUEST TO GET OTP");
        String txnId = cowinApiIntigration.getOTP();
        log.info("getAndValidateOTP | txnId: {}", txnId);

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter a OTP: ");
        String OTP= sc.nextLine();
        String encodedOTP = encoderDecoderUtil.encodeSHA256Hex(OTP);
        log.info("getAndValidateOTP | encoded OTP: {}", encodedOTP);

        String token = cowinApiIntigration.validateOTP(encodedOTP, txnId);
        log.info("getAndValidateOTP | auth token: {}", token);

        log.info("REQUEST TO GET OTP COMPLETED");
    }
}
