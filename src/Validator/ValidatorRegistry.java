/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

/**
 *
 * @author thinhnh
 */
import Validator.Core.ParametrizedRuleBuilder;
import static Validator.Util.Utilities.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum ValidatorRegistry {
    INSTANCE;

    private Map<String, Validator> _validators = new HashMap<>();
    private final Lock _readLock;
    private final Lock _writeLock;

    private ValidatorRegistry() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        _readLock = readWriteLock.readLock();
        _writeLock = readWriteLock.writeLock();
    }

    public static Validator register(String name, ParametrizedRuleBuilder... rules) {
        checkNotNull(rules);
        return register(name, ValidatorBuilder.rules(rules));
    }

    public static Validator register(String name, ValidatorBuilder builder) {
        checkNotNull(name);
        Validator validator = builder.build();
        Validator lastValidator = null;
        INSTANCE._writeLock.lock();
        try {
            lastValidator = INSTANCE._validators.put(name, validator);
        } finally {
            INSTANCE._writeLock.unlock();
        }

        return validator;
    }

    public static Validator getValidator(String name) {
        checkNotNull(name);
        INSTANCE._readLock.lock();
        try {
            return INSTANCE._validators.get(name);
        } finally {
            INSTANCE._readLock.unlock();
        }
    }
}
