package net.andrewhatch.quarkus.cdk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.AppProps;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.cxapi.CloudAssembly;
import software.amazon.awscdk.cxapi.CloudFormationStackArtifact;

import java.util.HashMap;
import java.util.Map;

import au.com.origin.snapshots.SnapshotMatcher;
import au.com.origin.snapshots.junit5.SnapshotExtension;

@ExtendWith(SnapshotExtension.class)
public class CdkTest {
    @Test
    public void testStack() {
        final Map<String, Object> context = new HashMap<>();
        context.put(CdkStack.LAMBDA_JAR_PATH, "src/test/resources/lambda-jar-dir");

        final App app = new App(
            AppProps.builder()
            .context(context).build());

        final CdkStack stack = new CdkStack(app, "test", StackProps.builder().build());

        final CloudAssembly synthedApp = app.synth();
        final CloudFormationStackArtifact stackArtifact = synthedApp.getStackArtifact(stack.getArtifactId());
        final Map template = (Map)stackArtifact.getTemplate();

        SnapshotMatcher.expect(template).toMatchSnapshot();
    }
}
