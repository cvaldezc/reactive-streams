package rx.chris.kycwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WalletResponse(@JsonProperty("customerId") String walletId, String status) {
}
