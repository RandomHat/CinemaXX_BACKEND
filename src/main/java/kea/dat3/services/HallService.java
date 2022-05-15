package kea.dat3.services;

import kea.dat3.dto.HallResponse;
import kea.dat3.repositories.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {
    HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<HallResponse> cinemaHalls(int cinemaId) {
        return hallRepository.findHallByCinema_Id(cinemaId).stream().map(HallResponse::new).collect(Collectors.toList());
    }
}
