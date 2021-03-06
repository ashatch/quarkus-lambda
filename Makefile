
lambda/target/function.zip:
	./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker

.PHONY: synth
synth: lambda/target/function.zip
	pushd cdk && cdk synth -c jarpath=../lambda/target/function.zip

.PHONY: diff
diff: lambda/target/function.zip
	pushd cdk && cdk diff -c jarpath=../lambda/target/function.zip

.PHONY: deploy
deploy: lambda/target/function.zip
	pushd cdk && cdk deploy -c jarpath=../lambda/target/function.zip

.PHONY: destroy
destroy:
	pushd cdk && cdk destroy -c jarpath=../lambda/target/function.zip

.PHONY: invoke
invoke:
	@aws lambda invoke --function-name dev-quarkus-lambda \
		--payload file://payload.json \
		--cli-binary-format raw-in-base64-out \
		out.log > /dev/null
	@cat out.log && rm out.log

# list all the targets in this makefile
.PHONY: list
list:
	@$(MAKE) -pRrq -f $(lastword $(MAKEFILE_LIST)) : 2>/dev/null | awk -v RS= -F: '/^# File/,/^# Finished Make data base/ {if ($$1 !~ "^[#.]") {print $$1}}' | sort | egrep -v -e '^[^[:alnum:]]' -e '^$@$$'
