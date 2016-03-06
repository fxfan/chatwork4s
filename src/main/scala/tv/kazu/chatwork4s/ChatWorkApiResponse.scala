package tv.kazu.chatwork4s

import play.api.libs.json.{JsNull, Reads, Json, JsValue}
import tv.kazu.chatwork4s.models._

import scalaj.http.HttpResponse

class ChatWorkApiResponse(val httpResponse: HttpResponse[String]) {
  val body = if (!httpResponse.body.isEmpty) Some(httpResponse.body) else None
  val jsValue: JsValue = body map { bodyStr =>
    Json.parse(bodyStr)
  } getOrElse {
    JsNull
  }
}

object ChatWorkApiResponse {
  object Implicits {
    implicit val userReads: Reads[User] = Json.reads[User]
    implicit val userMiniReads: Reads[UserMini] = Json.reads[UserMini]
    implicit val userFullReads: Reads[UserFull] = Json.reads[UserFull]
    implicit val roomReads: Reads[Room] = Json.reads[Room]
    implicit val messageReads: Reads[Message] = Json.reads[Message]
  }
}