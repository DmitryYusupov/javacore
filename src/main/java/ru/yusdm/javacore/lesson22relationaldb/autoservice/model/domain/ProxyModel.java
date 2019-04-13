package ru.yusdm.javacore.lesson22relationaldb.autoservice.model.domain;

public class ProxyModel extends Model {
    private static final String ERROR_MESSAGE = "You deal with proxy! All operations not supporte, nut get/set Id";

    public ProxyModel(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public int getProductionYearStart() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setProductionYearStart(int productionYearStart) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public Integer getProductionYearEnd() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setProductionYearEnd(Integer productionYearEnd) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public ModelDiscriminator getDiscriminator() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public Long getMarkId() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setMarkId(Long markId) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "ID " + id;
    }

    @Override
    protected void initDiscriminator() {
        
    }


}
