package com.github.raghuchandrasekaran.jsonvalidator.validators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Generic Validator
 */
public abstract class Validator<T> implements MessageBodyReader<T> {
    private Class<T> type;
    private String jsonSchemaName;

    Validator(Class<T> type, String jsonSchemaName) {
        this.type = type;
        this.jsonSchemaName = jsonSchemaName;
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == this.type;
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        final String jsonSchemaFileAsString = getFileFromURL(this.jsonSchemaName).toString();
        final String jsonData = getStringFromInputStream(entityStream);
        System.out.println(jsonData);

        InputStream isSchema = new FileInputStream(jsonSchemaFileAsString);
        String jsonSchema = getStringFromInputStream(isSchema);

        validateJsonData(jsonSchema, jsonData);

        ObjectMapper m = new ObjectMapper();

        return m.readValue(stringToStream(jsonData), type);
    }

    private void validateJsonData(final String jsonSchema, final String jsonData) {
        try {
            final JsonNode d = JsonLoader.fromString(jsonData);
            final JsonNode s = JsonLoader.fromString(jsonSchema);
            final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonValidator v = factory.getValidator();

            ProcessingReport report = v.validate(s, d);
            System.out.println(report);
            if (!report.toString().contains("success")) {
                System.out.println(report.toString());
            }

        } catch (IOException | ProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private InputStream stringToStream(final String str) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(str.getBytes("UTF-8"));
    }

    private File getFileFromURL(String fileName) {
        URL url = this.getClass().getClassLoader().getResource(fileName);
        File file = null;
        if (url != null) {
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException | NullPointerException e) {
                file = new File(url.getPath());
            }
        }
        return file;
    }
}
