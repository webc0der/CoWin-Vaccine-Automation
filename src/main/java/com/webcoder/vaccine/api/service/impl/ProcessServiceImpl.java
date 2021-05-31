package com.webcoder.vaccine.api.service.impl;

import com.webcoder.vaccine.api.commons.Constants;
import com.webcoder.vaccine.api.commons.StringUtil;
import com.webcoder.vaccine.api.dto.SessionsItem;
import com.webcoder.vaccine.api.dto.SlotsResponse;
import com.webcoder.vaccine.api.service.AlertService;
import com.webcoder.vaccine.api.service.CowinService;
import com.webcoder.vaccine.api.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    CowinService cowinService;

    @Autowired
    AlertService alertService;

    @Autowired
    StringUtil stringUtil;

    @Override
    public void searchAndBookSlot() {

        //BBMP Thread
        new Thread(() -> {

            SlotsResponse slotsResponse = null;
            SessionsItem sessionsItem = null;
            String bookingId;

            while (true) {

                try {
                    log.info("searchAndBookSlot | fetching slots");
                    slotsResponse = cowinService.getSlots(Constants.AUTH_TOKEN, Constants.BBMP);
                } catch (Exception e) {
                    log.info("searchAndBookSlot | Exception Occurred while fetching slots | cause: {} | message: {}", e.getCause(), e.getMessage());

                    if (e.getMessage().equals(Constants.UPDATE_USER_TOKEN)) {
                        log.info("searchAndBookSlot | breaking process | token expired | cause: {} | message: {}", e.getCause(), e.getMessage());
                        alertService.mailAlert(Constants.TOKEN_UPDATE_SUBJECT, Constants.TOKEN_UPDATE_BODY);
                    } else if (e.getMessage().equals(Constants.API_LIMIT_REACHED)) {
                        log.info("searchAndBookSlot | api hits limit reached | BBMP Thread sleeping for 6 mins | cause: {} | message: {}", e.getCause(), e.getMessage());

                        try {
                            Thread.sleep(360000);
                        } catch (InterruptedException interruptedException) {
                            log.info("searchAndBookSlot | Exception Occurred while BBMP Thread sleeping | cause: {} | message: {}", e.getCause(), e.getMessage());
                        }
                    }
                }

                if (!ObjectUtils.isEmpty(slotsResponse)) {
                    log.info("searchAndBookSlot | searching for available slots");
                    sessionsItem = cowinService.findSlots(slotsResponse);
                } else {
                    log.info("searchAndBookSlot | EMPTY SLOTS RESPONSE");
                }

                if (!ObjectUtils.isEmpty(sessionsItem)) {
                    log.info("SLOT FOUND SUCCESSFULLY IN BBMP REGION");
                    bookingId = cowinService.sendAlertAndBookSlot(sessionsItem, Constants.AUTH_TOKEN);
                    if (StringUtils.hasText(bookingId)) {
                        log.info("SLOT BOOKED SUCCESSFULLY IN BBMP REGION");
                        alertService.mailAlert(Constants.SLOT_BOOKED_SUBJECT, Constants.SLOT_BOOKED_BODY);
                        break;
                    } else {
                        log.info("SLOT BOOKED FAILED IN BBMP REGION");
                        alertService.mailAlert(Constants.SLOT_FOUND_SUBJECT_BOOKING_FAILED, Constants.SLOT_FOUND_BODY+"Session info: "+sessionsItem.getSessionId());
                    }
                } else {
                    try {
                        log.info("searchAndBookSlot | sleeping for random seconds before calling slots api");
                        Thread.sleep(Math.multiplyExact(Long.parseLong(stringUtil.getRandomElementFromStringArray(Constants.SECONDS)), 1000));
                    } catch (InterruptedException e) {
                        log.info("searchAndBookSlot | Exception Occurred while BBMP Thread sleeping | cause: {} | message: {}", e.getCause(), e.getMessage());

                    }
                }
            }
        }).start();

        //Bangalore Urban Thread
        new Thread(() -> {

            SlotsResponse slotsResponse = null;
            SessionsItem sessionsItem = null;
            String bookingId;

            while (true) {

                try {
                    log.info("searchAndBookSlot | fetching slots");
                    slotsResponse = cowinService.getSlots(Constants.AUTH_TOKEN, Constants.BANGALORE_URBAN);
                } catch (Exception e) {
                    log.info("searchAndBookSlot | Exception Occurred while fetching slots | cause: {} | message: {}", e.getCause(), e.getMessage());

                    if (e.getMessage().equals(Constants.UPDATE_USER_TOKEN)) {
                        log.info("searchAndBookSlot | breaking process | token expired | cause: {} | message: {}", e.getCause(), e.getMessage());
                        alertService.mailAlert(Constants.TOKEN_UPDATE_SUBJECT, Constants.TOKEN_UPDATE_BODY);
                    } else if (e.getMessage().equals(Constants.API_LIMIT_REACHED)) {
                        log.info("searchAndBookSlot | api hits limit reached | Bangalore Urban Thread sleeping for 6 mins | cause: {} | message: {}", e.getCause(), e.getMessage());

                        try {
                            Thread.sleep(360000);
                        } catch (InterruptedException interruptedException) {
                            log.info("searchAndBookSlot | Exception Occurred while sleeping Bangalore Urban Thread | cause: {} | message: {}", e.getCause(), e.getMessage());
                        }
                    }
                }

                if (!ObjectUtils.isEmpty(slotsResponse)) {
                    log.info("searchAndBookSlot | searching for available slots");
                    sessionsItem = cowinService.findSlots(slotsResponse);
                } else {
                    log.info("EMPTY SLOTS RESPONSE");
                }

                if (!ObjectUtils.isEmpty(sessionsItem)) {
                    log.info("SLOT FOUND SUCCESSFULLY IN Bangalore Urban REGION");
                    bookingId = cowinService.sendAlertAndBookSlot(sessionsItem, Constants.AUTH_TOKEN);
                    if (StringUtils.hasText(bookingId)) {
                        log.info("SLOT BOOKED SUCCESSFULLY IN Bangalore Urban REGION");
                        alertService.mailAlert(Constants.SLOT_BOOKED_SUBJECT, Constants.SLOT_BOOKED_BODY);
                        break;
                    } else {
                        log.info("SLOT BOOKED FAILED IN Bangalore Urban REGION");
                        alertService.mailAlert(Constants.SLOT_FOUND_SUBJECT_BOOKING_FAILED, Constants.SLOT_FOUND_BODY+"Session info: "+sessionsItem.getSessionId());
                    }
                } else {
                    try {
                        log.info("searchAndBookSlot | sleeping for random seconds before calling slots api");
                        Thread.sleep(Math.multiplyExact(Long.parseLong(stringUtil.getRandomElementFromStringArray(Constants.SECONDS)), 1000));
                    } catch (InterruptedException e) {
                        log.info("searchAndBookSlot | Exception Occurred while Bangalore Urban Thread | cause: {} | message: {}", e.getCause(), e.getMessage());

                    }
                }
            }
        }).start();

        //Bangalore Rural Thread
        new Thread(() -> {

            SlotsResponse slotsResponse = null;
            SessionsItem sessionsItem = null;
            String bookingId;

            while (true) {

                try {
                    log.info("searchAndBookSlot | fetching slots");
                    slotsResponse = cowinService.getSlots(Constants.AUTH_TOKEN, Constants.BANGALORE_RURAL);
                } catch (Exception e) {
                    log.info("searchAndBookSlot | Exception Occurred while fetching slots | cause: {} | message: {}", e.getCause(), e.getMessage());

                    if (e.getMessage().equals(Constants.UPDATE_USER_TOKEN)) {
                        log.info("searchAndBookSlot | breaking process | token expired | cause: {} | message: {}", e.getCause(), e.getMessage());
                        alertService.mailAlert(Constants.TOKEN_UPDATE_SUBJECT, Constants.TOKEN_UPDATE_BODY);
                    } else if (e.getMessage().equals(Constants.API_LIMIT_REACHED)) {
                        log.info("searchAndBookSlot | api hits limit reached | Bangalore Rural Thread sleeping for 6 mins | cause: {} | message: {}", e.getCause(), e.getMessage());

                        try {
                            Thread.sleep(360000);
                        } catch (InterruptedException interruptedException) {
                            log.info("searchAndBookSlot | Exception Occurred while sleeping Bangalore Rural Thread | cause: {} | message: {}", e.getCause(), e.getMessage());
                        }
                    }
                }

                if (!ObjectUtils.isEmpty(slotsResponse)) {
                    log.info("searchAndBookSlot | searching for available slots");
                    sessionsItem = cowinService.findSlots(slotsResponse);
                } else {
                    log.info("EMPTY SLOTS RESPONSE");
                }

                if (!ObjectUtils.isEmpty(sessionsItem)) {
                    log.info("SLOT FOUND SUCCESSFULLY IN Bangalore Rural REGION");
                    bookingId = cowinService.sendAlertAndBookSlot(sessionsItem, Constants.AUTH_TOKEN);
                    if (StringUtils.hasText(bookingId)) {
                        log.info("SLOT BOOKED SUCCESSFULLY IN Bangalore Rural REGION");
                        alertService.mailAlert(Constants.SLOT_BOOKED_SUBJECT, Constants.SLOT_BOOKED_BODY);
                        break;
                    } else {
                        log.info("SLOT BOOKED FAILED IN Bangalore Rural REGION");
                        alertService.mailAlert(Constants.SLOT_FOUND_SUBJECT_BOOKING_FAILED, Constants.SLOT_FOUND_BODY+"Session info: "+sessionsItem.getSessionId());
                    }
                } else {
                    try {
                        log.info("searchAndBookSlot | sleeping for random seconds before calling slots api");
                        Thread.sleep(Math.multiplyExact(Long.parseLong(stringUtil.getRandomElementFromStringArray(Constants.SECONDS)), 1000));
                    } catch (InterruptedException e) {
                        log.info("searchAndBookSlot | Exception Occurred while Bangalore Rural Thread | cause: {} | message: {}", e.getCause(), e.getMessage());

                    }
                }
            }
        }).start();
    }
}
