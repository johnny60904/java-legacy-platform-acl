package com.dxlan.acl.features.infrastructure.log;

import java.util.concurrent.TimeUnit;

public final class AclLoggerConfiguration {

    private AclLoggerConfiguration() { throw new AssertionError(); }

    static final int MAX_ALLOWED_LOGS = 5;
    static final String CURRENT_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    static final String LOG_FILE_TIMESTAMP_PATTERN = "yyyyMMdd'_'HHmmss";
    static final String LOG_FILE_NAME_PREFIX = "acl_";
    static final String LOG_FILE_NAME_SUFFIX = "_errors_";
    static final String LOG_FILE_EXTENSION = ".log";
    static final String DIRECTORY_PATH = "logs/acl/";
    static final String MODULE_NAME_DEFAULT = "common";
    static final String PREMIUM_ASSET_MODULE = "acl.premiumasset";
    static final String PREMIUM_ASSET_MODULE_NAME = "premiumasset";
    static final String HARDWARE_ASSET_MODULE = "acl.hardwareasset";
    static final String HARDWARE_ASSET_MODULE_NAME = "hardwareasset";
    static final String THREAD_WRITER_NAME = "ACL-Async-File-Writer";
    static final String THREAD_TIMER_NAME = "AclLogger-Reset-Timer";
    static final long TIMER_INITIAL_DELAY = 10;
    static final long TIMER_PERIOD = 10;
    static final TimeUnit TIMER_UNIT = TimeUnit.SECONDS;

}
