package com.webcoder.vaccine.api.commons;

public class Constants {
    //vaccines
    public static final String COVISHIELD = "COVISHIELD";
    public static final String COVAXIN = "COVAXIN";

    //user info
    public static final String USER_BENEFICIARY = "87769916949790";
    public static final String USER_MOBILE = "8050915209";

    //districts
    public static final String BANGALORE_RURAL = "276";
    public static final String BANGALORE_URBAN = "265";
    public static final String BBMP = "294";

    //vaccine url
    public static final String VACCINE_SLOT_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByDistrict?district_id=%DIST-ID%&date=%DATE-DD-MM-YYYY%&vaccine=%VACCINE%";
    public static final String VACCINE_SLOT_BOOK_URL = "https://cdn-api.co-vin.in/api/v2/appointment/schedule";
    public static final String SEND_OPT_URL = "https://cdn-api.co-vin.in/api/v2/auth/generateMobileOTP";
    public static final String VALIDATE_OPT_URL = "https://cdn-api.co-vin.in/api/v2/auth/validateMobileOtp";

    public static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJiMjM3ZTU4My0wYWQxLTRkMTktYTY1Yi1mYjMwNGMzZGQ3OTMiLCJ1c2VyX2lkIjoiYjIzN2U1ODMtMGFkMS00ZDE5LWE2NWItZmIzMDRjM2RkNzkzIiwidXNlcl90eXBlIjoiQkVORUZJQ0lBUlkiLCJtb2JpbGVfbnVtYmVyIjo4MDUwOTE1MjA5LCJiZW5lZmljaWFyeV9yZWZlcmVuY2VfaWQiOjg3NzY5OTE2OTQ5NzkwLCJzZWNyZXRfa2V5IjoiYjVjYWIxNjctNzk3Ny00ZGYxLTgwMjctYTYzYWExNDRmMDRlIiwic291cmNlIjoiY293aW4iLCJ1YSI6Ik1vemlsbGEvNS4wIChNYWNpbnRvc2g7IEludGVsIE1hYyBPUyBYIDEwXzE1XzcpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS85MC4wLjQ0MzAuMjEyIFNhZmFyaS81MzcuMzYiLCJkYXRlX21vZGlmaWVkIjoiMjAyMS0wNS0zMVQwNTozMzoyOC4xNzVaIiwiaWF0IjoxNjIyNDM5MjA4LCJleHAiOjE2MjI0NDAxMDh9.Oso5DfWa_OcFZMWFrMZkk-mTFr6cdK7nlW_9s95oTCc";
    public static final String OTP_SECRETE = "U2FsdGVkX1/hGnhOGfA/RlHQcTBjrlSIsYsNiOIRonB0YO70fNSs7OCUUr7PQ26M4HwKy13H0G/sfneDidA4MA==,U2FsdGVkX186fMQe+Vi0HWJql9AvyeNrbihzzdXR72f0ec4uRHmTLyNcaACXK+18mpcNewEbzIFW92c1nN4Z3A==";

    //error message
    public static final String UPDATE_USER_TOKEN = "UPDATE_USER_TOKEN";
    public static final String API_LIMIT_REACHED = "API_LIMIT_REACHED";

    //mail info
    public static final String TOKEN_UPDATE_BODY = "Your token has expired update it soon to continue searching";
    public static final String TOKEN_UPDATE_SUBJECT = "CoWin: YOUR TOKEN EXPIRED";

    public static final String SLOT_FOUND_BODY_WITH_DETAILS = "Your slot for vaccine is found but sorry booking failed center info:\n" +
                "Center Name: %center%\n" +
            "Region: %region%\n" +
            "Session Id: %session%\n";
    public static final String SLOT_FOUND_BODY = "Your slot for vaccine is found but sorry booking failed center info: ";
    public static final String SLOT_FOUND_SUBJECT = "CoWin: VACCINE SLOT FOUND FOUND";
    public static final String SLOT_FOUND_SUBJECT_BOOKING_FAILED = "CoWin: VACCINE SLOT FOUND FOUND BOOKING FAILED";

    public static final String SLOT_BOOKED_BODY = "Your slot for vaccine has booked log into your account and check your booking details";
    public static final String SLOT_BOOKED_SUBJECT = "CoWin: CONGRATULATION YOUR VACCINE SLOT HAS BOOKED";

    public static final String SLOTS_DATE_PATTERN = "dd-MM-yyyy";
    public static final String[] USER_AGENTS = {"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 4.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
            "Mozilla/5.0 (X11; OpenBSD i386) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.3319.102 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.2309.372 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.2117.157 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.47 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1866.237 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/4E423F",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36 Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.517 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1664.3 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1664.3 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1623.0 Safari/537.36"};

    public static final String[] SECONDS = {
            "30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51"
            ,"52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73"
            ,"74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95"
            ,"96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114"
            ,"115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132"
            ,"133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148","149","150"
            ,"151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167","168"
            ,"169","170","171","172","173","174","175","176","177","178","179","180"
    };
}
