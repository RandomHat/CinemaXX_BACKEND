package kea.dat3.api;

import kea.dat3.dto.ScreeningRequest;
import kea.dat3.dto.ScreeningResponse;
import kea.dat3.services.ScreeningService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {

    ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService){
        this.screeningService = screeningService;
    }

    @GetMapping("/{id}")
    public ScreeningResponse getScreening(@PathVariable long id){
        return screeningService.getScreening(id,false);
    }

    @PostMapping()
    @RolesAllowed("ADMIN")
    public ScreeningResponse addScreening(@RequestBody ScreeningRequest body){
        return screeningService.addScreening(body, true);
    }
}
