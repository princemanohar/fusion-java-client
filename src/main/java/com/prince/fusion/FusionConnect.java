package com.prince.fusion;

import okhttp3.*;

import java.io.IOException;

public class FusionConnect {

    public static void main(String[] args) throws IOException {

        String cookie = getSessionCookie();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.0.26:8764/api/apollo/collections/system_metrics")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie)
                .build();

        Response response = client.newCall(request).execute();

        String body = new String(response.body().bytes());

        System.out.println(body);
    }

    public static String getSessionCookie() throws IOException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{ \"username\" : \"admin\" , \"password\" : \"brother@2624\" }");
        Request request = new Request.Builder()
                .url("http://192.168.0.26:8764/api/session")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();


        String cookie = response.header("Set-Cookie");
        System.out.println("Cookie = " + cookie);

        return cookie;
    }

}
