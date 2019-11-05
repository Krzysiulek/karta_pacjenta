package karta_pacjenta.pacjent_service.Interfaces;


import karta_pacjenta.pacjent_service.Models.DAOs.MyServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAppUsersRepository extends JpaRepository<MyServiceUser, Long> {
    MyServiceUser findByUserName(String username);

    MyServiceUser findByUserId(long userId);
}
