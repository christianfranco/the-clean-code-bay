package com.github.christianfranco.geomatch.repository;

import com.github.christianfranco.geomatch.entities.ErrorCode;
import com.github.christianfranco.geomatch.exception.GeoMathException;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Created by Christian Franco on 12/12/2016.
 */
public class ErrorCodeRepository {
    private final ResourceBundle messagesBundle;

    public ErrorCodeRepository(Locale currentLocale) {
        messagesBundle = ResourceBundle.getBundle("ErrorMessages", currentLocale);
    }

    public String findErrorMessage(GeoMathException e) {
        if (isEmpty(e.getParameters())) {
            return findErrorMessage(e.getErrorCode());
        } else {
            return MessageFormat.format(findErrorMessage(e.getErrorCode()), e.getParameters());
        }
    }

    public String findErrorMessage(ErrorCode errorCode) {
            return messagesBundle.getString(errorCode.name());
    }
}
