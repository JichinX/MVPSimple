package com.example.crashhandler;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

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
 * Created by XuJiChang on 2017/2/21.
 */

class DeviceUtils {
    private static DeviceUtils deviceUtils = null;
    private static TelephonyManager manager;

    private DeviceUtils() {

    }

    static DeviceUtils newInstance() {
        if (deviceUtils == null) {
            deviceUtils = new DeviceUtils();
        }
        return deviceUtils;
    }


    /**
     * 获取设备信息
     */
    String getDeviceInfo(Context context, JSONObject info) throws JSONException {

        manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        info.put("deviceid", getDeviceId());
        StringBuilder builder = new StringBuilder("Device Info:");
        builder.append("cpu_name:").append(getCpuName())
                .append("   设备ID:").append(getDeviceId())
                .append("   手机品牌:").append(getPhoneBrand())
                .append("   手机型号:").append(getPhoneModel())
                .append("   Android API等级:").append(getBuildLevel())
                .append("   Android 版本:").append(getBuildVersion())
                .append("   系统版本显示：").append(getOsDisplay())
                .append("   HardWare:").append(getHardWare())
                .append("   Rom厂商:").append(getRom())
                .append("   mac地址:").append(getMac())
                .append("   设备序列号: ").append(getSerialNumber())
                .append("   软件版本:").append(getDeviceSoftwareVersion())
                .append("   国际移动用户识别码:").append(getSubscriberId())
                .append("   数据运营商:").append(manager.getNetworkOperatorName())
                .append("   移动运营商:").append(manager.getSimOperatorName())
                .append("   SIM卡识别码:").append(manager.getSimSerialNumber())
                .append("   SIM卡状态:").append(getSimState())
        ;
        return builder.toString();
    }

    private String getSimState() {
        String state = "未获得状态";
        switch (manager.getSimState()) {
            case TelephonyManager.SIM_STATE_READY:
                state = "正常";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                state = "Locked: requires a network PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                state = "Locked: requires the user's SIM PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                state = "Locked: requires the user's SIM PUK to unlock ";
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                state = "未获得状态";
                break;
            case TelephonyManager.SIM_STATE_ABSENT:
                state = "SIM卡不可用";
                break;
            default:
                break;
        }
        return state;
    }

    private String getRom() {
        return Build.MANUFACTURER;
    }

    private String getHardWare() {
        return Build.HARDWARE;
    }

    private String getOsDisplay() {
        return Build.DISPLAY;
    }

    /**
     * 获取CPU名字
     */
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
     * @return
     */
    private String getDeviceId() {
        String deviceId = manager.getDeviceId();
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
     * @return
     */
    private String getMac() {
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
        if (TextUtils.isEmpty(macSerial)) {
            macSerial = "不可用";
        }
        return macSerial;
    }

    /**
     * 设备序列号
     *
     * @return
     */
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
     * 设备的软件版本号
     */
    private String getDeviceSoftwareVersion() {
        return manager.getDeviceSoftwareVersion();
    }

    /**
     * 取得手机IMSI
     * 返回用户唯一标识
     */
    private String getSubscriberId() {

        return manager.getSubscriberId();
    }
}
