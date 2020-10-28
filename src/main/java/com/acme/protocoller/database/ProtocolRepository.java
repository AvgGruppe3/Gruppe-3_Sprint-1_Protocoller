package com.acme.protocoller.database;

import com.acme.protocoller.database.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

}

