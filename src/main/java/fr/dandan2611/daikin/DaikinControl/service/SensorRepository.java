package fr.dandan2611.daikin.DaikinControl.service;

import fr.dandan2611.daikin.DaikinControl.database.entities.SensoredTemperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<SensoredTemperature, Long> {

}
