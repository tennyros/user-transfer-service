package com.github.tennyros.transfers.util;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class DotenvLoader {

    public static void load() {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("JWT_ACCESS_KEY_SECRET", Objects.requireNonNull(dotenv.get("JWT_ACCESS_KEY_SECRET")));
        System.setProperty("JWT_ACCESS_KEY_EXP_TIME", Objects.requireNonNull(dotenv.get("JWT_ACCESS_KEY_EXP_TIME")));
    }

}
