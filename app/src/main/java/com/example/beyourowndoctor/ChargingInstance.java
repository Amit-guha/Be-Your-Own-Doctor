package com.example.beyourowndoctor;

import com.sharmin.charging.AdsLib;
import com.sharmin.charging.bdapps.Robi;

public class ChargingInstance {
    private static AdsLib adsLib;
    public static String APP_ID = "APP_029939"; //BDAPPS APP_ID
    public static String APP_PASSWORD = "";
    public static String APP_PATH = "http://appstore.bdappszone.com"; //http://appstore.bdappszone.com
    public static String MSG_TEXT = "START 22909"; //BDAPPS keyword
    public static String WINDOW_TEXT = "অ্যাড রিমুভ করতে আপনার রবি অথবা এয়ারটেল সিম থেকে \"+MSG_TEXT+\" লিখে এসএমএস করুন ২১২১৩ নাম্বারে । চার্জ প্রতি দিন ২ টাকা"; //অ্যাড রিমুভ করতে আপনার রবি অথবা এয়ারটেল সিম থেকে "+MSG_TEXT+" লিখে এসএমএস করুন ২১২১৩ নাম্বারে । চার্জ প্রতি দিন ২ টাকা

    public static synchronized AdsLib getAdsLib() {
        if (adsLib == null) {
            Robi robi= new Robi();
            robi.setAPP_ID(APP_ID);
            robi.setAPP_PASSWORD(APP_PASSWORD);
            robi.setAPP_PATH(APP_PATH);
            robi.setMSG_TEXT(MSG_TEXT);
            robi.setWINDOW_TEXT(WINDOW_TEXT);
            return new AdsLib(robi);
        }
        return adsLib;
    }
}
