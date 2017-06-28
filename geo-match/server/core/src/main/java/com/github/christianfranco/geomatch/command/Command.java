package com.github.christianfranco.geomatch.command;

import com.github.christianfranco.geomatch.exception.GeoMathException;

import javax.annotation.Nonnull;

/**
 * Created by Christian Franco on 16/12/2016.
 */
public interface Command<V, R> {

    R call(@Nonnull final V value) throws GeoMathException;
}
