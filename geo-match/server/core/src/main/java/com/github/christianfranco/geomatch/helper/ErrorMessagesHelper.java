package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.CallResponse;
import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.entities.ImmutableCallResponse;
import com.github.christianfranco.geomatch.exception.GeoMathException;
import com.github.christianfranco.geomatch.repository.ErrorCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/**
 * Created by Christian Franco on 18/12/2016.
 */
public class ErrorMessagesHelper<E> {

    private final Logger logger;
    private final Collection<String> errorMessages;
    private final ErrorCodeRepository errorCodeRepository;

    public ErrorMessagesHelper(Class<E> targetLogClass, String userLanguage) {
        logger = LoggerFactory.getLogger(targetLogClass);
        errorCodeRepository = new ErrorCodeRepository(getCurrentLocale(userLanguage));
        errorMessages = new HashSet<>();
    }

    public Collection<String> getErrorMessages() {
        return errorMessages;
    }

    public CallResponse criticalErrorResponse(@Nonnull final ErrorCode errorCode) {
        errorMessages.add(errorCodeRepository.findErrorMessage(errorCode));

        return ImmutableCallResponse.builder().errorMessages(errorMessages).build();
    }

    public void addErrorMessage(@Nonnull final GeoMathException e) {
        final String errorMessage = errorCodeRepository.findErrorMessage(e);
        logger.error(errorMessage);
        errorMessages.add(errorMessage);
    }

    private Locale getCurrentLocale(String userLanguage) {
        try {
            return new Locale(userLanguage);
        } catch (Exception e) {
            logger.error("Error get user locale", e);
            return Locale.US;
        }
    }
}
