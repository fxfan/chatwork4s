# chatwork4s

A ChatWork API client in Scala, based on scalaj-http & play-json.

## Prerequisites

* Only Scala 2.11 is supported.
* This library depends on play-json 2.3 since I'm still using Play! 2.3 for the project that I'm using this library for.

## Installation

```scala
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "tv.kazu" %% "chatwork4s" % "0.1-SNAPSHOT"
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

## License

[Apache License, Version 2](http://www.apache.org/licenses/LICENSE-2.0.html)
