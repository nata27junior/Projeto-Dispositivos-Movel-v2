package com.example.natanael.fatec_saude;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ConnectionUBS {

    public List<Marker> getData() throws JSONException {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final StringBuilder result = new StringBuilder();

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://api.myjson.com/bins/1jtha");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);



            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(result);

        List<Marker> finalResult = generateJSON(new JSONArray(result.toString()));

        return finalResult;
    }


    public List<Marker> generateJSON(JSONArray json){

        List<Marker> found = new LinkedList<Marker>();

        try {


            for (int i = 0; i < json.length(); i++) {
//                {"lat":"-23.200914",
//                        "lng":"-45.887820",
//                        "nome":"HOSPITAL DIA",
//                        "morada1":"Rua Amin Assad, 200",
//                        "morada2":"Jardim São Dimas",
//                        "codPostal":"(12) 3924-8500",
//                        "imagem":"\u003ca href=\"http://bit.ly/1r1QIF4\" target=\"_blank\"\u003eClique aqui para ver o local\u003c/a\u003e"}
                JSONObject obj = json.getJSONObject(i);
                found.add(new Marker(obj.getDouble("lat"),obj.getDouble("lng"),
                        obj.getString("nome"),
                        obj.getString("morada1"),
                        obj.getString("morada2"),
                        obj.getString("codPostal"),
                        obj.getString("imagem")));
            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;

    }

}

