package net.andrewhatch.quarkus.cdk;

import software.amazon.awscdk.core.App;

public class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkStack(app, "CdkStack1");
        new CdkStack(app, "CdkStack2");

        app.synth();
    }
}
