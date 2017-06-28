package com.github.christianfranco.geomatch.entities;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Created by Christian Franco on 17/12/2016.
 */
@ThreadSafe
@Immutable
public interface CallResponse extends Serializable {

    @Default
    default String getSelectedCustomerNumber() {
        return EMPTY;
    }

    @Default
    default String getLocation() {
        return EMPTY;
    }

    @Default
    default Collection<String> getErrorMessages() {
        return Collections.emptyList();
    }
}
