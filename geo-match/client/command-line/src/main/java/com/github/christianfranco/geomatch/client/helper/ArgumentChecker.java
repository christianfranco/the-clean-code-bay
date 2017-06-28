package com.github.christianfranco.geomatch.client.helper;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public final class ArgumentChecker {
    public static final String ARG_TOKEN = "--";
    public static final String ARG_SAME_COUNTRY = ARG_TOKEN + "same-country-only";
    static final String ARG_HELP = ARG_TOKEN + "help";

    private static final Collection<String> SUPPORTED_OPTIONS = Arrays.asList(ARG_SAME_COUNTRY, ARG_HELP);

    public static void checkArgumentHelp(String... args) {
        if (Stream.of(args).anyMatch(ARG_HELP::equals)) {
            HelpArgument.printHelp();
        }
    }

    public static void checkInvalidArguments(String... args) {
        checkArgument(!isEmpty(args),
                "Error to execute command:\n" +
                        "The arguments cannot be empty. Type --help for help.\n\n");

        List<String> invalidArguments = Stream.of(args)
                .filter(argument -> StringUtils.startsWith(argument, ARG_TOKEN))
                .filter(argument -> !SUPPORTED_OPTIONS.contains(argument))
                .collect(Collectors.toList());

        checkArgument(invalidArguments.isEmpty(),
                "Error to execute command:\n" +
                        "The argument(s) (" + Joiner.on(", ").join(invalidArguments) + ") is not supported. Type --help for help.\n\n");
    }
}
