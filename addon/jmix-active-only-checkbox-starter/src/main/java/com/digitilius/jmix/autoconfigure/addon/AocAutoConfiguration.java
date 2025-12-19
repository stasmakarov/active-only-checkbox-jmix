package com.digitilius.jmix.autoconfigure.addon;

import com.digitilius.jmix.addon.AocConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({AocConfiguration.class})
public class AocAutoConfiguration {
}

