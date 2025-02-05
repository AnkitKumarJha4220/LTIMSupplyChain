package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Insurance;
import com.wecp.progressive.service.impl.InsuranceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    InsuranceServiceImpl insuranceService;

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        try {
            List<Insurance> insuranceList = insuranceService.getAllInsurances();
            return new ResponseEntity<>(insuranceList, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{insuranceId}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int insuranceId) {
        try {
            Insurance insurance = insuranceService.getInsuranceById(insuranceId);
            if (insurance != null) {
                return new ResponseEntity<>(insurance, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createInsurance(@RequestBody Insurance insurance) {
        try {
            int insuranceId = insuranceService.addInsurance(insurance);
            return new ResponseEntity<>(insuranceId, HttpStatus.CREATED);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{insuranceId}")
    public ResponseEntity<Void> updateInsurance(@PathVariable int insuranceId, @RequestBody Insurance insurance) {
        try {
            insurance.setInsuranceId(insuranceId);
            insuranceService.updateInsurance(insurance);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable int insuranceId) {
        try {
            insuranceService.deleteInsurance(insuranceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
