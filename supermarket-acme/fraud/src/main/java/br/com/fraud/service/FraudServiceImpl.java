package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FraudServiceImpl implements FraudService {

    private final FraudRepository fraudRepository;
    private final ConvertUtils convertUtils;

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.fraudRepository = fraudRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public boolean isFraud(Long customerId) {
        var fraud = this.fraudRepository.save(
                FraudEntity.builder()
                        .customerId(customerId)
                        .isFraud(false)
                        .description("This is not a fraud")
                        .createAt(LocalDateTime.now())
                        .build());
        return fraud.isFraud();
    }

    @Override
    public FraudResponse createFraud(FraudRequest fraudRequest) {
        log.info("Calling the method to create fraud {}", fraudRequest);
        var fraudEntity =
                (FraudEntity) this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class);

        var entity = this.fraudRepository.save(fraudEntity);
        return (FraudResponse) this.convertUtils.convertEntityToResponse(entity, FraudResponse.class);
    }

    public List<FraudResponse> listFrauds() {
        log.info("Calling the method to list the frauds {}", "");
        return (List<FraudResponse>) this.convertUtils.convertToListResponse(this.fraudRepository.findAll(), FraudResponse.class);
    }
}
