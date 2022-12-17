package com.wagner.fleetmanager.model.enumerations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CarStatus {

    @JsonProperty("available")
    AVAILABLE,
    @JsonProperty("unavailable")
    UNAVAILABLE;
}
