package leandroasano.api.Controllers;

import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Sale;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Services.ReserveSevice;
import leandroasano.api.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReserveController {

    @Autowired
    ReserveSevice reserveSevice;


    @PostMapping("/users/{idpost}/reserve")
    public ResponseEntity makeNewReserve(@PathVariable("idpost") int idpost, @RequestBody Reserve reserve) throws Exception {
        try{
            reserveSevice.makeReserve(idpost,reserve);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/{username}/posts/reserve/")
    public ResponseEntity<List<Reserve>> getProductDetail(@PathVariable("username") String username) throws Exception {
        try{
            return new ResponseEntity<>(reserveSevice.showReservesOfUser(username),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/admin/reserves/unmark")
    public ResponseEntity unmarkReserves() throws Exception {
        try{
            reserveSevice.unamarkAllProductsReservations();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/admin/{idreserve}/delete")
    public ResponseEntity deleteReserve(@PathVariable("idreserve") int idres) throws Exception {
        try{
            reserveSevice.deleleteReserve(idres);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }



}
