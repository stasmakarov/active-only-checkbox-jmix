package com.digitilius.jmix.addon.component;

import io.jmix.core.annotation.JmixModule;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.flowui.FlowuiConfiguration;
import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@JmixModule(dependsOn = {EclipselinkConfiguration.class, FlowuiConfiguration.class})
public class ActiveOnlyCheckboxConfiguration {

    @Bean
    public ComponentRegistration activeOnlyCheckbox() {
        return ComponentRegistrationBuilder.create(ActiveOnlyCheckbox.class)
                .withComponentLoader("activeOnlyCheckbox", ActiveOnlyCheckboxLoader.class)
                .build();
    }

}