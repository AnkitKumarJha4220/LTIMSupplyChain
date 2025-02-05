package com.wecp.progressive.service;

import com.wecp.progressive.entity.Insurance;

import java.sql.SQLException;
import java.util.List;

public interface InsuranceService {

    List<Insurance> getAllInsurances() throws SQLException;

    int addInsurance(Insurance insurance) throws SQLException;

    Insurance getInsuranceById(int insuranceId) throws SQLException;

    void updateInsurance(Insurance insurance) throws SQLException;

    void deleteInsurance(int insuranceId) throws SQLException;
}
