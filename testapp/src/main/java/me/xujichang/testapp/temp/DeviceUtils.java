package me.xujichang.testapp.temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/21.
 */

public class DeviceUtils {


    private static DeviceUtils deviceUtils = null;

    private DeviceUtils() {

    }

    public static DeviceUtils newInstence() {
        if (deviceUtils == null) {
            deviceUtils = new DeviceUtils();
        }
        return deviceUtils;
    }


    //获取设备CPU信息
    public String getCpuInfo() {
        String name = getCpuName();
        return null;
    }

    // 获取CPU名字
    public String getCpuName() {
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
}
