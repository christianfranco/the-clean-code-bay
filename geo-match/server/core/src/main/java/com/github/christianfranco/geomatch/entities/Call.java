package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Christian Franco on 17/12/2016.
 */
@ThreadSafe
@Immutable
public interface Call extends Serializable {

    @Parameter
    String getDestinationNumber();

    @Parameter
    Collection<String> getCustomerNumbers();

    @Default
    default String getUserLanguage() {
        return "en";
    }

    @Default
    default boolean isCallToSameCountryOnly() {
        return false;
    }
}
