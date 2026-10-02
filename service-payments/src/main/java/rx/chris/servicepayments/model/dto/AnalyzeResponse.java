package rx.chris.servicepayments.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record AnalyzeResponse(String decision) {
}
