package ru.yusdm.javacore.lesson11io.autoservice.model.repo.impl.memory;

import ru.yusdm.javacore.lesson11io.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson11io.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson11io.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson11io.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson11io.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson11io.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.model.search.PassengerModelSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.model.search.TruckModelSearchCondition;
import ru.yusdm.javacore.lesson11io.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson11io.autoservice.storage.Storage.modelsList;


public class ModelCollectionRepo implements ModelRepo {

    @Override
    public void insert(Model model) {
        model.setId(SequenceGenerator.getNextValue());
        modelsList.add(model);
    }

    @Override
    public Model findById(Long id) {
        return findModelById(id);
    }

    @Override
    public void update(Model model) {
        //we already in memory, no need to update object
    }

    @Override
    public List<? extends Model> search(ModelSearchCondition searchCondition) {

        ModelDiscriminator modelDiscriminator = searchCondition.getModelDiscriminator();

        switch (modelDiscriminator) {
            case PASSENGER: {
                return searchPassengerModels((PassengerModelSearchCondition) searchCondition);
            }
            case TRUCK: {
                return searchTruckModels((TruckModelSearchCondition) searchCondition);
            }
            default: {
                return modelsList;
            }
        }
    }

    private List<TruckModel> searchTruckModels(TruckModelSearchCondition searchCondition) {
        List<TruckModel> result = new ArrayList<>();

        for (Model model : modelsList) {

            if (ModelDiscriminator.TRUCK.equals(model.getDiscriminator())) {
                TruckModel truckModel = (TruckModel) model;

                boolean found = true;
                if (searchCondition.searchByTankSize()) {
                    found = searchCondition.getTankSize().equals(truckModel.getTankSize());
                }

                if (found && searchCondition.searchByEmbeddedKitchen()) {
                    found = searchCondition.getEmbeddedKitchen().equals(truckModel.isEmbeddedKitchen());
                }

                if (found && searchCondition.searchByWeight()) {
                    found = searchCondition.getWeight().equals(truckModel.getWeight());
                }

                if (found) {
                    result.add(truckModel);
                }
            }

        }

        return result;
    }

    private List<PassengerModel> searchPassengerModels(PassengerModelSearchCondition searchCondition) {
        List<PassengerModel> result = new ArrayList<>();

        for (Model model : modelsList) {

            if (ModelDiscriminator.PASSENGER.equals(model.getDiscriminator())) {
                PassengerModel passengerModel = (PassengerModel) model;

                boolean found = true;
                if (searchCondition.searchByAudioSystemName()) {
                    found = searchCondition.getAudioSystemName().equals(passengerModel.getAudioSystemName());
                }

                if (found && searchCondition.searchByNumberOfSeats()) {
                    found = searchCondition.getNumberOfSeats().equals(passengerModel.getNumberOfSeats());
                }

                if (found && searchCondition.searchByNumberOfAirBags()) {
                    found = searchCondition.getNumberOfAirbags().equals(passengerModel.getNumberOfAirbags());
                }

                if (found) {
                    result.add(passengerModel);
                }
            }

        }

        return result;
    }

    @Override
    public void deleteById(Long id) {
        Model found = findModelById(id);

        if (found != null) {
            modelsList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Model model : modelsList) {
            System.out.println(model);
        }
    }

    private Model findModelById(long modelId) {
        for (Model model : modelsList) {
            if (Long.valueOf(modelId).equals(model.getId())) {
                return model;
            }
        }
        return null;
    }

}
