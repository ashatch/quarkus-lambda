package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;

public class CdkStack extends Stack {

    public static final String LAMBDA_JAR_PATH = "jarpath";

    public CdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final String jarPath = String.valueOf(scope.getNode().tryGetContext(LAMBDA_JAR_PATH));
        final Code quarkusLambdaCode = Code.fromAsset(jarPath);

        new Function(this,
            "quarkus-lambda",
            FunctionProps.builder()
                .functionName("dev-quarkus-lambda")
                .memorySize(512)
                .timeout(Duration.seconds(20))
                .runtime(Runtime.JAVA_8)
                .code(quarkusLambdaCode)
                .handler("test")
                .build());
    }
}
