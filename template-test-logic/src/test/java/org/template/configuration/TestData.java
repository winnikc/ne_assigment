package org.template.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.template.model.EnvironmentData;
import org.template.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class TestData {

    public List<User> getUsersData() {
        return getFileDataAsListOf(User.class, "usersData.json");
    }

    public EnvironmentData getEnvironmentData() {
        return getFileDataAsListOf(EnvironmentData.class, "environmentData.json").get(0);
    }

    private <T> List<T> getFileDataAsListOf(Class<T> type, String fileName) {
        String env = Configuration.ENVIRONMENT;
        ObjectMapper mapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource(fileName);
        requireNonNull(url, "Cannot find resource file: " + fileName);
        Map<String, List<T>> envsWithData = null;
        try {
            envsWithData = mapper.readValue(url.openStream(), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<T> specificEnvData = requireNonNull(envsWithData.get(env.toUpperCase()), "There's no data for env: " + env.toUpperCase());
        JavaType desiredListType = mapper.getTypeFactory().constructParametricType(ArrayList.class, type);
        return mapper.convertValue(specificEnvData, desiredListType);
    }

}
