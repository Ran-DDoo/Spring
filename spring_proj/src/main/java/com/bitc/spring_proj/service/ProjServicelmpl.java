package com.bitc.spring_proj.service;

import com.bitc.spring_proj.dto.FestaDTO;
import com.bitc.spring_proj.dto.FestaItemDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ProjServicelmpl implements ProjService{

    @Override
    public List<FestaItemDTO> FestaList(String serviceUrl) throws Exception{
        List<FestaItemDTO> festaList = null;

        URL url = null;
        HttpURLConnection urlCon = null;
        BufferedReader reader = null;

        try {
            url = new URL(serviceUrl);
            urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();

            FestaDTO festa = gson.fromJson(sb.toString(), FestaDTO.class);
            festaList = festa.getGetFestivalKr().getItem();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {reader.close();}
            if (urlCon != null) {urlCon.disconnect();}
        }

        return festaList;
    }
}
