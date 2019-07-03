package org.flyants.chat.dto;

import java.util.List;

/**
 * 地级市
 * @author jx on 2018/4/12.
 */

public class City extends Area{
    private List<Area> areaList;

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}
