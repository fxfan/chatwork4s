package com.everforth.cw4s

import play.api.libs.json.JsNull
import com.everforth.cw4s.models._

import scala.concurrent.{ExecutionContext, Future}
import scalaj.http.{HttpRequest, Http}

import ChatWorkApiResponse.Implicits._

trait ChatWorkApiClient {

  val apiBase = "https://api.chatwork.com/v2"

  protected val apiToken: String
  protected val connTimeoutMs: Int
  protected val readTimeoutMs: Int
  implicit protected val executionContext: ExecutionContext

  def me(): Future[UserFull] = {
    get("/me").map(_.as[UserFull])
  }

  def contacts(): Future[List[User]] = {
    get("/contacts").map(_.as[List[User]])
  }

  def room(roomId: Int): Future[Room] = {
    roomOrErrors(roomId).map(throwLeft)
  }

  def roomOrErrors(roomId: Int): Future[Either[List[String], Room]] = {
    get(s"/rooms/$roomId").map(_.asEither[Room])
  }

  def rooms(): Future[List[Room]] = {
    roomsOrErrors().map(throwLeft)
  }

  def roomsOrErrors(): Future[Either[List[String], List[Room]]] = {
    get("/rooms").map(_.asEither[List[Room]])
  }

  def roomMembers(roomId: Int): Future[List[User]] = {
    roomMembersOrErrors(roomId).map(throwLeft)
  }

  def roomMembersOrErrors(roomId: Int): Future[Either[List[String], List[User]]] = {
    get(s"/rooms/$roomId/members").map(_.asEither[List[User]])
  }

  def messages(roomId: Int, fetchNext: Boolean = false): Future[List[Message]] = {
    messagesOrErrors(roomId, fetchNext).map(throwLeft)
  }

  def messagesOrErrors(roomId: Int, fetchNext: Boolean = false): Future[Either[List[String], List[Message]]] = {
    val params = if (fetchNext) Seq() else Seq(("force", "1"))
    get(s"/rooms/$roomId/messages", params).map { response =>
      if (response.httpResponse.isSuccess) {
        if (response.jsValue != JsNull) {
          Right(response.as[List[Message]])
        } else {
          Right(Nil)
        }
      } else {
        response.asEither[List[Message]] // This should return Left[List[String]]
      }
    }
  }

  def postMessage(
      roomId: Int,
      message: String,
      mentions: Seq[User] = Seq.empty,
  ): Future[ChatWorkApiResponse] = {
    val body = if (mentions.isEmpty)
      message
    else {
      val tos = mentions.map(u => s"[To:${u.accountId}] ${u.name}さん").mkString("\n")
      s"$tos\n$message"
    }
    post(s"/rooms/$roomId/messages", Seq(("body", body)))
  }

  def get(path: String, params: Seq[(String, String)] = Seq()): Future[ChatWorkApiResponse] = Future {
    new ChatWorkApiResponse(getRequest(path, params).asString)
  }

  def post(path: String, params: Seq[(String, String)]): Future[ChatWorkApiResponse] = Future {
    new ChatWorkApiResponse(postRequest(path, params).asString)
  }

  def getRequest(path: String, params: Seq[(String, String)]): HttpRequest = {
    baseHttpRequest(path).params(params)
  }

  def postRequest(path: String, params: Seq[(String, String)]): HttpRequest = {
    baseHttpRequest(path).postForm(params)
  }

  private def baseHttpRequest(path: String): HttpRequest = {
    if (!path.startsWith("/")) {
      throw new IllegalArgumentException(s"'path' must start with '/', but '$path' was passed.")
    }
    Http(apiBase + path).header("X-ChatWorkToken", apiToken).timeout(connTimeoutMs, readTimeoutMs)
  }

  private def throwLeft[T](either: Either[List[String], T]): T = {
    either match {
      case Left(list) =>
        throw new ApiErrorException(list)
      case Right(x) => x
    }
  }
}

object ChatWorkApiClient {

  def apply(
      apiToken: String,
      connTimeoutMs: Int = 1000 * 5,
      readTimeoutMs: Int = 1000 * 30,
  )(implicit
      ec: ExecutionContext
  ): ChatWorkApiClient = {

    val _apiToken = apiToken
    val _connTimeoutMs = connTimeoutMs
    val _readTimeoutMs = readTimeoutMs

    new ChatWorkApiClient {
      override val executionContext: ExecutionContext = ec
      override val apiToken: String = _apiToken
      override val connTimeoutMs: Int = _connTimeoutMs
      override val readTimeoutMs: Int = _readTimeoutMs
    }
  }

}
