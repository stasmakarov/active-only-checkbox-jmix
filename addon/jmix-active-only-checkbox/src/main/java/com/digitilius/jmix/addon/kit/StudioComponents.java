package com.digitilius.jmix.addon.kit;

import com.digitilius.jmix.addon.component.ActiveOnlyCheckbox;
import io.jmix.flowui.kit.meta.StudioComponent;
import io.jmix.flowui.kit.meta.StudioProperty;
import io.jmix.flowui.kit.meta.StudioPropertyType;
import io.jmix.flowui.kit.meta.StudioUiKit;

@StudioUiKit
public interface StudioComponents {
    @StudioComponent(
        name = "ActiveOnlyCheckbox",
        classFqn = "com.digitilius.jmix.addon.component.ActiveOnlyCheckbox",
        category = "Components",
        xmlElement = "activeOnlyCheckbox",
        xmlns = "http://digitilius.com/schema/active-only-checkbox",
        xmlnsAlias = "act",
        icon = "com/digitilius/jmix/addon/component/icons/checkbox.svg",
        properties = {
            @StudioProperty(xmlAttribute = "id", category = StudioProperty.Category.GENERAL,
                    type = StudioPropertyType.COMPONENT_ID),
            @StudioProperty(xmlAttribute = "label", category = StudioProperty.Category.GENERAL,
                    type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "activeField", category = StudioProperty.Category.DATA_BINDING,
                    type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "orderByField", category = StudioProperty.Category.DATA_BINDING,
                    type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "dataLoader", category = StudioProperty.Category.DATA_BINDING,
                    type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "orderDirection", category = StudioProperty.Category.DATA_BINDING,
                    type = StudioPropertyType.ENUMERATION,
                    defaultValue = "ASC",
                    options = {"ASC", "DESC"}),
            @StudioProperty(xmlAttribute = "initialValue", category = StudioProperty.Category.GENERAL,
                    type = StudioPropertyType.BOOLEAN),
            @StudioProperty(xmlAttribute = "enabled", category = StudioProperty.Category.LOOK_AND_FEEL,
                    type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "visible", category = StudioProperty.Category.LOOK_AND_FEEL,
                    type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "classNames", category = StudioProperty.Category.LOOK_AND_FEEL,
                    type = StudioPropertyType.VALUES_LIST),
            @StudioProperty(xmlAttribute = "css", type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "helperText",
                    type = StudioPropertyType.LOCALIZED_STRING)

    })
    ActiveOnlyCheckbox activeOnlyCheckbox();
}
