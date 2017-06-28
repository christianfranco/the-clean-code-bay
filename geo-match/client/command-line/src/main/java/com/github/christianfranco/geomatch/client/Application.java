package com.github.christianfranco.geomatch.client;

import com.github.christianfranco.geomatch.client.configuration.ConfigProperties;
import com.github.christianfranco.geomatch.client.helper.ArgumentChecker;
import com.github.christianfranco.geomatch.client.helper.RestServiceInvoker;
import com.github.christianfranco.geomatch.client.json.RequestJson;
import com.github.christianfranco.geomatch.client.json.ResponseJson;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.christianfranco.geomatch.client.helper.ArgumentChecker.checkArgumentHelp;
import static com.github.christianfranco.geomatch.client.helper.ArgumentChecker.checkInvalidArguments;
import static java.lang.System.exit;

/**
 * Created by Christian Franco on 17/12/2016.
 */
public class Application {
    private static final String SEPARATOR = "--";

    public static void main(String... args) {
        try {
            checkInvalidArguments(args);
            checkArgumentHelp(args);

            RestServiceInvoker<RequestJson, ResponseJson> restServiceInvoker =
                    new RestServiceInvoker<>(ConfigProperties.getGeoMatchEndPoint(), ResponseJson.class);

            RequestJson requestJson = buildJsonRequest(args);

            printHeader();

            ResponseJson responseJson = restServiceInvoker.invoke(requestJson);

            printResponse(responseJson);
        } catch (Exception e) {
            println("--");
            println("Error to process: " + e.getMessage());
            exit(1);
        }
    }

    private static void printHeader() {
        println(SEPARATOR);
        println("The Clean Code Bay");
        println(SEPARATOR);
        println("Calling phone number geo-match service...");
    }

    private static void printResponse(ResponseJson responseJson) {
        println(SEPARATOR);
        println("Selected Number...........: " + responseJson.getSelectedCustomerNumber());
        println("Selected Number Location..: " + responseJson.getLocation());

        if (!responseJson.getErrorMessages().isEmpty()) {
            println("Error Messages............: ");
            responseJson.getErrorMessages()
                    .stream()
                    .map(message -> "\t-" + message)
                    .forEach(System.out::println);
        }
        println(SEPARATOR);
    }

    private static RequestJson buildJsonRequest(@Nonnull final String... args) {
        final RequestJson requestJson = new RequestJson();

        Stream.of(args)
                .filter(arg -> !arg.startsWith(ArgumentChecker.ARG_TOKEN))
                .findFirst()
                .ifPresent(requestJson::setDestinationNumber);

        requestJson.setCustomerNumbers(
                Stream.of(args)
                .filter(arg -> !arg.startsWith(ArgumentChecker.ARG_TOKEN))
                .filter(arg -> !arg.equalsIgnoreCase(requestJson.getDestinationNumber()))
                .collect(Collectors.toSet())
        );

        if (Stream.of(args).anyMatch(ArgumentChecker.ARG_SAME_COUNTRY::equals)) {
            requestJson.setCallToSameCountryOnly(true);
        }

        return requestJson;
    }

    public static void println(String input) {
        System.out.println(input);
    }
}
