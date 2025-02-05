package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Insurance;
import com.wecp.progressive.repository.InsuranceRepository;
import com.wecp.progressive.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Override
    public List<Insurance> getAllInsurances() throws SQLException {
        return insuranceRepository.findAll();
    }

    @Override
    public int addInsurance(Insurance insurance) throws SQLException {
        return insuranceRepository.save(insurance).getInsuranceId();
    }

    @Override
    public Insurance getInsuranceById(int insuranceId) throws SQLException {
        return insuranceRepository.findByInsuranceId(insuranceId);
    }

    @Override
    public void updateInsurance(Insurance insurance) throws SQLException {
        insuranceRepository.save(insurance);
    }

    @Override
    public void deleteInsurance(int insuranceId) throws SQLException {
        insuranceRepository.deleteById(insuranceId);
    }
}