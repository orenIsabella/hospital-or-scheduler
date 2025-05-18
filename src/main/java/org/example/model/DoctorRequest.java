package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DoctorRequest {
    @NotNull(message = "The doctor type is required. Valid values: HEART or BRAIN.")
    private DoctorType doctorType;
    @NotBlank(message = "Please provide the doctor's name.")
    private String doctorName;

    public DoctorRequest() {}

    public DoctorRequest(DoctorType doctorType, String doctorName) {
        this.doctorType = doctorType;
        this.doctorName = doctorName;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
