package io.github.ibuildthecloud.dstack.archaius.util;

import java.util.ArrayList;
import java.util.List;

import io.github.ibuildthecloud.dstack.archaius.polling.RefreshableFixedDelayPollingScheduler;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicDoubleProperty;
import com.netflix.config.DynamicFloatProperty;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class ArchaiusUtil {

    private static List<RefreshableFixedDelayPollingScheduler> schedulers = new ArrayList<RefreshableFixedDelayPollingScheduler>();

    public static DynamicLongProperty getLong(String key) {
        return DynamicPropertyFactory.getInstance().getLongProperty(key, 0);
    }

    public static DynamicIntProperty getInt(String key) {
        return DynamicPropertyFactory.getInstance().getIntProperty(key, 0);
    }

    public static DynamicBooleanProperty getBoolean(String key) {
        return DynamicPropertyFactory.getInstance().getBooleanProperty(key, false);
    }

    public static DynamicDoubleProperty getDouble(String key) {
        return DynamicPropertyFactory.getInstance().getDoubleProperty(key, 0);
    }

    public static DynamicFloatProperty getFloat(String key) {
        return DynamicPropertyFactory.getInstance().getFloatProperty(key, 0);
    }

    public static DynamicStringProperty getString(String key) {
        return DynamicPropertyFactory.getInstance().getStringProperty(key, null);
    }

    public static void addSchedulers(List<RefreshableFixedDelayPollingScheduler> schedulers) {
        for ( RefreshableFixedDelayPollingScheduler scheduler : schedulers ) {
            if ( ! ArchaiusUtil.schedulers.contains(scheduler) ) {
                ArchaiusUtil.schedulers.add(scheduler);
            }
        }
    }

    public static void refresh() {
        for ( RefreshableFixedDelayPollingScheduler scheduler : schedulers ) {
            scheduler.refresh();
        }
    }
}
