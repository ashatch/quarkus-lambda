package net.andrewhatch.quarkus.cdk;

import software.amazon.awscdk.core.App;

public class CdkApp {
  public static void main(final String[] args) {
    App app = new App();

    new CdkStack(app, "dev-test-graalvm-cdk-lambda");

    app.synth();
  }
}
