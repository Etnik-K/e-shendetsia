package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Insurance;

public record CreateInsuranceRequest(
    Long id, Long userId, String provider, String policyNumber, String coverageDetails, boolean active)
{
    public Insurance toInsurance() {
        return new Insurance(id, userId, provider, policyNumber, coverageDetails, active);
    }
}
