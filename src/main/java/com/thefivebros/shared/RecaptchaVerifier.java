package com.thefivebros.shared;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class RecaptchaVerifier {
    // Replace with your secret key
    private static final String SECRET_KEY = "6LfRe9oqAAAAAA-I8lNemk9yxVGCOeBwlpEklFaY";

    public static boolean verify(String recaptchaResponse) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "secret=" + SECRET_KEY + "&response=" + recaptchaResponse;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send the POST request
            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();

            // Read the response
            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();

            // Parse JSON response
            JSONObject json = new JSONObject(response);
            return json.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
