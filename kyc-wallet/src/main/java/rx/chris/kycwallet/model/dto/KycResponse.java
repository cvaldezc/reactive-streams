package rx.chris.kycwallet.model.dto;

public record KycResponse(Boolean passed, Long riskScore) {
}
