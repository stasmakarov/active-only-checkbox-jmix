package com.digitilius.jmix.addon.component;

import io.jmix.core.Messages;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataLoader;
import io.jmix.flowui.xml.layout.loader.AbstractComponentLoader;
import org.springframework.lang.NonNull;


public class ActiveOnlyCheckboxLoader extends AbstractComponentLoader<ActiveOnlyCheckbox> {

    private static final String DEFAULT_ACTIVE_FIELD = "active";
    private static final String DEFAULT_ORDER_BY_FIELD = "name";
    private static final OrderDirection DEFAULT_ORDER_DIRECTION = OrderDirection.ASC;
    private static final String MSG_ACTIVE_ONLY_LABEL = "com.digitilius.jmix.addon/activeOnly.label";


    @Override
    @NonNull
    protected ActiveOnlyCheckbox createComponent() {
        return factory.create(ActiveOnlyCheckbox.class);
    }

    @Override
    public void loadComponent() {

        componentLoader().loadLabel(resultComponent, element);

        loadString(element, "dataLoader", resultComponent::setDataLoader);
        if (resultComponent.getDataLoader() == null) {
            throw new ActiveOnlyCheckboxException("Mandatory property 'dataLoader' is not set for ActiveOnlyCheckbox component");
        }
        attachCollectionLoader(resultComponent.getDataLoader());

        loadString(element, "activeField", resultComponent::setActiveField);
        loadString(element, "orderByField", resultComponent::setOrderByField);
        loadEnum(element, OrderDirection.class, "orderDirection", resultComponent::setOrderDirection);

        validateAttributes(resultComponent);

        loadBoolean(element, "value", resultComponent::setValue);
        if (resultComponent.getValue() == null) {
            resultComponent.setValue(false);
        }

        String label = resultComponent.getLabel();
        if (label == null) {
            label = messages().getMessage(MSG_ACTIVE_ONLY_LABEL);
            resultComponent.setLabel(label);
        }

        componentLoader().loadEnabled(resultComponent, element);
        componentLoader().loadClassNames(resultComponent, element);
        componentLoader().loadHelperText(resultComponent, element);
        componentLoader().loadAriaLabel(resultComponent, element);
        componentLoader().loadCss(resultComponent, element);
    }

    protected void attachCollectionLoader(@NonNull String loaderId) {
        DataLoader loader = context.getDataHolder().getLoader(loaderId);
        if (loader instanceof CollectionLoader<?>) {
            resultComponent.setLoader((CollectionLoader<?>) loader);
        } else {
            throw new ActiveOnlyCheckboxException(String.format("DataLoader '%s' is not a CollectionLoader", loaderId));
        }
    }

    protected void validateAttributes(ActiveOnlyCheckbox component) {
        MetaClass metaClass = component.getLoader().getContainer().getEntityMetaClass();

        if (component.getActiveField() == null) {
            component.setActiveField(DEFAULT_ACTIVE_FIELD);
        }
        MetaProperty activeProperty = checkProperty(metaClass, component.getActiveField());
        Class<?> type = activeProperty.getJavaType();
        if (!Boolean.class.isAssignableFrom(type)
                && !boolean.class.equals(type)) {
            throw new ActiveOnlyCheckboxException(
                    "Active field must be boolean or Boolean"
            );
        }

        if (component.getOrderByField() == null) {
            component.setOrderByField(DEFAULT_ORDER_BY_FIELD);
        }
        checkProperty(metaClass, component.getOrderByField());

        if (component.getOrderDirection() == null) {
            component.setOrderDirection(DEFAULT_ORDER_DIRECTION);
        }
    }

    private MetaProperty checkProperty(MetaClass metaClass, String property) {
        MetaProperty metaProperty = metaClass.findProperty(property);

        if (metaProperty == null) {
            throw new ActiveOnlyCheckboxException("No such attribute: " + property);
        }
        return metaProperty;
    }

    protected Messages messages() {
        return applicationContext.getBean(Messages.class);
    }

}
