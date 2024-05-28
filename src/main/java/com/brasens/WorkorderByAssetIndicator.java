package com.brasens;

import com.brasens.dtos.Asset;

import java.util.ArrayList;
import java.util.List;

public class WorkorderByAssetIndicator {

    List<Asset> assets = new ArrayList<>();
    List<Integer> workordersByAsset = new ArrayList<>();

    public WorkorderByAssetIndicator() {
    }

    public WorkorderByAssetIndicator(List<Asset> assets, List<Integer> workordersByAsset) {
        this.assets = assets;
        this.workordersByAsset = workordersByAsset;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Integer> getWorkordersByAsset() {
        return workordersByAsset;
    }

    public void setWorkordersByAsset(List<Integer> workordersByAsset) {
        this.workordersByAsset = workordersByAsset;
    }
}