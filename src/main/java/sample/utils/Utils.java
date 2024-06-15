/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

/**
 *
 * @author phamx
 */
public class Utils {
    private static final Random RANDOM = new Random();

    public Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generatePNR(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder pnrBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            pnrBuilder.append(randomChar);
        }

        return pnrBuilder.toString();
    }

    public static boolean isSixteenYearsOld(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthday, today);
        return age.getYears() >= 16;
    }
}
