package ru.yusdm.javacore.lesson24web.autoservice.model.repo.impl.jdbc;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.exception.jdbc.ResultSetMappingException;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.TruckModel;

import java.sql.ResultSet;

public final class ModelMapper {

    private static final String MODEL_CLASS_NAME = Model.class.getSimpleName();

    private ModelMapper() {

    }

    public static TruckModel mapTruck(ResultSet rs) throws ResultSetMappingException {
        try {
            TruckModel truckModel = new TruckModel();
            truckModel.setWeight(rs.getInt("WEIGHT"));
            truckModel.setTankSize(rs.getInt("TANK_SIZE"));
            truckModel.setEmbeddedKitchen(rs.getBoolean("EMBEDDED_KITCHEN"));

            mapCommonModelData(truckModel, rs);
            return truckModel;
        } catch (Exception e) {
            throw new ResultSetMappingException(MODEL_CLASS_NAME, e);
        }
    }

    public static PassengerModel mapPassenger(ResultSet rs) throws ResultSetMappingException {
        try {

            PassengerModel passengerModel = new PassengerModel();
            passengerModel.setAudioSystemName(rs.getString("AUDIO_SYSTEM_NAME"));
            passengerModel.setNumberOfAirbags(rs.getInt("NUMBER_OF_AIR_BAGS"));
            passengerModel.setNumberOfSeats(rs.getInt("NUMBER_OF_SEATS"));

            mapCommonModelData(passengerModel, rs);

            return passengerModel;
        } catch (Exception e) {
            throw new ResultSetMappingException(MODEL_CLASS_NAME, e);
        }

    }

    public static void mapCommonModelData(Model model, ResultSet rs) throws ResultSetMappingException {
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
            throw new ResultSetMappingException(MODEL_CLASS_NAME, e);
        }
    }
}
