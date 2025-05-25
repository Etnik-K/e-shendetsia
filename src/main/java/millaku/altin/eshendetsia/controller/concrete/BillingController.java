package millaku.altin.eshendetsia.controller.concrete;

import com.auth0.jwt.exceptions.JWTVerificationException;
import millaku.altin.eshendetsia.controller.BaseController;
import millaku.altin.eshendetsia.exception.concrete.NotFoundException;
import millaku.altin.eshendetsia.exception.concrete.UnauthorizedException;
import millaku.altin.eshendetsia.http.request.body.CreateBillRequest;
import millaku.altin.eshendetsia.http.response.ApiResponse;
import millaku.altin.eshendetsia.model.entity.Bill;
import millaku.altin.eshendetsia.service.interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontrolluesi per menaxhimin e faturave ne sistem
 * Ofron funksionalitete per krijimin, leximin, perditesimin dhe fshirjen e faturave
 */
@RestController
@RequestMapping("/bills")
public class BillingController extends BaseController {

    private final BillService billService;

    @Autowired
    public BillingController(BillService billService) {
            this.billService = billService;
        }

    /**
     * Krijon nje fature te re
     *
     * @param billRequest Fatura per tu krijuar
     * @return Mesazh konfirmues
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> create(@RequestBody CreateBillRequest billRequest) {
        try{
            billService.save(billRequest.toBill());
            return this.ok("Faktura u krijua me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Merr te gjitha faturat e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return Lista e faturave
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Bill>>> getByUser(@PathVariable Long userId) {
        try{
            return this.ok(billService.getByUser(userId));
        } catch (JWTVerificationException  | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar!", HttpStatus.UNAUTHORIZED);
        }catch (NotFoundException exception){
            return this.error("Nuk eshte gjetur",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Merr faturat sipas statusit te pageses
     *
     * @param paid Statusi i pageses
     * @return Lista e faturave
     */
    @GetMapping("/status/{paid}")
    public ResponseEntity<ApiResponse<List<Bill>>> getByStatus(@PathVariable Boolean paid) {
        try{
            return this.ok(billService.getByPaymentStatus(paid));
        } catch (JWTVerificationException  | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar!", HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException exception){
            return this.error("Nuk eshte gjetur",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Shenon nje fature si te paguar
     *
     * @param id ID e fatures
     * @return Pergjigje bosh
     */
    @PutMapping("/{id}/pay")
    public ResponseEntity<ApiResponse<String>> markAsPaid(@PathVariable Long id) {
        try{
            this.billService.markAsPaid(id);
            return this.ok("Eshte paguar");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }catch (NotFoundException exception){
            return this.error("Nuk eshte gjetur",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fshin nje fature
     *
     * @param id ID e fatures per tu fshire
     * @return Pergjigje bosh
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try{
            this.billService.delete(id);
            return this.ok("Faktura u fshi me sukses");
        } catch (JWTVerificationException | UnauthorizedException exception) {
            return this.error("Nuk jeni i autorizuar", HttpStatus.UNAUTHORIZED);
        }catch (NotFoundException exception){
            return this.error("Nuk eshte gjetur",HttpStatus.NOT_FOUND);
        }
    }
}
