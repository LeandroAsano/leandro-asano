package leandroasano.api.Controllers;

import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Sale;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Services.ReserveSevice;
import leandroasano.api.Services.SaleService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ReserveController {

    @Autowired
    ReserveSevice reserveSevice;

    @Autowired
    HttpSession session;


    @PostMapping("/reserve/{idpost}")
    public ResponseEntity makeNewReserve(@PathVariable("idpost") int idpost, @RequestBody LocalDate datereserve) throws Exception {
        try{
            int idcurrentuser = (int) session.getAttribute("iduser");
            reserveSevice.makeReserve(idpost,idcurrentuser,datereserve);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Error in service");
        }
    }


    @GetMapping("/reserve/{username}")
    public ResponseEntity<List<Reserve>> getReservesOfUser(@PathVariable("username") String username) throws Exception {
        try{
            return new ResponseEntity<>(reserveSevice.showReservesOfUser(username),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/reserve/myreserves")
    public ResponseEntity<List<Reserve>> getReservesOfCurrentUser() throws Exception {
        try{
            int iduser = (int) session.getAttribute("iduser");
            return new ResponseEntity<>(reserveSevice.showMyReserves(iduser),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/admin/reserve/unmark")
    public ResponseEntity unmarkReserves() throws Exception {
        if (session.getAttribute("role").equals("admin")){
            reserveSevice.unamarkAllProductsReservations();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    @DeleteMapping("/admin/reserve/{idreserve}")
    public ResponseEntity deleteReserve(@PathVariable("idreserve") int idres) throws Exception {
        try{
            reserveSevice.deleleteReserve(idres);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }



}
