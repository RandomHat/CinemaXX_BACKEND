package kea.dat3.services;

import kea.dat3.repositories.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ScreeningServiceTest {

    @Mock
    ScreeningRepository screeningRepository;

    ScreeningService screeningService;

    @BeforeEach
    void setup(){
        screeningRepository.deleteAll();
        screeningService = new ScreeningService(screeningRepository);
    }

    @Test
    void getScreening() {
    }

    @Test
    void addScreening() {


    }
}