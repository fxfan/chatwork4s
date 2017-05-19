# chatwork4s

A ChatWork API client in Scala, based on scalaj-http & play-json.

## Prerequisites

* Only Scala 2.11 is supported.

## Releases

* This library depends on play-json, and a chatwork4s version is for a specific version of
Play! framework.

|chatwork4s Release|Target Play! framework version|
|-------|---------------------|
|0.2.5.2|2.5.X|

## Installation

```scala
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "tv.kazu" %% "chatwork4s" % "0.2.5.2"
```

## Usage

```scala
import tv.kazu.chatwork4s.ChatWorkApiClient
import tv.kazu.chatwork4s.models._

val client = new ChatWorkApiClient("api-token")
val resultFuture: Future[UserFull] = client.me()
```

## Limitations

Not all the API endpoints are supported.

## Changelog

* 0.2.5.2 (2017-05-18)
  * Updated some classes to switch to ChatWork API v2 since v1 is no longer available
* 0.2.5.1 (2017-05-18)
  * Updated dependencies
  * Updated sbt to 0.13.15

## License

[Apache License, Version 2](http://www.apache.org/licenses/LICENSE-2.0.html)
