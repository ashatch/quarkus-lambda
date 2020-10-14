package com.myorg;

import static com.myorg.CdkStack.LAMBDA_JAR_PATH;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.AppProps;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App(AppProps.builder()
            .context(Collections.singletonMap(LAMBDA_JAR_PATH, "cdk/src/test/resources/lambda-jar-dir"))
            .build());

        new CdkStack(app, "CdkStack", StackProps.builder().build());

        app.synth();
    }
}
