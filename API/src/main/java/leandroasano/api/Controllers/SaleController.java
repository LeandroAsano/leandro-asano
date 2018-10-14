package leandroasano.api.Controllers;

import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Sale;
import leandroasano.api.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping("/{idreserve}/addsale")
    public ResponseEntity makeSale( @PathVariable("idreservse") int idres, @RequestBody Sale sale) throws Exception {
        try{
            saleService.makeSale(idres);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            throw new Exception("Error creating sale");
        }
    }

    @GetMapping("/sales/all")
    public ResponseEntity<List<Sale>> getAllSales() throws Exception {
        try{
            return new ResponseEntity<>(saleService.showAllSalesOfPosts(),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/{idsale}/delete")
    public ResponseEntity deeleteASale(@PathVariable("idsale") int idsale) throws Exception {
        try{
            saleService.deleleteReserve(idsale);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

}
