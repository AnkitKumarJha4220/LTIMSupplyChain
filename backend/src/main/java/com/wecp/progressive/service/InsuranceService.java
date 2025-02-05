package com.wecp.progressive.service;

import com.wecp.progressive.entity.Insurance;

import java.util.List;

public interface InsuranceService {

    List<Insurance> getAllInsurances();

    int addInsurance(Insurance insurance);

    Insurance getInsuranceById(int insuranceId);

    void updateInsurance(Insurance insurance);

    void deleteInsurance(int insuranceId);
}
