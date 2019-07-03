package org.flyants.chat.dto;

import java.util.List;

/**
 * 省份
 * @author jx on 2018/4/12.
 */

public class Province  extends Area{
    private List<City> cityList;
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
