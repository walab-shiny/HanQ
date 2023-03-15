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

    public static DecodedToken getDecodedToken(String tokenString) {
        String[] pieces = tokenString.split("\\.");
        StringBuilder b64payload = new StringBuilder(pieces[1]);
        System.out.println("b64payload.length() = " + b64payload.length());
        while(b64payload.length()%4!=0) {
            b64payload.append("=");
        }
        String jsonString = new String(Base64.decodeBase64(b64payload.toString()), StandardCharsets.UTF_8);

        return new Gson().fromJson(jsonString,DecodedToken.class);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
