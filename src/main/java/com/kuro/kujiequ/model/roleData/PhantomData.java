package com.kuro.kujiequ.model.roleData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-07-30 16:22
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhantomData {
    private int cost;
    private List<Phantom> equipPhantomList;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Phantom> getEquipPhantomList() {
        return equipPhantomList;
    }

    public void setEquipPhantomList(List<Phantom> equipPhantomList) {
        this.equipPhantomList = equipPhantomList;
    }
}