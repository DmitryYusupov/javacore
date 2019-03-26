package ru.yusdm.javacore.lesson14serialization.autoservice.model.repo.impl.memory;

import ru.yusdm.javacore.lesson14serialization.autoservice.common.business.search.Paginator;
import ru.yusdm.javacore.lesson14serialization.autoservice.common.solutions.utils.CollectionUtils;
import ru.yusdm.javacore.lesson14serialization.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.repo.ModelRepo;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.search.ModelSearchCondition;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.search.PassengerModelSearchCondition;
import ru.yusdm.javacore.lesson14serialization.autoservice.model.search.TruckModelSearchCondition;
import ru.yusdm.javacore.lesson14serialization.autoservice.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;

import static ru.yusdm.javacore.lesson14serialization.autoservice.storage.Storage.modelsList;


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

        List<? extends Model> result = modelsList;

        switch (modelDiscriminator) {
            case PASSENGER: {
                result = searchPassengerModels((PassengerModelSearchCondition) searchCondition);
                break;
            }
            case TRUCK: {
                result = searchTruckModels((TruckModelSearchCondition) searchCondition);
                break;
            }
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<? extends Model> getPageableData(List<? extends Model> models, Paginator paginator) {
        return CollectionUtils.getPageableData(models, paginator.getLimit(), paginator.getOffset());
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

    @Override
    public List<Model> findAll() {
        return modelsList;
    }

    @Override
    public int countAll() {
        return modelsList.size();
    }
}
