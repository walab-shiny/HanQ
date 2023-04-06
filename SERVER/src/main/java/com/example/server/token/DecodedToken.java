package com.example.server.token;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class DecodedToken {
    private String sub;
    private String name;
    private String email;
    private String picture;
    private String family_name;

    public static DecodedToken getDecodedToken(String tokenString) {
        String[] pieces = tokenString.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(Base64.decodeBase64URLSafe(b64payload), StandardCharsets.UTF_8);

        return new Gson().fromJson(jsonString,DecodedToken.class);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
