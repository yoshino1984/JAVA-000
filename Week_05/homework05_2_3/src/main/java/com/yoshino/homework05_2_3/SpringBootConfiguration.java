/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yoshino.homework05_2_3;

import com.yoshino.homework05_2_3.model.Student;
import com.yoshino.homework05_2_3.prop.DemoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.yoshino.homework05_2_3.model.*;


/**
 * Spring boot starter configuration.
 */
@Configuration
@ComponentScan()
@EnableConfigurationProperties(DemoProperties.class)
//@ConditionalOnProperty(prefix = "spring.yoshino", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SpringBootConfiguration implements EnvironmentAware {

    private Environment environment;

    @Bean
    @ConditionalOnMissingBean
    Klass klass() {
        return new Klass();
    }

    @Bean
    @ConditionalOnMissingBean
    School school() {
        return new School();
    }

    @Bean(name = "student100")
    @ConditionalOnMissingBean
    Student student() {
        return new Student().create();
    }

    
    @Override
    public final void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
}
