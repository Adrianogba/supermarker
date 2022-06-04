package br.com.fraud.service;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;

import java.util.List;

public interface FraudService {

    boolean isFraud(Long customerId);
    FraudResponse createFraud(FraudRequest fraudRequest);
    List<FraudResponse> listFrauds();

}
