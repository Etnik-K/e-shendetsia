package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.unipr.eshendetsia.http.request.body.CreateEmergencyContactRequest;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;

/**
 * Klasa test per verifikimin e funksionalitetit te krijimit te kontakteve emergjente.
 * Teston konvertimin e drejte te kerkeses ne objekt kontakti emergjent.
 */
class CreateEmergencyContactRequestTest {

    /**
     * Teston metoden toEmergencyContact() duke verifikuar nese te gjitha
     * atributet e kerkeses transformohen sakte ne objektin e kontaktit emergjent
     */
    @Test
    void testToEmergencyContact() {
        Long id = 1L;
        Long userId = 2L;
        String name = "John Doe";
        String phone = "1234567890";
        String relation = "Father";

        CreateEmergencyContactRequest request = new CreateEmergencyContactRequest(id, userId, name, phone, relation);
        EmergencyContact emergencyContact = request.toEmergencyContact();

        assertEquals(id, emergencyContact.getId());
        assertEquals(userId, emergencyContact.getUserId());
        assertEquals(name, emergencyContact.getName());
        assertEquals(phone, emergencyContact.getPhone());
        assertEquals(relation, emergencyContact.getRelationship());
    }
}