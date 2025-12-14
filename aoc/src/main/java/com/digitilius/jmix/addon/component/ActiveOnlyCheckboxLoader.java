package com.digitilius.jmix.addon.component;

import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.flowui.exception.GuiDevelopmentException;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import io.jmix.flowui.xml.layout.support.DataLoaderSupport;
import org.springframework.lang.NonNull;

public class ActiveOnlyCheckboxLoader extends AbstractComponentLoader<ActiveOnlyCheckbox> {
    protected DataLoaderSupport dataLoaderSupport;

    @Override
    @NonNull
    protected ActiveOnlyCheckbox createComponent() {
        return factory.create(ActiveOnlyCheckbox.class);
    }

    @Override
    public void loadComponent() {

        getDataLoaderSupport().loadData(resultComponent, element);

        loadString(element, "activeField", resultComponent::setActiveField);
        loadString(element, "orderByField", resultComponent::setOrderByField);
        loadString(element, "orderDirection", resultComponent::setOrderDirection);
        loadBoolean(element, "initialValue", resultComponent::setInitialValue);
        loadString(element, "dataLoader", resultComponent::setDataLoader);

        if (resultComponent.getDataLoader() == null) {
            throw new ActiveOnlyCheckboxException("Mandatory property 'dataLoader' is not set for ActiveOnlyCheckbox component");
        }

        loadLoader(resultComponent.getDataLoader());
        validateAttributes(resultComponent);
    }

    protected DataLoaderSupport getDataLoaderSupport() {
        if (dataLoaderSupport == null) {
            dataLoaderSupport = applicationContext.getBean(DataLoaderSupport.class, context);
        }
        return dataLoaderSupport;
    }

    protected void loadLoader(@NonNull String loaderId) {
        DataLoader loader = context.getDataHolder().getLoader(loaderId);
        if (loader instanceof CollectionLoader<?>) {
            resultComponent.setLoader((CollectionLoader<?>) loader);
        } else {
            throw new GuiDevelopmentException("Not supported loader type: %", loaderId);
        }
    }

    protected void validateAttributes(ActiveOnlyCheckbox component) {
        MetaClass metaClass = component.getLoader().getContainer().getEntityMetaClass();

        MetaProperty activeProperty = checkProperty(metaClass, component.getActiveField());
        Class<?> type = activeProperty.getJavaType();
        if (!type.equals(Boolean.class)) {
            throw new ActiveOnlyCheckboxException("Active field must be 'Boolean'");
        }

        checkProperty(metaClass, component.getOrderByField());
    }

    private MetaProperty checkProperty(MetaClass metaClass, String property) {
        MetaProperty metaProperty = metaClass.findProperty(property);

        if (metaProperty == null) {
            throw new ActiveOnlyCheckboxException("No such attribute: " + property);
        }
        return metaProperty;
    }


}
