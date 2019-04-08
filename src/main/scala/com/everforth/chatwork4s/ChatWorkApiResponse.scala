package com.everforth.chatwork4s

import com.everforth.chatwork4s.models.{User, UserFull, UserMini}
import play.api.libs.json.{JsNull, JsValue, Json, Reads}
import scalaj.http.HttpResponse
import com.everforth.chatwork4s.models._

class ChatWorkApiResponse(val httpResponse: HttpResponse[String]) {
  val body = if (!httpResponse.body.isEmpty) Some(httpResponse.body) else None
  val jsValue: JsValue = body map { bodyStr =>
    Json.parse(bodyStr)
  } getOrElse {
    JsNull
  }

  def as[T](implicit reads: Reads[T]): T = {
    jsValue.as[T]
  }

  def asEither[T](implicit reads: Reads[T]): Either[List[String], T] = {
    if (httpResponse.isSuccess) {
      Right(jsValue.as[T])
    } else {
      Left((jsValue \ "errors").get.as[List[String]])
    }
  }
}

object ChatWorkApiResponse {
  object Implicits {
    import com.github.tototoshi.play.json.JsonNaming

    implicit val userReads: Reads[User] = JsonNaming.snakecase(Json.reads[User])
    implicit val userMiniReads: Reads[UserMini] = JsonNaming.snakecase(Json.reads[UserMini])
    implicit val userFullReads: Reads[UserFull] = JsonNaming.snakecase(Json.reads[UserFull])
    implicit val roomReads: Reads[Room] = JsonNaming.snakecase(Json.reads[Room])
    implicit val messageReads: Reads[Message] = JsonNaming.snakecase(Json.reads[Message])
  }
}