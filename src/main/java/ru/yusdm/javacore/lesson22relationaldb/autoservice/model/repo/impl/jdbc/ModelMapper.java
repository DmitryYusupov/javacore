package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.repo.impl.jdbc;

import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.model.exception.unchecked.ResultSetModelMappingException;

import java.sql.ResultSet;

import static ru.yusdm.javacore.lesson22relationaldb.autoservice.model.exception.ModelExceptionMeta.RESULT_SET_MODEL_MAP_ERROR;

public class ModelMapper {

    public static TruckModel mapTruck(ResultSet rs) throws ResultSetModelMappingException {
        try {
            TruckModel truckModel = new TruckModel();
            truckModel.setWeight(rs.getInt("WEIGHT"));
            truckModel.setTankSize(rs.getInt("TANK_SIZE"));
            truckModel.setEmbeddedKitchen(rs.getBoolean("EMBEDDED_KITCHEN"));

            mapCommonModelData(truckModel, rs);
            return truckModel;
        } catch (Exception e) {
            throw new ResultSetModelMappingException(RESULT_SET_MODEL_MAP_ERROR.getCode(), RESULT_SET_MODEL_MAP_ERROR.getDescription(), e);
        }
    }

    public static PassengerModel mapPassenger(ResultSet rs) throws ResultSetModelMappingException {
        try {

            PassengerModel passengerModel = new PassengerModel();
            passengerModel.setAudioSystemName(rs.getString("AUDIO_SYSTEM_NAME"));
            passengerModel.setNumberOfAirbags(rs.getInt("NUMBER_OF_AIR_BAGS"));
            passengerModel.setNumberOfSeats(rs.getInt("NUMBER_OF_SEATS"));

            mapCommonModelData(passengerModel, rs);

            return passengerModel;
        } catch (Exception e) {
            throw new ResultSetModelMappingException(RESULT_SET_MODEL_MAP_ERROR.getCode(), RESULT_SET_MODEL_MAP_ERROR.getDescription(), e);
        }

    }

    public static void mapCommonModelData(Model model, ResultSet rs) throws ResultSetModelMappingException {
        try {
            model.setName(rs.getString("NAME"));
            model.setMarkId(rs.getLong("MARK_ID"));
            model.setDescription(rs.getString("DESCRIPTION"));
            model.setProductionYearStart(rs.getInt("PRODUCTION_YEAR_START"));
            Integer yearEnd = rs.getInt("PRODUCTION_YEAR_END");
            if (!rs.wasNull()) {
                model.setProductionYearEnd(yearEnd);
            }
        } catch (Exception e) {
            throw new ResultSetModelMappingException(RESULT_SET_MODEL_MAP_ERROR.getCode(), RESULT_SET_MODEL_MAP_ERROR.getDescription(), e);
        }
    }
}
