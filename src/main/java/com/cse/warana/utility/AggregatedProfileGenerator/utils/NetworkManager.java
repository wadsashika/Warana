package com.cse.warana.utility.AggregatedProfileGenerator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Thilina on 8/22/2014.
 */
public class NetworkManager {
    public String Get(String urlString){

        String info="";
        URL url = null;
        String test="https://api.github.com/search/users?q=thilina premasiri&order=desc&access_token=69e07dde89a8a0a6713f810cfd4c461f04f47e85";
        try {
            url = new URL(urlString);
            System.out.println("request-----" + urlString);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                    "cache.mrt.ac.lk", 3128));
//            HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection(); /*With out proxy */
            uc.setConnectTimeout(0);
            uc.setReadTimeout(0);
            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");
//            uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36s");
            uc.connect();
            String line = null;
            StringBuffer tmp = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = in.readLine()) != null) {
                tmp.append(line);
//                System.out.println(line);
            }
//            Document doc = Jsoup.parse(String.valueOf(tmp));
//            System.out.println("result " + tmp.toString());
            info = tmp.toString();
//            AddInfo(tmp.toString());
        } catch (SocketTimeoutException e) {
            System.out.println("More than  elapsed.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println();
        return info;
    }
    public void Testing(String urlStr) {
        int TIMEOUT_VALUE = 5000;
        try {
            URL testUrl = new URL(urlStr);
            StringBuilder answer = new StringBuilder(100000);

            long start = System.nanoTime();

            URLConnection testConnection = testUrl.openConnection();
            testConnection.setConnectTimeout(TIMEOUT_VALUE);
            testConnection.setReadTimeout(TIMEOUT_VALUE);
            BufferedReader in = new BufferedReader(new InputStreamReader(testConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                answer.append(inputLine);
                answer.append("\n");
            }
            in.close();

            long elapsed = System.nanoTime() - start;
            System.out.println("Elapsed (ms): " + elapsed / 1000000);
            System.out.println("Answer:");
            System.out.println(answer);
        } catch (SocketTimeoutException e) {
            System.out.println("More than " + TIMEOUT_VALUE + " elapsed.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
