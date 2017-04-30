package com.example.ivan.easyreader.DI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Ivan on 30.03.2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface ReadingScope {
}
