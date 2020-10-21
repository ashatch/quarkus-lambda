# qarkus-lambda

Experiments with quarkus with AWS lambda. Recipe includes:

- Quarkus
- AWS Lambda
- GraalVM
- native-image
- CDK (Java) orchestrated
- GNU make

## Howto

See what `make` targets are available:
```
make list
```

Then run one, e.g:

```
make deploy
```

## How it was made

```
mvn archetype:generate \
       -DarchetypeGroupId=io.quarkus \
       -DarchetypeArtifactId=quarkus-amazon-lambda-archetype \
       -DarchetypeVersion=1.8.3.Final
```

