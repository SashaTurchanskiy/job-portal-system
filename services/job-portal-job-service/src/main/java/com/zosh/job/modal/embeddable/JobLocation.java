package com.zosh.job.modal.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobLocation {

    private String city;
    private String address;
    private String country;
    private String state;
    private String zipCode;

}
