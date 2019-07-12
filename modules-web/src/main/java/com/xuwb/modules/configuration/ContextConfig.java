package com.xuwb.modules.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class ContextConfig implements BeanFactoryPostProcessor, EnvironmentAware {

    private ConfigurableEnvironment env;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
//            env.getPropertySources().addBefore("systemEnvironment",new ResourcePropertySource(name,filePath));
            addLastPropertySource("file.local","local.properties");
            env.getPropertySources().addBefore("systemEnvironment",new ResourcePropertySource("classpath:apollo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLastPropertySource(String name, String fileName) throws IOException {
        String filePath = getPropertyFilePath(fileName);
        if (!Files.exists(Paths.get(filePath))){
            return;
        }
        env.getPropertySources().addBefore("systemEnvironment",new ResourcePropertySource(name,"file:"+filePath));
    }

    private String getPropertyFilePath(String fileName) {
        String path =env.getProperty("user.home");
        return path + "/Desktop/test/" + fileName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = (ConfigurableEnvironment) environment;
    }
}
