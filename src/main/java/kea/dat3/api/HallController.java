package kea.dat3.api;

import kea.dat3.dto.HallResponse;
import kea.dat3.services.HallService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/halls")
public class HallController {

    HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping()
    @RolesAllowed({"USER","ADMIN"})
    public List<HallResponse> getHallsFromCinema(@RequestParam int cinemaId){
        return hallService.cinemaHalls(cinemaId);

    }
}
