package fr.dandan2611.daikin.DaikinControl.service;

import fr.dandan2611.daikin.DaikinControl.database.entities.PeripheralOption;
import fr.dandan2611.daikin.DaikinControl.option.PeripheralOptionKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OptionsRepository extends CrudRepository<PeripheralOption, Integer> {

    @Query(nativeQuery = true, value = "SELECT o.* FROM peripheral_option as o WHERE o.name=:#{#key.name()}")
    public PeripheralOption findByKey(@Param("key") PeripheralOptionKey key);

}
