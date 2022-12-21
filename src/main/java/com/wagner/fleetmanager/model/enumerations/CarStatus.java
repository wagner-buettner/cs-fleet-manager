package com.wagner.fleetmanager.model.enumerations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CarStatus {

    @JsonProperty("available")
    AVAILABLE,
    @JsonProperty("in-maintenance")
    IN_MAINTENANCE,
    @JsonProperty("out-of-service")
    OUT_OF_SERVICE
}
