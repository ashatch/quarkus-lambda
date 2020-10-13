package com.myorg;

import static com.myorg.CdkStack.LAMBDA_JAR_PATH;
import au.com.origin.snapshots.SnapshotMatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.AppProps;
import software.amazon.awscdk.core.StackProps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import au.com.origin.snapshots.junit5.SnapshotExtension;

@ExtendWith(SnapshotExtension.class)
public class CdkTest {
    private final static ObjectMapper JSON =
        new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    @Test
    public void testStack() throws IOException {
        Map<String, Object> context = new HashMap<>();
        context.put(LAMBDA_JAR_PATH, "src/test/resources/lambda-jar-dir");

        App app = new App(
            AppProps.builder()
            .context(context).build());

        CdkStack stack = new CdkStack(app, "test", StackProps.builder().build());



        JsonNode actual = JSON.valueToTree(app.synth().getStackArtifact(stack.getArtifactId()).getTemplate());
        SnapshotMatcher.expect(actual).toMatchSnapshot();
//        assertEquals(new ObjectMapper().createObjectNode(), actual);

//        expect(actual).toMatchSnapshot();
    }
}
