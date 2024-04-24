package br.com.neurotech.challenge.repository;


import br.com.neurotech.challenge.entity.NeurotechClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<NeurotechClient, Long> {

    @Query("SELECT client FROM NeurotechClient client WHERE client.age >= 23 AND  client.age <= 49 ")
    List<NeurotechClient> findAgeGroup();
}
