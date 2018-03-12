package org.springframework.boot.springbootaxon.bike.query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeEntryRepository extends CrudRepository<BikeEntry, String> {
}
