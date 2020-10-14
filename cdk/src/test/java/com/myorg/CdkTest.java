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
import software.amazon.awscdk.cxapi.CloudAssembly;
import software.amazon.awscdk.cxapi.CloudFormationStackArtifact;

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

        final App app = new App(
            AppProps.builder()
            .context(context).build());

        final CdkStack stack = new CdkStack(app, "test", StackProps.builder().build());

        final CloudAssembly synthedApp = app.synth();
        final CloudFormationStackArtifact stackArtifact = synthedApp.getStackArtifact(stack.getArtifactId());
        System.out.println(stackArtifact.getTemplate());
//        final JsonNode actual = JSON.valueToTree(stackArtifact.getTemplate());
//        SnapshotMatcher.expect(actual).toMatchSnapshot();
    }
}
