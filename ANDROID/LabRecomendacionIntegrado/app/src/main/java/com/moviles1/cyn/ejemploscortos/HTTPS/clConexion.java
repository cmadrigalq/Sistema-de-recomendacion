package com.moviles1.cyn.ejemploscortos.HTTPS;

import android.os.StrictMode;

import com.moviles1.cyn.ejemploscortos.Pantallas.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;


public class clConexion {
    final String urlFormat = "http://%s/BE_Productos/Servlet";
    final String defaultIP = Model.getIp();
    public clConexion() {
    }

    String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL,String ip) {
        if(ip == null || ip.trim().isEmpty())
            ip = defaultIP;
        this.URL = String.format(urlFormat,ip) + URL;
        /*
        ipconfig
        Adaptador de LAN inalámbrica Wi-Fi
        Dirección IPv4
        http://192.168.0.12:8081/webProductosFE/Index.jsp*/
    }

    public String getOutputFromUrl(String url, String ip,String tipo,HashMap<String,Object>parametros) {
        this.setURL(url,ip);
        StringBuffer output = new StringBuffer("");
        try {
            InputStream stream = getHttpConnection(this.URL,tipo,parametros);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(stream));
            String s = "";
            while ((s = buffer.readLine()) != null)
                output.append(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return output.toString();
    }
    // Makes HttpURLConnection and returns InputStream
    private static InputStream getHttpConnection(String urlString,String tipo,HashMap<String,Object>parametros)
            throws IOException {
        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod(tipo);
            if(parametros != null){
                OutputStream os = httpConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(parametros));
                writer.flush();
                writer.close();
            }
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stream;
    }
    private static  String getQuery(HashMap<String,Object> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Set<String> keys = params.keySet();
        for (String key : keys)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(params.get(key).toString(), "UTF-8"));
        }

        return result.toString();
    }
}