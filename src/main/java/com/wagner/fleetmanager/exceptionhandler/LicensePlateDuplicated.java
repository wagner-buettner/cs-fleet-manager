package com.wagner.fleetmanager.exceptionhandler;

import java.io.Serial;

public class LicensePlateDuplicated extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3275978676898702311L;

    public LicensePlateDuplicated(String licensePlate) {
        super("License Plate " + licensePlate + " already registered.");
    }
}
