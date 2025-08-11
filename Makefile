.PHONY: help package setup

##@ Build
package: ## Build the Infinispan distribution.
	./mvnw -B -Drelease-mode=upstream -Pdistribution -Pcommunity-release -DskipTests -Dinfinispan.brand.version=infinispan package

install: ## Install
    ./mvnw install -DskipTests=true

setup: ## Setup the Infinispan server.
	cd distribution/target/distribution && \
	rm -rf infinispan-server-infinispan && \
	unzip infinispan-server-infinispan.zip
	cp chstudy/conf/infinispan.xml distribution/target/distribution/infinispan-server-infinispan/server/conf
	cd distribution/target/distribution/infinispan-server-infinispan && \
	cp -r server server2 && \
	cp -r server server3 && \
	./bin/cli.sh user create admin -p secret -g admin -s server && \
	./bin/cli.sh user create admin -p secret -g admin -s server2 && \
	./bin/cli.sh user create admin -p secret -g admin -s server3



##@ Help
help: ## Display this help.
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help