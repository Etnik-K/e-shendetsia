package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Insurance;
import millaku.altin.eshendetsia.model.entity.User;

public record CreateInsuranceRequest(
    Long id,
    Long userId,
    String provider,
    String policyNumber,
    String coverageDetails,
    boolean active
) {
    public Insurance toInsurance() {
        User user = new User();
        user.setId(userId);

        return new Insurance(id, user, provider, policyNumber, coverageDetails, active);
    }
}
