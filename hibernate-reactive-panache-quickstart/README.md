# Quarkus demo: Hibernate Reactive with Panache and RESTEasy Reactive - Reproducer

Just start and call: http://localhost:8080/fruits

```
2023-05-21 20:20:48,551 ERROR [org.acm.hib.orm.pan.FruitResource] (vert.x-eventloop-thread-1) Failed to handle request: org.hibernate.HibernateException: Could not generate fetch : org.acme.hibernate.orm.panache.Fruit(f) -> {id}
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.buildFetch(BaseSqmToSqlAstConverter.java:7466)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.createFetch(BaseSqmToSqlAstConverter.java:7295)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitIdentifierFetch(BaseSqmToSqlAstConverter.java:7143)
        at org.hibernate.sql.results.graph.entity.AbstractEntityResultGraphNode.afterInitialize(AbstractEntityResultGraphNode.java:63)
        at org.hibernate.reactive.persister.entity.impl.ReactiveAbstractPersisterDelegate.createDomainResult(ReactiveAbstractPersisterDelegate.java:106)
        at org.hibernate.reactive.persister.entity.impl.ReactiveSingleTableEntityPersister.createDomainResult(ReactiveSingleTableEntityPersister.java:105)
        at org.hibernate.query.sqm.sql.internal.AbstractSqmPathInterpretation.createDomainResult(AbstractSqmPathInterpretation.java:55)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.lambda$visitSelection$23(BaseSqmToSqlAstConverter.java:2240)
        at java.base/java.util.Collections$SingletonList.forEach(Collections.java:4966)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitSelection(BaseSqmToSqlAstConverter.java:2235)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitSelectClause(BaseSqmToSqlAstConverter.java:2153)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitQuerySpec(BaseSqmToSqlAstConverter.java:2021)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitQuerySpec(BaseSqmToSqlAstConverter.java:425)
        at org.hibernate.query.sqm.tree.select.SqmQuerySpec.accept(SqmQuerySpec.java:122)
        at org.hibernate.query.sqm.spi.BaseSemanticQueryWalker.visitQueryPart(BaseSemanticQueryWalker.java:221)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitQueryPart(BaseSqmToSqlAstConverter.java:1881)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitSelectStatement(BaseSqmToSqlAstConverter.java:1566)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitSelectStatement(BaseSqmToSqlAstConverter.java:425)
        at org.hibernate.query.sqm.tree.select.SqmSelectStatement.accept(SqmSelectStatement.java:222)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.translate(BaseSqmToSqlAstConverter.java:759)
        at org.hibernate.reactive.query.sqm.iternal.ConcreteSqmSelectReactiveQueryPlan.buildCacheableSqmInterpretation(ConcreteSqmSelectReactiveQueryPlan.java:208)
        at org.hibernate.reactive.query.sqm.iternal.ConcreteSqmSelectReactiveQueryPlan.withCacheableSqmInterpretation(ConcreteSqmSelectReactiveQueryPlan.java:138)
        at org.hibernate.reactive.query.sqm.iternal.ConcreteSqmSelectReactiveQueryPlan.reactivePerformList(ConcreteSqmSelectReactiveQueryPlan.java:122)
        at org.hibernate.reactive.query.sqm.iternal.ReactiveQuerySqmImpl.doReactiveList(ReactiveQuerySqmImpl.java:200)
        at org.hibernate.reactive.query.spi.ReactiveAbstractSelectionQuery.doReactiveList(ReactiveAbstractSelectionQuery.java:287)
        at org.hibernate.reactive.query.spi.ReactiveAbstractSelectionQuery.reactiveList(ReactiveAbstractSelectionQuery.java:202)
        at org.hibernate.reactive.query.sqm.iternal.ReactiveQuerySqmImpl.reactiveList(ReactiveQuerySqmImpl.java:154)
        at org.hibernate.reactive.query.ReactiveSelectionQuery.getReactiveResultList(ReactiveSelectionQuery.java:42)
        at io.smallrye.context.impl.wrappers.SlowContextualSupplier.get(SlowContextualSupplier.java:21)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromCompletionStage.subscribe(UniCreateFromCompletionStage.java:24)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniRunSubscribeOn.lambda$subscribe$0(UniRunSubscribeOn.java:27)
        at org.hibernate.reactive.context.impl.VertxContext.execute(VertxContext.java:90)
        at io.smallrye.mutiny.operators.uni.UniRunSubscribeOn.subscribe(UniRunSubscribeOn.java:25)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransformToUni$UniOnItemTransformToUniProcessor.performInnerSubscription(UniOnItemTransformToUni.java:81)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransformToUni$UniOnItemTransformToUniProcessor.onItem(UniOnItemTransformToUni.java:57)
        at io.smallrye.mutiny.operators.uni.UniOnItemConsume$UniOnItemComsumeProcessor.onItem(UniOnItemConsume.java:43)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransform$UniOnItemTransformProcessor.onItem(UniOnItemTransform.java:43)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransformToUni$UniOnItemTransformToUniProcessor.onItem(UniOnItemTransformToUni.java:60)
        at io.smallrye.mutiny.operators.uni.UniOperatorProcessor.onItem(UniOperatorProcessor.java:47)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromItemSupplier.subscribe(UniCreateFromItemSupplier.java:29)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnFailureFlatMap.subscribe(UniOnFailureFlatMap.java:31)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransformToUni$UniOnItemTransformToUniProcessor.performInnerSubscription(UniOnItemTransformToUni.java:81)
        at io.smallrye.mutiny.operators.uni.UniOnItemTransformToUni$UniOnItemTransformToUniProcessor.onItem(UniOnItemTransformToUni.java:57)
        at io.smallrye.mutiny.operators.uni.UniOperatorProcessor.onItem(UniOperatorProcessor.java:47)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromCompletionStage$CompletionStageUniSubscription.forwardResult(UniCreateFromCompletionStage.java:63)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.vertx.core.Future.lambda$toCompletionStage$3(Future.java:384)
        at io.vertx.core.impl.future.FutureImpl$3.onSuccess(FutureImpl.java:141)
        at io.vertx.core.impl.future.FutureBase.emitSuccess(FutureBase.java:60)
        at io.vertx.core.impl.future.FutureImpl.tryComplete(FutureImpl.java:211)
        at io.vertx.core.impl.future.Mapping.onSuccess(Mapping.java:40)
        at io.vertx.core.impl.future.FutureBase.lambda$emitSuccess$0(FutureBase.java:54)
        at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:174)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:167)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: org.hibernate.HibernateException: Could not generate fetch : org.acme.hibernate.orm.panache.Fruit(f).{id}(568535256026422) -> basket
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.buildFetch(BaseSqmToSqlAstConverter.java:7466)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.createFetch(BaseSqmToSqlAstConverter.java:7295)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.visitFetches(BaseSqmToSqlAstConverter.java:7412)
        at org.hibernate.sql.results.graph.AbstractFetchParent.afterInitialize(AbstractFetchParent.java:32)
        at org.hibernate.sql.results.graph.embeddable.internal.EmbeddableFetchImpl.<init>(EmbeddableFetchImpl.java:75)
        at org.hibernate.metamodel.internal.AbstractCompositeIdentifierMapping.geetch(AbstractCompositeIdentifierMapping.java:109)
        at org.hibernate.sql.results.graph.FetchParent.generateFetchableFetch(FetchParent.java:108)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.buildFetch(BaseSqmToSqlAstConverter.java:7455)
        ... 66 more
Caused by: org.hibernate.sql.ast.tree.from.UnknownTableReferenceException: Unable to determine TableReference (`fruit`) for `org.acme.hibernate.orm.panache.Fruit(f).{id}(568535256026422).basket.{fk}`
        at org.hibernate.sql.ast.tree.from.LazyTableGroup.resolveTableReference(LazyTableGroup.java:256)
        at org.hibernate.sql.ast.tree.from.DelegatingTableGroup.resolveTableReference(DelegatingTableGroup.java:62)
        at org.hibernate.metamodel.mapping.internal.SimpleForeignKeyDescriptor.createDomainResult(SimpleForeignKeyDescriptor.java:305)
        at org.hibernate.metamodel.mapping.internal.SimpleForeignKeyDescriptor.createDomainResult(SimpleForeignKeyDescriptor.java:254)
        at org.hibernate.metamodel.mapping.internal.ToOneAttributeMapping.generateFetch(ToOneAttributeMapping.java:1487)
        at org.hibernate.metamodel.mapping.internal.ToOneAttributeMapping.generateFetch(ToOneAttributeMapping.java:107)
        at org.hibernate.sql.results.graph.FetchParent.generateFetchableFetch(FetchParent.java:108)
        at org.hibernate.query.sqm.sql.BaseSqmToSqlAstConverter.buildFetch(BaseSqmToSqlAstConverter.java:7455)
        ... 73 more

```

---
This is a minimal CRUD service exposing a couple of endpoints over REST,
with a front-end based on Angular so you can play with it from your browser.

While the code is surprisingly simple, under the hood this is using:
 - RESTEasy Reactive to expose the REST endpoints
 - Hibernate Reactive with Panache to perform the CRUD operations on the database
 - A PostgreSQL database; see below to run one via Docker
 - ArC, the CDI inspired dependency injection tool with zero overhead

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.

### Configuring GraalVM and JDK 11+

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 11+ `java` command is on the path.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image)
for help setting up your environment.

## Building the demo

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

## Running the demo

### Prepare a PostgreSQL instance

Make sure you have a PostgreSQL instance running. To set up a PostgreSQL database with Docker:

> docker run -it --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3

Connection properties for the Agroal datasource are defined in the standard Quarkus configuration file,
`src/main/resources/application.properties`.

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

In this mode you can make changes to the code and have the changes immediately applied, by just refreshing your browser.

    Hot reload works even when modifying your JPA entities.
    Try it! Even the database schema will be updated on the fly.

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

> ./mvnw package

Then run it:

> java -jar ./target/quarkus-app/quarkus-run.jar

    Have a look at how fast it boots.
    Or measure total native memory consumption...

### Run Quarkus as a native application

You can also create a native executable from this application without making any
source code changes. A native executable removes the dependency on the JVM:
everything needed to run the application on the target platform is included in
the executable, allowing the application to run with minimal resource overhead.

Compiling a native executable takes a bit longer, as GraalVM performs additional
steps to remove unnecessary codepaths. Use the  `native` profile to compile a
native executable:

> ./mvnw package -Dnative

After getting a cup of coffee, you'll be able to run this binary directly:

> ./target/hibernate-reactive-panache-quickstart-1.0.0-SNAPSHOT-runner

    Please brace yourself: don't choke on that fresh cup of coffee you just got.
    
    Now observe the time it took to boot, and remember: that time was mostly spent to generate the tables in your database and import the initial data.
    
    Next, maybe you're ready to measure how much memory this service is consuming.

N.B. This implies all dependencies have been compiled to native;
that's a whole lot of stuff: from the bytecode enhancements that Panache
applies to your entities, to the lower level essential components such as the PostgreSQL JDBC driver, the Undertow webserver.

## See the demo in your browser

Navigate to:

<http://localhost:8080/index.html>

Have fun, and join the team of contributors!

## Running the demo in Kubernetes

This section provides extra information for running both the database and the demo on Kubernetes.
As well as running the DB on Kubernetes, a service needs to be exposed for the demo to connect to the DB.

Then, rebuild demo docker image with a system property that points to the DB. 

```bash
-Dquarkus.datasource.reactive.url=vertx-reactive:postgresql://<DB_SERVICE_NAME>/quarkus_test
```
