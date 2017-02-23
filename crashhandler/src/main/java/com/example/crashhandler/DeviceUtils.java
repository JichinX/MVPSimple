package com.example.crashhandler;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;

/**
 * 获取设备信息
 * Created by Administrator on 2017/2/21.
 */

class DeviceUtils {
    private static DeviceUtils deviceUtils = null;

    private DeviceUtils() {

    }

    static DeviceUtils newInstence() {
        if (deviceUtils == null) {
            deviceUtils = new DeviceUtils();
        }
        return deviceUtils;
    }


    //获取设备CPU信息
    String getDeviceInfo(Context context, JSONObject info) throws JSONException {
        info.put("deviceid", getDeviceId(context));
        TelephoneUtil u = TelephoneUtil.getInstance(context);
        u.setCTelephoneInfo();
        StringBuilder builder = new StringBuilder("Device Info:");
        builder.append("cpu_name:").append(getCpuName())
                .append("   device_id:").append(getDeviceId(context))
                .append("   phone_brand:").append(getPhoneBrand())
                .append("   phone_model:").append(getPhoneModel())
                .append("   build_level:").append(getBuildLevel())
                .append("   build_version:").append(getBuildVersion())
                .append("   mac:").append(getMac())
                .append("   serialNumber: ").append(getSerialNumber())
                .append("   软件版本:").append(getDeviceSoftwareVersion(context))
                .append("   设备序列号:").append(getSubscriberId(context))
                .append("   Sim1手机号:").append(u.getINumeric1())
                .append("   Sim2手机号:").append(u.getINumeric2())
                .append("   Sim1 IMEI:").append(u.getImeiSIM2())
                .append("   Sim2 IMEI:").append(u.getImeiSIM1());

        return builder.toString();
    }

    // 获取CPU名字
    private String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     */
    private String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }


    /**
     * 获取手机品牌
     *
     * @return
     */
    private String getPhoneBrand() {
        return android.os.Build.BRAND;
    }


    /**
     * 获取手机型号
     *
     * @return
     */
    private String getPhoneModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    private int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }


    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    private String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取Mac地址
     *
     * @param ctx
     * @return
     */
    private String getMac(Context ctx) {

        WifiManager wifiManager = (WifiManager) ctx
                .getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wi = wifiManager.getConnectionInfo();
            return wi.getMacAddress();
        }
        return null;
    }

    String getMac() {
        String macSerial = "";
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);


            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return macSerial;
    }

    private String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    /**
     * 取得IMEI SV
     * 设备的软件版本号： 返回移动终端的软件版本，例如：GSM手机的IMEI/SV码。 例如：the IMEI/SV(software version)
     * for GSM phones. Return null if the software version is not available.
     */
    public static String getDeviceSoftwareVersion(Context context) {
        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceSoftwareVersion();
    }

    /**
     * 取得手机IMSI
     * 返回用户唯一标识，比如GSM网络的IMSI编号 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone.
     * 需要权限：READ_PHONE_STATE
     */
    public static String getSubscriberId(Context context) {

        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }

    public static String getSubscriberNumber(Context context) {

        return ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }
}
