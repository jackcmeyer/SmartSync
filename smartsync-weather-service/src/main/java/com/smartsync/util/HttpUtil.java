package com.smartsync.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Executes http requests
 */
public class HttpUtil {

    public HttpUtil() {

    }

    /**
     * Executes a get request at the url provided
     * @param url
     * @return the json string
     */
    public static String executeGetRequest(String url) {

        try {
            URL connection = new URL(url);
            InputStream inputStream = connection.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuilder builder = new StringBuilder();
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch(Exception e) {
            e.printStackTrace();

            return null;
        }
    }


}
