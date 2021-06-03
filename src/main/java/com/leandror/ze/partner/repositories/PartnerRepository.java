package com.leandror.ze.partner.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.leandror.ze.partner.model.Partner;

public interface PartnerRepository extends CrudRepository<Partner, UUID> {

}
