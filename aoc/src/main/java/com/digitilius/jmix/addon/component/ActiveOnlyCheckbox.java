package com.digitilius.jmix.addon.component;

import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.model.CollectionLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveOnlyCheckbox extends JmixCheckbox {

    private static final Logger log = LoggerFactory.getLogger(ActiveOnlyCheckbox.class);

    private CollectionLoader<?> loader;
    private String dataLoader;
    private String activeField;
    private String orderByField;
    private OrderDirection orderDirection;
    private String queryActiveOnly;
    private String queryAll;
    private boolean queriesInitialized = false;

    public ActiveOnlyCheckbox() {
        addValueChangeListener(e -> {
            Boolean v = e.getValue();
            applyFilter(Boolean.TRUE.equals(v));
        });
        getElement().getStyle().set("align-self", "center");
    }

    public void setLoader(CollectionLoader<?> loader) {
        this.loader = loader;
    }

    public CollectionLoader<?> getLoader() {
        return loader;
    }

    public void setDataLoader(String dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void setActiveField(String activeField) {
        this.activeField = activeField;
    }

    public String getActiveField() {
        return activeField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public String getDataLoader() {
        return dataLoader;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = OrderDirection.valueOf(orderDirection);
    }

    private void applyFilter(boolean activeOnly) {
        if (loader == null) {
            log.info("Loader isn't set yet");
            return;
        }

        if (!queriesInitialized) {
            queryActiveOnly = buildQuery(true);
            queryAll = buildQuery(false);
            queriesInitialized = true;
        }

        String query = activeOnly ? queryActiveOnly : queryAll;
        loader.setQuery(query);
        try {
            loader.load();
        } catch (Exception ex) {
            log.error("Failed to load data for loader (query: {}). Exception: {}", query, ex.getMessage(), ex);
        }
    }

    private String buildQuery(boolean activeOnly) {
        MetaClass metaClass = loader.getContainer().getEntityMetaClass();
        String entityName = metaClass.getName();

        StringBuilder sb = new StringBuilder("select e from ")
                .append(entityName)
                .append(" e");

        if (activeOnly) {
            sb.append(" where e.").append(activeField).append(" = true");
        }

        if (orderByField != null) {
            sb.append(" order by e.").append(orderByField);
            if (orderDirection != null) {
                sb.append(' ').append(orderDirection);
            }
        }

        return sb.toString();
    }

}
