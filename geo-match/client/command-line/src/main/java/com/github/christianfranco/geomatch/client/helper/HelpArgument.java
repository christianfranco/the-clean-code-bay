package com.github.christianfranco.geomatch.client.helper;

import com.github.christianfranco.geomatch.client.Application;

/**
 * Created by Christian Franco on 18/12/2016.
 */
final class HelpArgument {
    private static final String SEPARATOR = "\n---------------------------------------------------------------------------------\n";

    private HelpArgument() {
    }

    public static void printHelp() {
        final StringBuilder stringBuilder = new StringBuilder();

        String tab = "\t\t";

        stringBuilder.append(SEPARATOR);
        stringBuilder.append("Welcome to [Offline Phone Number Geolocation]");
        stringBuilder.append(SEPARATOR);

        stringBuilder.append("Optional arguments:\n");
        stringBuilder.append(tab);
        stringBuilder.append(ArgumentChecker.ARG_HELP + " : Open this help\n");
        stringBuilder.append(tab);
        stringBuilder.append(ArgumentChecker.ARG_SAME_COUNTRY + " :  Only select matches belonging to the same country\n\n");

        stringBuilder.append("Usage: \n");
        stringBuilder.append(tab);
        stringBuilder.append("$ java -jar phone_number_geo_match.jar [--same-country-only | --help] <target number> <customer numbers...>");

        stringBuilder.append("\n\n");
        stringBuilder.append("Call to New Jersey:\n");
        stringBuilder.append(tab);
        stringBuilder.append("$ java -jar phone_number_geo_match.jar --same-country-only +12018840000 +15148710000 +14159690000\n");
        stringBuilder.append(tab);
        stringBuilder.append("# Selected customer number: +14159690000 (US - San Francisco)\n\n");

        stringBuilder.append("Call to France:\n");
        stringBuilder.append(tab);
        stringBuilder.append("$ java -jar phone_number_geo_match.jar +33975180000 +441732600000 +14159690000\n");
        stringBuilder.append(tab);
        stringBuilder.append("# Selected customer number: +441732600000 (GB - London)\n\n");

        stringBuilder.append("Call to Portugal:\n");
        stringBuilder.append(tab);
        stringBuilder.append("$ java -jar phone_number_geo_match.jar +351211230000 +18009970000 +448008080000\n");
        stringBuilder.append(tab);
        stringBuilder.append("# Selected customer number: +448008080000 (GB - toll-free)\n\n");

        Application.println(stringBuilder.toString());
        System.exit(0);
    }
}
