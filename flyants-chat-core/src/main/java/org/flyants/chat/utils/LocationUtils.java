package org.flyants.chat.utils;

import org.flyants.chat.dto.Address;
import org.flyants.common.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangchao
 * @Date 2019/6/26 14:08
 * @Version v1.0
 */
public class LocationUtils {

    public static final Logger logger = LoggerFactory.getLogger(LocationUtils.class);

    private static final String ak = "GBItrgLRKoYOd24gK519wj2v6TqHQxm5";
    //31.225696563611,121.49884033194
    private static final String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak={0}&output=json&coordtype=wgs84ll&location={1},{2}";
    private static final String SEARCH_URL = "http://api.map.baidu.com/place/v2/search?ak={0}&location={1},{2}&query={3}&radius=200&output=json&page_size=20";
    private static final String GEOCONV_URL = "http://api.map.baidu.com/geoconv/v1/?ak={0}&coords={1},{2}&from=1&to=5";
    private static final String GEOSEARCH_URL = "http://api.map.baidu.com/geosearch/v3/nearby?ak={0}&geotable_id=****&location=116.395884,39.932154&radius=1000&tags=&sortby=distance:1|price:1&filter=price:200,300";


    /**
     *
     * @param longitude
     * @param latitude
     * @return
     */
    public static String getLocationAddress(String longitude, String latitude) {
        String address = null;
        String response = HttpRequestUtils.get(MessageFormat.format(url, ak, latitude, longitude));
        logger.info("{}", response);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("status") == 0) {
            JSONObject result = jsonObject.getJSONObject("result");
            address = result.getString("formatted_address");
            logger.info("formatted_address:{}", address);
        }
        return address;
    }


    public static String geoconv(String longitude, String latitude) {
        String longitudeAndLatitude = null;
        String response = HttpRequestUtils.get(MessageFormat.format(GEOCONV_URL, ak, latitude, longitude));
        logger.info("{}", response);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("status") == 0) {
            JSONArray result = jsonObject.getJSONArray("result");
            if(result != null && result.length() > 0){
                longitudeAndLatitude = result.getJSONObject(0).getDouble("x")+"";
                longitudeAndLatitude += ","+result.getJSONObject(0).getDouble("y");
            }
            logger.info("longitudeAndLatitude:{}", longitudeAndLatitude);
        }
        return longitudeAndLatitude;
    }


    public static List<Address> searchLocation(String longitude, String latitude) {
        List<Address> resuts = new ArrayList<>();
        String response = HttpRequestUtils.get(MessageFormat.format(SEARCH_URL, ak, longitude, latitude,"银行$酒店$美食$园区$学校"));
//        logger.info("{}", response);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("status") == 0) {
            JSONArray result = jsonObject.getJSONArray("results");
            if(result != null && result.length() > 0){
                int length = result.length();
                for(int i =0;i<length;i++){
                    JSONObject object = result.getJSONObject(i);
                    resuts.add(new Address(object.getString("name"),object.getString("address")));
                }
            }
        }
        return resuts;
    }




    public static void main(String[] args) {
//        System.out.println(geoconv("30.573256015777588", "103.9844799041748"));
//        System.out.println(getLocationAddress("30.57629700765519","103.99329785274806"));
        List<Address> stringList = searchLocation("30.57629700765519", "103.99329785274806");
        System.out.println(JsonUtils.obj2json(stringList));
    }


}
