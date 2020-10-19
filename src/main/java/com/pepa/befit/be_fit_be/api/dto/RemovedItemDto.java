package com.pepa.befit.be_fit_be.api.dto;


import lombok.Value;
import com.pepa.befit.be_fit_be.api.enumeration.DeletionStatus;

@Value
public class RemovedItemDto {

    private Long id;
    private DeletionStatus state;
    private String description;
}
