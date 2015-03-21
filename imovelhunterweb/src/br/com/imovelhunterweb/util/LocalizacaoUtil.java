package br.com.imovelhunterweb.util;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.imovelhunterweb.entitys.PontoGeografico;

public class LocalizacaoUtil{
    JSONObject jsonObject; 
    List<PontoGeografico> addrs;
    HttpUtil httpUtil;
    

    public LocalizacaoUtil() {
        
        this.addrs = new ArrayList<PontoGeografico>();    
        this.httpUtil = new HttpUtil("");
    }      

    public PontoGeografico getPontoGeografico(String address) throws IOException {

    	
        String query = "http://maps.google.com/maps/api/geocode/json?address=" + address.replaceAll(" ","%20")
                + "&sensor=false";
        
        this.httpUtil.setUrl(query);
        
        PontoGeografico addr = null;
        

        
        StringBuilder stringBuilder = new StringBuilder(this.httpUtil.enviarRequestGet());

        try {
           
            if (stringBuilder.length() > 0) {

               
                try {
                    jsonObject = new JSONObject(stringBuilder.toString());
                    JSONArray addrComp = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONArray("address_components");
                    String locality = ((JSONArray)((JSONObject)addrComp.get(0)).get("types")).getString(0);
                    if (locality.compareTo("locality") == 0) {
                        locality = ((JSONObject)addrComp.get(0)).getString("long_name");
                    }
                    String adminArea = ((JSONArray)((JSONObject)addrComp.get(2)).get("types")).getString(0);
                    if (adminArea.compareTo("administrative_area_level_1") == 0) {
                        adminArea = ((JSONObject)addrComp.get(2)).getString("long_name");
                    }
                    String country = ((JSONArray)((JSONObject)addrComp.get(3)).get("types")).getString(0);
                    if (country.compareTo("country") == 0) {
                        country = ((JSONObject)addrComp.get(3)).getString("long_name");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Double lon = Double.valueOf(0);
                Double lat = Double.valueOf(0);

                try {

                    lon = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lng");

                    lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                            .getJSONObject("geometry").getJSONObject("location")
                            .getDouble("lat");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                addr = new PontoGeografico();
                addr.setLatitude(lat);
                addr.setLongitude(lon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addr;
    }
}