package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {

    private static class InstanceHolder {
        private static final StatisticAdvertisementManager STATISTIC_ADVERTISEMENT_MANAGER = new StatisticAdvertisementManager();
    }


    public static StatisticAdvertisementManager getInstance() {
        return InstanceHolder.STATISTIC_ADVERTISEMENT_MANAGER;
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private StatisticAdvertisementManager() {}


    public List<Advertisement> getActiveVideoSet() {
        List<Advertisement> activeVideoList = new ArrayList<>();

        for (Advertisement ad : storage.list()) {
            if (ad.isActive()) {
                activeVideoList.add(ad);
            }
        }
        return activeVideoList;
    }

    public List<Advertisement> getArchivedVideoSet() {
        List<Advertisement> archivedVideoList = new ArrayList<>();

        for (Advertisement ad : storage.list()) {
            if (!ad.isActive()) {
                archivedVideoList.add(ad);
            }
        }
        return archivedVideoList;
    }

}
