package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import static org.example.util.DoctorRequestConstants.*;

public class DoctorRequest {

    @NotNull(message = DOCTOR_TYPE_ISSUE)
    private DoctorType doctorType;
    @NotBlank(message = DOCTOR_NAME_REQUIERED)
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
